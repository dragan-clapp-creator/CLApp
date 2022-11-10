package clapp.weave.res;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Visibility {

  //=== Attributes =============================================================

    Private("private"),
    Protected("protected"),
    Public("public"),
;

    String val;

    private Visibility(String val) {
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
