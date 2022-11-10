package clp.run.cel.dom;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ClappStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 313L;


  //=== Attributes =============================================================

  private clp.run.cel.dom.ClappCommand clappCommand;
  private String webName;


  //=== Constructor ============================================================

  public ClappStatement() {
  }

  //=== Methods ================================================================

  public clp.run.cel.dom.ClappCommand getClappCommand() {
    return clappCommand;
  }

  public void setClappCommand(clp.run.cel.dom.ClappCommand x) {
    this.clappCommand = x;
  }

  //----------------------------------------------------------------------------

  public String getWebName() {
    return webName;
  }

  public void setWebName(String x) {
    this.webName = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitClappStatement(this);
  }



}
