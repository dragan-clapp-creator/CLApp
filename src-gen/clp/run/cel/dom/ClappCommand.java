package clp.run.cel.dom;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum ClappCommand {

  //=== Attributes =============================================================

    WEBREFLECT("WEBREFLECT"),
;

    String val;

    private ClappCommand(String val) {
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
