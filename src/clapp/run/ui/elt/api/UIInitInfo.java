package clapp.run.ui.elt.api;

abstract public class UIInitInfo extends UIInfo {

  private static final long serialVersionUID = -4577480707042198290L;

  private Object value;

  public UIInitInfo() {
    super();
  }

  public void setInitialValue(Object val) {
    value = val;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "UIInitInfo [value=" + value + "]";
  }
}
