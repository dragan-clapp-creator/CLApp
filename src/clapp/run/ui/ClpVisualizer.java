package clapp.run.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import clapp.run.ui.elt.BundleInfo;
import clapp.run.ui.elt.ButtonInfo;
import clapp.run.ui.elt.api.UIInfo;
import clapp.run.ui.vis.ClpCstOrVarVisitor;
import clp.run.res.Resources;
import clp.run.res.ui.UiBundle;
import clp.run.res.ui.UiVar;

public class ClpVisualizer implements Serializable {

  private static final long serialVersionUID = 3306989729370022268L;

  private static Hashtable<UiVar, ClpVisualizer> visualizers = new Hashtable<>();

  private static boolean isBuisy;

  /**
   * @return the isBuisy
   */
  private static synchronized boolean isBuisy() {
    return isBuisy;
  }

  /**
   * @param isBuisy the isBuisy to set
   */
  public static synchronized void setBuisy(boolean isBuisy) {
    ClpVisualizer.isBuisy = isBuisy;
  }

  public static ClpVisualizer getRegistered(UiVar v) {
    while (isBuisy) {
      try {
        Thread.sleep(135);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return visualizers.get(v);
  }

  public static void stopAll() {
    for (ClpVisualizer v : visualizers.values()) {
      v.setStop(true);
    }
    visualizers.clear();
  }

  private Resources res;

  private Hashtable<String, UIInfo> displayInfo;

  private BundleInfo uiroot;

  private boolean isStop;

  private boolean isKeepAlive;


  /**
   * CONSTRUCTOR: visualizer will be initialized using {@link UiVar} info
   * 
   * @param var
   * @param isKeepAlive 
   */
  public ClpVisualizer(Resources res, UiVar var, boolean isKeepAlive) {
    this.res = res;
    String title = initializeTitle(var);
    initializeUIElements(var, title);
    visualizers.put(var, this);
    this.isKeepAlive = isKeepAlive;
  }

  /**
   * the whole visualization/processing will be made here
   */
  public void handleVisualization() {
    if (isBuisy()) {
      return;
    }
    setBuisy(true);
    resetOtherVisualizers();
    Window win = new Window(uiroot.getTitle());
    win.initialize(uiroot, res);
    uiroot.setFrameReference(win);
    win.showFrame(uiroot.getTitle());
    setStop(false);
    while (!isStopped()) {
      ButtonInfo ib = uiroot.getSelectedButtonInfo();
      if (ib != null) {
        if (ib.isRollback()) {
          uiroot.rollBack();
          ib.setVariableValue();
          setStop(true);
        }
        else if (!uiroot.checkGlobalConsistency(win)) {
          win.setMessage("<p><span style=\"color: #7f0000\">consistency check failed!</span>");
          ib.setSelected(false);
          setStop(false);
          win.showFrame(uiroot.getTitle());
        }
        else {
          ib.setVariableValue();
          setStop(true);
        }
      }
    }
    win.disposeFrame();
    setBuisy(false);
  }

  //
  private void resetOtherVisualizers() {
    UiVar key = null;
    for (UiVar var : visualizers.keySet()) {
      ClpVisualizer visu = visualizers.get(var);
      if (visu != this) {
        visu.resetValues();
      }
      else {
        key = var;
      }
    }
    visualizers.clear();
    if (key != null) {
      visualizers.put(key, this);
    }
  }

  //
  private void resetValues() {
    for (UIInfo info : uiroot.getChildren()) {
      if (info instanceof BundleInfo) {
        BundleInfo group = (BundleInfo)info;
        resetGroup(group.getChildren());
      }
    }
  }

  //
  private void resetGroup(ArrayList<UIInfo> children) {
    System.out.println();
    for (UIInfo info : children) {
      info.setupVariable(res);
    }
  }

  //
  private String initializeTitle(UiVar var) {
    if (var.isTitle()) {
      ClpCstOrVarVisitor vis = new ClpCstOrVarVisitor(res);
      vis.accept(var.getTitle());
      if ( vis.getTitle() != null ) {
        return vis.getTitle();
      }
    }
    return var.getName();
  }

  //
  private void initializeUIElements(UiVar var, String title) {
    displayInfo = new Hashtable<String, UIInfo>();
    ArrayList<UiBundle> elts = new ArrayList<>();
    UiBundle elt = var.getUiBundle();
    if (elt != null) {
      elts.add(elt);
    }
    elts.addAll(var.getUiBundles());
    parse(elts, title);
  }

  //
  private void parse(ArrayList<UiBundle> elts, String title) {
    uiroot = new BundleInfo(title);
    for (UiBundle elt : elts) {
      uiroot.addTochildren(uiroot.parseChildren(elt, this));
    }
  }

  public void setDisplayInfo(String key, UIInfo value) {
    this.displayInfo.put(key, value);
  }

  public Resources getRes() {
    return res;
  }

  public BundleInfo getRoot() {
    return uiroot;
  }

  synchronized public void interrupt() {
    isStop = true;
  }

  public synchronized boolean isStopped() {
    return isStop;
  }

  public synchronized void setStop(boolean isStop) {
    this.isStop = isStop;
  }

  public synchronized boolean isInterruptable() {
    return !isKeepAlive;
  }
}
