package clp.run.cel.dom;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ExecStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 292L;


  //=== Attributes =============================================================

  private String filename;


  //=== Constructor ============================================================

  public ExecStatement() {
  }

  //=== Methods ================================================================

  public String getFilename() {
    return filename;
  }

  public void setFilename(String x) {
    this.filename = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitExecStatement(this);
  }



}
