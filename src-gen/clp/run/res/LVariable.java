package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LVariable  implements Setting, java.io.Serializable {

  private static final long serialVersionUID = 387L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TLONG;
  private clp.run.res.LongVariable longVariable;
  private long value;
  private java.util.ArrayList<java.sql.Time> values;


  //=== Constructor ============================================================

  public LVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTLONG() {
    return TLONG;
  }

  public void setTLONG(clp.run.res.VarType x) {
    this.TLONG = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.LongVariable getLongVariable() {
    return longVariable;
  }

  public void setLongVariable(clp.run.res.LongVariable x) {
    this.longVariable = x;
  }

  //----------------------------------------------------------------------------

  public long getValue() {
    return value;
  }

  public void setValue(long x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------

  public java.util.ArrayList<java.sql.Time> getValues() {
    return values;
  }

  public void setValues(java.util.ArrayList<java.sql.Time> x) {
    this.values = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(SettingVisitor visitor) {
    visitor.visitLVariable(this);
  }



}
