package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class DInitList  implements java.io.Serializable {

  private static final long serialVersionUID = 124L;

  //=== Attributes =============================================================

  private clp.run.res.DInit dInit;
  private char ckey;
  private java.util.ArrayList<clp.run.res.DInit> dInits = new java.util.ArrayList<clp.run.res.DInit>();


  //=== Constructor ============================================================

  public DInitList() {
  }

  //=== Methods ================================================================

  public clp.run.res.DInit getDInit() {
    return dInit;
  }

  public void setDInit(clp.run.res.DInit x) {
    this.dInit = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasDInits() {
    return dInits != null && !dInits.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.DInit> getDInits() {
    return dInits;
  }

  public void setDInits(java.util.ArrayList<clp.run.res.DInit> x) {
    dInits = x;
  }

  public void addDInit(clp.run.res.DInit x) {
    dInits.add( x );
  }

  public void removeDInit(clp.run.res.DInit x) {
    dInits.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
