package clapp.run.ui.vis;

import clapp.run.util.ResourceUtility;
import clp.run.res.Resources;
import clp.run.res.Variable;
import clp.run.res.weave.CstOrVar;

public class ClpCstOrVarVisitor {

  private String title;
  private Resources res;

  public ClpCstOrVarVisitor(Resources res) {
    this.res = res;
  }

  public String getTitle() {
    return title;
  }

  public void accept(CstOrVar x) {
    if (x.getCst() != null) {
      title = x.getCst();
    }
    else {
      Variable v = ResourceUtility.getInstance().getVariable(res, x.getId());
      title = (String) ResourceUtility.getInstance().getValue(v); 
    }
  }
}
