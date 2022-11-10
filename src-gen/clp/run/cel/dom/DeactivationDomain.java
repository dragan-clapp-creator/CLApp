package clp.run.cel.dom;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class DeactivationDomain  implements java.io.Serializable {

  private static final long serialVersionUID = 273L;

  //=== Attributes =============================================================

  private java.util.ArrayList<clp.run.cel.log.LogicalExpression> logicalExpressions = new java.util.ArrayList<clp.run.cel.log.LogicalExpression>();


  //=== Constructor ============================================================

  public DeactivationDomain() {
  }

  //=== Methods ================================================================

  public boolean hasLogicalExpressions() {
    return logicalExpressions != null && !logicalExpressions.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.log.LogicalExpression> getLogicalExpressions() {
    return logicalExpressions;
  }

  public void setLogicalExpressions(java.util.ArrayList<clp.run.cel.log.LogicalExpression> x) {
    logicalExpressions = x;
  }

  public void addLogicalExpression(clp.run.cel.log.LogicalExpression x) {
    logicalExpressions.add( x );
  }

  public void removeLogicalExpression(clp.run.cel.log.LogicalExpression x) {
    logicalExpressions.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
