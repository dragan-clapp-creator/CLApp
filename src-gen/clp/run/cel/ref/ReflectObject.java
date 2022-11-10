package clp.run.cel.ref;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum ReflectObject {

  //=== Attributes =============================================================

    DATA("DATA"),
    STRUCTURE("STRUCTURE"),
    ACTIVITY("ACTIVITY"),
;

    String val;

    private ReflectObject(String val) {
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
