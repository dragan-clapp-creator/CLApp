package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 96L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TLONG;
  private clp.run.res.LongVar longVar;
  private long value;
  private java.util.ArrayList<java.sql.Time> values;


  //=== Constructor ============================================================

  public LVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTLONG() {
    return TLONG;
  }

  public void setTLONG(clp.run.res.VarType x) {
    this.TLONG = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.LongVar getLongVar() {
    return longVar;
  }

  public void setLongVar(clp.run.res.LongVar x) {
    this.longVar = x;
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

  public void accept(VariableVisitor visitor) {
    visitor.visitLVar(this);
  }



}
