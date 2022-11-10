package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SVariable  implements Setting, java.io.Serializable {

  private static final long serialVersionUID = 395L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TSTRING;
  private clp.run.res.StringVariable stringVariable;
  private clp.run.res.VarType varType;
  private String value;
  private java.util.ArrayList<String> values;


  //=== Constructor ============================================================

  public SVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTSTRING() {
    return TSTRING;
  }

  public void setTSTRING(clp.run.res.VarType x) {
    this.TSTRING = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.StringVariable getStringVariable() {
    return stringVariable;
  }

  public void setStringVariable(clp.run.res.StringVariable x) {
    this.stringVariable = x;
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

  public void accept(SettingVisitor visitor) {
    visitor.visitSVariable(this);
  }



}
