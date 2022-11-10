package clp.run.cel.java;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class JavaCallNew  implements JavaStatementInterface, java.io.Serializable {

  private static final long serialVersionUID = 295L;


  //=== Attributes =============================================================

  private clp.run.cel.java.ClassName className;
  private boolean isPass;
  private boolean isJavaExportPart;
  private clp.run.cel.java.JavaExportPart javaExportPart;
  private boolean isGet;
  private String variable;
  private boolean isVariable;


  //=== Constructor ============================================================

  public JavaCallNew() {
  }

  //=== Methods ================================================================

  public clp.run.cel.java.ClassName getClassName() {
    return className;
  }

  public void setClassName(clp.run.cel.java.ClassName x) {
    this.className = x;
  }

  //----------------------------------------------------------------------------

  public boolean isPass() {
    return isPass;
  }

  public void setIsPass(boolean x) {
    this.isPass = x;
  }

  //----------------------------------------------------------------------------

  public boolean isJavaExportPart() {
    return isJavaExportPart;
  }

  public void setIsJavaExportPart(boolean x) {
    this.isJavaExportPart = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.java.JavaExportPart getJavaExportPart() {
    return javaExportPart;
  }

  public void setJavaExportPart(clp.run.cel.java.JavaExportPart x) {
    this.javaExportPart = x;
  }

  //----------------------------------------------------------------------------

  public boolean isGet() {
    return isGet;
  }

  public void setIsGet(boolean x) {
    this.isGet = x;
  }

  //----------------------------------------------------------------------------

  public String getVariable() {
    return variable;
  }

  public void setVariable(String x) {
    this.variable = x;
  }

  //----------------------------------------------------------------------------


  public boolean isVariable() {
    return isVariable;
  }

  public void setIsVariable(boolean x) {
    this.isVariable = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(JavaStatementInterfaceVisitor visitor) {
    visitor.visitJavaCallNew(this);
  }



}
