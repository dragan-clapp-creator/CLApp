package clp.run.res.ui;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiTable  implements UiDefType, java.io.Serializable {

  private static final long serialVersionUID = 212L;


  //=== Attributes =============================================================

  private String name;
  private clp.run.res.ui.VarIdentifier varIdentifier;
  private boolean isSEL;
  private boolean isSelType;
  private clp.run.res.ui.SelType selType;


  //=== Constructor ============================================================

  public UiTable() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.ui.VarIdentifier getVarIdentifier() {
    return varIdentifier;
  }

  public void setVarIdentifier(clp.run.res.ui.VarIdentifier x) {
    this.varIdentifier = x;
  }

  //----------------------------------------------------------------------------

  public boolean isSEL() {
    return isSEL;
  }

  public void setIsSEL(boolean x) {
    this.isSEL = x;
  }

  //----------------------------------------------------------------------------

  public boolean isSelType() {
    return isSelType;
  }

  public void setIsSelType(boolean x) {
    this.isSelType = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.ui.SelType getSelType() {
    return selType;
  }

  public void setSelType(clp.run.res.ui.SelType x) {
    this.selType = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(UiDefTypeVisitor visitor) {
    visitor.visitUiTable(this);
  }



}
