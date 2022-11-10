package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleFVar  implements FloatVar, java.io.Serializable {

  private static final long serialVersionUID = 79L;


  //=== Attributes =============================================================

  private String name;
  private char ckey;
  private boolean isFsigned;
  private clp.run.res.Fsigned fsigned;


  //=== Constructor ============================================================

  public SimpleFVar() {
  }

  //=== Methods ================================================================

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

  public boolean isFsigned() {
    return isFsigned;
  }

  public void setIsFsigned(boolean x) {
    this.isFsigned = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.Fsigned getFsigned() {
    return fsigned;
  }

  public void setFsigned(clp.run.res.Fsigned x) {
    this.fsigned = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(FloatVarVisitor visitor) {
    visitor.visitSimpleFVar(this);
  }



}
