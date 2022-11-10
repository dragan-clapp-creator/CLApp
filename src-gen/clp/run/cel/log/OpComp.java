package clp.run.cel.log;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum OpComp {

  //=== Attributes =============================================================

    EQ("="),
    DIFF("#"),
    SUP(">"),
    INF("<"),
;

    String val;

    private OpComp(String val) {
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
