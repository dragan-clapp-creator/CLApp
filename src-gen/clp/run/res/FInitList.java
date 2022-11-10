package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class FInitList  implements ArrayFInit, java.io.Serializable {

  private static final long serialVersionUID = 72L;


  //=== Attributes =============================================================

  private clp.run.res.Fsigned fsigned;
  private char ckey;
  private java.util.ArrayList<clp.run.res.Fsigned> fsigneds = new java.util.ArrayList<clp.run.res.Fsigned>();


  //=== Constructor ============================================================

  public FInitList() {
  }

  //=== Methods ================================================================

  public clp.run.res.Fsigned getFsigned() {
    return fsigned;
  }

  public void setFsigned(clp.run.res.Fsigned x) {
    this.fsigned = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasFsigneds() {
    return fsigneds != null && !fsigneds.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Fsigned> getFsigneds() {
    return fsigneds;
  }

  public void setFsigneds(java.util.ArrayList<clp.run.res.Fsigned> x) {
    fsigneds = x;
  }

  public void addFsigned(clp.run.res.Fsigned x) {
    fsigneds.add( x );
  }

  public void removeFsigned(clp.run.res.Fsigned x) {
    fsigneds.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArrayFInitVisitor visitor) {
    visitor.visitFInitList(this);
  }



}
