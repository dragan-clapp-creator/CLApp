package clp.run.res.ui;

import clp.run.res.Variable;
import clp.run.res.VariableVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 191L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TUI;
  private String name;
  private boolean isTitle;
  private boolean isCstOrVar;
  private clp.run.res.weave.CstOrVar title;
  private clp.run.res.ui.UiBundle uiBundle;
  private java.util.ArrayList<clp.run.res.ui.UiBundle> uiBundles = new java.util.ArrayList<clp.run.res.ui.UiBundle>();


  //=== Constructor ============================================================

  public UiVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTUI() {
    return TUI;
  }

  public void setTUI(clp.run.res.VarType x) {
    this.TUI = x;
  }

  //----------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public boolean isTitle() {
    return isTitle;
  }

  public void setIsTitle(boolean x) {
    this.isTitle = x;
  }

  //----------------------------------------------------------------------------

  public boolean isCstOrVar() {
    return isCstOrVar;
  }

  public void setIsCstOrVar(boolean x) {
    this.isCstOrVar = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.weave.CstOrVar getTitle() {
    return title;
  }

  public void setTitle(clp.run.res.weave.CstOrVar x) {
    this.title = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.ui.UiBundle getUiBundle() {
    return uiBundle;
  }

  public void setUiBundle(clp.run.res.ui.UiBundle x) {
    this.uiBundle = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasUiBundles() {
    return uiBundles != null && !uiBundles.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.ui.UiBundle> getUiBundles() {
    return uiBundles;
  }

  public void setUiBundles(java.util.ArrayList<clp.run.res.ui.UiBundle> x) {
    uiBundles = x;
  }

  public void addUiBundle(clp.run.res.ui.UiBundle x) {
    uiBundles.add( x );
  }

  public void removeUiBundle(clp.run.res.ui.UiBundle x) {
    uiBundles.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(VariableVisitor visitor) {
    visitor.visitUiVar(this);
  }



}
