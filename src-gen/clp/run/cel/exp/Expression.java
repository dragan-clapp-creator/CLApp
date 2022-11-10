package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Expression  implements java.io.Serializable {

  private static final long serialVersionUID = 246L;

  //=== Attributes =============================================================

  private clp.run.cel.exp.Term term;
  private boolean isTermOperator;
  private clp.run.res.TermOperator op;
  private boolean isExpression;
  private clp.run.cel.exp.Expression expression;


  //=== Constructor ============================================================

  public Expression() {
  }

  //=== Methods ================================================================

  public clp.run.cel.exp.Term getTerm() {
    return term;
  }

  public void setTerm(clp.run.cel.exp.Term x) {
    this.term = x;
  }

  //----------------------------------------------------------------------------

  public boolean isTermOperator() {
    return isTermOperator;
  }

  public void setIsTermOperator(boolean x) {
    this.isTermOperator = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.TermOperator getOp() {
    return op;
  }

  public void setOp(clp.run.res.TermOperator x) {
    this.op = x;
  }

  //----------------------------------------------------------------------------

  public boolean isExpression() {
    return isExpression;
  }

  public void setIsExpression(boolean x) {
    this.isExpression = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.exp.Expression getExpression() {
    return expression;
  }

  public void setExpression(clp.run.cel.exp.Expression x) {
    this.expression = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
