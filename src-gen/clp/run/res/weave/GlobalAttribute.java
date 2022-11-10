package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GlobalAttribute  implements Attribute, java.io.Serializable {

  private static final long serialVersionUID = 185L;


  //=== Attributes =============================================================

  private String type;
  private boolean isType;
  private String attribute;


  //=== Constructor ============================================================

  public GlobalAttribute() {
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
    visitor.visitGlobalAttribute(this);
  }



}
