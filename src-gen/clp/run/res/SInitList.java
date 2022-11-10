package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SInitList  implements java.io.Serializable {

  private static final long serialVersionUID = 116L;

  //=== Attributes =============================================================

  private String initial;
  private char ckey;
  private java.util.ArrayList<String> initials = new java.util.ArrayList<String>();


  //=== Constructor ============================================================

  public SInitList() {
  }

  //=== Methods ================================================================

  public String getInitial() {
    return initial;
  }

  public void setInitial(String x) {
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

  public java.util.ArrayList<String> getInitials() {
    return initials;
  }

  public void setInitials(java.util.ArrayList<String> x) {
    initials = x;
  }

  public void addInitial(String x) {
    initials.add( x );
  }

  public void removeInitial(String x) {
    initials.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
