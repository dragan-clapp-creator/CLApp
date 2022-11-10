package clp.run.cel.java;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class JavaStatement  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 293L;


  //=== Attributes =============================================================

  private clp.run.cel.java.JavaStatementInterface javaStatementInterface;


  //=== Constructor ============================================================

  public JavaStatement() {
  }

  //=== Methods ================================================================

  public clp.run.cel.java.JavaStatementInterface getJavaStatementInterface() {
    return javaStatementInterface;
  }

  public void setJavaStatementInterface(clp.run.cel.java.JavaStatementInterface x) {
    this.javaStatementInterface = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitJavaStatement(this);
  }



}
