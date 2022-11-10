package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class BInitSegment  implements ArrayBInit, java.io.Serializable {

  private static final long serialVersionUID = 64L;


  //=== Attributes =============================================================

  private boolean init1;
  private boolean init2;


  //=== Constructor ============================================================

  public BInitSegment() {
  }

  //=== Methods ================================================================

  public boolean getInit1() {
    return init1;
  }

  public void setInit1(boolean x) {
    this.init1 = x;
  }

  //----------------------------------------------------------------------------

  public boolean getInit2() {
    return init2;
  }

  public void setInit2(boolean x) {
    this.init2 = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArrayBInitVisitor visitor) {
    visitor.visitBInitSegment(this);
  }



}
