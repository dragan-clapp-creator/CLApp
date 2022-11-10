package clapp.run.ui.vis;

import java.util.ArrayList;

import clapp.run.ui.ClpVisualizer;
import clapp.run.ui.elt.BundleInfo;
import clapp.run.ui.elt.LineInfo;
import clapp.run.ui.elt.api.UIInitInfo;
import clapp.run.ui.elt.api.UISetInfo;
import clp.run.res.ui.UiBundle;
import clp.run.res.ui.UiBundleVisitor;
import clp.run.res.ui.UiDefType;
import clp.run.res.ui.UiGroup;
import clp.run.res.ui.UiLine;

public class ClpUIBundleVisitor implements UiBundleVisitor {

  private ClpVisualizer visualizer;
  private UISetInfo info;

  public ClpUIBundleVisitor(ClpVisualizer visualizer) {
    this.visualizer = visualizer;
  }

  @Override
  public void visitUiGroup(UiGroup x) {
    BundleInfo bundle = new BundleInfo(x.getTitle());
    bundle.addTochildren(bundle.parseChildren(x.getUiBundle(), visualizer));
    ArrayList<UiBundle> bundles = x.getUiBundles();
    if (bundles != null) {
      for (UiBundle b : bundles) {
        bundle.addTochildren(bundle.parseChildren(b, visualizer));
      }
    }
    info = bundle;
  }

  @Override
  public void visitUiLine(UiLine x) {
    String key = "line"+x.getNumber();
    info = new LineInfo(key);
    visualizer.setDisplayInfo(key, info);
    info.addTochildren( parseFromDefinition(x.getUiDefType()) );
    ArrayList<UiDefType> defs = x.getUiDefTypes();
    if (defs != null) {
      for (UiDefType def : defs) {
        info.addTochildren( parseFromDefinition(def) );
      }
    }
  }

  //
  private UIInitInfo parseFromDefinition(UiDefType uiDefinition) {
    ClpUiDefTypeVisitor vis = new ClpUiDefTypeVisitor(visualizer);
    uiDefinition.accept(vis);
    return vis.getInfo();
  }

  public UISetInfo getInfo() {
    return info;
  }

}
