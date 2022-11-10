package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Notification  implements MethodAddOn, java.io.Serializable {

  private static final long serialVersionUID = 188L;


  //=== Attributes =============================================================

  private String clappVariable;


  //=== Constructor ============================================================

  public Notification() {
  }

  //=== Methods ================================================================

  public String getClappVariable() {
    return clappVariable;
  }

  public void setClappVariable(String x) {
    this.clappVariable = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(MethodAddOnVisitor visitor) {
    visitor.visitNotification(this);
  }



}
