package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class BInitList  implements ArrayBInit, java.io.Serializable {

  private static final long serialVersionUID = 63L;


  //=== Attributes =============================================================

  private boolean initial;
  private char ckey;
  private java.util.ArrayList<Boolean> initials = new java.util.ArrayList<Boolean>();


  //=== Constructor ============================================================

  public BInitList() {
  }

  //=== Methods ================================================================

  public boolean getInitial() {
    return initial;
  }

  public void setInitial(boolean x) {
    this.initial = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasInitials() {
    return initials != null && !initials.isEmpty();
  }

  public java.util.ArrayList<Boolean> getInitials() {
    return initials;
  }

  public void setInitials(java.util.ArrayList<Boolean> x) {
    initials = x;
  }

  public void addInitial(Boolean x) {
    initials.add( x );
  }

  public void removeInitial(Boolean x) {
    initials.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArrayBInitVisitor visitor) {
    visitor.visitBInitList(this);
  }



}
