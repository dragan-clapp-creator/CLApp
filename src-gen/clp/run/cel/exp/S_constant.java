package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class S_constant  implements Sfactor, java.io.Serializable {

  private static final long serialVersionUID = 255L;


  //=== Attributes =============================================================

  private String constant;


  //=== Constructor ============================================================

  public S_constant() {
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

  public void accept(SfactorVisitor visitor) {
    visitor.visitS_constant(this);
  }



}
