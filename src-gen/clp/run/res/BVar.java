package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class BVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 57L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TBOOL;
  private clp.run.res.BoolVar boolVar;
  private boolean value;
  private java.util.ArrayList<java.lang.Boolean> values;


  //=== Constructor ============================================================

  public BVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTBOOL() {
    return TBOOL;
  }

  public void setTBOOL(clp.run.res.VarType x) {
    this.TBOOL = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.BoolVar getBoolVar() {
    return boolVar;
  }

  public void setBoolVar(clp.run.res.BoolVar x) {
    this.boolVar = x;
  }

  //----------------------------------------------------------------------------

  public boolean getValue() {
    return value;
  }

  public void setValue(boolean x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------

  public java.util.ArrayList<java.lang.Boolean> getValues() {
    return values;
  }

  public void setValues(java.util.ArrayList<java.lang.Boolean> x) {
    this.values = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(VariableVisitor visitor) {
    visitor.visitBVar(this);
  }



}
