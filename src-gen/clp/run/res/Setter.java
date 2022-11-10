package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Setter  implements java.io.Serializable {

  private static final long serialVersionUID = 362L;

  //=== Attributes =============================================================

  private String res;
  private java.util.ArrayList<clp.run.res.Setting> settings = new java.util.ArrayList<clp.run.res.Setting>();


  //=== Constructor ============================================================

  public Setter() {
  }

  //=== Methods ================================================================

  public String getRes() {
    return res;
  }

  public void setRes(String x) {
    this.res = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasSettings() {
    return settings != null && !settings.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Setting> getSettings() {
    return settings;
  }

  public void setSettings(java.util.ArrayList<clp.run.res.Setting> x) {
    settings = x;
  }

  public void addSetting(clp.run.res.Setting x) {
    settings.add( x );
  }

  public void removeSetting(clp.run.res.Setting x) {
    settings.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
