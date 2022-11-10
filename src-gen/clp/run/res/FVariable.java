package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class FVariable  implements Setting, java.io.Serializable {

  private static final long serialVersionUID = 371L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TFLOAT;
  private clp.run.res.FloatVariable floatVariable;
  private double value;
  private java.util.ArrayList<java.lang.Double> values;


  //=== Constructor ============================================================

  public FVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTFLOAT() {
    return TFLOAT;
  }

  public void setTFLOAT(clp.run.res.VarType x) {
    this.TFLOAT = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.FloatVariable getFloatVariable() {
    return floatVariable;
  }

  public void setFloatVariable(clp.run.res.FloatVariable x) {
    this.floatVariable = x;
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

  public void accept(SettingVisitor visitor) {
    visitor.visitFVariable(this);
  }



}
