package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayFVar  implements FloatVar, java.io.Serializable {

  private static final long serialVersionUID = 69L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private char ckey;
  private boolean isArrayFInit;
  private clp.run.res.ArrayFInit arrayFInit;


  //=== Constructor ============================================================

  public ArrayFVar() {
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

  public boolean isArrayFInit() {
    return isArrayFInit;
  }

  public void setIsArrayFInit(boolean x) {
    this.isArrayFInit = x;
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

  public void accept(FloatVarVisitor visitor) {
    visitor.visitArrayFVar(this);
  }



}
