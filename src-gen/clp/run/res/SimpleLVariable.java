package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleLVariable  implements LongVariable, java.io.Serializable {

  private static final long serialVersionUID = 393L;


  //=== Attributes =============================================================

  private String name;
  private clp.run.res.Lsigned lsigned;


  //=== Constructor ============================================================

  public SimpleLVariable() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.Lsigned getLsigned() {
    return lsigned;
  }

  public void setLsigned(clp.run.res.Lsigned x) {
    this.lsigned = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(LongVariableVisitor visitor) {
    visitor.visitSimpleLVariable(this);
  }



}
