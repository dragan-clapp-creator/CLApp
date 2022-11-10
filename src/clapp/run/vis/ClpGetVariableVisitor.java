package clapp.run.vis;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import clapp.run.util.SignUtil;
import clp.run.res.ArrayBVar;
import clp.run.res.ArrayDVar;
import clp.run.res.ArrayFVar;
import clp.run.res.ArrayIVar;
import clp.run.res.ArrayLVar;
import clp.run.res.ArraySVar;
import clp.run.res.ArrayTVar;
import clp.run.res.BVar;
import clp.run.res.BoolVarVisitor;
import clp.run.res.DVar;
import clp.run.res.DateVarVisitor;
import clp.run.res.Encryption;
import clp.run.res.FVar;
import clp.run.res.FloatVarVisitor;
import clp.run.res.HVar;
import clp.run.res.IVar;
import clp.run.res.IntVarVisitor;
import clp.run.res.LVar;
import clp.run.res.LongVarVisitor;
import clp.run.res.PVar;
import clp.run.res.SVar;
import clp.run.res.SimpleBVar;
import clp.run.res.SimpleDVar;
import clp.run.res.SimpleFVar;
import clp.run.res.SimpleIVar;
import clp.run.res.SimpleLVar;
import clp.run.res.SimpleSVar;
import clp.run.res.SimpleTVar;
import clp.run.res.StringVarVisitor;
import clp.run.res.TVar;
import clp.run.res.TimeVarVisitor;
import clp.run.res.VarType;
import clp.run.res.VariableVisitor;
import clp.run.res.WebVariable;
import clp.run.res.graph.Graph;
import clp.run.res.ui.UiVar;
import clp.run.res.weave.CstOrVar;
import clp.run.res.weave.MethodEnhancement;
import clp.run.res.weave.WeaveVar;

public class ClpGetVariableVisitor implements VariableVisitor {

  private Class<?> type;
  private String name;
  private boolean hasArray;
  private Object initial;
  private Object value;
  private ArrayList<? extends Object> values;
  private VarType varType;
  private int port;
  private Encryption encryption;
  private CstOrVar pack;
  private CstOrVar clazz;
  private ArrayList<MethodEnhancement> items;
  private String sentences;
  private String address;

  public ClpGetVariableVisitor() {
  }

  @Override
  public void visitBVar(BVar x) {
    varType = x.getTBOOL();
    value = x.getValue();
    values = x.getValues();
    BoolVarVisitor vis = new BoolVarVisitor() {
      @Override
      public void visitSimpleBVar(SimpleBVar x) {
        type = Boolean.class;
        name = x.getName();
        initial = x.getInitial();
      }
      @Override
      public void visitArrayBVar(ArrayBVar x) {
        name = x.getName();
        type = Boolean[].class;
        hasArray = true;
      }
    };
    x.getBoolVar().accept(vis);
  }

  @Override
  public void visitFVar(FVar x) {
    varType = x.getTFLOAT();
    value = x.getValue();
    values = x.getValues();
    FloatVarVisitor vis = new FloatVarVisitor() {
      @Override
      public void visitSimpleFVar(SimpleFVar x) {
        type = Double.class;
        name = x.getName();
        initial = SignUtil.getFInitial(x.getFsigned());
      }
      @Override
      public void visitArrayFVar(ArrayFVar x) {
        name = x.getName();
        type = Double[].class;
        hasArray = true;
      }
    };
    x.getFloatVar().accept(vis);
  }

  @Override
  public void visitHVar(HVar x) {
    varType = x.getTHASH();
    value = x.getValue();
    values = x.getValues();
   if (x.isArray()) {
     type = Object[].class;
     hasArray = true;
     values = x.getValues();
    }
    value = x.getValue();
    type = Object.class;
    name = x.getName();
  }

  @Override
  public void visitIVar(IVar x) {
    varType = x.getTINT();
    value = x.getValue();
    values = x.getValues();
    IntVarVisitor vis = new IntVarVisitor() {
      @Override
      public void visitSimpleIVar(SimpleIVar x) {
        type = Integer.class;
        name = x.getName();
        initial = SignUtil.getIInitial(x.getIsigned());
      }
      @Override
      public void visitArrayIVar(ArrayIVar x) {
        name = x.getName();
        type = Integer[].class;
        hasArray = true;
      }
    };
    x.getIntVar().accept(vis);
  }

