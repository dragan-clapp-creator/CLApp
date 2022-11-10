package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Lsigned  implements java.io.Serializable {

  private static final long serialVersionUID = 103L;

  //=== Attributes =============================================================

  private boolean isTermOperator;
  private clp.run.res.TermOperator termOperator;
  private long initial;


  //=== Constructor ============================================================

  public Lsigned() {
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

  public long getInitial() {
    return initial;
  }

  public void setInitial(long x) {
    this.initial = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
