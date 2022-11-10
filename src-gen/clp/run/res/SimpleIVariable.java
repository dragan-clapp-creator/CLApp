package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleIVariable  implements IntVariable, java.io.Serializable {

  private static final long serialVersionUID = 385L;


  //=== Attributes =============================================================

  private String name;
  private clp.run.res.Isigned isigned;


  //=== Constructor ============================================================

  public SimpleIVariable() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.Isigned getIsigned() {
    return isigned;
  }

  public void setIsigned(clp.run.res.Isigned x) {
    this.isigned = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(IntVariableVisitor visitor) {
    visitor.visitSimpleIVariable(this);
  }



}
