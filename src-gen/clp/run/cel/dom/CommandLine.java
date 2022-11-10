package clp.run.cel.dom;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CommandLine  implements java.io.Serializable {

  private static final long serialVersionUID = 278L;

  //=== Attributes =============================================================

  private int level;
  private boolean isLevel;
  private char ckey;
  private clp.run.cel.dom.Command command;
  private int next;
  private boolean isNext;


  //=== Constructor ============================================================

  public CommandLine() {
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

  public clp.run.cel.dom.Command getCommand() {
    return command;
  }

  public void setCommand(clp.run.cel.dom.Command x) {
    this.command = x;
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
