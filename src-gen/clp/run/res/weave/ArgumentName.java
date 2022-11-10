package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgumentName  implements java.io.Serializable {

  private static final long serialVersionUID = 169L;

  //=== Attributes =============================================================

  private boolean isArgtype;
  private clp.run.res.weave.Argtype argtype;
  private String name;


  //=== Constructor ============================================================

  public ArgumentName() {
  }

  //=== Methods ================================================================

  public boolean isArgtype() {
    return isArgtype;
  }

  public void setIsArgtype(boolean x) {
    this.isArgtype = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.weave.Argtype getArgtype() {
    return argtype;
  }

  public void setArgtype(clp.run.res.weave.Argtype x) {
    this.argtype = x;
  }

  //----------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
