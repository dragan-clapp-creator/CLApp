package clp.run.msc;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum MscTaskName {

  //=== Attributes =============================================================

    DEBUGGER("DEBUGGER"),
    JAVA_ENQUEUER("JAVA_ENQUEUER"),
    EXCHANGER("EXCHANGER"),
    SCHEDULER("SCHEDULER"),
;

    String val;

    private MscTaskName(String val) {
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
