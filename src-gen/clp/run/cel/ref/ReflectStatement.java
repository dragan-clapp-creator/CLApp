package clp.run.cel.ref;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ReflectStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 288L;


  //=== Attributes =============================================================

  private clp.run.cel.ref.ReflectObject reflectObject;
  private clp.run.msc.OutputTarget outputTarget;


  //=== Constructor ============================================================

  public ReflectStatement() {
  }

  //=== Methods ================================================================

  public clp.run.cel.ref.ReflectObject getReflectObject() {
    return reflectObject;
  }

  public void setReflectObject(clp.run.cel.ref.ReflectObject x) {
    this.reflectObject = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.msc.OutputTarget getOutputTarget() {
    return outputTarget;
  }

  public void setOutputTarget(clp.run.msc.OutputTarget x) {
    this.outputTarget = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitReflectStatement(this);
  }



}
