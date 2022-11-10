package clapp.run.ui.elt;

import clapp.run.ui.elt.api.UIInfo;
import clapp.run.ui.elt.api.UISetInfo;
import clp.run.res.Resources;

public class LineInfo extends UISetInfo {

  private static final long serialVersionUID = 3104306715276677254L;

  private String key;

  public LineInfo(String key) {
    super();
    this.key = key;
  }

  @Override
  public String toString() {
    return "LineInfo [key=" + key + "]";
  }

  @Override
  public void setupVariable(Resources res) {
    for (UIInfo info : getChildren()) {
      info.setupVariable(res);
    }
  }
}
