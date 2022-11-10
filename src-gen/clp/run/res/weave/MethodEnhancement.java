package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MethodEnhancement  implements java.io.Serializable {

  private static final long serialVersionUID = 163L;

  //=== Attributes =============================================================

  private clp.run.res.weave.CstOrVar methodTarget;
  private char ckey;
  private boolean isArgListApprox;
  private clp.run.res.weave.ArgListApprox argListApprox;
  private boolean isPlace;
  private boolean isLocation;
  private clp.run.res.weave.Location loadLoc;
  private java.util.ArrayList<clp.run.res.weave.MethodAddOn> methodAddOns = new java.util.ArrayList<clp.run.res.weave.MethodAddOn>();


  //=== Constructor ============================================================

  public MethodEnhancement() {
  }

  //=== Methods ================================================================

  public clp.run.res.weave.CstOrVar getMethodTarget() {
    return methodTarget;
  }

  public void setMethodTarget(clp.run.res.weave.CstOrVar x) {
    this.methodTarget = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean isArgListApprox() {
    return isArgListApprox;
  }

  public void setIsArgListApprox(boolean x) {
    this.isArgListApprox = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.weave.ArgListApprox getArgListApprox() {
    return argListApprox;
  }

  public void setArgListApprox(clp.run.res.weave.ArgListApprox x) {
    this.argListApprox = x;
  }

  //----------------------------------------------------------------------------

  public boolean isPlace() {
    return isPlace;
  }

  public void setIsPlace(boolean x) {
    this.isPlace = x;
  }

  //----------------------------------------------------------------------------

  public boolean isLocation() {
    return isLocation;
  }

  public void setIsLocation(boolean x) {
    this.isLocation = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.weave.Location getLoadLoc() {
    return loadLoc;
  }

  public void setLoadLoc(clp.run.res.weave.Location x) {
    this.loadLoc = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasMethodAddOns() {
    return methodAddOns != null && !methodAddOns.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.weave.MethodAddOn> getMethodAddOns() {
    return methodAddOns;
  }

  public void setMethodAddOns(java.util.ArrayList<clp.run.res.weave.MethodAddOn> x) {
    methodAddOns = x;
  }

  public void addMethodAddOn(clp.run.res.weave.MethodAddOn x) {
    methodAddOns.add( x );
  }

  public void removeMethodAddOn(clp.run.res.weave.MethodAddOn x) {
    methodAddOns.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
