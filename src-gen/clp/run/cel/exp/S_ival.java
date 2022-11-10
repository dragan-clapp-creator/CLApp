package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class S_ival  implements Sfactor, java.io.Serializable {

  private static final long serialVersionUID = 253L;


  //=== Attributes =============================================================

  private boolean isTermOperator;
  private clp.run.res.TermOperator termOperator;
  private int ival;


  //=== Constructor ============================================================

  public S_ival() {
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

  public int getIval() {
    return ival;
  }

  public void setIval(int x) {
    this.ival = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(SfactorVisitor visitor) {
    visitor.visitS_ival(this);
  }



}
