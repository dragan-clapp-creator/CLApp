package clp.run.cel.graph.map;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CompoundMapping  implements Mapping, java.io.Serializable {

  private static final long serialVersionUID = 340L;


  //=== Attributes =============================================================

  private String node;
  private clp.run.cel.graph.map.SimpleMapping simpleMapping;
  private char ckey;
  private boolean isCompoundMapping;
  private clp.run.cel.graph.map.CompoundMapping compoundMapping;


  //=== Constructor ============================================================

  public CompoundMapping() {
  }

  //=== Methods ================================================================

  public String getNode() {
    return node;
  }

  public void setNode(String x) {
    this.node = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.graph.map.SimpleMapping getSimpleMapping() {
    return simpleMapping;
  }

  public void setSimpleMapping(clp.run.cel.graph.map.SimpleMapping x) {
    this.simpleMapping = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean isCompoundMapping() {
    return isCompoundMapping;
  }

  public void setIsCompoundMapping(boolean x) {
    this.isCompoundMapping = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.graph.map.CompoundMapping getCompoundMapping() {
    return compoundMapping;
  }

  public void setCompoundMapping(clp.run.cel.graph.map.CompoundMapping x) {
    this.compoundMapping = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(MappingVisitor visitor) {
    visitor.visitCompoundMapping(this);
  }



}
