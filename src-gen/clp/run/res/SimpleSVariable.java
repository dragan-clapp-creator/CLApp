package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleSVariable  implements StringVariable, java.io.Serializable {

  private static final long serialVersionUID = 401L;


  //=== Attributes =============================================================

  private String name;
  private String initial;


  //=== Constructor ============================================================

  public SimpleSVariable() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public String getInitial() {
    return initial;
  }

  public void setInitial(String x) {
    this.initial = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(StringVariableVisitor visitor) {
    visitor.visitSimpleSVariable(this);
  }



}
