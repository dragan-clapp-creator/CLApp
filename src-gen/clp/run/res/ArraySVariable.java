package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArraySVariable  implements StringVariable, java.io.Serializable {

  private static final long serialVersionUID = 398L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private clp.run.res.SInitList sInitList;


  //=== Constructor ============================================================

  public ArraySVariable() {
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

  public clp.run.res.SInitList getSInitList() {
    return sInitList;
  }

  public void setSInitList(clp.run.res.SInitList x) {
    this.sInitList = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(StringVariableVisitor visitor) {
    visitor.visitArraySVariable(this);
  }



}
