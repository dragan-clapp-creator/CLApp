package clp.run.cel.asm;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class BoolAssignment extends AssignStatement implements java.io.Serializable {

  private static final long serialVersionUID = 322L;

  //=== Attributes =============================================================

  private boolean isModifier;
  private clp.run.cel.asm.Modifier modifier;
  private boolean isKeeping;
  private String var;
  private boolean isIfclause;
  private clp.run.cel.asm.Ifclause ifclause;


  //=== Constructor ============================================================

  public BoolAssignment() {
  }

  //=== Methods ================================================================

  public boolean isModifier() {
    return isModifier;
  }

  public void setIsModifier(boolean x) {
    this.isModifier = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.asm.Modifier getModifier() {
    return modifier;
  }

  public void setModifier(clp.run.cel.asm.Modifier x) {
    this.modifier = x;
  }

  //----------------------------------------------------------------------------

  public boolean isKeeping() {
    return isKeeping;
  }

  public void setIsKeeping(boolean x) {
    this.isKeeping = x;
  }

  //----------------------------------------------------------------------------

  public String getVar() {
    return var;
  }

  public void setVar(String x) {
    this.var = x;
  }

  //----------------------------------------------------------------------------

  public boolean isIfclause() {
    return isIfclause;
  }

  public void setIsIfclause(boolean x) {
    this.isIfclause = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.asm.Ifclause getIfclause() {
    return ifclause;
  }

  public void setIfclause(clp.run.cel.asm.Ifclause x) {
    this.ifclause = x;
  }

  //----------------------------------------------------------------------------



}
