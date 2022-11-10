package clp.run.cel.dom;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SysStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 315L;


  //=== Attributes =============================================================

  private clp.run.cel.dom.SysCommand sysCommand;
  private clp.run.cel.dom.SysExp sysExp;
  private clp.run.cel.dom.LoadMarks loadMarks;


  //=== Constructor ============================================================

  public SysStatement() {
  }

  //=== Methods ================================================================

  public clp.run.cel.dom.SysCommand getSysCommand() {
    return sysCommand;
  }

  public void setSysCommand(clp.run.cel.dom.SysCommand x) {
    this.sysCommand = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.dom.SysExp getSysExp() {
    return sysExp;
  }

  public void setSysExp(clp.run.cel.dom.SysExp x) {
    this.sysExp = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.dom.LoadMarks getLoadMarks() {
    return loadMarks;
  }

  public void setLoadMarks(clp.run.cel.dom.LoadMarks x) {
    this.loadMarks = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitSysStatement(this);
  }



}
