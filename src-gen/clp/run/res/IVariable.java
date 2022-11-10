package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IVariable  implements Setting, java.io.Serializable {

  private static final long serialVersionUID = 379L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TINT;
  private clp.run.res.IntVariable intVariable;
  private int value;
  private java.util.ArrayList<java.lang.Integer> values;


  //=== Constructor ============================================================

  public IVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTINT() {
    return TINT;
  }

  public void setTINT(clp.run.res.VarType x) {
    this.TINT = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.IntVariable getIntVariable() {
    return intVariable;
  }

  public void setIntVariable(clp.run.res.IntVariable x) {
    this.intVariable = x;
  }

  //----------------------------------------------------------------------------

  public int getValue() {
    return value;
  }

  public void setValue(int x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------

  public java.util.ArrayList<java.lang.Integer> getValues() {
    return values;
  }

  public void setValues(java.util.ArrayList<java.lang.Integer> x) {
    this.values = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(SettingVisitor visitor) {
    visitor.visitIVariable(this);
  }



}
