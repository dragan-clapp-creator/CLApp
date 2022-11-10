package clapp.weave.res.util;

import java.util.List;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.InstructionList;

import clapp.weave.res.ClassWeaver;
import clapp.weave.res.LocationWeaver;

public class InvokeVirtual extends Invoke {

  private List methodSuite;

  public InvokeVirtual(String fc, String m, List lst, String rt) {
    super(fc, m, lst, rt);
  }

  public InvokeVirtual(String fc, String m, List lst, String var, String rt) {
    super(fc, m, lst, var, rt);
  }

  public InvokeVirtual( String var, String rt, List ms) {
    super(null, null, null, var, rt);
    methodSuite = ms;
  }

  public InstructionList weave(ClassWeaver cw, LocationWeaver lw, boolean isWeavingDone) {
    if (!isWeavingDone) {
      if (methodSuite == null) {
        return weave(cw, lw, Constants.INVOKEVIRTUAL);
      }
      return weave(cw, lw.getMethodWeaver().getLocalVariables(), Constants.INVOKEVIRTUAL);
    }
    return null;
  }
}
