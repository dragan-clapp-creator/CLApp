package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CheckEvent  implements java.io.Serializable {

  private static final long serialVersionUID = 238L;

  //=== Attributes =============================================================

  private clp.run.cel.log.Vfunction function;
  private String name;


  //=== Constructor ============================================================

  public CheckEvent() {
  }

  //=== Methods ================================================================

  public clp.run.cel.log.Vfunction getFunction() {
    return function;
  }

  public void setFunction(clp.run.cel.log.Vfunction x) {
    this.function = x;
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
