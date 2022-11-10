package clapp.weave.res;

/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public enum Jmodifier {

  //=== Attributes =============================================================

    PRIVATE("PRIVATE", "PRIVATE"),
    PROTECTED("PROTECTED", "PROTECTED"),
    PUBLIC("PUBLIC", "PUBLIC");

    String id;
    String val;

    private Jmodifier(String id, String val) {
      this.id=id;
      this.val=val;
    }

  //=== Methods ================================================================

    public String getId(){
      return id;
    }

    public String getVal(){
      return val;
    }

    public String toString(){
      return id + " = " + val;
    }
}
