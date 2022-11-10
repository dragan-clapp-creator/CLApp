package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class EVariable  implements Setting, java.io.Serializable {

  private static final long serialVersionUID = 430L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TEVENT;
  private String name;
  private boolean initial;


  //=== Constructor ============================================================

  public EVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTEVENT() {
    return TEVENT;
  }

  public void setTEVENT(clp.run.res.VarType x) {
    this.TEVENT = x;
  }

  //----------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public boolean getInitial() {
    return initial;
  }

  public void setInitial(boolean x) {
    this.initial = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(SettingVisitor visitor) {
    visitor.visitEVariable(this);
  }



}
