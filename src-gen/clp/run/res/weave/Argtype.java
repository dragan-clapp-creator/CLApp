package clp.run.res.weave;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Argtype {

  //=== Attributes =============================================================

    CST("CST"),
    LOC("LOC"),
    GLOB("GLOB"),
;

    String val;

    private Argtype(String val) {
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
