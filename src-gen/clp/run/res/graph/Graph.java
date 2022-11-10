package clp.run.res.graph;

import clp.run.res.Variable;
import clp.run.res.VariableVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Graph  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 157L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TGRAPH;
  private String graphName;
  private String graphSentences;


  //=== Constructor ============================================================

  public Graph() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTGRAPH() {
    return TGRAPH;
  }

  public void setTGRAPH(clp.run.res.VarType x) {
    this.TGRAPH = x;
  }

  //----------------------------------------------------------------------------

  public String getGraphName() {
    return graphName;
  }

  public void setGraphName(String x) {
    this.graphName = x;
  }

  //----------------------------------------------------------------------------

  public String getGraphSentences() {
    return graphSentences;
  }

  public void setGraphSentences(String x) {
    this.graphSentences = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(VariableVisitor visitor) {
    visitor.visitGraph(this);
  }



}
