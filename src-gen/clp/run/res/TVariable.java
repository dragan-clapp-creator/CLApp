package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TVariable  implements Setting, java.io.Serializable {

  private static final long serialVersionUID = 412L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TTIME;
  private clp.run.res.TimeVariable timeVariable;
  private clp.run.res.VarType varType;
  private java.sql.Time value;
  private java.util.ArrayList<java.sql.Time> values;


  //=== Constructor ============================================================

  public TVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTTIME() {
    return TTIME;
  }

  public void setTTIME(clp.run.res.VarType x) {
    this.TTIME = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.TimeVariable getTimeVariable() {
    return timeVariable;
  }

  public void setTimeVariable(clp.run.res.TimeVariable x) {
    this.timeVariable = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.VarType getVarType() {
    return varType;
  }

  public void setVarType(clp.run.res.VarType x) {
    this.varType = x;
  }

  //----------------------------------------------------------------------------

  public java.sql.Time getValue() {
    return value;
  }

  public void setValue(java.sql.Time x) {
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
    visitor.visitTVariable(this);
  }



}
