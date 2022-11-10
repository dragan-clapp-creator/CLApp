package clp.run.cel.graph.map;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ClappVar  implements GraphMapType, java.io.Serializable {

  private static final long serialVersionUID = 339L;


  //=== Attributes =============================================================

  private String name;


  //=== Constructor ============================================================

  public ClappVar() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(GraphMapTypeVisitor visitor) {
    visitor.visitClappVar(this);
  }



}
