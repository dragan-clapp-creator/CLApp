package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleFactor  implements Factor, java.io.Serializable {

  private static final long serialVersionUID = 249L;


  //=== Attributes =============================================================

  private clp.run.cel.exp.Sfactor sfactor;
  private char ckey;
  private int exp;
  private boolean isExp;


  //=== Constructor ============================================================

  public SimpleFactor() {
  }

  //=== Methods ================================================================

  public clp.run.cel.exp.Sfactor getSfactor() {
    return sfactor;
  }

  public void setSfactor(clp.run.cel.exp.Sfactor x) {
    this.sfactor = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public int getExp() {
    return exp;
  }

  public void setExp(int x) {
    this.exp = x;
  }

  //----------------------------------------------------------------------------


  public boolean isExp() {
    return isExp;
  }

  public void setIsExp(boolean x) {
    this.isExp = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(FactorVisitor visitor) {
    visitor.visitSimpleFactor(this);
  }



}
