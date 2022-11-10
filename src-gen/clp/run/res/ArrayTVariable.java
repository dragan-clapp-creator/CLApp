package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayTVariable  implements TimeVariable, java.io.Serializable {

  private static final long serialVersionUID = 415L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private clp.run.res.TInitList tInitList;


  //=== Constructor ============================================================

  public ArrayTVariable() {
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

  public clp.run.res.TInitList getTInitList() {
    return tInitList;
  }

  public void setTInitList(clp.run.res.TInitList x) {
    this.tInitList = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(TimeVariableVisitor visitor) {
    visitor.visitArrayTVariable(this);
  }



}
