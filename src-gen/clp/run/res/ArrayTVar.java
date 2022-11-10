package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayTVar  implements TimeVar, java.io.Serializable {

  private static final long serialVersionUID = 133L;


  //=== Attributes =============================================================

  private clp.run.res.Array array;
  private String name;
  private char ckey;
  private boolean isTInitList;
  private clp.run.res.TInitList tInitList;


  //=== Constructor ============================================================

  public ArrayTVar() {
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

  public boolean isTInitList() {
    return isTInitList;
  }

  public void setIsTInitList(boolean x) {
    this.isTInitList = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.TInitList getTInitList() {
    return tInitList;
  }

  public void setTInitList(clp.run.res.TInitList x) {
    this.tInitList = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(TimeVarVisitor visitor) {
    visitor.visitArrayTVar(this);
  }



}
