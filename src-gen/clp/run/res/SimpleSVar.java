package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleSVar  implements StringVar, java.io.Serializable {

  private static final long serialVersionUID = 117L;


  //=== Attributes =============================================================

  private String name;
  private char ckey;
  private String initial;
  private boolean isInitial;


  //=== Constructor ============================================================

  public SimpleSVar() {
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

  public String getInitial() {
    return initial;
  }

  public void setInitial(String x) {
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

  public void accept(StringVarVisitor visitor) {
    visitor.visitSimpleSVar(this);
  }



}
