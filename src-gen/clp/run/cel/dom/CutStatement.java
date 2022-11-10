package clp.run.cel.dom;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CutStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 291L;


  //=== Attributes =============================================================

  private String node;


  //=== Constructor ============================================================

  public CutStatement() {
  }

  //=== Methods ================================================================

  public String getNode() {
    return node;
  }

  public void setNode(String x) {
    this.node = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitCutStatement(this);
  }



}
