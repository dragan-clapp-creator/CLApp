package clapp.run.ui.elt.api;

import java.awt.Component;
import java.util.ArrayList;

import clapp.run.ui.Window;

public abstract class UISetInfo extends UIInfo {

  private static final long serialVersionUID = -8068295380901074438L;

  private ArrayList<UIInfo> children;

  public UISetInfo() {
    super();
    children = new ArrayList<UIInfo>();
  }

  public void addTochildren(UIInfo child) {
    children.add(child);
  }

  public ArrayList<UIInfo> getChildren() {
    return children;
  }

  @Override
  public Component getUIElement() {
    return null;
  }

  @Override
  public boolean checkAndUpdate(Window win) {
    boolean isok = true;
    for (UIInfo info : getChildren()) {
      if (!info.checkAndUpdate(win)) {
        isok &= false;
      }
    }
    return isok;
  }

  @Override
  public void rollBack() {
    for (UIInfo info : getChildren()) {
      info.rollBack();
    }
  }
}
