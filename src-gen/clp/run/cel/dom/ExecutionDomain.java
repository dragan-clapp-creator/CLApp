package clp.run.cel.dom;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ExecutionDomain  implements java.io.Serializable {

  private static final long serialVersionUID = 277L;

  //=== Attributes =============================================================

  private java.util.ArrayList<clp.run.cel.dom.CommandLine> commandLines = new java.util.ArrayList<clp.run.cel.dom.CommandLine>();


  //=== Constructor ============================================================

  public ExecutionDomain() {
  }

  //=== Methods ================================================================

  public boolean hasCommandLines() {
    return commandLines != null && !commandLines.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.dom.CommandLine> getCommandLines() {
    return commandLines;
  }

  public void setCommandLines(java.util.ArrayList<clp.run.cel.dom.CommandLine> x) {
    commandLines = x;
  }

  public void addCommandLine(clp.run.cel.dom.CommandLine x) {
    commandLines.add( x );
  }

  public void removeCommandLine(clp.run.cel.dom.CommandLine x) {
    commandLines.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
