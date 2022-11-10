package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class S_tval  implements Sfactor, java.io.Serializable {

  private static final long serialVersionUID = 252L;


  //=== Attributes =============================================================

  private int hour;
  private int min;
  private int sec;


  //=== Constructor ============================================================

  public S_tval() {
  }

  //=== Methods ================================================================

  public int getHour() {
    return hour;
  }

  public void setHour(int x) {
    this.hour = x;
  }

  //----------------------------------------------------------------------------

  public int getMin() {
    return min;
  }

  public void setMin(int x) {
    this.min = x;
  }

  //----------------------------------------------------------------------------

  public int getSec() {
    return sec;
  }

  public void setSec(int x) {
    this.sec = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(SfactorVisitor visitor) {
    visitor.visitS_tval(this);
  }



}
