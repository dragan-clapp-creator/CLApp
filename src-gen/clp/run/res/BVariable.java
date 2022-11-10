package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class BVariable  implements Setting, java.io.Serializable {

  private static final long serialVersionUID = 364L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TBOOL;
  private clp.run.res.BoolVariable boolVariable;
  private boolean value;
  private java.util.ArrayList<java.lang.Boolean> values;


  //=== Constructor ============================================================

  public BVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTBOOL() {
    return TBOOL;
  }

  public void setTBOOL(clp.run.res.VarType x) {
    this.TBOOL = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.BoolVariable getBoolVariable() {
    return boolVariable;
  }

  public void setBoolVariable(clp.run.res.BoolVariable x) {
    this.boolVariable = x;
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

  public void accept(SettingVisitor visitor) {
    visitor.visitBVariable(this);
  }



}
