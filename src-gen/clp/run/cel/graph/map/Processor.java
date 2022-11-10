package clp.run.cel.graph.map;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Processor  implements GraphMapType, java.io.Serializable {

  private static final long serialVersionUID = 335L;


  //=== Attributes =============================================================

  private clp.run.cel.graph.map.CoreProcessor coreProcessor;
  private clp.run.cel.graph.map.JavaProcessor javaProcessor;


  //=== Constructor ============================================================

  public Processor() {
  }

  //=== Methods ================================================================

  public clp.run.cel.graph.map.CoreProcessor getCoreProcessor() {
    return coreProcessor;
  }

  public void setCoreProcessor(clp.run.cel.graph.map.CoreProcessor x) {
    this.coreProcessor = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.graph.map.JavaProcessor getJavaProcessor() {
    return javaProcessor;
  }

  public void setJavaProcessor(clp.run.cel.graph.map.JavaProcessor x) {
    this.javaProcessor = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(GraphMapTypeVisitor visitor) {
    visitor.visitProcessor(this);
  }



}
