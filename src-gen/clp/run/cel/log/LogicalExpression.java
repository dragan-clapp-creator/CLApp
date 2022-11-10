package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LogicalExpression  implements java.io.Serializable {

  private static final long serialVersionUID = 274L;

  //=== Attributes =============================================================

  private int level;
  private boolean isLevel;
  private char ckey;
  private clp.run.cel.log.LogicalTerms logicalTerms;
  private int next;
  private boolean isNext;
  private int otherwise;
  private boolean isOtherwise;


  //=== Constructor ============================================================

  public LogicalExpression() {
  }

  //=== Methods ================================================================

  public int getLevel() {
    return level;
  }

  public void setLevel(int x) {
    this.level = x;
  }

  //----------------------------------------------------------------------------


  public boolean isLevel() {
    return isLevel;
  }

  public void setIsLevel(boolean x) {
    this.isLevel = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.log.LogicalTerms getLogicalTerms() {
    return logicalTerms;
  }

  public void setLogicalTerms(clp.run.cel.log.LogicalTerms x) {
    this.logicalTerms = x;
  }

  //----------------------------------------------------------------------------

  public int getNext() {
    return next;
  }

  public void setNext(int x) {
    this.next = x;
  }

  //----------------------------------------------------------------------------


  public boolean isNext() {
    return isNext;
  }

  public void setIsNext(boolean x) {
    this.isNext = x;
  }

  //----------------------------------------------------------------------------

  public int getOtherwise() {
    return otherwise;
  }

  public void setOtherwise(int x) {
    this.otherwise = x;
  }

  //----------------------------------------------------------------------------


  public boolean isOtherwise() {
    return isOtherwise;
  }

  public void setIsOtherwise(boolean x) {
    this.isOtherwise = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
