package clp.run.cel.log;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Vfunction {

  //=== Attributes =============================================================

    UP("isSetUp"),
    DOWN("isSetDown"),
;

    String val;

    private Vfunction(String val) {
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
