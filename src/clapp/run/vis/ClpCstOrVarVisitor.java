package clapp.run.vis;

import clp.run.res.weave.CstOrVar;

public class ClpCstOrVarVisitor {

  private String name;

  public void visitCstName(String x) {
    name = x;
  }

  public void visitVRef(String x) {
    // TODO Auto-generated method stub

  }

  public String getName() {
    return name;
  }

  public void accept(CstOrVar x) {
    if (x.getCst() != null) {
      visitCstName(x.getCst());
    }
    else {
      visitVRef(x.getId());
    }
  }

}
