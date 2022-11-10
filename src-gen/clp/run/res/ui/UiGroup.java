package clp.run.res.ui;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiGroup  implements UiBundle, java.io.Serializable {

  private static final long serialVersionUID = 195L;


  //=== Attributes =============================================================

  private String title;
  private clp.run.res.ui.UiBundle uiBundle;
  private java.util.ArrayList<clp.run.res.ui.UiBundle> uiBundles = new java.util.ArrayList<clp.run.res.ui.UiBundle>();


  //=== Constructor ============================================================

  public UiGroup() {
  }

  //=== Methods ================================================================

  public String getTitle() {
    return title;
  }

  public void setTitle(String x) {
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

  public void accept(UiBundleVisitor visitor) {
    visitor.visitUiGroup(this);
  }



}
