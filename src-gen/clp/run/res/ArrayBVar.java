package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayBVar  implements BoolVar, java.io.Serializable {

  private static final long serialVersionUID = 60L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private char ckey;
  private boolean isArrayBInit;
  private clp.run.res.ArrayBInit arrayBInit;


  //=== Constructor ============================================================

  public ArrayBVar() {
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

  public boolean isArrayBInit() {
    return isArrayBInit;
  }

  public void setIsArrayBInit(boolean x) {
    this.isArrayBInit = x;
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

  public void accept(BoolVarVisitor visitor) {
    visitor.visitArrayBVar(this);
  }



}
