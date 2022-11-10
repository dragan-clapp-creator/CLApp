package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IInitList  implements ArrayIInit, java.io.Serializable {

  private static final long serialVersionUID = 87L;


  //=== Attributes =============================================================

  private clp.run.res.Isigned isigned;
  private char ckey;
  private java.util.ArrayList<clp.run.res.Isigned> isigneds = new java.util.ArrayList<clp.run.res.Isigned>();


  //=== Constructor ============================================================

  public IInitList() {
  }

  //=== Methods ================================================================

  public clp.run.res.Isigned getIsigned() {
    return isigned;
  }

  public void setIsigned(clp.run.res.Isigned x) {
    this.isigned = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasIsigneds() {
    return isigneds != null && !isigneds.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Isigned> getIsigneds() {
    return isigneds;
  }

  public void setIsigneds(java.util.ArrayList<clp.run.res.Isigned> x) {
    isigneds = x;
  }

  public void addIsigned(clp.run.res.Isigned x) {
    isigneds.add( x );
  }

  public void removeIsigned(clp.run.res.Isigned x) {
    isigneds.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArrayIInitVisitor visitor) {
    visitor.visitIInitList(this);
  }



}
