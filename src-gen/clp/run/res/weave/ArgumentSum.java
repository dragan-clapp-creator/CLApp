package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgumentSum  implements java.io.Serializable {

  private static final long serialVersionUID = 168L;

  //=== Attributes =============================================================

  private clp.run.res.weave.ArgumentName argumentName;
  private char ckey;
  private java.util.ArrayList<clp.run.res.weave.ArgumentName> argumentNames = new java.util.ArrayList<clp.run.res.weave.ArgumentName>();


  //=== Constructor ============================================================

  public ArgumentSum() {
  }

  //=== Methods ================================================================

  public clp.run.res.weave.ArgumentName getArgumentName() {
    return argumentName;
  }

  public void setArgumentName(clp.run.res.weave.ArgumentName x) {
    this.argumentName = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasArgumentNames() {
    return argumentNames != null && !argumentNames.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.weave.ArgumentName> getArgumentNames() {
    return argumentNames;
  }

  public void setArgumentNames(java.util.ArrayList<clp.run.res.weave.ArgumentName> x) {
    argumentNames = x;
  }

  public void addArgumentName(clp.run.res.weave.ArgumentName x) {
    argumentNames.add( x );
  }

  public void removeArgumentName(clp.run.res.weave.ArgumentName x) {
    argumentNames.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
