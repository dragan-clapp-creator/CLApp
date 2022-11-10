package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UsedLib  implements java.io.Serializable {

  private static final long serialVersionUID = 38L;

  //=== Attributes =============================================================

  private clp.run.res.UsedJava usedJava;
  private char ckey;
  private java.util.ArrayList<clp.run.res.UsedJava> usedJavas = new java.util.ArrayList<clp.run.res.UsedJava>();


  //=== Constructor ============================================================

  public UsedLib() {
  }

  //=== Methods ================================================================

  public clp.run.res.UsedJava getUsedJava() {
    return usedJava;
  }

  public void setUsedJava(clp.run.res.UsedJava x) {
    this.usedJava = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasUsedJavas() {
    return usedJavas != null && !usedJavas.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.UsedJava> getUsedJavas() {
    return usedJavas;
  }

  public void setUsedJavas(java.util.ArrayList<clp.run.res.UsedJava> x) {
    usedJavas = x;
  }

  public void addUsedJava(clp.run.res.UsedJava x) {
    usedJavas.add( x );
  }

  public void removeUsedJava(clp.run.res.UsedJava x) {
    usedJavas.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
