package clapp.weave.res.util;

import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.ReturnInstruction;

import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.patch.APatch;
import clp.run.res.weave.KindOf;

public class PatchAfter extends APatch {

  public PatchAfter(BCELMethodInfo bm, String n, int ind, KindOf of, int i, int off, boolean b) {
    super(bm, n, ind, of, i, off, b);
  }

  @Override
  public int patch(IWeaverForLocation w, InstructionList added) {
    added = insertAt(getTarget(), added);
    return added.getLength();
  }

  @Override
  public void setInitialTarget() {
    InstructionHandle[] ihs = getIl().getInstructionHandles();
    setTarget(getAfterLocation(ihs, 0));
  }

  @Override
  public InstructionList insertAt(int index, InstructionList pil) {
    if (index < 1) {
      return pil;
    }
    InstructionHandle handle = getIl().getInstructionHandles()[index];
    InstructionHandle href = getIl().getInstructionHandles()[index - 1];
    InstructionList p = pil.copy();
    if (href.getInstruction() instanceof GOTO) {
      getIl().insert(href, pil);
    }
    else {
      getIl().insert(handle, pil);
    }
    return p;
  }

  // ===== PRIVATE METHODS =====================================================

  //
  private int getAfterLocation(InstructionHandle[] ihs, int count) {
    int targ = 0;
    String code = getCode().toString();
    switch(getKind()) {
      case LOC:
        LocalVariableGen lv = ClpLocalVariable.getOriginalVariable(getName(), getMg());
        InstructionHandle h = lv.getStart();
        return getIndex(ihs, h);
      case GLOB:
        break;
      case CALL:
        break;
      case INSTR:
        break;
    }
    if (getKind() == KindOf.LOC) {
      if (getIndex() > 0) {
        String str = "store\t\t%" + getIndex();
        int from = code.indexOf(str);
        if (from < 0) {
          str = "astore_" + getIndex();
          from = code.indexOf(str);
          if (from < 0) {
            return 0;
          }
        }
        str = code.substring(from);
        from = str.indexOf("\n") + 1;
        int to = str.indexOf(":") + 1;
        str = str.substring(from, to);
        targ = indexInList(ihs, str, count);
        return targ;
      }
      else {
        int from = code.indexOf("LocalVariable(start_pc =");
        String str = code.substring(from);
        int to = code.indexOf(" " + getName() + ")");
        if (to >= 0) {
          str = code.substring(0, to);
          str = str.substring(str.lastIndexOf("start_pc = ") + 11);
          str = str.substring(0, str.indexOf(","));
          targ = indexInList(ihs, str, count);
        }
      }
    }
    else if (getKind() == KindOf.GLOB) {
    }
    else if (getKind() == KindOf.CALL || getKind() == KindOf.INSTR) {
      targ = findInList(code.toString(), getName(), getOccurences());
      if (targ < 0) {
        return 0;
      }
    }
    for (int i = targ + getOffset() + 1; i < ihs.length; i++) {
      if (ihs[i].hasTargeters()) {
        InstructionHandle ih = ihs[i - 1];
        if (ih.getInstruction() instanceof ReturnInstruction) {
          insertIntermediate(ihs, ih, i);
          return i - 1;
        }
        return i;
      }
    }
    return targ;
  }

  //
  private int getIndex(InstructionHandle[] ihs, InstructionHandle h) {
    for (int i=0; i< ihs.length; i++) {
      if (ihs[i] == h) {
        return i;
      }
    }
    return 0;
  }
}
