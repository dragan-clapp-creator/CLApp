package clp.run.cel.graph;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GParser  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 325L;


  //=== Attributes =============================================================

  private clp.run.cel.graph.ParseObject parseObject;
  private clp.run.cel.graph.GraphRef graphRef;


  //=== Constructor ============================================================

  public GParser() {
  }

  //=== Methods ================================================================

  public clp.run.cel.graph.ParseObject getParseObject() {
    return parseObject;
  }

  public void setParseObject(clp.run.cel.graph.ParseObject x) {
    this.parseObject = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.graph.GraphRef getGraphRef() {
    return graphRef;
  }

  public void setGraphRef(clp.run.cel.graph.GraphRef x) {
    this.graphRef = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitGraphParseStatement(this);
  }



}
