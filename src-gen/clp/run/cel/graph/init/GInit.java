package clp.run.cel.graph.init;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GInit  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 348L;


  //=== Attributes =============================================================

  private String gname;


  //=== Constructor ============================================================

  public GInit() {
  }

  //=== Methods ================================================================

  public String getGname() {
    return gname;
  }

  public void setGname(String x) {
    this.gname = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitGraphReinitStatement(this);
  }



}
