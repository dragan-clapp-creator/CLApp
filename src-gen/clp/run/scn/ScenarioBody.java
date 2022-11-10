package clp.run.scn;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScenarioBody  implements java.io.Serializable {

  private static final long serialVersionUID = 17L;

  //=== Attributes =============================================================

  private clp.run.scn.ScnPropBody scnPropBody;
  private java.util.ArrayList<clp.run.act.Actor> actors = new java.util.ArrayList<clp.run.act.Actor>();


  //=== Constructor ============================================================

  public ScenarioBody() {
  }

  //=== Methods ================================================================

  public clp.run.scn.ScnPropBody getScnPropBody() {
    return scnPropBody;
  }

  public void setScnPropBody(clp.run.scn.ScnPropBody x) {
    this.scnPropBody = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasActors() {
    return actors != null && !actors.isEmpty();
  }

  public java.util.ArrayList<clp.run.act.Actor> getActors() {
    return actors;
  }

  public void setActors(java.util.ArrayList<clp.run.act.Actor> x) {
    actors = x;
  }

  public void addActor(clp.run.act.Actor x) {
    actors.add( x );
  }

  public void removeActor(clp.run.act.Actor x) {
    actors.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
