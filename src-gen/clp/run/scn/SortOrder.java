package clp.run.scn;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum SortOrder {

  //=== Attributes =============================================================

    ISERIAL("Is"),
    DSERIAL("Ds"),
    QUEUE_TOP("Qt"),
    QUEUE_END("Qe"),
;

    String val;

    private SortOrder(String val) {
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
