package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class HVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 141L;


  //=== Attributes =============================================================

  private clp.run.res.VarType THASH;
  private boolean isArray;
  private clp.run.res.Array array;
  private String name;
  private char ckey;
  private boolean isInitHash;
  private clp.run.res.InitHash initHash;
  private java.util.ArrayList<clp.run.res.InitHash> initHashs = new java.util.ArrayList<clp.run.res.InitHash>();
  transient private java.lang.Object value;
  transient private java.util.ArrayList<java.lang.Object> values;


  //=== Constructor ============================================================

  public HVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTHASH() {
    return THASH;
  }

  public void setTHASH(clp.run.res.VarType x) {
    this.THASH = x;
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

  public boolean isInitHash() {
    return isInitHash;
  }

  public void setIsInitHash(boolean x) {
    this.isInitHash = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.InitHash getInitHash() {
    return initHash;
  }

  public void setInitHash(clp.run.res.InitHash x) {
    this.initHash = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasInitHashs() {
    return initHashs != null && !initHashs.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.InitHash> getInitHashs() {
    return initHashs;
  }

  public void setInitHashs(java.util.ArrayList<clp.run.res.InitHash> x) {
    initHashs = x;
  }

  public void addInitHash(clp.run.res.InitHash x) {
    initHashs.add( x );
  }

  public void removeInitHash(clp.run.res.InitHash x) {
    initHashs.remove( x );
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
    visitor.visitHVar(this);
  }



}
