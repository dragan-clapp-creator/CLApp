package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgListCount  implements ArgListApprox, java.io.Serializable {

  private static final long serialVersionUID = 173L;


  //=== Attributes =============================================================

  private int count;


  //=== Constructor ============================================================

  public ArgListCount() {
  }

  //=== Methods ================================================================

  public int getCount() {
    return count;
  }

  public void setCount(int x) {
    this.count = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArgListApproxVisitor visitor) {
    visitor.visitArgListCount(this);
  }



}
