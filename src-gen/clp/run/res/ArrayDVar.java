package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayDVar  implements DateVar, java.io.Serializable {

  private static final long serialVersionUID = 122L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private char ckey;
  private boolean isDInitList;
  private clp.run.res.DInitList dInitList;


  //=== Constructor ============================================================

  public ArrayDVar() {
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

  public boolean isDInitList() {
    return isDInitList;
  }

  public void setIsDInitList(boolean x) {
    this.isDInitList = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.DInitList getDInitList() {
    return dInitList;
  }

  public void setDInitList(clp.run.res.DInitList x) {
    this.dInitList = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(DateVarVisitor visitor) {
    visitor.visitArrayDVar(this);
  }



}
