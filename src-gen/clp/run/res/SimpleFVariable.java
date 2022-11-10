package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleFVariable  implements FloatVariable, java.io.Serializable {

  private static final long serialVersionUID = 377L;


  //=== Attributes =============================================================

  private String name;
  private clp.run.res.Fsigned fsigned;


  //=== Constructor ============================================================

  public SimpleFVariable() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.Fsigned getFsigned() {
    return fsigned;
  }

  public void setFsigned(clp.run.res.Fsigned x) {
    this.fsigned = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(FloatVariableVisitor visitor) {
    visitor.visitSimpleFVariable(this);
  }



}
