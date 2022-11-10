package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LogicalTerm  implements java.io.Serializable {

  private static final long serialVersionUID = 230L;

  //=== Attributes =============================================================

  private clp.run.cel.log.LogicalFactor logicalFactor;
  private String sAND;
  private java.util.ArrayList<clp.run.cel.log.LogicalFactor> logicalFactors = new java.util.ArrayList<clp.run.cel.log.LogicalFactor>();


  //=== Constructor ============================================================

  public LogicalTerm() {
  }

  //=== Methods ================================================================

  public clp.run.cel.log.LogicalFactor getLogicalFactor() {
    return logicalFactor;
  }

  public void setLogicalFactor(clp.run.cel.log.LogicalFactor x) {
    this.logicalFactor = x;
  }

  //----------------------------------------------------------------------------

  public String getStringAND() {
    return sAND;
  }

  public void setStringAND(String x) {
    this.sAND = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasLogicalFactors() {
    return logicalFactors != null && !logicalFactors.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.log.LogicalFactor> getLogicalFactors() {
    return logicalFactors;
  }

  public void setLogicalFactors(java.util.ArrayList<clp.run.cel.log.LogicalFactor> x) {
    logicalFactors = x;
  }

  public void addLogicalFactor(clp.run.cel.log.LogicalFactor x) {
    logicalFactors.add( x );
  }

  public void removeLogicalFactor(clp.run.cel.log.LogicalFactor x) {
    logicalFactors.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
