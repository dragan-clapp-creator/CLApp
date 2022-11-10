package clapp.weave.res.patch;

import org.apache.bcel.generic.InstructionList;

import clapp.weave.res.LocationWeaver;
import clapp.weave.res.LocationWeaver.AddHelper;
import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.util.BCELMethodInfo;
import clapp.weave.res.util.ClpLocalVariable;
import clapp.weave.res.util.PatchAfter;
import clp.run.res.weave.KindOf;
import clp.run.res.weave.Position;

public class CompoundPatch extends APatch {

  private LocationWeaver locationWeaver;
  private Position position;
  private int loopNumber;

  public CompoundPatch(BCELMethodInfo bmi, LocationWeaver lw) {
    super(bmi);
    locationWeaver = lw;
  }

  public CompoundPatch(BCELMethodInfo bmi, String n, Position pos, int ind, KindOf of, int i, LocationWeaver lw, boolean b) {
    super(bmi, n, ind, of, i, 0, b);
    position = pos;
    loopNumber = i;
    locationWeaver = lw;
  }

  @Override
  public void setInitialTarget() {
    // nothing to do here
  }

  @Override
  public int patch(IWeaverForLocation w, InstructionList added) {
    if (position == null) {
      return 0;
    }
    switch (position) {
      case BEFORE:
        new PatchBefore(getBm(), getName(), getIndex(), getKind(), getOccurences(), false).patch(w, added);
        break;
      case BEFORE_LOOP:
        new PatchAtLoopTop(getBm(), getName(), false, getOccurences()).patch(w, added);
        break;
      case BEFORE_END_LOOP:
        new PatchAtLoopEnd(getBm(), getName(), true, getOccurences()).patch(w, added);
        break;
      case BEFORE_EACH:
      {
        PatchBefore p = new PatchBefore(getBm(), getName(), getIndex(), getKind(), loopNumber, true);
        // do it in a loop
        p.patch(w, added);
      }
        break;
      case AFTER:
      {
        int offset = added.getLength();
        AddHelper ah = locationWeaver.getAdditionalHelper();
        if (ah != null) {
          IWeaverForLocation wfl = ah.getWeaveForLocation();
          if (wfl instanceof ClpLocalVariable) {
            InstructionList p =
              ((ClpLocalVariable)wfl).assignVariable(ah.getClassWeaver(),
                                                  ah.getLocationWeaver(), 1);
            PatchAfter pa = new PatchAfter(getBm(), getName(), getIndex(), getKind(),
                                           getOccurences(), offset, false);
            pa.patch(w, p);
          }
        }
      }
        break;
      case AFTER_LOOP_TOP:
        new PatchAtLoopTop(getBm(), getName(), true, getOccurences()).patch(w, added);
        break;
      case AFTER_END_LOOP:
        new PatchAtLoopEnd(getBm(), getName(), false, getOccurences()).patch(w, added);
        break;
      case AFTER_EACH:
      {
        PatchAfter p = new PatchAfter(getBm(), getName(), getIndex(), getKind(), 1, 0, true);
        // do it in a loop
        p.patch(w, added);
      }
        break;

      default:
        break;
    }
    return 0;
  }
}
