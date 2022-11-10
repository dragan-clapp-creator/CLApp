package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayLVar  implements LongVar, java.io.Serializable {

  private static final long serialVersionUID = 99L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private char ckey;
  private boolean isArrayLInit;
  private clp.run.res.ArrayLInit arrayLInit;


  //=== Constructor ============================================================

  public ArrayLVar() {
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

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean isArrayLInit() {
    return isArrayLInit;
  }

  public void setIsArrayLInit(boolean x) {
    this.isArrayLInit = x;
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

  public void accept(LongVarVisitor visitor) {
    visitor.visitArrayLVar(this);
  }



}
