package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayLVariable  implements LongVariable, java.io.Serializable {

  private static final long serialVersionUID = 390L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private clp.run.res.ArrayLInit arrayLInit;


  //=== Constructor ============================================================

  public ArrayLVariable() {
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

  public clp.run.res.ArrayLInit getArrayLInit() {
    return arrayLInit;
  }

  public void setArrayLInit(clp.run.res.ArrayLInit x) {
    this.arrayLInit = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(LongVariableVisitor visitor) {
    visitor.visitArrayLVariable(this);
  }



}
