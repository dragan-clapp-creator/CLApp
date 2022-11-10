package clp.run.cel.asm;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class VarAssignment extends AssignStatement implements java.io.Serializable {

  private static final long serialVersionUID = 320L;

  //=== Attributes =============================================================

  private String var;
  private clp.run.cel.exp.Expression expression;


  //=== Constructor ============================================================

  public VarAssignment() {
  }

  //=== Methods ================================================================

  public String getVar() {
    return var;
  }

  public void setVar(String x) {
    this.var = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.exp.Expression getExpression() {
    return expression;
  }

  public void setExpression(clp.run.cel.exp.Expression x) {
    this.expression = x;
  }

  //----------------------------------------------------------------------------



}
