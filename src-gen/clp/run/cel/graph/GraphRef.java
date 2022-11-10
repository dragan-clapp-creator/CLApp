package clp.run.cel.graph;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GraphRef  implements java.io.Serializable {

  private static final long serialVersionUID = 327L;

  //=== Attributes =============================================================

  private String gname;
  private char ckey;
  private String node;
  private boolean isNode;
  private int element;
  private boolean isElement;
  private clp.run.res.graph.Graph coreGraph;


  //=== Constructor ============================================================

  public GraphRef() {
  }

  //=== Methods ================================================================

  public String getGname() {
    return gname;
  }

  public void setGname(String x) {
    this.gname = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public String getNode() {
    return node;
  }

  public void setNode(String x) {
    this.node = x;
  }

  //----------------------------------------------------------------------------


  public boolean isNode() {
    return isNode;
  }

  public void setIsNode(boolean x) {
    this.isNode = x;
  }

  //----------------------------------------------------------------------------

  public int getElement() {
    return element;
  }

  public void setElement(int x) {
    this.element = x;
  }

  //----------------------------------------------------------------------------


  public boolean isElement() {
    return isElement;
  }

  public void setIsElement(boolean x) {
    this.isElement = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.graph.Graph getCoreGraph() {
    return coreGraph;
  }

  public void setCoreGraph(clp.run.res.graph.Graph x) {
    this.coreGraph = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
