package clapp.weave.res.patch;

import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;

import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.util.BCELMethodInfo;
import clp.run.res.weave.KindOf;

public class PatchBefore extends APatch {

  public PatchBefore(BCELMethodInfo bm, String n, int ind, KindOf of, int i, boolean b) {
    super(bm, n, ind, of, i, 0, b);
  }

  @Override
  public int patch(IWeaverForLocation w, InstructionList added) {
    added = insertAt(getTarget(), added);

    updateOnBeforeInsert(getTarget()+added.getLength());
    return added.getLength();
  }

  @Override
  public void setInitialTarget() {
    InstructionHandle[] ihs = getIl().getInstructionHandles();
    setTarget(getBeforeLocation(ihs, 0));
  }

  // ===== PRIVATE METHODS =====================================================

  //
  private void updateOnBeforeInsert(int offset) {
    InstructionHandle[] ihs = getIl().getInstructionHandles();
    InstructionHandle ihref = ihs[offset];
    InstructionHandle ih = ihs[0];
    while (ih != getIl().getEnd()) {
      if (ih instanceof BranchHandle) {
        BranchHandle bh = (BranchHandle) ih;
        if (bh.getTarget() == ihref) {
          bh.setTarget(ihs[getTarget()]);
        }
      }
      ih = ih.getNext();
    }
  }

  //
  private int getBeforeLocation(InstructionHandle[] ihs, int count) {
    int targ = 0;
    String code = getCode().toString();
    if (getKind() == KindOf.LOC) {
      int from = code.indexOf("LocalVariable(start_pc =");
      String str = code.substring(from);
      int to = code.indexOf(" " + getName() + ")");
      if (to >= 0) {
        str = code.substring(0, to);
        str = str.substring(0, str.lastIndexOf("start_pc = ") );
        str = str.substring(str.lastIndexOf("start_pc = ") + 11 );
        str = str.substring(0, str.indexOf(","));
        targ = indexInList(ihs, str, count);
      }
    }
    else if (getKind() == KindOf.GLOB) {
    }
    else if (getKind() == KindOf.CALL || getKind() == KindOf.INSTR) {
      targ = findInList(code, getName(), getOccurences());
//      while (targ > 0) {
//  if (isPointcut0(ihs[targ-1].getInstruction().getName(),
//                  ihs[targ].getInstruction().getName())) {
//    return targ;
//  }
//  if (isPointcut1(ihs[targ].getInstruction().getName())) {
//    return targ+1;
//  }
//  targ--;
//      }
    }
    for (int i=targ; i>0; i--) {
      if (ihs[i].hasTargeters() || ihs[i-1].getInstruction().getName().indexOf("invoke") >= 0) {
        return i;
      }
    }
    return 0;
  }
}
