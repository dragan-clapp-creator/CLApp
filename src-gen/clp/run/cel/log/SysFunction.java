package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SysFunction  implements java.io.Serializable {

  private static final long serialVersionUID = 235L;

  //=== Attributes =============================================================

  private clp.run.cel.log.Efunction function;
  private String name;
  private clp.run.cel.log.Operand operand;


  //=== Constructor ============================================================

  public SysFunction() {
  }

  //=== Methods ================================================================

  public clp.run.cel.log.Efunction getFunction() {
    return function;
  }

  public void setFunction(clp.run.cel.log.Efunction x) {
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

  public clp.run.cel.log.Operand getOperand() {
    return operand;
  }

  public void setOperand(clp.run.cel.log.Operand x) {
    this.operand = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
