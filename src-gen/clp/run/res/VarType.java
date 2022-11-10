package clp.run.res;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum VarType {

  //=== Attributes =============================================================

    TBOOL("BOOL"),
    TFLOAT("FLOAT"),
    TINT("INT"),
    TLONG("LONG"),
    TDATE("DATE"),
    TTIME("TIME"),
    TREF("REF"),
    THASH("HASH"),
    TSTRING("STRING"),
    TWEB("WEB"),
    TGRAPH("GRAPH"),
    TWEAVER("WEAVER"),
    TUI("UI"),
    TEVENT("EVENT"),
;

    String val;

    private VarType(String val) {
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
