package clp.run.cel.dom;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum SysExp {

  //=== Attributes =============================================================

    ACTIVE("isActive"),
    INACTIVE("isInactive"),
;

    String val;

    private SysExp(String val) {
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
