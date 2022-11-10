package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LocalAttribute  implements Attribute, java.io.Serializable {

  private static final long serialVersionUID = 184L;


  //=== Attributes =============================================================

  private String type;
  private boolean isType;
  private String attribute;


  //=== Constructor ============================================================

  public LocalAttribute() {
  }

  //=== Methods ================================================================

  public String getType() {
    return type;
  }

  public void setType(String x) {
    this.type = x;
  }

  //----------------------------------------------------------------------------


  public boolean isType() {
    return isType;
  }

  public void setIsType(boolean x) {
    this.isType = x;
  }

  //----------------------------------------------------------------------------

  public String getAttribute() {
    return attribute;
  }

  public void setAttribute(String x) {
    this.attribute = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(AttributeVisitor visitor) {
    visitor.visitLocalAttribute(this);
  }



}
