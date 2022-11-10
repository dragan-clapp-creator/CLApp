package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleBVar  implements BoolVar, java.io.Serializable {

  private static final long serialVersionUID = 65L;


  //=== Attributes =============================================================

  private String name;
  private char ckey;
  private boolean initial;
  private boolean isInitial;


  //=== Constructor ============================================================

  public SimpleBVar() {
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

  public boolean getInitial() {
    return initial;
  }

  public void setInitial(boolean x) {
    this.initial = x;
  }

  //----------------------------------------------------------------------------


  public boolean isInitial() {
    return isInitial;
  }

  public void setIsInitial(boolean x) {
    this.isInitial = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(BoolVarVisitor visitor) {
    visitor.visitSimpleBVar(this);
  }



}
