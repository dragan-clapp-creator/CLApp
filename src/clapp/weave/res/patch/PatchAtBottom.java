package clapp.weave.res.patch;

import org.apache.bcel.generic.ARETURN;
import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.DRETURN;
import org.apache.bcel.generic.FRETURN;
import org.apache.bcel.generic.IRETURN;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LRETURN;
import org.apache.bcel.generic.LoadInstruction;
import org.apache.bcel.generic.ReturnInstruction;

import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.util.BCELMethodInfo;

public class PatchAtBottom extends APatch {

  public PatchAtBottom(BCELMethodInfo bm) {
    super(bm);
  }

  @Override
  public int patch(IWeaverForLocation w, InstructionList added) {
    added = insertAt(getTarget(), added);
    // offset += added.getLength(); // for eventl. next weaving

    updateOnBottomInsert(added);
    return added.getLength();
  }

  @Override
  public void setInitialTarget() {
    InstructionHandle[] ihs = getIl().getInstructionHandles();
    int target = ihs.length - 1;
    if (target > 1 &&
        ihs[target-1].getInstruction() instanceof LoadInstruction) {
      target -= 1;
    }
    setTarget(target);
  }


  /**
   * after the patch insert, the target of branches which point to return are
   * automatically changed, in order to still point to return. We want them to
   * point to our patched instruction, because, this has to be the last one.
   * @param il
   * @param pil
   */
  private void updateOnBottomInsert(InstructionList pil) {
    InstructionHandle[] ihs = getIl().getInstructionHandles();
    InstructionHandle ih = ihs[0];
    for (int i=0; ih != getIl().getEnd(); i++) {
      if (ih instanceof BranchHandle) {
  BranchHandle bh = (BranchHandle) ih;
  if (bh.getTarget() == getIl().getEnd()) {
    bh.setTarget(ihs[getTarget()]);
  }
      }
      else if (ih.getInstruction() instanceof ARETURN
            || ih.getInstruction() instanceof IRETURN
            || ih.getInstruction() instanceof DRETURN
            || ih.getInstruction() instanceof FRETURN
            || ih.getInstruction() instanceof LRETURN) {
        if (insertIntermediate(ihs, ih, i)) {
          pil = insertAt(i + 1, pil);
        }
        else {
          pil = insertAt(i, pil);
        }
      }
      else if (ih.getInstruction() instanceof ReturnInstruction) {
        pil = conditionalInsertAt(getIl(), ih, pil);
      }
      ih = ih.getNext();
    }
  }
}
