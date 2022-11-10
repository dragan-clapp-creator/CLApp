package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleIVar  implements IntVar, java.io.Serializable {

  private static final long serialVersionUID = 94L;


  //=== Attributes =============================================================

  private String name;
  private char ckey;
  private boolean isIsigned;
  private clp.run.res.Isigned isigned;


  //=== Constructor ============================================================

  public SimpleIVar() {
  }

  //=== Methods ================================================================

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

  public boolean isIsigned() {
    return isIsigned;
  }

  public void setIsIsigned(boolean x) {
    this.isIsigned = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.Isigned getIsigned() {
    return isigned;
  }

  public void setIsigned(clp.run.res.Isigned x) {
    this.isigned = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(IntVarVisitor visitor) {
    visitor.visitSimpleIVar(this);
  }



}
