package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class FVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 66L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TFLOAT;
  private clp.run.res.FloatVar floatVar;
  private double value;
  private java.util.ArrayList<java.lang.Double> values;


  //=== Constructor ============================================================

  public FVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTFLOAT() {
    return TFLOAT;
  }

  public void setTFLOAT(clp.run.res.VarType x) {
    this.TFLOAT = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.FloatVar getFloatVar() {
    return floatVar;
  }

  public void setFloatVar(clp.run.res.FloatVar x) {
    this.floatVar = x;
  }

  //----------------------------------------------------------------------------

  public double getValue() {
    return value;
  }

  public void setValue(double x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------

  public java.util.ArrayList<java.lang.Double> getValues() {
    return values;
  }

  public void setValues(java.util.ArrayList<java.lang.Double> x) {
    this.values = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(VariableVisitor visitor) {
    visitor.visitFVar(this);
  }



}
