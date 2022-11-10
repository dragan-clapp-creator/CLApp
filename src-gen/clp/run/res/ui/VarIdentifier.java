package clp.run.res.ui;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class VarIdentifier  implements CstOrVarIdentifier, java.io.Serializable {

  private static final long serialVersionUID = 203L;


  //=== Attributes =============================================================

  private String var;
  private char ckey;
  private int index;
  private boolean isIndex;
  private int index2;
  private boolean isIndex2;


  //=== Constructor ============================================================

  public VarIdentifier() {
  }

  //=== Methods ================================================================

  public String getVar() {
    return var;
  }

  public void setVar(String x) {
    this.var = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public int getIndex() {
    return index;
  }

  public void setIndex(int x) {
    this.index = x;
  }

  //----------------------------------------------------------------------------


  public boolean isIndex() {
    return isIndex;
  }

  public void setIsIndex(boolean x) {
    this.isIndex = x;
  }

  //----------------------------------------------------------------------------

  public int getIndex2() {
    return index2;
  }

  public void setIndex2(int x) {
    this.index2 = x;
  }

  //----------------------------------------------------------------------------


  public boolean isIndex2() {
    return isIndex2;
  }

  public void setIsIndex2(boolean x) {
    this.isIndex2 = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CstOrVarIdentifierVisitor visitor) {
    visitor.visitVarIdentifier(this);
  }



}
