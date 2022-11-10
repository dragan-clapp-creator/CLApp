package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 81L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TINT;
  private clp.run.res.IntVar intVar;
  private int value;
  private java.util.ArrayList<java.lang.Integer> values;


  //=== Constructor ============================================================

  public IVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTINT() {
    return TINT;
  }

  public void setTINT(clp.run.res.VarType x) {
    this.TINT = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.IntVar getIntVar() {
    return intVar;
  }

  public void setIntVar(clp.run.res.IntVar x) {
    this.intVar = x;
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

  public void accept(VariableVisitor visitor) {
    visitor.visitIVar(this);
  }



}
