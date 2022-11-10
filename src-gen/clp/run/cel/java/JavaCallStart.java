package clp.run.cel.java;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class JavaCallStart  implements JavaStatementInterface, java.io.Serializable {

  private static final long serialVersionUID = 300L;


  //=== Attributes =============================================================

  private boolean isStart;
  private clp.run.cel.java.JavaCall javaCall;


  //=== Constructor ============================================================

  public JavaCallStart() {
  }

  //=== Methods ================================================================

  public boolean isStart() {
    return isStart;
  }

  public void setIsStart(boolean x) {
    this.isStart = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.java.JavaCall getJavaCall() {
    return javaCall;
  }

  public void setJavaCall(clp.run.cel.java.JavaCall x) {
    this.javaCall = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(JavaStatementInterfaceVisitor visitor) {
    visitor.visitJavaCallStart(this);
  }



}
