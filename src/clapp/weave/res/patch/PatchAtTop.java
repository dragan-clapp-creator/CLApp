package clapp.weave.res.patch;

import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LocalVariableGen;

import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.util.BCELMethodInfo;

public class PatchAtTop extends APatch {

  public PatchAtTop(BCELMethodInfo bm) {
    super(bm);
  }

  @Override
  public int patch(IWeaverForLocation w, InstructionList added) {
    added = insertAt(getTarget(), added);
    updateLocalVariableScope();
    return added.getLength();
  }

  @Override
  public void setInitialTarget() {
    setTarget(0);
  }

  private void updateLocalVariableScope() {
    LocalVariableGen[] lvgs = getMg().getLocalVariables();
    InstructionHandle start = lvgs[0].getStart();
    InstructionHandle newstart = getIl().getInstructionHandles()[0];
    if (start == newstart) {
      return;
    }
    for (int i = 0; i < lvgs.length; i++) {
      LocalVariableGen lvg = lvgs[i];
      if (lvg != null) {
        InstructionHandle current = lvg.getStart();
        if (current == start) {
          lvg.setStart(newstart);
        }
      }
    }
  }
}
