package clp.run.res;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum TermOperator {

  //=== Attributes =============================================================

    PLUS("+"),
    MINUS("-"),
;

    String val;

    private TermOperator(String val) {
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
