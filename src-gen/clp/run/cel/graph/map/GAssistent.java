package clp.run.cel.graph.map;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GAssistent  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 329L;


  //=== Attributes =============================================================

  private String gname1;
  private String gname2;


  //=== Constructor ============================================================

  public GAssistent() {
  }

  //=== Methods ================================================================

  public String getGname1() {
    return gname1;
  }

  public void setGname1(String x) {
    this.gname1 = x;
  }

  //----------------------------------------------------------------------------

  public String getGname2() {
    return gname2;
  }

  public void setGname2(String x) {
    this.gname2 = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitGraphAssistStatement(this);
  }



}
