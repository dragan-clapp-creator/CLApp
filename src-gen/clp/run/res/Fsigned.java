package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Fsigned  implements java.io.Serializable {

  private static final long serialVersionUID = 73L;

  //=== Attributes =============================================================

  private boolean isTermOperator;
  private clp.run.res.TermOperator termOperator;
  private double initial;


  //=== Constructor ============================================================

  public Fsigned() {
  }

  //=== Methods ================================================================

  public boolean isTermOperator() {
    return isTermOperator;
  }

  public void setIsTermOperator(boolean x) {
    this.isTermOperator = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.TermOperator getTermOperator() {
    return termOperator;
  }

  public void setTermOperator(clp.run.res.TermOperator x) {
    this.termOperator = x;
  }

  //----------------------------------------------------------------------------

  public double getInitial() {
    return initial;
  }

  public void setInitial(double x) {
    this.initial = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
