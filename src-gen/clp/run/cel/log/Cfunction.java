package clp.run.cel.log;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Cfunction {

  //=== Attributes =============================================================

    ACTIVE("activated"),
    INACTIVE("deactivated"),
;

    String val;

    private Cfunction(String val) {
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
