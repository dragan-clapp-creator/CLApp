package clp.run.cel.asm;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class AssignStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 319L;


  //=== Attributes =============================================================

  private clp.run.cel.asm.VarAssignment varAssignment;
  private clp.run.cel.asm.BoolAssignment boolAssignment;


  //=== Constructor ============================================================

  public AssignStatement() {
  }

  //=== Methods ================================================================

  public clp.run.cel.asm.VarAssignment getVarAssignment() {
    return varAssignment;
  }

  public void setVarAssignment(clp.run.cel.asm.VarAssignment x) {
    this.varAssignment = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.asm.BoolAssignment getBoolAssignment() {
    return boolAssignment;
  }

  public void setBoolAssignment(clp.run.cel.asm.BoolAssignment x) {
    this.boolAssignment = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitAssignStatement(this);
  }



}
