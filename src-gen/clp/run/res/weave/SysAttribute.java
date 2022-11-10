package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SysAttribute  implements Attribute, java.io.Serializable {

  private static final long serialVersionUID = 186L;


  //=== Attributes =============================================================

  private clp.run.res.weave.SysRef attribute;


  //=== Constructor ============================================================

  public SysAttribute() {
  }

  //=== Methods ================================================================

  public clp.run.res.weave.SysRef getAttribute() {
    return attribute;
  }

  public void setAttribute(clp.run.res.weave.SysRef x) {
    this.attribute = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(AttributeVisitor visitor) {
    visitor.visitSysAttribute(this);
  }



}
