package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class DVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 119L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TDATE;
  private clp.run.res.DateVar dateVar;
  private clp.run.res.VarType varType;
  private java.util.Date value;
  private java.util.ArrayList<java.util.Date> values;


  //=== Constructor ============================================================

  public DVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTDATE() {
    return TDATE;
  }

  public void setTDATE(clp.run.res.VarType x) {
    this.TDATE = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.DateVar getDateVar() {
    return dateVar;
  }

  public void setDateVar(clp.run.res.DateVar x) {
    this.dateVar = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.VarType getVarType() {
    return varType;
  }

  public void setVarType(clp.run.res.VarType x) {
    this.varType = x;
  }

  //----------------------------------------------------------------------------

  public java.util.Date getValue() {
    return value;
  }

  public void setValue(java.util.Date x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------

  public java.util.ArrayList<java.util.Date> getValues() {
    return values;
  }

  public void setValues(java.util.ArrayList<java.util.Date> x) {
    this.values = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(VariableVisitor visitor) {
    visitor.visitDVar(this);
  }



}
