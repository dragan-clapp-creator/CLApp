package clapp.graph;

public class GraphContext {
  private String name;
  private String node;
  private String corenode;
  private int element = -1;
  private JavaContext javaContext;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getNode() {
    return node;
  }
  public void setNode(String node) {
    this.node = node;
  }
  public String getCoreNode() {
    return corenode;
  }
  public void setCoreNode(String node) {
    this.corenode = node;
  }
  public int getElement() {
    return element;
  }
  public void setElement(int element) {
    this.element = element;
  }
  public JavaContext getJavaContext() {
    return javaContext;
  }
  public void setJavaContext(JavaContext jc) {
    this.javaContext = jc;
  }
}
