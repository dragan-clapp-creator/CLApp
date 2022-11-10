package clp.run.cel.asm;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Modifier {

  //=== Attributes =============================================================

    NOT("!"),
    UP("^"),
    DOWN("v"),
;

    String val;

    private Modifier(String val) {
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
