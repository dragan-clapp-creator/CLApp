package clapp.run.ui.elt.api;

import clapp.run.util.ResourceUtility;
import clp.run.res.Event;
import clp.run.res.Variable;

public abstract class UIRefInfo extends UIInitInfo {

  private static final long serialVersionUID = -8183535840427529736L;

  private Event refEvent;
  private Variable refVariable;
  private String refName;


  public UIRefInfo() {
    super();
  }

  public Class<?> getVariableType() {
    if (getReference() == null) {
      return null;
    }
    return ResourceUtility.getInstance().getClass(getReference());
  }

  public void setReference(Variable v) {
    refVariable = v;
  }

  public void setReference(Variable v, int i) {
    refVariable = v;
  }

  public Variable getReference() {
    return refVariable;
  }

  public void setReferenceName(String name) {
    refName = name;
  }

  public String getRefName() {
    return refName;
  }

  public void setEventReference(Event ev) {
    refEvent = ev;
  }

  /**
   * @return the refEvent
   */
  public Event getRefEvent() {
    return refEvent;
  }
}
