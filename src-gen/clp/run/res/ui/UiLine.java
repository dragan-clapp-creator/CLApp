package clp.run.res.ui;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiLine  implements UiBundle, java.io.Serializable {

  private static final long serialVersionUID = 198L;


  //=== Attributes =============================================================

  private int number;
  private clp.run.res.ui.UiDefType uiDefType;
  private char ckey;
  private java.util.ArrayList<clp.run.res.ui.UiDefType> uiDefTypes = new java.util.ArrayList<clp.run.res.ui.UiDefType>();


  //=== Constructor ============================================================

  public UiLine() {
  }

  //=== Methods ================================================================

  public int getNumber() {
    return number;
  }

  public void setNumber(int x) {
    this.number = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.ui.UiDefType getUiDefType() {
    return uiDefType;
  }

  public void setUiDefType(clp.run.res.ui.UiDefType x) {
    this.uiDefType = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasUiDefTypes() {
    return uiDefTypes != null && !uiDefTypes.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.ui.UiDefType> getUiDefTypes() {
    return uiDefTypes;
  }

  public void setUiDefTypes(java.util.ArrayList<clp.run.res.ui.UiDefType> x) {
    uiDefTypes = x;
  }

  public void addUiDefType(clp.run.res.ui.UiDefType x) {
    uiDefTypes.add( x );
  }

  public void removeUiDefType(clp.run.res.ui.UiDefType x) {
    uiDefTypes.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(UiBundleVisitor visitor) {
    visitor.visitUiLine(this);
  }



}
