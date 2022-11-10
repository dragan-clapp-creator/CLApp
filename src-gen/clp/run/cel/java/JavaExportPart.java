package clp.run.cel.java;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class JavaExportPart  implements java.io.Serializable {

  private static final long serialVersionUID = 297L;

  //=== Attributes =============================================================

  private clp.run.cel.exp.Expression expression;
  private char ckey;
  private java.util.ArrayList<clp.run.cel.exp.Expression> expressions = new java.util.ArrayList<clp.run.cel.exp.Expression>();


  //=== Constructor ============================================================

  public JavaExportPart() {
  }

  //=== Methods ================================================================

  public clp.run.cel.exp.Expression getExpression() {
    return expression;
  }

  public void setExpression(clp.run.cel.exp.Expression x) {
    this.expression = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasExpressions() {
    return expressions != null && !expressions.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.exp.Expression> getExpressions() {
    return expressions;
  }

  public void setExpressions(java.util.ArrayList<clp.run.cel.exp.Expression> x) {
    expressions = x;
  }

  public void addExpression(clp.run.cel.exp.Expression x) {
    expressions.add( x );
  }

  public void removeExpression(clp.run.cel.exp.Expression x) {
    expressions.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
