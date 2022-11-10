package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LInitSegment  implements ArrayLInit, java.io.Serializable {

  private static final long serialVersionUID = 106L;


  //=== Attributes =============================================================

  private clp.run.res.Lsigned init1;
  private clp.run.res.Lsigned init2;


  //=== Constructor ============================================================

  public LInitSegment() {
  }

  //=== Methods ================================================================

  public clp.run.res.Lsigned getInit1() {
    return init1;
  }

  public void setInit1(clp.run.res.Lsigned x) {
    this.init1 = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.Lsigned getInit2() {
    return init2;
  }

  public void setInit2(clp.run.res.Lsigned x) {
    this.init2 = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArrayLInitVisitor visitor) {
    visitor.visitLInitSegment(this);
  }



}
