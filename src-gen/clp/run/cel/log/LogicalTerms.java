package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LogicalTerms  implements TokenAMarking, java.io.Serializable {

  private static final long serialVersionUID = 229L;


  //=== Attributes =============================================================

  private clp.run.cel.log.LogicalTerm logicalTerm;
  private String sOR;
  private java.util.ArrayList<clp.run.cel.log.LogicalTerm> logicalTerms = new java.util.ArrayList<clp.run.cel.log.LogicalTerm>();


  //=== Constructor ============================================================

  public LogicalTerms() {
  }

  //=== Methods ================================================================

  public clp.run.cel.log.LogicalTerm getLogicalTerm() {
    return logicalTerm;
  }

  public void setLogicalTerm(clp.run.cel.log.LogicalTerm x) {
    this.logicalTerm = x;
  }

  //----------------------------------------------------------------------------

  public String getStringOR() {
    return sOR;
  }

  public void setStringOR(String x) {
    this.sOR = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasLogicalTerms() {
    return logicalTerms != null && !logicalTerms.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.log.LogicalTerm> getLogicalTerms() {
    return logicalTerms;
  }

  public void setLogicalTerms(java.util.ArrayList<clp.run.cel.log.LogicalTerm> x) {
    logicalTerms = x;
  }

  public void addLogicalTerm(clp.run.cel.log.LogicalTerm x) {
    logicalTerms.add( x );
  }

  public void removeLogicalTerm(clp.run.cel.log.LogicalTerm x) {
    logicalTerms.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(TokenAMarkingVisitor visitor) {
    visitor.visitLogicalTerms(this);
  }



}
