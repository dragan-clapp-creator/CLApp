package clapp.weave.res.util;

import java.util.List;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.InstructionList;

import clapp.weave.res.ClassWeaver;
import clapp.weave.res.LocationWeaver;

public class InvokeSpecial extends Invoke {

  public InvokeSpecial(String fc, List lst, String rt) {
    super(fc, "<init>", lst, rt);
  }

  public InstructionList weave(ClassWeaver cw, LocationWeaver lw, boolean isWeavingDone) {
    if (!isWeavingDone) {
      return weave(cw, lw, Constants.INVOKESPECIAL);
    }
    return null;
  }
}
