package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayDVariable  implements DateVariable, java.io.Serializable {

  private static final long serialVersionUID = 406L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private clp.run.res.DInitList dInitList;


  //=== Constructor ============================================================

  public ArrayDVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.Array getArray() {
    return array;
  }

  public void setArray(clp.run.res.Array x) {
    this.array = x;
  }

  //----------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.DInitList getDInitList() {
    return dInitList;
  }

  public void setDInitList(clp.run.res.DInitList x) {
    this.dInitList = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(DateVariableVisitor visitor) {
    visitor.visitArrayDVariable(this);
  }



}
