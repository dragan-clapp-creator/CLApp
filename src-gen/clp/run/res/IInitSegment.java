package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IInitSegment  implements ArrayIInit, java.io.Serializable {

  private static final long serialVersionUID = 91L;


  //=== Attributes =============================================================

  private clp.run.res.Isigned init1;
  private clp.run.res.Isigned init2;


  //=== Constructor ============================================================

  public IInitSegment() {
  }

  //=== Methods ================================================================

  public clp.run.res.Isigned getInit1() {
    return init1;
  }

  public void setInit1(clp.run.res.Isigned x) {
    this.init1 = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.Isigned getInit2() {
    return init2;
  }

  public void setInit2(clp.run.res.Isigned x) {
    this.init2 = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArrayIInitVisitor visitor) {
    visitor.visitIInitSegment(this);
  }



}
