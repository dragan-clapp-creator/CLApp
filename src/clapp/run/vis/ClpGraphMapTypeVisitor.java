package clapp.run.vis;

import clapp.run.util.ClpJavaCallCatcher;
import clapp.run.util.ResourceUtility;
import clp.run.cel.graph.map.ClappVar;
import clp.run.cel.graph.map.GraphMapTypeVisitor;
import clp.run.cel.graph.map.Processor;
import clp.run.res.Resources;
import clp.run.res.Variable;

public class ClpGraphMapTypeVisitor implements GraphMapTypeVisitor {

  private Resources res;
  private boolean isClappVariable;
  private Variable var;

  public ClpGraphMapTypeVisitor(Resources res) {
    this.res = res;
  }

  @Override
  public void visitClappVar(ClappVar x) {
    isClappVariable = true;
    var = ResourceUtility.getInstance().getVariable(res, x.getName());
  }

  @Override
  public void visitProcessor(Processor x) {
    isClappVariable = false;
    if (x.getJavaProcessor() != null) {
      ClpJavaCallCatcher cc = new ClpJavaCallCatcher(x.getJavaProcessor().getJavaCall());
      cc.getJavaContext();
    }
    else {
      x.getCoreProcessor().getGnode();
    }
  }

  public boolean isClappVariable() {
    return isClappVariable;
  }

  public Variable getVariable() {
    return var;
  }

}
