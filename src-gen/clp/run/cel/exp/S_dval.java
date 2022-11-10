package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class S_dval  implements Sfactor, java.io.Serializable {

  private static final long serialVersionUID = 251L;


  //=== Attributes =============================================================

  private int day;
  private int month;
  private int year;


  //=== Constructor ============================================================

  public S_dval() {
  }

  //=== Methods ================================================================

  public int getDay() {
    return day;
  }

  public void setDay(int x) {
    this.day = x;
  }

  //----------------------------------------------------------------------------

  public int getMonth() {
    return month;
  }

  public void setMonth(int x) {
    this.month = x;
  }

  //----------------------------------------------------------------------------

  public int getYear() {
    return year;
  }

  public void setYear(int x) {
    this.year = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(SfactorVisitor visitor) {
    visitor.visitS_dval(this);
  }



}
