package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgList  implements ArgListApprox, java.io.Serializable {

  private static final long serialVersionUID = 166L;


  //=== Attributes =============================================================

  private clp.run.res.weave.ArgumentPair argumentPair;
  private char ckey;
  private java.util.ArrayList<clp.run.res.weave.ArgumentPair> argumentPairs = new java.util.ArrayList<clp.run.res.weave.ArgumentPair>();


  //=== Constructor ============================================================

  public ArgList() {
  }

  //=== Methods ================================================================

  public clp.run.res.weave.ArgumentPair getArgumentPair() {
    return argumentPair;
  }

  public void setArgumentPair(clp.run.res.weave.ArgumentPair x) {
    this.argumentPair = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasArgumentPairs() {
    return argumentPairs != null && !argumentPairs.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.weave.ArgumentPair> getArgumentPairs() {
    return argumentPairs;
  }

  public void setArgumentPairs(java.util.ArrayList<clp.run.res.weave.ArgumentPair> x) {
    argumentPairs = x;
  }

  public void addArgumentPair(clp.run.res.weave.ArgumentPair x) {
    argumentPairs.add( x );
  }

  public void removeArgumentPair(clp.run.res.weave.ArgumentPair x) {
    argumentPairs.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(ArgListApproxVisitor visitor) {
    visitor.visitArgList(this);
  }



}
