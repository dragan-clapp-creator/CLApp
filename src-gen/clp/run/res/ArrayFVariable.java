package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayFVariable  implements FloatVariable, java.io.Serializable {

  private static final long serialVersionUID = 374L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private clp.run.res.ArrayFInit arrayFInit;


  //=== Constructor ============================================================

  public ArrayFVariable() {
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

  public clp.run.res.ArrayFInit getArrayFInit() {
    return arrayFInit;
  }

  public void setArrayFInit(clp.run.res.ArrayFInit x) {
    this.arrayFInit = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(FloatVariableVisitor visitor) {
    visitor.visitArrayFVariable(this);
  }



}
