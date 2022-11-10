package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CellEvent  implements Event, java.io.Serializable {

  private static final long serialVersionUID = 45L;


  //=== Attributes =============================================================

  private String cellName;
  private String time;
  private boolean isTime;
  private int delay;
  private boolean isDelay;
  private boolean isUnit;
  private clp.run.res.Unit unit;
  private boolean cycle;
  private boolean isCycle;


  //=== Constructor ============================================================

  public CellEvent() {
  }

  //=== Methods ================================================================

  public String getCellName() {
    return cellName;
  }

  public void setCellName(String x) {
    this.cellName = x;
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

  public int getDelay() {
    return delay;
  }

  public void setDelay(int x) {
    this.delay = x;
  }

  //----------------------------------------------------------------------------


  public boolean isDelay() {
    return isDelay;
  }

  public void setIsDelay(boolean x) {
    this.isDelay = x;
  }

  //----------------------------------------------------------------------------

  public boolean isUnit() {
    return isUnit;
  }

  public void setIsUnit(boolean x) {
    this.isUnit = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.Unit getUnit() {
    return unit;
  }

  public void setUnit(clp.run.res.Unit x) {
    this.unit = x;
  }

  //----------------------------------------------------------------------------

  public boolean getCycle() {
    return cycle;
  }

  public void setCycle(boolean x) {
    this.cycle = x;
  }

  //----------------------------------------------------------------------------


  public boolean isCycle() {
    return isCycle;
  }

  public void setIsCycle(boolean x) {
    this.isCycle = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(EventVisitor visitor) {
    visitor.visitCellEvent(this);
  }



}
