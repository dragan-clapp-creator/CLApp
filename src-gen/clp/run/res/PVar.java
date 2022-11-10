package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class PVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 148L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TREF;
  private boolean isArray;
  private clp.run.res.Array array;
  private String name;
  private char ckey;
  private String initial;
  private boolean isInitial;
  transient private java.lang.Object value;
  transient private java.util.ArrayList<java.lang.Object> values;


  //=== Constructor ============================================================

  public PVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTREF() {
    return TREF;
  }

  public void setTREF(clp.run.res.VarType x) {
    this.TREF = x;
  }

  //----------------------------------------------------------------------------

  public boolean isArray() {
    return isArray;
  }

  public void setIsArray(boolean x) {
    this.isArray = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.Array getArray() {
    return array;
  }

  public void setArray(clp.run.res.Array x) {
    this.array = x;
  }

  //----------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public String getInitial() {
    return initial;
  }

  public void setInitial(String x) {
    this.initial = x;
  }

  //----------------------------------------------------------------------------


  public boolean isInitial() {
    return isInitial;
  }

  public void setIsInitial(boolean x) {
    this.isInitial = x;
  }

  //----------------------------------------------------------------------------

  public java.lang.Object getValue() {
    return value;
  }

  public void setValue(java.lang.Object x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------

  public java.util.ArrayList<java.lang.Object> getValues() {
    return values;
  }

  public void setValues(java.util.ArrayList<java.lang.Object> x) {
    this.values = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(VariableVisitor visitor) {
    visitor.visitPVar(this);
  }



}
