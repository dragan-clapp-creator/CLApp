package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 111L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TSTRING;
  private clp.run.res.StringVar stringVar;
  private clp.run.res.VarType varType;
  private String value;
  private java.util.ArrayList<String> values;


  //=== Constructor ============================================================

  public SVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTSTRING() {
    return TSTRING;
  }

  public void setTSTRING(clp.run.res.VarType x) {
    this.TSTRING = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.StringVar getStringVar() {
    return stringVar;
  }

  public void setStringVar(clp.run.res.StringVar x) {
    this.stringVar = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.VarType getVarType() {
    return varType;
  }

  public void setVarType(clp.run.res.VarType x) {
    this.varType = x;
  }

  //----------------------------------------------------------------------------

  public String getValue() {
    return value;
  }

  public void setValue(String x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------

  public java.util.ArrayList<String> getValues() {
    return values;
  }

  public void setValues(java.util.ArrayList<String> x) {
    this.values = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(VariableVisitor visitor) {
    visitor.visitSVar(this);
  }



}
