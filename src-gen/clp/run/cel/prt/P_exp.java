package clp.run.cel.prt;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class P_exp  implements PrintElt, java.io.Serializable {

  private static final long serialVersionUID = 284L;


  //=== Attributes =============================================================

  private clp.run.cel.exp.Expression expression;


  //=== Constructor ============================================================

  public P_exp() {
  }

  //=== Methods ================================================================

  public clp.run.cel.exp.Expression getExpression() {
    return expression;
  }

  public void setExpression(clp.run.cel.exp.Expression x) {
    this.expression = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(PrintEltVisitor visitor) {
    visitor.visitP_exp(this);
  }



}
