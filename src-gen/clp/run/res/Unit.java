package clp.run.res;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Unit {

  //=== Attributes =============================================================

    MILLIS("ms"),
    SECONDS("s"),
    MINUTES("mn"),
    HOURS("h"),
;

    String val;

    private Unit(String val) {
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
