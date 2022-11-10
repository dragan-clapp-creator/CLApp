package clp.run.res.weave;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Position {

  //=== Attributes =============================================================

    AFTER("after"),
    BEFORE("before"),
    AFTER_LOOP_TOP("within_loop_at_top"),
    AFTER_END_LOOP("after_end_loop"),
    BEFORE_LOOP("before_loop"),
    BEFORE_END_LOOP("before_loop_end"),
    AFTER_EACH("after_each"),
    BEFORE_EACH("before_each"),
;

    String val;

    private Position(String val) {
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
