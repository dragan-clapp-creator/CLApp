package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 130L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TTIME;
  private clp.run.res.TimeVar timeVar;
  private clp.run.res.VarType varType;
  private java.sql.Time value;
  private java.util.ArrayList<java.sql.Time> values;


  //=== Constructor ============================================================

  public TVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTTIME() {
    return TTIME;
  }

  public void setTTIME(clp.run.res.VarType x) {
    this.TTIME = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.TimeVar getTimeVar() {
    return timeVar;
  }

  public void setTimeVar(clp.run.res.TimeVar x) {
    this.timeVar = x;
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

  public void accept(VariableVisitor visitor) {
    visitor.visitTVar(this);
  }



}
