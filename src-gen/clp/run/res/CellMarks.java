package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CellMarks  implements java.io.Serializable {

  private static final long serialVersionUID = 51L;

  //=== Attributes =============================================================

  private String cellName;
  private clp.run.res.CellMarkCheck cellMarkCheck;


  //=== Constructor ============================================================

  public CellMarks() {
  }

  //=== Methods ================================================================

  public String getCellName() {
    return cellName;
  }

  public void setCellName(String x) {
    this.cellName = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.CellMarkCheck getCellMarkCheck() {
    return cellMarkCheck;
  }

  public void setCellMarkCheck(clp.run.res.CellMarkCheck x) {
    this.cellMarkCheck = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
