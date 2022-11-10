package clp.run.msc;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MetaScenario  implements java.io.Serializable {

  private static final long serialVersionUID = 2L;

  //=== Attributes =============================================================

  private String name;
  private clp.run.msc.MetaScenarioBody metaScenarioBody;


  //=== Constructor ============================================================

  public MetaScenario() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.msc.MetaScenarioBody getMetaScenarioBody() {
    return metaScenarioBody;
  }

  public void setMetaScenarioBody(clp.run.msc.MetaScenarioBody x) {
    this.metaScenarioBody = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
