package clp.run.cel.graph.map;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum MapOperator {

  //=== Attributes =============================================================

    GETS("<"),
    SETS(">"),
    EXCHANGES("="),
;

    String val;

    private MapOperator(String val) {
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
