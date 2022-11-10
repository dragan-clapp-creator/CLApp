package clp.run.res.ui;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum IsPassword {

  //=== Attributes =============================================================

    PASS("PASS"),
;

    String val;

    private IsPassword(String val) {
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
