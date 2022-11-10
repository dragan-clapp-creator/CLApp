package clp.run.res.weave;

import clp.run.res.Variable;
import clp.run.res.VariableVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class WeaveVar  implements Variable, java.io.Serializable {

  private static final long serialVersionUID = 159L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TWEAVER;
  private String name;
  private clp.run.res.weave.CstOrVar pack;
  private clp.run.res.weave.CstOrVar clazz;
  private java.util.ArrayList<clp.run.res.weave.MethodEnhancement> methodEnhancements = new java.util.ArrayList<clp.run.res.weave.MethodEnhancement>();


  //=== Constructor ============================================================

  public WeaveVar() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTWEAVER() {
    return TWEAVER;
  }

  public void setTWEAVER(clp.run.res.VarType x) {
    this.TWEAVER = x;
  }

  //----------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.weave.CstOrVar getPack() {
    return pack;
  }

  public void setPack(clp.run.res.weave.CstOrVar x) {
    this.pack = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.weave.CstOrVar getClazz() {
    return clazz;
  }

  public void setClazz(clp.run.res.weave.CstOrVar x) {
    this.clazz = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasMethodEnhancements() {
    return methodEnhancements != null && !methodEnhancements.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.weave.MethodEnhancement> getMethodEnhancements() {
    return methodEnhancements;
  }

  public void setMethodEnhancements(java.util.ArrayList<clp.run.res.weave.MethodEnhancement> x) {
    methodEnhancements = x;
  }

  public void addMethodEnhancement(clp.run.res.weave.MethodEnhancement x) {
    methodEnhancements.add( x );
  }

  public void removeMethodEnhancement(clp.run.res.weave.MethodEnhancement x) {
    methodEnhancements.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(VariableVisitor visitor) {
    visitor.visitWeaveVar(this);
  }



}
