package clp.run.res.ui;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiButton  implements UiDefType, java.io.Serializable {

  private static final long serialVersionUID = 215L;


  //=== Attributes =============================================================

  private String name;
  private boolean isIsRollback;
  private clp.run.res.ui.IsRollback isRollback;
  private boolean isCaption;
  private String title;
  private boolean isTitle;
  private char ckey;
  private boolean isVarIdentifier;
  private clp.run.res.ui.VarIdentifier varIdentifier;


  //=== Constructor ============================================================

  public UiButton() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public boolean isIsRollback() {
    return isIsRollback;
  }

  public void setIsIsRollback(boolean x) {
    this.isIsRollback = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.ui.IsRollback getIsRollback() {
    return isRollback;
  }

  public void setIsRollback(clp.run.res.ui.IsRollback x) {
    this.isRollback = x;
  }

  //----------------------------------------------------------------------------

  public boolean isCaption() {
    return isCaption;
  }

  public void setIsCaption(boolean x) {
    this.isCaption = x;
  }

  //----------------------------------------------------------------------------

  public String getTitle() {
    return title;
  }

  public void setTitle(String x) {
    this.title = x;
  }

  //----------------------------------------------------------------------------


  public boolean isTitle() {
    return isTitle;
  }

  public void setIsTitle(boolean x) {
    this.isTitle = x;
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
    visitor.visitUiButton(this);
  }



}
