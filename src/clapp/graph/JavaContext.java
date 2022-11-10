package clapp.graph;

public class JavaContext {
  private String clname;
  private String method;
  private String[] expressions;
  private String resultVariableName;

  public String getClname() {
    return clname;
  }
  public void setClname(String clname) {
    this.clname = clname;
  }
  public String getMethod() {
    return method;
  }
  public void setMethod(String method) {
    this.method = method;
  }
  public String[] getExpressions() {
    return expressions;
  }
  public void setExpressions(String[] expressions) {
    this.expressions = expressions;
  }
  public String getResultVariableName() {
    return resultVariableName;
  }
  public void setResultVariableName(String result) {
    this.resultVariableName = result;
  }
}
