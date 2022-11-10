package clp.run.cel.prt;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class P_constant  implements PrintElt, java.io.Serializable {

  private static final long serialVersionUID = 282L;


  //=== Attributes =============================================================

  private String constant;


  //=== Constructor ============================================================

  public P_constant() {
  }

  //=== Methods ================================================================

  public String getConstant() {
    return constant;
  }

  public void setConstant(String x) {
    this.constant = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(PrintEltVisitor visitor) {
    visitor.visitP_constant(this);
  }



}
