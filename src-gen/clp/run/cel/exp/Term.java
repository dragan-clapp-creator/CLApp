package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Term  implements java.io.Serializable {

  private static final long serialVersionUID = 247L;

  //=== Attributes =============================================================

  private clp.run.cel.exp.Factor factor;
  private boolean isFactOperator;
  private clp.run.res.FactOperator op;
  private boolean isTerm;
  private clp.run.cel.exp.Term term;


  //=== Constructor ============================================================

  public Term() {
  }

  //=== Methods ================================================================

  public clp.run.cel.exp.Factor getFactor() {
    return factor;
  }

  public void setFactor(clp.run.cel.exp.Factor x) {
    this.factor = x;
  }

  //----------------------------------------------------------------------------

  public boolean isFactOperator() {
    return isFactOperator;
  }

  public void setIsFactOperator(boolean x) {
    this.isFactOperator = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.FactOperator getOp() {
    return op;
  }

  public void setOp(clp.run.res.FactOperator x) {
    this.op = x;
  }

  //----------------------------------------------------------------------------

  public boolean isTerm() {
    return isTerm;
  }

  public void setIsTerm(boolean x) {
    this.isTerm = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.exp.Term getTerm() {
    return term;
  }

  public void setTerm(clp.run.cel.exp.Term x) {
    this.term = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
