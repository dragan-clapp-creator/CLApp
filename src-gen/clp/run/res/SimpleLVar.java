package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleLVar  implements LongVar, java.io.Serializable {

  private static final long serialVersionUID = 109L;


  //=== Attributes =============================================================

  private String name;
  private char ckey;
  private boolean isLsigned;
  private clp.run.res.Lsigned lsigned;


  //=== Constructor ============================================================

  public SimpleLVar() {
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

  public boolean isLsigned() {
    return isLsigned;
  }

  public void setIsLsigned(boolean x) {
    this.isLsigned = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.Lsigned getLsigned() {
    return lsigned;
  }

  public void setLsigned(clp.run.res.Lsigned x) {
    this.lsigned = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(LongVarVisitor visitor) {
    visitor.visitSimpleLVar(this);
  }



}
