package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class VarEvent  implements Event, java.io.Serializable {

  private static final long serialVersionUID = 47L;


  //=== Attributes =============================================================

  private String name;


  //=== Constructor ============================================================

  public VarEvent() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(EventVisitor visitor) {
    visitor.visitVarEvent(this);
  }



}
