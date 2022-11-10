package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Isigned  implements java.io.Serializable {

  private static final long serialVersionUID = 88L;

  //=== Attributes =============================================================

  private boolean isTermOperator;
  private clp.run.res.TermOperator termOperator;
  private int initial;


  //=== Constructor ============================================================

  public Isigned() {
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

  public int getInitial() {
    return initial;
  }

  public void setInitial(int x) {
    this.initial = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
