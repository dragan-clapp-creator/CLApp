package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LineNumber  implements Location, java.io.Serializable {

  private static final long serialVersionUID = 177L;


  //=== Attributes =============================================================

  private int number;


  //=== Constructor ============================================================

  public LineNumber() {
  }

  //=== Methods ================================================================

  public int getNumber() {
    return number;
  }

  public void setNumber(int x) {
    this.number = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(LocationVisitor visitor) {
    visitor.visitLineNumber(this);
  }



}
