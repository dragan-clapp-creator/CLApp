package clp.run.res.ui;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Literal  implements CstOrVarIdentifier, java.io.Serializable {

  private static final long serialVersionUID = 202L;


  //=== Attributes =============================================================

  private String value;


  //=== Constructor ============================================================

  public Literal() {
  }

  //=== Methods ================================================================

  public String getValue() {
    return value;
  }

  public void setValue(String x) {
    this.value = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CstOrVarIdentifierVisitor visitor) {
    visitor.visitLiteral(this);
  }



}
