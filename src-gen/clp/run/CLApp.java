package clp.run;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CLApp  implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  //=== Attributes =============================================================

  private boolean isMetaScenario;
  private clp.run.msc.MetaScenario metaScenario;
  private java.util.ArrayList<clp.run.scn.Scenario> uscenarios = new java.util.ArrayList<clp.run.scn.Scenario>();
  private java.util.ArrayList<clp.run.res.Resources> uresourcess = new java.util.ArrayList<clp.run.res.Resources>();
  private java.util.ArrayList<clp.run.act.Actor> uactors = new java.util.ArrayList<clp.run.act.Actor>();
  private java.util.ArrayList<clp.run.cel.Heap> uheaps = new java.util.ArrayList<clp.run.cel.Heap>();
  private java.util.ArrayList<clp.run.res.Setter> setters = new java.util.ArrayList<clp.run.res.Setter>();


  //=== Constructor ============================================================

  public CLApp() {
  }

  //=== Methods ================================================================

  public boolean isMetaScenario() {
    return isMetaScenario;
  }

  public void setIsMetaScenario(boolean x) {
    this.isMetaScenario = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.msc.MetaScenario getMetaScenario() {
    return metaScenario;
  }

  public void setMetaScenario(clp.run.msc.MetaScenario x) {
    this.metaScenario = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasScenarios() {
    return uscenarios != null && !uscenarios.isEmpty();
  }

  public java.util.ArrayList<clp.run.scn.Scenario> getScenarios() {
    return uscenarios;
  }

  public void setScenarios(java.util.ArrayList<clp.run.scn.Scenario> x) {
    uscenarios = x;
  }

  public void addScenario(clp.run.scn.Scenario x) {
    uscenarios.add( x );
  }

  public void removeScenario(clp.run.scn.Scenario x) {
    uscenarios.remove( x );
  }

  //----------------------------------------------------------------------------

  public boolean hasResourcess() {
    return uresourcess != null && !uresourcess.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Resources> getResourcess() {
    return uresourcess;
  }

  public void setResourcess(java.util.ArrayList<clp.run.res.Resources> x) {
    uresourcess = x;
  }

  public void addResources(clp.run.res.Resources x) {
    uresourcess.add( x );
  }

  public void removeResources(clp.run.res.Resources x) {
    uresourcess.remove( x );
  }

  //----------------------------------------------------------------------------

  public boolean hasActors() {
    return uactors != null && !uactors.isEmpty();
  }

  public java.util.ArrayList<clp.run.act.Actor> getActors() {
    return uactors;
  }

  public void setActors(java.util.ArrayList<clp.run.act.Actor> x) {
    uactors = x;
  }

  public void addActor(clp.run.act.Actor x) {
    uactors.add( x );
  }

  public void removeActor(clp.run.act.Actor x) {
    uactors.remove( x );
  }

  //----------------------------------------------------------------------------

  public boolean hasHeaps() {
    return uheaps != null && !uheaps.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.Heap> getHeaps() {
    return uheaps;
  }

  public void setHeaps(java.util.ArrayList<clp.run.cel.Heap> x) {
    uheaps = x;
  }

  public void addHeap(clp.run.cel.Heap x) {
    uheaps.add( x );
  }

  public void removeHeap(clp.run.cel.Heap x) {
    uheaps.remove( x );
  }

  //----------------------------------------------------------------------------

  public boolean hasSetters() {
    return setters != null && !setters.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Setter> getSetters() {
    return setters;
  }

  public void setSetters(java.util.ArrayList<clp.run.res.Setter> x) {
    setters = x;
  }

  public void addSetter(clp.run.res.Setter x) {
    setters.add( x );
  }

  public void removeSetter(clp.run.res.Setter x) {
    setters.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
