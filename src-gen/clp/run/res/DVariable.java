package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class DVariable  implements Setting, java.io.Serializable {

  private static final long serialVersionUID = 403L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TDATE;
  private clp.run.res.DateVariable dateVariable;
  private clp.run.res.VarType varType;
  private java.util.Date value;
  private java.util.ArrayList<java.util.Date> values;


  //=== Constructor ============================================================

  public DVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTDATE() {
    return TDATE;
  }

  public void setTDATE(clp.run.res.VarType x) {
    this.TDATE = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.DateVariable getDateVariable() {
    return dateVariable;
  }

  public void setDateVariable(clp.run.res.DateVariable x) {
    this.dateVariable = x;
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

  public void accept(SettingVisitor visitor) {
    visitor.visitDVariable(this);
  }



}
