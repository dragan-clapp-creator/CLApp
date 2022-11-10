package clp.run.cel.prt;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class PrintStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 280L;


  //=== Attributes =============================================================

  private boolean isPrintElt;
  private clp.run.cel.prt.PrintElt printElt;
  private char ckey;
  private java.util.ArrayList<clp.run.cel.prt.PrintElt> printElts = new java.util.ArrayList<clp.run.cel.prt.PrintElt>();
  private boolean isTo;
  private boolean isOutputTarget;
  private clp.run.msc.OutputTarget outputTarget;


  //=== Constructor ============================================================

  public PrintStatement() {
  }

  //=== Methods ================================================================

  public boolean isPrintElt() {
    return isPrintElt;
  }

  public void setIsPrintElt(boolean x) {
    this.isPrintElt = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.prt.PrintElt getPrintElt() {
    return printElt;
  }

  public void setPrintElt(clp.run.cel.prt.PrintElt x) {
    this.printElt = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasPrintElts() {
    return printElts != null && !printElts.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.prt.PrintElt> getPrintElts() {
    return printElts;
  }

  public void setPrintElts(java.util.ArrayList<clp.run.cel.prt.PrintElt> x) {
    printElts = x;
  }

  public void addPrintElt(clp.run.cel.prt.PrintElt x) {
    printElts.add( x );
  }

  public void removePrintElt(clp.run.cel.prt.PrintElt x) {
    printElts.remove( x );
  }

  //----------------------------------------------------------------------------

  public boolean isTo() {
    return isTo;
  }

  public void setIsTo(boolean x) {
    this.isTo = x;
  }

  //----------------------------------------------------------------------------

  public boolean isOutputTarget() {
    return isOutputTarget;
  }

  public void setIsOutputTarget(boolean x) {
    this.isOutputTarget = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.msc.OutputTarget getOutputTarget() {
    return outputTarget;
  }

  public void setOutputTarget(clp.run.msc.OutputTarget x) {
    this.outputTarget = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitPrintStatement(this);
  }



}
