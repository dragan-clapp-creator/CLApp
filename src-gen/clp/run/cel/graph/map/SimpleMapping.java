package clp.run.cel.graph.map;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleMapping  implements Mapping, java.io.Serializable {

  private static final long serialVersionUID = 332L;


  //=== Attributes =============================================================

  private clp.run.cel.graph.map.MapOperator mapOperator;
  private clp.run.cel.graph.map.GraphMapType graphMapType;


  //=== Constructor ============================================================

  public SimpleMapping() {
  }

  //=== Methods ================================================================

  public clp.run.cel.graph.map.MapOperator getMapOperator() {
    return mapOperator;
  }

  public void setMapOperator(clp.run.cel.graph.map.MapOperator x) {
    this.mapOperator = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.graph.map.GraphMapType getGraphMapType() {
    return graphMapType;
  }

  public void setGraphMapType(clp.run.cel.graph.map.GraphMapType x) {
    this.graphMapType = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(MappingVisitor visitor) {
    visitor.visitSimpleMapping(this);
  }



}
