package clp.run.scn;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum DeactType {

  //=== Attributes =============================================================

    MANUAL("MANUAL"),
    AUTO("AUTO"),
;

    String val;

    private DeactType(String val) {
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
