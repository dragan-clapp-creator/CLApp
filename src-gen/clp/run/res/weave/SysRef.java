package clp.run.res.weave;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum SysRef {

  //=== Attributes =============================================================

    CODE("code"),
    INSTRUCTION_LIST("instList"),
;

    String val;

    private SysRef(String val) {
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
