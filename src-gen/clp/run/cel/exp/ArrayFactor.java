package clp.run.cel.exp;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayFactor  implements Factor, java.io.Serializable {

  private static final long serialVersionUID = 259L;


  //=== Attributes =============================================================

  private clp.run.cel.exp.SimpleFactor simpleFactor;
  private char ckey;
  private java.util.ArrayList<clp.run.cel.exp.SimpleFactor> simpleFactors = new java.util.ArrayList<clp.run.cel.exp.SimpleFactor>();


  //=== Constructor ============================================================

  public ArrayFactor() {
  }

  //=== Methods ================================================================

  public clp.run.cel.exp.SimpleFactor getSimpleFactor() {
    return simpleFactor;
  }

  public void setSimpleFactor(clp.run.cel.exp.SimpleFactor x) {
    this.simpleFactor = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasSimpleFactors() {
    return simpleFactors != null && !simpleFactors.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.exp.SimpleFactor> getSimpleFactors() {
    return simpleFactors;
  }

  public void setSimpleFactors(java.util.ArrayList<clp.run.cel.exp.SimpleFactor> x) {
    simpleFactors = x;
  }

  public void addSimpleFactor(clp.run.cel.exp.SimpleFactor x) {
    simpleFactors.add( x );
  }

  public void removeSimpleFactor(clp.run.cel.exp.SimpleFactor x) {
    simpleFactors.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(FactorVisitor visitor) {
    visitor.visitArrayFactor(this);
  }



}
