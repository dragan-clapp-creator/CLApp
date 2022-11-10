package clp.run.res.weave;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Place {

  //=== Attributes =============================================================

    TOP("TOP"),
    BOTTOM("BOTTOM"),
;

    String val;

    private Place(String val) {
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
