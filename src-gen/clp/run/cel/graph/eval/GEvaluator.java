package clp.run.cel.graph.eval;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GEvaluator  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 345L;


  //=== Attributes =============================================================

  private clp.run.cel.graph.GraphRef graphRef;
  private boolean isUsing;
  private boolean isProcessor;
  private clp.run.cel.graph.map.Processor processor;


  //=== Constructor ============================================================

  public GEvaluator() {
  }

  //=== Methods ================================================================

  public clp.run.cel.graph.GraphRef getGraphRef() {
    return graphRef;
  }

  public void setGraphRef(clp.run.cel.graph.GraphRef x) {
    this.graphRef = x;
  }

  //----------------------------------------------------------------------------

  public boolean isUsing() {
    return isUsing;
  }

  public void setIsUsing(boolean x) {
    this.isUsing = x;
  }

  //----------------------------------------------------------------------------

  public boolean isProcessor() {
    return isProcessor;
  }

  public void setIsProcessor(boolean x) {
    this.isProcessor = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.graph.map.Processor getProcessor() {
    return processor;
  }

  public void setProcessor(clp.run.cel.graph.map.Processor x) {
    this.processor = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitGraphEvaluateStatement(this);
  }



}
