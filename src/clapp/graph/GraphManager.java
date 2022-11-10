package clapp.graph;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import akdl.core.api.ABundleBean;
import akdl.core.api.ACoreBean;
import akdl.core.symbol.TypeBean;
import akdl.graph.api.AGraph;
import akdl.root.ParseResult;
import akdl.root.api.ASymbol;
import clapp.run.util.ClpClassHandler;
import clapp.run.util.JavaInvoker;
import clapp.run.util.ResourceUtility;
import clapp.run.util.JavaInvoker.ClassInfo;
import clp.run.res.Resources;
import clp.run.res.Variable;
import clp.run.res.graph.Graph;

public class GraphManager {

  static private GraphManager instance = new GraphManager();

  private HashMap<String, ClpGraphMaker> graphMaker;

  // PRIVATE CONSTRUCTOR
  private GraphManager() {
    graphMaker = new HashMap<String, ClpGraphMaker>();
  }

  static public GraphManager getInstance() {
    return instance;
  }

  public ClpGraphMaker getGraphMaker(String name) {
    return graphMaker.get(name);
  }

  public ClpGraphMaker addGraphMaker(String name, String str) throws IOException {
    ClpGraphMaker gm = new ClpGraphMaker(new StringReader(str), name);
    if (gm.createGraph(new ParseResult())) {
      graphMaker.put(name, gm);
      compile(gm.getGraph());
      return gm;
    }
    return null;
  }

  public ClpGraphMaker addGraphMaker(String name, File f) throws IOException {
    ClpGraphMaker gm = new ClpGraphMaker(new FileReader(f), name);
    if (gm.createGraph(new ParseResult())) {
      graphMaker.put(name, gm);
      compile( gm.getGraph() );
      return gm;
    }
    return null;
  }

  /**
   * compile generated source code and, if ok, add it to the clapp common loader
   * @param graph
   */
  private void compile(AGraph graph) {
    if (graph != null) {
      String dest = graph.getCodeDestination();
      String bindest = dest+"/bin";
      DynamicCompiler cmp = new DynamicCompiler();
      try {
        if (cmp.compileDynamically(dest, bindest, true)) {
          ClpClassHandler.getInstance().registerBin(bindest);
          ClpClassHandler.getInstance().removePatches();
          ClpClassHandler.getInstance().initializeLoader();
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void parse(Resources res, String value, GraphContext gc) {
    ClpGraphMaker gm = getOrCreateGraphMaker(gc.getName(), res);
    if (gm != null) {
      AGraph graph = gm.getGraph();
      ACoreBean node = getEntryNode((ACoreBean) graph.getRoot(), gc);
      if (node != null) {
        String cname = node.getCompPackage() +"." + node.getCTypeName();
        Class<?>[] argTps = { Reader.class };
        Object[] args = new Object[1];
        args[0] = new StringReader(value);
        JavaInvoker ji = JavaInvoker.getInstance();
        ClassInfo info = ji.javaCall(cname, "parse", argTps, args);

        String method = "get" + node.getITypeName();
        Object parser = info.getInstance();
        gm.setParserInstance(parser);
        Object rt = ji.callMethod(parser, method, null, null);
        gm.setRuntimeInstance(rt);
      }
    }
  }

  private ACoreBean getEntryNode(ACoreBean bean, GraphContext gc) {
    String snode = gc.getNode();
    if (snode != null) {
      ACoreBean b = findBean(bean, snode, gc.getElement());
      if (b != null) {
        return b;
      }
    }
    return bean;
  }

  private ACoreBean findBean(ACoreBean bean, String snode, int num) {
    if (bean.getCName().equals(snode)) {
      return bean;
    }
    if (bean instanceof TypeBean) {
      bean = ((TypeBean)bean).getRefBean();
    }
    if (!(bean instanceof ABundleBean)) {
      return null;
    }
    ABundleBean bb = (ABundleBean) bean;
    for (ASymbol sym : bb.getSyntax()) {
      if (sym instanceof ACoreBean) {
        bean = findBean((ACoreBean) sym, snode, num);
        bean = found(bean, num, bb);
        if (bean != null) {
          return bean;
        }
      }
    }
    return null;
  }

  private ACoreBean found(ACoreBean bean, int num, ABundleBean bb) {
    if (bean != null) {
      if (num < 0) {
        return bean;
      }
      ArrayList<ASymbol> syntax = bb.getSyntax();
      for (int i=0; i<syntax.size(); i++) {
        ASymbol sym = syntax.get(i);
        if (sym instanceof ACoreBean) {
          num--;
          if (num < 0) {
            return (ACoreBean) sym;
          }
        }
      }
    }
    return null;
  }

  public StringBuffer render(GraphContext gc, Resources res) {
    ClpGraphMaker gm = getOrCreateGraphMaker(gc.getName(), res);
    if (gm != null) {
      AGraph graph = gm.getGraph();
      ACoreBean node = getEntryNode((ACoreBean) graph.getRoot(), gc);
      if (node != null && gm.getParserInstance() != null) {
        if (GraphExpressionEvaluator.isRTRendering()) {
          return GraphExpressionEvaluator.getRendering(node.getITypeName());
        }
        return (StringBuffer) JavaInvoker.getInstance()
               .callMethod(gm.getParserInstance(), "getRendering", null, null);
      }
    }
    return null;
  }

  public void evaluate(GraphContext gc, Resources res) {
    ClpGraphMaker gm = getOrCreateGraphMaker(gc.getName(), res);
    if (gm != null) {
      AGraph graph = gm.getGraph();
      ACoreBean node = getEntryNode((ACoreBean) graph.getRoot(), gc);
      if (node != null) {
        if (gc.getJavaContext() != null) {
          // TODO
        }
        else if (gc.getCoreNode() != null) {
          GenericEvaluator ge =
            new GenericEvaluator(node, gc.getCoreNode(), gm);
          Object rt = ge.evaluate();
          gm.setRuntimeInstance(rt);
        }
      }
    }
  }

  private ClpGraphMaker getOrCreateGraphMaker(String gname, Resources res) {
    ClpGraphMaker gm = getGraphMaker(gname);
    if (gm == null) {
      Variable v = ResourceUtility.getInstance().getVariable(res, gname);
      if (v instanceof Graph) {
        String str = ((Graph)v).getGraphSentences();
        try {
          gm = addGraphMaker(gname, str);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return gm;
  }
}
