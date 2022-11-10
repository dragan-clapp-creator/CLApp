package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleDVar  implements DateVar, java.io.Serializable {

  private static final long serialVersionUID = 127L;


  //=== Attributes =============================================================

  private String name;
  private char ckey;
  private boolean isDInit;
  private clp.run.res.DInit dInit;


  //=== Constructor ============================================================

  public SimpleDVar() {
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

  public boolean isDInit() {
    return isDInit;
  }

  public void setIsDInit(boolean x) {
    this.isDInit = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.DInit getDInit() {
    return dInit;
  }

  public void setDInit(clp.run.res.DInit x) {
    this.dInit = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(DateVarVisitor visitor) {
    visitor.visitSimpleDVar(this);
  }



}
