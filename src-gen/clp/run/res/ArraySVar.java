package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArraySVar  implements StringVar, java.io.Serializable {

  private static final long serialVersionUID = 114L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private char ckey;
  private boolean isSInitList;
  private clp.run.res.SInitList sInitList;


  //=== Constructor ============================================================

  public ArraySVar() {
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

  public boolean isSInitList() {
    return isSInitList;
  }

  public void setIsSInitList(boolean x) {
    this.isSInitList = x;
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

  public void accept(StringVarVisitor visitor) {
    visitor.visitArraySVar(this);
  }



}
