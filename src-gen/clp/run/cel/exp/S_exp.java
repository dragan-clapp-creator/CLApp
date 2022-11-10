package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class S_exp  implements Sfactor, java.io.Serializable {

  private static final long serialVersionUID = 257L;


  //=== Attributes =============================================================

  private clp.run.cel.exp.Expression expression;


  //=== Constructor ============================================================

  public S_exp() {
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

  public void accept(SfactorVisitor visitor) {
    visitor.visitS_exp(this);
  }



}
