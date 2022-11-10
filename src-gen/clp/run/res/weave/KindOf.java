package clp.run.res.weave;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum KindOf {

  //=== Attributes =============================================================

    LOC("LOC"),
    GLOB("GLOB"),
    CALL("CALL"),
    INSTR("INSTR"),
;

    String val;

    private KindOf(String val) {
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
