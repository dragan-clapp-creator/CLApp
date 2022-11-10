package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayBVariable  implements BoolVariable, java.io.Serializable {

  private static final long serialVersionUID = 367L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private clp.run.res.ArrayBInit arrayBInit;


  //=== Constructor ============================================================

  public ArrayBVariable() {
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

  public clp.run.res.ArrayBInit getArrayBInit() {
    return arrayBInit;
  }

  public void setArrayBInit(clp.run.res.ArrayBInit x) {
    this.arrayBInit = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(BoolVariableVisitor visitor) {
    visitor.visitArrayBVariable(this);
  }



}
