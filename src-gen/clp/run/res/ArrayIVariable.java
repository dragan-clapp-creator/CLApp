package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayIVariable  implements IntVariable, java.io.Serializable {

  private static final long serialVersionUID = 382L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private clp.run.res.ArrayIInit arrayIInit;


  //=== Constructor ============================================================

  public ArrayIVariable() {
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

  public clp.run.res.ArrayIInit getArrayIInit() {
    return arrayIInit;
  }

  public void setArrayIInit(clp.run.res.ArrayIInit x) {
    this.arrayIInit = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(IntVariableVisitor visitor) {
    visitor.visitArrayIVariable(this);
  }



}
