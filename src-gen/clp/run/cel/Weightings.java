package clp.run.cel;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Weightings  implements java.io.Serializable {

  private static final long serialVersionUID = 222L;

  //=== Attributes =============================================================

  private clp.run.res.Weighting weighting;
  private char ckey;
  private java.util.ArrayList<clp.run.res.Weighting> weightings = new java.util.ArrayList<clp.run.res.Weighting>();


  //=== Constructor ============================================================

  public Weightings() {
  }

  //=== Methods ================================================================

  public clp.run.res.Weighting getWeighting() {
    return weighting;
  }

  public void setWeighting(clp.run.res.Weighting x) {
    this.weighting = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasWeightings() {
    return weightings != null && !weightings.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Weighting> getWeightings() {
    return weightings;
  }

  public void setWeightings(java.util.ArrayList<clp.run.res.Weighting> x) {
    weightings = x;
  }

  public void addWeighting(clp.run.res.Weighting x) {
    weightings.add( x );
  }

  public void removeWeighting(clp.run.res.Weighting x) {
    weightings.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
