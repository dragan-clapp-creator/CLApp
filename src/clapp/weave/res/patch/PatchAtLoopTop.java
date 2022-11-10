/**
 * 
 */
package clapp.weave.res.patch;

import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;

import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.util.BCELMethodInfo;


/**
 *
 */
public class PatchAtLoopTop extends APatch {

  /**
   * CONSTRUCTOR
   */
  public PatchAtLoopTop(BCELMethodInfo bm, String n, boolean b, int ln) {
    super(bm, n, b, ln);
  }

  /* (non-Javadoc)
   * @see com.sap.clapp.weave.api.IPatch#determineTarget()
   */
  public void setInitialTarget() {
    InstructionHandle[] ihs = getIl().getInstructionHandles();
    setTarget(getLocation(ihs, isInLoop(), getOccurences()));
  }

  //
  int getLocation(InstructionHandle[] ihs, boolean isInLoop, int ln) {
    int pos = getBranchHandle(ihs, ln).getTarget().getPosition();
    if (pos > 0) {
      for (int i=0; i<ihs.length; i++) {
	if (ihs[i].getPosition() == pos) {
	  if (isInLoop) {
	    return i;
	  }
	  for (int j=i-1; j>0; j--) {
	    if (ihs[j].hasTargeters()) {
	      return j;
	    }
	  }
	}
      }
    }
    return 0;
  }

  /* (non-Javadoc)
   * @see com.sap.clapp.weave.api.IPatch#patch(com.sap.clapp.weave.api.IWeaveForLocation)
   */
  public int patch(IWeaverForLocation w, InstructionList added) {
    InstructionHandle ih0 = getIl().getInstructionHandles()[getTarget()];
    added = insertAt(getTarget(), added);
    updateOnLoopTop(ih0, getTarget());
    return added.getLength();
  }

  //
  private void updateOnLoopTop(InstructionHandle ih0, int target) {
    InstructionHandle[] ihs = getIl().getInstructionHandles();
    for (int i=0; i<ihs.length; i++) {
      InstructionHandle ih = ihs[i];
      if (ih instanceof BranchHandle) {
	if (((BranchHandle)ih).getTarget() == ih0) {
	  ((BranchHandle)ih).setTarget(ihs[target]);
	}
      }
    }
  }
}
