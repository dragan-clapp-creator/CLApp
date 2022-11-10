package clp.run.res.ui;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiLabel  implements UiDefType, java.io.Serializable {

  private static final long serialVersionUID = 200L;


  //=== Attributes =============================================================

  private String name;
  private clp.run.res.ui.CstOrVarIdentifier value;


  //=== Constructor ============================================================

  public UiLabel() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.ui.CstOrVarIdentifier getValue() {
    return value;
  }

  public void setValue(clp.run.res.ui.CstOrVarIdentifier x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(UiDefTypeVisitor visitor) {
    visitor.visitUiLabel(this);
  }



}
