package clapp.weave.res.vis;

import java.util.ArrayList;
import java.util.List;

import clp.run.res.weave.ArgList;
import clp.run.res.weave.ArgListApproxVisitor;
import clp.run.res.weave.ArgListCount;
import clp.run.res.weave.ArgumentPair;

public class ClpArgListApproxVisitor implements ArgListApproxVisitor {

  private List<ArgumentPair> args;
  private int count;

  public ClpArgListApproxVisitor() {
    super();
    count = -1;
  }

  public void visitArgList(ArgList argList) {
    args = new ArrayList<ArgumentPair>();
    args.add(argList.getArgumentPair());
    args.addAll(argList.getArgumentPairs());
  }

  public void visitArgListCount(ArgListCount argListCount) {
    count = argListCount.getCount();
  }

  public boolean isCount() {
    return args == null && count >= 0;
  }

  public List<ArgumentPair> getArglist() {
    return args;
  }

  public int getCount() {
    return count;
  }
}
