package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class S_var  implements Sfactor, java.io.Serializable {

  private static final long serialVersionUID = 256L;


  //=== Attributes =============================================================

  private String var;


  //=== Constructor ============================================================

  public S_var() {
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

  public void accept(SfactorVisitor visitor) {
    visitor.visitS_var(this);
  }



}