  @Override
  public void visitLVar(LVar x) {
    varType = x.getTLONG();
    value = x.getValue();
    values = x.getValues();
    LongVarVisitor vis = new LongVarVisitor() {
      @Override
      public void visitSimpleLVar(SimpleLVar x) {
        type = Long.class;
        name = x.getName();
        initial = SignUtil.getLInitial(x.getLsigned());
      }
      @Override
      public void visitArrayLVar(ArrayLVar x) {
        name = x.getName();
        type = Long[].class;
        hasArray = true;
      }
    };
    x.getLongVar().accept(vis);
  }

  @Override
  public void visitPVar(PVar x) {
    varType = x.getTREF();
    value = x.getValue();
    if (x.isArray()) {
      type = Object[].class;
      hasArray = true;
      values = x.getValues();
    }
    else {
      type = Object.class;
    }
    name = x.getName();
    initial = x.getInitial();
  }

  @Override
  public void visitSVar(SVar x) {
    varType = x.getTSTRING();
    value = x.getValue();
    values = x.getValues();
    StringVarVisitor vis = new StringVarVisitor() {
      @Override
      public void visitSimpleSVar(SimpleSVar x) {
        type = String.class;
        name = x.getName();
        initial = x.getInitial();
      }
      @Override
      public void visitArraySVar(ArraySVar x) {
        name = x.getName();
        type = String[].class;
        hasArray = true;
      }
    };
    x.getStringVar().accept(vis);
  }

  @Override
  public void visitWebVariable(WebVariable x) {
    varType = x.getTWEB();
    type = Object.class;
    name = x.getName();
    port = x.getPort().getNum();
    if (x.getAddress() != null) {
      address = x.getAddress().getAddr();
    }
    encryption = x.getEncryption();
  }

  @Override
  public void visitGraph(Graph x) {
    varType = x.getTGRAPH();
    type = Graph.class;
    name = x.getGraphName();
    sentences = x.getGraphSentences();
  }
  
  @Override
  public void visitWeaveVar(WeaveVar x) {
    varType = x.getTWEAVER();
    type = Object.class;
    name = x.getName();
    pack = x.getPack();
    clazz = x.getClazz();
    items = x.getMethodEnhancements();
  }
  
  @Override
  public void visitDVar(DVar x) {
    varType = x.getTDATE();
    value = x.getValue();
    values = x.getValues();
    x.getDateVar().accept(new DateVarVisitor() {
      @Override
      public void visitSimpleDVar(SimpleDVar x) {
        type = Date.class;
        name = x.getName();
      }
      @Override
      public void visitArrayDVar(ArrayDVar x) {
        name = x.getName();
        type = Date[].class;
        hasArray = true;
      }
    });
  }
  
  @Override
  public void visitTVar(TVar x) {
    varType = x.getTTIME();
    value = x.getValue();
    values = x.getValues();
    x.getTimeVar().accept(new TimeVarVisitor() {
      @Override
      public void visitArrayTVar(ArrayTVar x) {
        name = x.getName();
        type = Time[].class;
        hasArray = true;
      }
      @Override
      public void visitSimpleTVar(SimpleTVar x) {
        type = Time.class;
        name = x.getName();
      }
    });
  }
  
  @Override
  public void visitUiVar(UiVar x) {
    varType = x.getTUI();
    name = x.getName();
    initial = x.getTitle();
  }

  public Class<?> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public boolean hasArray() {
    return hasArray;
  }

  public Object getValue() {
    return value;
  }

  public Object getInitial() {
    return initial;
  }

  public Object[] getValues() {
    if (values == null) {
      return null;
    }
    return values.toArray();
  }

  public VarType getVarType() {
    return varType;
  }

  /**
   * @return the port
   */
  public int getPort() {
    return port;
  }

  /**
   * @return the encryption
   */
  public Encryption getEncryption() {
    return encryption;
  }

  /**
   * @return the pack
   */
  public CstOrVar getPack() {
    return pack;
  }

  /**
   * @return the clazz
   */
  public CstOrVar getClazz() {
    return clazz;
  }

  /**
   * @return the items
   */
  public ArrayList<MethodEnhancement> getItems() {
    return items;
  }

  /**
   * @return the sentences
   */
  public String getSentences() {
    return sentences;
  }

  /**
   * @return the address
   */
  public synchronized String getAddress() {
    return address;
  }
}
