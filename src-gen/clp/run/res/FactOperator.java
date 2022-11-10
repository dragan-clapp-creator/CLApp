package clp.run.res;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum FactOperator {

  //=== Attributes =============================================================

    MULTIPLY("*"),
    DIVIDE("/"),
;

    String val;

    private FactOperator(String val) {
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
