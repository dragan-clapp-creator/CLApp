package clp.run.cel.dom;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LoadMarks extends SysStatement implements java.io.Serializable {

  private static final long serialVersionUID = 318L;

  //=== Attributes =============================================================

  private String name;
  private String marks;


  //=== Constructor ============================================================

  public LoadMarks() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public String getMarks() {
    return marks;
  }

  public void setMarks(String x) {
    this.marks = x;
  }

  //----------------------------------------------------------------------------



}
