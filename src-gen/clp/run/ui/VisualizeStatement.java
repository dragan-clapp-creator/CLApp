package clp.run.ui;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class VisualizeStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 349L;


  //=== Attributes =============================================================

  private boolean isKeeping;
  private String uiname;


  //=== Constructor ============================================================

  public VisualizeStatement() {
  }

  //=== Methods ================================================================

  public boolean isKeeping() {
    return isKeeping;
  }

  public void setIsKeeping(boolean x) {
    this.isKeeping = x;
  }

  //----------------------------------------------------------------------------

  public String getUiname() {
    return uiname;
  }

  public void setUiname(String x) {
    this.uiname = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitVisualizeStatement(this);
  }



}
