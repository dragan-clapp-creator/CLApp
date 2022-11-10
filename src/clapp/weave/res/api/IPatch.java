package clapp.weave.res.api;

import org.apache.bcel.generic.InstructionList;


public interface IPatch {
  public int patch(IWeaverForLocation w, InstructionList added);
  public void setInitialTarget();
  public int getTarget();
  public void setTarget(int target);
}
