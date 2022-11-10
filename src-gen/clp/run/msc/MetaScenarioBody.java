package clp.run.msc;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MetaScenarioBody  implements java.io.Serializable {

  private static final long serialVersionUID = 3L;

  //=== Attributes =============================================================

  private clp.run.msc.MscTasks mscTasks;
  private boolean isPort;
  private clp.run.msc.Port port;
  private boolean isMscOutput;
  private clp.run.msc.MscOutput mscOutput;
  private java.util.ArrayList<clp.run.scn.Scenario> scenarios = new java.util.ArrayList<clp.run.scn.Scenario>();
  private java.util.ArrayList<clp.run.res.Resources> resourcess = new java.util.ArrayList<clp.run.res.Resources>();


  //=== Constructor ============================================================

  public MetaScenarioBody() {
  }

  //=== Methods ================================================================

  public clp.run.msc.MscTasks getMscTasks() {
    return mscTasks;
  }

  public void setMscTasks(clp.run.msc.MscTasks x) {
    this.mscTasks = x;
  }

  //----------------------------------------------------------------------------

  public boolean isPort() {
    return isPort;
  }

  public void setIsPort(boolean x) {
    this.isPort = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.msc.Port getPort() {
    return port;
  }

  public void setPort(clp.run.msc.Port x) {
    this.port = x;
  }

  //----------------------------------------------------------------------------

  public boolean isMscOutput() {
    return isMscOutput;
  }

  public void setIsMscOutput(boolean x) {
    this.isMscOutput = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.msc.MscOutput getMscOutput() {
    return mscOutput;
  }

  public void setMscOutput(clp.run.msc.MscOutput x) {
    this.mscOutput = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasScenarios() {
    return scenarios != null && !scenarios.isEmpty();
  }

  public java.util.ArrayList<clp.run.scn.Scenario> getScenarios() {
    return scenarios;
  }

  public void setScenarios(java.util.ArrayList<clp.run.scn.Scenario> x) {
    scenarios = x;
  }

  public void addScenario(clp.run.scn.Scenario x) {
    scenarios.add( x );
  }

  public void removeScenario(clp.run.scn.Scenario x) {
    scenarios.remove( x );
  }

  //----------------------------------------------------------------------------

  public boolean hasResourcess() {
    return resourcess != null && !resourcess.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Resources> getResourcess() {
    return resourcess;
  }

  public void setResourcess(java.util.ArrayList<clp.run.res.Resources> x) {
    resourcess = x;
  }

  public void addResources(clp.run.res.Resources x) {
    resourcess.add( x );
  }

  public void removeResources(clp.run.res.Resources x) {
    resourcess.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
