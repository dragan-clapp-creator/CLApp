package clapp.run.ui.elt;

import java.util.ArrayList;
import java.util.List;

import clapp.run.ui.ClpVisualizer;
import clapp.run.ui.Window;
import clapp.run.ui.elt.api.UIInfo;
import clapp.run.ui.elt.api.UISetInfo;
import clapp.run.ui.vis.ClpUIBundleVisitor;
import clp.run.res.Resources;
import clp.run.res.ui.UiBundle;

public class BundleInfo extends UISetInfo {

  private static final long serialVersionUID = -7418015960582834159L;

  private String title;
  private List<ButtonInfo> buttonsInfo;
  private Resources res;
  private Window panel;

  public BundleInfo(String t) {
    title = t;
    buttonsInfo = new ArrayList<>();
  }

  /**
   * 
   * @param uiBundle
   * @return
   */
  public UISetInfo parseChildren(UiBundle uiBundle, ClpVisualizer visualizer) {
    res = visualizer.getRes();
    ClpUIBundleVisitor vis = new ClpUIBundleVisitor(visualizer);
    uiBundle.accept(vis);
    return vis.getInfo();
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return "BundleInfo [title=" + title + "]";
  }

  public boolean checkGlobalConsistency(Window win) {
    return checkAndUpdate(win);
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

  public ButtonInfo getSelectedButtonInfo() {
    for (ButtonInfo bi : buttonsInfo) {
      if (bi.isSelected()) {
        return bi;
      }
    }
    return null;
  }

  public Resources getRes() {
    return res;
  }

  public void setFrameReference(Window win) {
    panel = win;
  }

  public void releaseFrame() {
    if (panel != null) {
      panel.releaseFrame();
    }
  }

  @Override
  public void setupVariable(Resources res) {
  }

  public void addButtonInfo(ButtonInfo button, boolean b) {
    button.setIsRollback(b);
    buttonsInfo.add(button);
  }
}
