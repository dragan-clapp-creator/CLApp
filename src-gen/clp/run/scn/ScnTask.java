package clp.run.scn;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnTask  implements java.io.Serializable {

  private static final long serialVersionUID = 27L;

  //=== Attributes =============================================================

  private clp.run.scn.ScnTaskName scnTaskName;
  private String operOn;
  private boolean isPassingTo;
  private String passTo;
  private boolean isPassTo;


  //=== Constructor ============================================================

  public ScnTask() {
  }

  //=== Methods ================================================================

  public clp.run.scn.ScnTaskName getScnTaskName() {
    return scnTaskName;
  }

  public void setScnTaskName(clp.run.scn.ScnTaskName x) {
    this.scnTaskName = x;
  }

  //----------------------------------------------------------------------------

  public String getOperOn() {
    return operOn;
  }

  public void setOperOn(String x) {
    this.operOn = x;
  }

  //----------------------------------------------------------------------------

  public boolean isPassingTo() {
    return isPassingTo;
  }

  public void setIsPassingTo(boolean x) {
    this.isPassingTo = x;
  }

  //----------------------------------------------------------------------------

  public String getPassTo() {
    return passTo;
  }

  public void setPassTo(String x) {
    this.passTo = x;
  }

  //----------------------------------------------------------------------------


  public boolean isPassTo() {
    return isPassTo;
  }

  public void setIsPassTo(boolean x) {
    this.isPassTo = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
