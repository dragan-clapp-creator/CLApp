package clapp.graph;

import java.util.Hashtable;

import akdl.core.CoreParser;
import akdl.core.CoreSemantalyzer;
import akdl.core.api.ACoreParser;
import akdl.core.api.ACoreSemantalyzer;
import akdl.core.gen.AGenerator;
import akdl.core.gen.java.JavaGenerator;
import akdl.graph.CoreGraph;
import akdl.ker.def.api.AItem;
import akdl.root.DefWrapper;
import akdl.root.ParseResult;

public class ClpCoreGraph extends CoreGraph {

  private ClpRootGraph usedGraph;
  private Hashtable<String, AItem> usedDefs;
  private ClpCoreGraph cgraph;

  public void setUsedGraph(ClpRootGraph gr) {
    usedGraph = gr;
  }

  public ClpRootGraph getUsedGraph() {
    return usedGraph;
  }

  public void setUsedDefs(Hashtable<String, AItem> u) {
    usedDefs = u;
  }

  public Hashtable<String, AItem> getUsedDefs() {
    return usedDefs;
  }

  @Override
  public void parse(Hashtable<String, DefWrapper> defs, ParseResult res) {
    ACoreParser cparser = getCoreParser(getParserName());
    ACoreSemantalyzer csemantics = getCoreSemanticAnalyzer(getSemantalyzerName());
//    if (cparser.parse(csemantics, wrapper.getRootGraph(), getDefs(), usedGraph.getDefs(), defs)) {
////      cgraph = wrapper.getGraph();
//      AGenerator generator = getGenerator(getGeneratorName());
//      generator.handle(csemantics, getDestination(), getTemplates());
//    }
//    else {
//      cparser.dump();
//    }
  }


  private ACoreParser getCoreParser(String pname) {
    if (pname != null) {
      Object par = getObject(pname);
      if (par instanceof ACoreParser) {
        return (ACoreParser) par;
      }
    }
    return new CoreParser(new ParseResult());
  }


  private AGenerator getGenerator(String name) {
    if (name == null) {
      return new JavaGenerator();
    }
    return (AGenerator) getObject(name);
  }


  private ACoreSemantalyzer getCoreSemanticAnalyzer(String name) {
    if (name != null) {
      Object par = getObject(name);
      if (par instanceof ACoreSemantalyzer) {
        return (ACoreSemantalyzer) getObject(name);
      }
    }
    return new CoreSemantalyzer();
  }

  public ClpCoreGraph getCgraph() {
    return cgraph;
  }
}
