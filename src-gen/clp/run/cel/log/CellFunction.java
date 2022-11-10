package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CellFunction  implements java.io.Serializable {

  private static final long serialVersionUID = 233L;

  //=== Attributes =============================================================

  private clp.run.cel.log.Cfunction function;
  private String cellName;
  private boolean isSINCE;
  private String time;
  private boolean isTime;


  //=== Constructor ============================================================

  public CellFunction() {
  }

  //=== Methods ================================================================

  public clp.run.cel.log.Cfunction getFunction() {
    return function;
  }

  public void setFunction(clp.run.cel.log.Cfunction x) {
    this.function = x;
  }

  //----------------------------------------------------------------------------

  public String getCellName() {
    return cellName;
  }

  public void setCellName(String x) {
    this.cellName = x;
  }

  //----------------------------------------------------------------------------

  public boolean isSINCE() {
    return isSINCE;
  }

  public void setIsSINCE(boolean x) {
    this.isSINCE = x;
  }

  //----------------------------------------------------------------------------

  public String getTime() {
    return time;
  }

  public void setTime(String x) {
    this.time = x;
  }

  //----------------------------------------------------------------------------


  public boolean isTime() {
    return isTime;
  }

  public void setIsTime(boolean x) {
    this.isTime = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
