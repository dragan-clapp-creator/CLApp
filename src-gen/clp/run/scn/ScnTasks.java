package clp.run.scn;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnTasks  implements java.io.Serializable {

  private static final long serialVersionUID = 26L;

  //=== Attributes =============================================================

  private java.util.ArrayList<clp.run.scn.ScnTask> scnTasks = new java.util.ArrayList<clp.run.scn.ScnTask>();


  //=== Constructor ============================================================

  public ScnTasks() {
  }

  //=== Methods ================================================================

  public boolean hasScnTasks() {
    return scnTasks != null && !scnTasks.isEmpty();
  }

  public java.util.ArrayList<clp.run.scn.ScnTask> getScnTasks() {
    return scnTasks;
  }

  public void setScnTasks(java.util.ArrayList<clp.run.scn.ScnTask> x) {
    scnTasks = x;
  }

  public void addScnTask(clp.run.scn.ScnTask x) {
    scnTasks.add( x );
  }

  public void removeScnTask(clp.run.scn.ScnTask x) {
    scnTasks.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
