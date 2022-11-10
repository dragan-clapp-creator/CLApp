package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TransposingLine  implements java.io.Serializable {

  private static final long serialVersionUID = 227L;

  //=== Attributes =============================================================

  private int level;
  private boolean isLevel;
  private char ckey;
  private clp.run.cel.log.TokenAMarking tokenAMarking;
  private boolean isCellMarkCheck;
  private clp.run.res.CellMarkCheck outputs;
  private int next;
  private boolean isNext;


  //=== Constructor ============================================================

  public TransposingLine() {
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

  public clp.run.cel.log.TokenAMarking getTokenAMarking() {
    return tokenAMarking;
  }

  public void setTokenAMarking(clp.run.cel.log.TokenAMarking x) {
    this.tokenAMarking = x;
  }

  //----------------------------------------------------------------------------

  public boolean isCellMarkCheck() {
    return isCellMarkCheck;
  }

  public void setIsCellMarkCheck(boolean x) {
    this.isCellMarkCheck = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.CellMarkCheck getOutputs() {
    return outputs;
  }

  public void setOutputs(clp.run.res.CellMarkCheck x) {
    this.outputs = x;
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





  //=== Acceptors ==============================================================



}
