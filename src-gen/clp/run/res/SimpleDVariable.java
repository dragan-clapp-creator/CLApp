package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleDVariable  implements DateVariable, java.io.Serializable {

  private static final long serialVersionUID = 409L;


  //=== Attributes =============================================================

  private String name;
  private clp.run.res.DInit dInit;


  //=== Constructor ============================================================

  public SimpleDVariable() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.DInit getDInit() {
    return dInit;
  }

  public void setDInit(clp.run.res.DInit x) {
    this.dInit = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(DateVariableVisitor visitor) {
    visitor.visitSimpleDVariable(this);
  }



}
