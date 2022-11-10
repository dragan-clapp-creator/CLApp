package clp.run.scn;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnLogBody  implements java.io.Serializable {

  private static final long serialVersionUID = 20L;

  //=== Attributes =============================================================

  private clp.run.scn.ScnDeact scnDeact;
  private clp.run.scn.ScnLevel scnLevel;
  private boolean isScnLtype;
  private clp.run.scn.ScnLtype scnLtype;


  //=== Constructor ============================================================

  public ScnLogBody() {
  }

  //=== Methods ================================================================

  public clp.run.scn.ScnDeact getScnDeact() {
    return scnDeact;
  }

  public void setScnDeact(clp.run.scn.ScnDeact x) {
    this.scnDeact = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.scn.ScnLevel getScnLevel() {
    return scnLevel;
  }

  public void setScnLevel(clp.run.scn.ScnLevel x) {
    this.scnLevel = x;
  }

  //----------------------------------------------------------------------------

  public boolean isScnLtype() {
    return isScnLtype;
  }

  public void setIsScnLtype(boolean x) {
    this.isScnLtype = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.scn.ScnLtype getScnLtype() {
    return scnLtype;
  }

  public void setScnLtype(clp.run.scn.ScnLtype x) {
    this.scnLtype = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
