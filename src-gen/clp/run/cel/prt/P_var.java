package clp.run.cel.prt;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class P_var  implements PrintElt, java.io.Serializable {

  private static final long serialVersionUID = 283L;


  //=== Attributes =============================================================

  private String var;


  //=== Constructor ============================================================

  public P_var() {
  }

  //=== Methods ================================================================

  public String getVar() {
    return var;
  }

  public void setVar(String x) {
    this.var = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(PrintEltVisitor visitor) {
    visitor.visitP_var(this);
  }



}
