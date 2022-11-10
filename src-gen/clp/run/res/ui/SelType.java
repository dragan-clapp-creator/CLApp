package clp.run.res.ui;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum SelType {

  //=== Attributes =============================================================

    LINE("LINE"),
    COLUMN("COLUMN"),
    CELL("CELL"),
;

    String val;

    private SelType(String val) {
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
