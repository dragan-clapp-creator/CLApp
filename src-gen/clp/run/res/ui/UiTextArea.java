package clp.run.res.ui;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiTextArea  implements UiDefType, java.io.Serializable {

  private static final long serialVersionUID = 209L;


  //=== Attributes =============================================================

  private String name;
  private clp.run.res.ui.CstOrVarIdentifier value;
  private char ckey;
  private java.util.ArrayList<clp.run.res.ui.VarIdentifier> varIdentifiers = new java.util.ArrayList<clp.run.res.ui.VarIdentifier>();


  //=== Constructor ============================================================

  public UiTextArea() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.ui.CstOrVarIdentifier getValue() {
    return value;
  }

  public void setValue(clp.run.res.ui.CstOrVarIdentifier x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasVarIdentifiers() {
    return varIdentifiers != null && !varIdentifiers.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.ui.VarIdentifier> getVarIdentifiers() {
    return varIdentifiers;
  }

  public void setVarIdentifiers(java.util.ArrayList<clp.run.res.ui.VarIdentifier> x) {
    varIdentifiers = x;
  }

  public void addVarIdentifier(clp.run.res.ui.VarIdentifier x) {
    varIdentifiers.add( x );
  }

  public void removeVarIdentifier(clp.run.res.ui.VarIdentifier x) {
    varIdentifiers.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(UiDefTypeVisitor visitor) {
    visitor.visitUiTextArea(this);
  }



}
