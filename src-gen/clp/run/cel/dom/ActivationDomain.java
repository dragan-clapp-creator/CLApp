package clp.run.cel.dom;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ActivationDomain  implements java.io.Serializable {

  private static final long serialVersionUID = 226L;

  //=== Attributes =============================================================

  private java.util.ArrayList<clp.run.cel.log.TransposingLine> transposingLines = new java.util.ArrayList<clp.run.cel.log.TransposingLine>();


  //=== Constructor ============================================================

  public ActivationDomain() {
  }

  //=== Methods ================================================================

  public boolean hasTransposingLines() {
    return transposingLines != null && !transposingLines.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.log.TransposingLine> getTransposingLines() {
    return transposingLines;
  }

  public void setTransposingLines(java.util.ArrayList<clp.run.cel.log.TransposingLine> x) {
    transposingLines = x;
  }

  public void addTransposingLine(clp.run.cel.log.TransposingLine x) {
    transposingLines.add( x );
  }

  public void removeTransposingLine(clp.run.cel.log.TransposingLine x) {
    transposingLines.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
