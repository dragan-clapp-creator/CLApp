package clp.run.cel.graph.rnd;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GRenderer  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 342L;


  //=== Attributes =============================================================

  private clp.run.cel.graph.GraphRef graphRef;
  private clp.run.msc.OutputTarget outputTarget;


  //=== Constructor ============================================================

  public GRenderer() {
  }

  //=== Methods ================================================================

  public clp.run.cel.graph.GraphRef getGraphRef() {
    return graphRef;
  }

  public void setGraphRef(clp.run.cel.graph.GraphRef x) {
    this.graphRef = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.msc.OutputTarget getOutputTarget() {
    return outputTarget;
  }

  public void setOutputTarget(clp.run.msc.OutputTarget x) {
    this.outputTarget = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitGraphRenderStatement(this);
  }



}
