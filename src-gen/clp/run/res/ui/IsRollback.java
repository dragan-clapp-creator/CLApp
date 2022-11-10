package clp.run.res.ui;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum IsRollback {

  //=== Attributes =============================================================

    ROLLBACK("ROLLBACK"),
;

    String val;

    private IsRollback(String val) {
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
