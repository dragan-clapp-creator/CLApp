package clp.run.scn;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnQueue  implements java.io.Serializable {

  private static final long serialVersionUID = 30L;

  //=== Attributes =============================================================

  private String name;
  private clp.run.scn.SortOrder sortOrder;


  //=== Constructor ============================================================

  public ScnQueue() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.scn.SortOrder getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(clp.run.scn.SortOrder x) {
    this.sortOrder = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
