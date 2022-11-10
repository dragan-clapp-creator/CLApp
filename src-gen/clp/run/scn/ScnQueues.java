package clp.run.scn;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnQueues  implements java.io.Serializable {

  private static final long serialVersionUID = 29L;

  //=== Attributes =============================================================

  private java.util.ArrayList<clp.run.scn.ScnQueue> scnQueues = new java.util.ArrayList<clp.run.scn.ScnQueue>();


  //=== Constructor ============================================================

  public ScnQueues() {
  }

  //=== Methods ================================================================

  public boolean hasScnQueues() {
    return scnQueues != null && !scnQueues.isEmpty();
  }

  public java.util.ArrayList<clp.run.scn.ScnQueue> getScnQueues() {
    return scnQueues;
  }

  public void setScnQueues(java.util.ArrayList<clp.run.scn.ScnQueue> x) {
    scnQueues = x;
  }

  public void addScnQueue(clp.run.scn.ScnQueue x) {
    scnQueues.add( x );
  }

  public void removeScnQueue(clp.run.scn.ScnQueue x) {
    scnQueues.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
