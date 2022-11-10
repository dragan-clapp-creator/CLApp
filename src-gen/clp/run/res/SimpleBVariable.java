package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleBVariable  implements BoolVariable, java.io.Serializable {

  private static final long serialVersionUID = 370L;


  //=== Attributes =============================================================

  private String name;
  private boolean initial;


  //=== Constructor ============================================================

  public SimpleBVariable() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public boolean getInitial() {
    return initial;
  }

  public void setInitial(boolean x) {
    this.initial = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(BoolVariableVisitor visitor) {
    visitor.visitSimpleBVariable(this);
  }



}
