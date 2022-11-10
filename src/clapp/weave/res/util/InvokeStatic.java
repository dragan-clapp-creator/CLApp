package clapp.weave.res.util;

import java.util.List;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.InstructionList;

import clapp.weave.res.ClassWeaver;
import clapp.weave.res.LocationWeaver;

public class InvokeStatic extends Invoke {

  private List methodSuite;

  public InvokeStatic(String fc, String m, List lst, String rt) {
    super(fc, m, lst, rt);
  }

  public InvokeStatic(String var, String rt, List ms) {
    super(null, null, null, var, rt);
    methodSuite = ms;
  }

  public InstructionList weave(ClassWeaver cw, LocationWeaver lw, boolean isWeavingDone) {
    if (!isWeavingDone) {
      if (methodSuite == null) {
        return weave(cw, lw, Constants.INVOKESTATIC);
      }
      return weave(cw, lw.getMethodWeaver().getLocalVariables(), Constants.INVOKESTATIC);
    }
    return null;
  }
}
