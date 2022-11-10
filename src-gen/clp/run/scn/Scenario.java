package clp.run.scn;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Scenario  implements java.io.Serializable {

  private static final long serialVersionUID = 15L;

  //=== Attributes =============================================================

  private String name;
  private clp.run.msc.MetaScenario metaScenario;
  private clp.run.scn.ScenarioBody scenarioBody;
  private String msc;
  private boolean active;


  //=== Constructor ============================================================

  public Scenario() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.msc.MetaScenario getMetaScenario() {
    return metaScenario;
  }

  public void setMetaScenario(clp.run.msc.MetaScenario x) {
    this.metaScenario = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.scn.ScenarioBody getScenarioBody() {
    return scenarioBody;
  }

  public void setScenarioBody(clp.run.scn.ScenarioBody x) {
    this.scenarioBody = x;
  }

  //----------------------------------------------------------------------------

  public String getMsc() {
    return msc;
  }

  public void setMsc(String x) {
    this.msc = x;
  }

  //----------------------------------------------------------------------------

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean x) {
    this.active = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
