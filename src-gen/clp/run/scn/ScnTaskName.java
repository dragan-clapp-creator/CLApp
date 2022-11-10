package clp.run.scn;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum ScnTaskName {

  //=== Attributes =============================================================

    ACTIVATOR("ACTIVATOR"),
    EXECUTOR("EXECUTOR"),
    DEACTIVATOR("DEACTIVATOR"),
;

    String val;

    private ScnTaskName(String val) {
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
