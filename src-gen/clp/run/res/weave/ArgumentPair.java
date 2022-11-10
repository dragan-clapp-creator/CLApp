package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgumentPair  implements java.io.Serializable {

  private static final long serialVersionUID = 167L;

  //=== Attributes =============================================================

  private clp.run.res.weave.ArgumentSum argumentSum;
  private char ckey;
  private String type;
  private boolean isType;


  //=== Constructor ============================================================

  public ArgumentPair() {
  }

  //=== Methods ================================================================

  public clp.run.res.weave.ArgumentSum getArgumentSum() {
    return argumentSum;
  }

  public void setArgumentSum(clp.run.res.weave.ArgumentSum x) {
    this.argumentSum = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public String getType() {
    return type;
  }

  public void setType(String x) {
    this.type = x;
  }

  //----------------------------------------------------------------------------


  public boolean isType() {
    return isType;
  }

  public void setIsType(boolean x) {
    this.isType = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
