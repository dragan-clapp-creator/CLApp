package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayIVar  implements IntVar, java.io.Serializable {

  private static final long serialVersionUID = 84L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private char ckey;
  private boolean isArrayIInit;
  private clp.run.res.ArrayIInit arrayIInit;


  //=== Constructor ============================================================

  public ArrayIVar() {
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

  public boolean isArrayIInit() {
    return isArrayIInit;
  }

  public void setIsArrayIInit(boolean x) {
    this.isArrayIInit = x;
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

  public void accept(IntVarVisitor visitor) {
    visitor.visitArrayIVar(this);
  }



}
