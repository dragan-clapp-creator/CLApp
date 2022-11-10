package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LInitList  implements ArrayLInit, java.io.Serializable {

  private static final long serialVersionUID = 102L;


  //=== Attributes =============================================================

  private clp.run.res.Lsigned lsigned;
  private char ckey;
  private java.util.ArrayList<clp.run.res.Lsigned> lsigneds = new java.util.ArrayList<clp.run.res.Lsigned>();


  //=== Constructor ============================================================

  public LInitList() {
  }

  //=== Methods ================================================================

  public clp.run.res.Lsigned getLsigned() {
    return lsigned;
  }

  public void setLsigned(clp.run.res.Lsigned x) {
    this.lsigned = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasLsigneds() {
    return lsigneds != null && !lsigneds.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Lsigned> getLsigneds() {
    return lsigneds;
  }

  public void setLsigneds(java.util.ArrayList<clp.run.res.Lsigned> x) {
    lsigneds = x;
  }

  public void addLsigned(clp.run.res.Lsigned x) {
    lsigneds.add( x );
  }

  public void removeLsigned(clp.run.res.Lsigned x) {
    lsigneds.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArrayLInitVisitor visitor) {
    visitor.visitLInitList(this);
  }



}
