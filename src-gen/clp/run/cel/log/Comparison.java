package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Comparison  implements java.io.Serializable {

  private static final long serialVersionUID = 244L;

  //=== Attributes =============================================================

  private clp.run.cel.log.OpComp opComp;
  private clp.run.cel.exp.Expression exp1;
  private clp.run.cel.exp.Expression exp2;


  //=== Constructor ============================================================

  public Comparison() {
  }

  //=== Methods ================================================================

  public clp.run.cel.log.OpComp getOpComp() {
    return opComp;
  }

  public void setOpComp(clp.run.cel.log.OpComp x) {
    this.opComp = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.exp.Expression getExp1() {
    return exp1;
  }

  public void setExp1(clp.run.cel.exp.Expression x) {
    this.exp1 = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.exp.Expression getExp2() {
    return exp2;
  }

  public void setExp2(clp.run.cel.exp.Expression x) {
    this.exp2 = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
