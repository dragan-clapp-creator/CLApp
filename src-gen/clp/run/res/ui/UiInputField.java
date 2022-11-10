package clp.run.res.ui;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiInputField  implements UiDefType, java.io.Serializable {

  private static final long serialVersionUID = 204L;


  //=== Attributes =============================================================

  private String name;
  private boolean isIsRequired;
  private clp.run.res.ui.IsRequired isRequired;
  private boolean isIsEnabled;
  private clp.run.res.ui.IsEnabled isEnabled;
  private boolean isIsPassword;
  private clp.run.res.ui.IsPassword isPassword;
  private char ckey;
  private boolean isVarIdentifier;
  private clp.run.res.ui.VarIdentifier varIdentifier;


  //=== Constructor ============================================================

  public UiInputField() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public boolean isIsRequired() {
    return isIsRequired;
  }

  public void setIsIsRequired(boolean x) {
    this.isIsRequired = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.ui.IsRequired getIsRequired() {
    return isRequired;
  }

  public void setIsRequired(clp.run.res.ui.IsRequired x) {
    this.isRequired = x;
  }

  //----------------------------------------------------------------------------

  public boolean isIsEnabled() {
    return isIsEnabled;
  }

  public void setIsIsEnabled(boolean x) {
    this.isIsEnabled = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.ui.IsEnabled getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(clp.run.res.ui.IsEnabled x) {
    this.isEnabled = x;
  }

  //----------------------------------------------------------------------------

  public boolean isIsPassword() {
    return isIsPassword;
  }

  public void setIsIsPassword(boolean x) {
    this.isIsPassword = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.ui.IsPassword getIsPassword() {
    return isPassword;
  }

  public void setIsPassword(clp.run.res.ui.IsPassword x) {
    this.isPassword = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean isVarIdentifier() {
    return isVarIdentifier;
  }

  public void setIsVarIdentifier(boolean x) {
    this.isVarIdentifier = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.ui.VarIdentifier getVarIdentifier() {
    return varIdentifier;
  }

  public void setVarIdentifier(clp.run.res.ui.VarIdentifier x) {
    this.varIdentifier = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(UiDefTypeVisitor visitor) {
    visitor.visitUiInputField(this);
  }



}
