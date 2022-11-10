package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TInitList  implements java.io.Serializable {

  private static final long serialVersionUID = 135L;

  //=== Attributes =============================================================

  private clp.run.res.TInit tInit;
  private char ckey;
  private java.util.ArrayList<clp.run.res.TInit> tInits = new java.util.ArrayList<clp.run.res.TInit>();


  //=== Constructor ============================================================

  public TInitList() {
  }

  //=== Methods ================================================================

  public clp.run.res.TInit getTInit() {
    return tInit;
  }

  public void setTInit(clp.run.res.TInit x) {
    this.tInit = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasTInits() {
    return tInits != null && !tInits.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.TInit> getTInits() {
    return tInits;
  }

  public void setTInits(java.util.ArrayList<clp.run.res.TInit> x) {
    tInits = x;
  }

  public void addTInit(clp.run.res.TInit x) {
    tInits.add( x );
  }

  public void removeTInit(clp.run.res.TInit x) {
    tInits.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
