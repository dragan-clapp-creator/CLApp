package clp.run.cel.dom;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum SysCommand {

  //=== Attributes =============================================================

    EXIT("exit"),
    PROCESS_INBOUND("processInbound"),
;

    String val;

    private SysCommand(String val) {
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
