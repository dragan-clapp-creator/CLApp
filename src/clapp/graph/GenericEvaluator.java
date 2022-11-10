package clapp.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import akdl.core.api.ACoreBean;
import akdl.core.symbol.CoreBean;
import akdl.core.symbol.CustTypeBean;
import akdl.core.symbol.TypeBean;
import akdl.root.api.ASymbol;
import clapp.run.util.JavaInvoker;
import clapp.run.util.ResourceUtility;
import clp.run.res.Variable;

public class GenericEvaluator {

  static enum EvalType {
    EXPRESSION;
  }

  private ACoreBean node;
  private EvalType coreType;
  private ClpGraphMaker graphMaker;
  private ClpGraphMaker refGraphMaker;

  public GenericEvaluator(ACoreBean node, String cnn, ClpGraphMaker gm) {
    this.node = node;
    this.coreType = EvalType.valueOf(cnn.toUpperCase());
    this.graphMaker = gm;
    this.refGraphMaker = gm.getAssisted();
  }

  public Object evaluate() {
    if (refGraphMaker == null) {
      return evaluateGraphUsingClappMapping();
    }
    return evaluateUsingAssociatedGraph();
  }

  /**
   * input values are read from mapped clapp input variables
   * and result is set to mapped clapp output variables
   * @return
   */
  private Object evaluateGraphUsingClappMapping() {
    JavaInvoker ji = JavaInvoker.getInstance();
    Object dc = getDefaultContext(ji);
    if (dc == null) {
      return null;
    }
    if (!isInputMapping(ji, dc) && !isAssociatedGraphInput(ji, dc)) {
      return null;
    }

    Object ret = doEvaluation(ji, dc);
    doOutputMapping(ji, dc);
    return ret;
  }

  /**
   * input values are extracted from the associated node
   * @return
   */
  private Object evaluateUsingAssociatedGraph() {
    JavaInvoker ji = JavaInvoker.getInstance();
    Object dc = getDefaultContext(ji);
    if (dc == null) {
      return null;
    }
    if (!isAssociatedGraphInput(ji, dc)) {
      return null;
    }

    Object ret = doEvaluation(ji, dc);
    doOutputMapping(ji, dc);
    return ret;
  }

  private Object doEvaluation(JavaInvoker ji, Object dc) {
    ACoreBean root = getRoot(node);
    Object prsmain = getRootNodeInstance(ji, dc, root);
    Boolean ret = (Boolean) ji.callMethod(prsmain, "parse", null, null);
    graphMaker.setParserInstance(prsmain);
    if (refGraphMaker != null) {
      refGraphMaker.setParserInstance(prsmain);
    }
    Object rtobj = null;
    if (ret != null && ret.booleanValue()) {
      Object rtmain;
      if (root != node) {
        rtmain = ji.findRTObject(prsmain, dc.getClass());
        Object any = getAnyNodeInstance(ji, node);
        rtobj = ji.findRTObject(rtmain, any);
      }
      else {
        rtobj = ji.findRTObject(prsmain, dc.getClass());
        rtmain = rtobj;
      }
      switch(coreType) {
        case EXPRESSION:
          new ExpressionExtractor(rtmain, ji, dc).extract( rtobj );
          break;
      }
    }
    return rtobj;
  }

  private ACoreBean getRoot(ACoreBean node) {
    if (node == null) {
      return null;
    }
    while (node.getParentBean() != null) {
      node = (ACoreBean) node.getParentBean();
    }
    return node;
  }

  private Object getRootNodeInstance(JavaInvoker ji, Object dc, ACoreBean node) {
    Class<?>[] argTypes = new Class[1];
    argTypes[0] = dc.getClass();
    Object[] args = new Object[1];
    args[0] = dc;
    Object main =
      ji.newInstance(node.getCompPackage(), node.getCTypeName(), argTypes, args);
    return main;
  }

  private Object getAnyNodeInstance(JavaInvoker ji, ACoreBean node) {
    Object any =
      ji.newInstance(node.getIntPackage(), node.getITypeName(), (Class<?>[])null, (Object[])null);
    return any;
  }

  private Object getDefaultContext(JavaInvoker ji) {
    return ji.newInstance(node.getCompPackage(), "DefaultContext", null, null);
  }

  private boolean isInputMapping(JavaInvoker ji, Object dc) {
    HashMap<String, Variable> im = graphMaker.getIMap();
    Set<String> keys = im.keySet();
    if (keys.isEmpty()) {
      return false;
    }
    ResourceUtility util = ResourceUtility.getInstance();
    Class<?>[] argTypes = new Class[2];
    argTypes[0] = String.class;
    argTypes[1] = Object.class;
    Object[] args = new Object[2];
    for (String key : keys) {
      args[0] = key;
      args[1] = util.getValue(im.get(key));
      ji.callMethod(dc, "put", argTypes, args);
    }
    return true;
  }

  private boolean isAssociatedGraphInput(JavaInvoker ji, Object dc) {
    ACoreBean root = refGraphMaker.getGraph().getRoot();
    Object rtmain = refGraphMaker.getRuntimeInstance();
    Class<?>[] argTypes = new Class[2];
    argTypes[0] = String.class;
    argTypes[1] = Object.class;
    Object[] args = new Object[2];
    boolean isMinus = false;
    for (ASymbol sym : ((CoreBean)root).getSyntax()) {
      if (sym instanceof TypeBean) {
        String getter = "get"+((TypeBean)sym).getITypeName();
        Object obj = ji.callMethod(rtmain, getter, (Class[])null, (Object[])null);
        if (obj.getClass().isEnum()) {
          Object val = ji.callMethod(obj, "getVal", (Class[])null, (Object[])null);
          if (val instanceof String) {
            isMinus = val.equals("-");
          }
        }
        else {
          args[0] = ((TypeBean)sym).getIName();
          getter = "get"+findName((TypeBean)sym);
          Object val = ji.callMethod(obj, getter, (Class[])null, (Object[])null);
          if (isMinus) {
            if (val instanceof Integer) {
              args[1] = -(Integer)val;
            }
            else if (val instanceof Long) {
              args[1] = -(Long)val;
            }
            else if (val instanceof Float) {
              args[1] = -(Float)val;
            }
            else if (val instanceof Double) {
              args[1] = -(Double)val;
            }
            isMinus = false;
          }
          else {
            args[1] = val;
          }
          ji.callMethod(dc, "put", argTypes, args);
        }
      }
    }
    return true;
  }

  private String findName(TypeBean tb) {
    ACoreBean rb = tb.getRefBean();
    if (rb instanceof CoreBean) {
      ArrayList<ASymbol> syn = ((CoreBean)rb).getSyntax();
      if (syn.size() == 1) {
        ASymbol s = syn.get(0);
        if (s instanceof CustTypeBean) {
          String ret = ((CustTypeBean)s).getTName();
          if (ret != null) {
            return ret;
          }
        }
      }
    }
    return tb.getITypeName();
  }

  private void doOutputMapping(JavaInvoker ji, Object dc) {
    HashMap<String, Variable> om = graphMaker.getOMap();
    Set<String> keys = om.keySet();
    if (keys.isEmpty()) {
      return;
    }
    ResourceUtility util = ResourceUtility.getInstance();
    Class<?>[] argTypes = new Class[1];
    argTypes[0] = String.class;
    Object[] args = new Object[1];
    for (String key : keys) {
      args[0] = key;
      // method name "getValue" is known within the DefaultContext object
      Object val = ji.callMethod(dc, "getValue", argTypes, args);
      if (val != null) {
        util.setValue(key, om.get(key), val);
      }
    }
  }
}
