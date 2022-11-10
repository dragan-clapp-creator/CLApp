package clp.run.cel.log;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Efunction {

  //=== Attributes =============================================================

    EQUALS("equals"),
    DIFFERS("differs"),
    LEVEL("hasLevel"),
;

    String val;

    private Efunction(String val) {
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
