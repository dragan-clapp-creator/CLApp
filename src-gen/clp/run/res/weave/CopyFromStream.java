package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CopyFromStream  implements MethodAddOn, java.io.Serializable {

  private static final long serialVersionUID = 189L;


  //=== Attributes =============================================================

  private clp.run.res.weave.Attribute attribute;
  private String clappVariable;


  //=== Constructor ============================================================

  public CopyFromStream() {
  }

  //=== Methods ================================================================

  public clp.run.res.weave.Attribute getAttribute() {
    return attribute;
  }

  public void setAttribute(clp.run.res.weave.Attribute x) {
    this.attribute = x;
  }

  //----------------------------------------------------------------------------

  public String getClappVariable() {
    return clappVariable;
  }

  public void setClappVariable(String x) {
    this.clappVariable = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(MethodAddOnVisitor visitor) {
    visitor.visitCopyFromStream(this);
  }



}
