package clp.run.msc;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Out {

  //=== Attributes =============================================================

    LOG("LOG"),
    ON("ON"),
    OFF("OFF"),
;

    String val;

    private Out(String val) {
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
