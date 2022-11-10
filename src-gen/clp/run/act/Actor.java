package clp.run.act;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Actor  implements java.io.Serializable {

  private static final long serialVersionUID = 32L;

  //=== Attributes =============================================================

  private String name;
  private clp.run.scn.Scenario scenario;
  private java.util.ArrayList<clp.run.cel.Heap> heaps = new java.util.ArrayList<clp.run.cel.Heap>();
  private boolean active;
  private String scn;


  //=== Constructor ============================================================

  public Actor() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.scn.Scenario getScenario() {
    return scenario;
  }

  public void setScenario(clp.run.scn.Scenario x) {
    this.scenario = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasHeaps() {
    return heaps != null && !heaps.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.Heap> getHeaps() {
    return heaps;
  }

  public void setHeaps(java.util.ArrayList<clp.run.cel.Heap> x) {
    heaps = x;
  }

  public void addHeap(clp.run.cel.Heap x) {
    heaps.add( x );
  }

  public void removeHeap(clp.run.cel.Heap x) {
    heaps.remove( x );
  }

  //----------------------------------------------------------------------------

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean x) {
    this.active = x;
  }

  //----------------------------------------------------------------------------

  public String getScn() {
    return scn;
  }

  public void setScn(String x) {
    this.scn = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
