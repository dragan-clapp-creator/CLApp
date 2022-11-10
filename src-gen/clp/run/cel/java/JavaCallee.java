package clp.run.cel.java;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class JavaCallee  implements java.io.Serializable {

  private static final long serialVersionUID = 303L;

  //=== Attributes =============================================================

  private char ckey;
  private boolean isMethodName;
  private clp.run.cel.java.MethodName methodName;
  private boolean isPass;
  private boolean isJavaExportPart;
  private clp.run.cel.java.JavaExportPart javaExportPart;
  private boolean isGet;
  private String variable;
  private boolean isVariable;


  //=== Constructor ============================================================

  public JavaCallee() {
  }

  //=== Methods ================================================================

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean isMethodName() {
    return isMethodName;
  }

  public void setIsMethodName(boolean x) {
    this.isMethodName = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.java.MethodName getMethodName() {
    return methodName;
  }

  public void setMethodName(clp.run.cel.java.MethodName x) {
    this.methodName = x;
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



}
