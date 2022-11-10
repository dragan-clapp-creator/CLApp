package clp.run.scn;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Ltype {

  //=== Attributes =============================================================

    POSITIVE("+"),
    NEGATIVE("-"),
    NEUTRAL("."),
;

    String val;

    private Ltype(String val) {
      this.val=val;
    }

  //=== Methods ================================================================

    public String getVal(){
      return val;
    }

    public String toString(){
      return name() + " = " + val;
    }
}
