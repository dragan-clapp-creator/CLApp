package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class InitialATokens  implements TokenAMarking, java.io.Serializable {

  private static final long serialVersionUID = 268L;


  //=== Attributes =============================================================

  private String trName;
  private clp.run.res.CellMarkSet entries;
  private String sAND;
  private java.util.ArrayList<clp.run.cel.log.LogicalFactor> logicalFactors = new java.util.ArrayList<clp.run.cel.log.LogicalFactor>();


  //=== Constructor ============================================================

  public InitialATokens() {
  }

  //=== Methods ================================================================

  public String getTrName() {
    return trName;
  }

  public void setTrName(String x) {
    this.trName = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.CellMarkSet getEntries() {
    return entries;
  }

  public void setEntries(clp.run.res.CellMarkSet x) {
    this.entries = x;
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

  public void accept(TokenAMarkingVisitor visitor) {
    visitor.visitInitialATokens(this);
  }



}
