package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class AtLocation  implements Location, java.io.Serializable {

  private static final long serialVersionUID = 175L;


  //=== Attributes =============================================================

  private clp.run.res.weave.Place place;


  //=== Constructor ============================================================

  public AtLocation() {
  }

  //=== Methods ================================================================

  public clp.run.res.weave.Place getPlace() {
    return place;
  }

  public void setPlace(clp.run.res.weave.Place x) {
    this.place = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(LocationVisitor visitor) {
    visitor.visitAtLocation(this);
  }



}
