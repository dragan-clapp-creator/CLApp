package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleTVariable  implements TimeVariable, java.io.Serializable {

  private static final long serialVersionUID = 418L;


  //=== Attributes =============================================================

  private String name;
  private clp.run.res.TInit tInit;


  //=== Constructor ============================================================

  public SimpleTVariable() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.TInit getTInit() {
    return tInit;
  }

  public void setTInit(clp.run.res.TInit x) {
    this.tInit = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(TimeVariableVisitor visitor) {
    visitor.visitSimpleTVariable(this);
  }



}
