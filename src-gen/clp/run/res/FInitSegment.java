package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class FInitSegment  implements ArrayFInit, java.io.Serializable {

  private static final long serialVersionUID = 76L;


  //=== Attributes =============================================================

  private clp.run.res.Fsigned init1;
  private clp.run.res.Fsigned init2;


  //=== Constructor ============================================================

  public FInitSegment() {
  }

  //=== Methods ================================================================

  public clp.run.res.Fsigned getInit1() {
    return init1;
  }

  public void setInit1(clp.run.res.Fsigned x) {
    this.init1 = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.Fsigned getInit2() {
    return init2;
  }

  public void setInit2(clp.run.res.Fsigned x) {
    this.init2 = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArrayFInitVisitor visitor) {
    visitor.visitFInitSegment(this);
  }



}
