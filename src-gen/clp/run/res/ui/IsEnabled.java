package clp.run.res.ui;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum IsEnabled {

  //=== Attributes =============================================================

    ENABLED("ENABLED"),
;

    String val;

    private IsEnabled(String val) {
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
