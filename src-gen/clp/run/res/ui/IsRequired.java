package clp.run.res.ui;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum IsRequired {

  //=== Attributes =============================================================

    REQUIRED("REQUIRED"),
;

    String val;

    private IsRequired(String val) {
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
