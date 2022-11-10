package clapp.weave.res;

import java.util.ArrayList;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.Type;

import clapp.weave.res.api.IPatch;
import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.api.IWeaverForMethod;
import clapp.weave.res.patch.APatch;
import clapp.weave.res.patch.CompoundPatch;
import clapp.weave.res.patch.PatchAtBottom;
import clapp.weave.res.patch.PatchAtTop;
import clapp.weave.res.patch.PatchBefore;
import clapp.weave.res.util.BCELMethodInfo;
import clapp.weave.res.util.ClpLocalVariable;
import clapp.weave.res.util.PatchAfter;
import clapp.weave.res.vis.ClpLocVisitor;
import clp.run.res.weave.Location;
import clp.run.res.weave.Place;
import clp.run.res.weave.Position;

public class LocationWeaver implements IWeaverForMethod {

  private ArrayList<IWeaverForLocation> methodAddOnList;
  private MethodWeaver methodWeaver;
  private Location location;

  // weaving stuff
  private BCELMethodInfo  bm;
  private InstructionList added;
  private AddHelper       addHelper;
  private APatch  patchLocation;

  public LocationWeaver(MethodWeaver weaver, Location l) {
    methodWeaver = weaver;
    location = l;
    methodAddOnList = new ArrayList<IWeaverForLocation>();
    added = new InstructionList();
  }

  /**
   * WEAVING of a specific location within a method
   */
  public void weave(ClassWeaver cw, boolean isWeavingDone) {
    bm = methodWeaver.getBm();
    MethodGen mg = bm.getMethodGen();
    IPatch p = getPatchLocation(getLocation(), bm);
    int target = p.getTarget();
    for (int i = 0; i < methodAddOnList.size(); i++) {
      cw.getClassGen().removeMethod(bm.getMethod());
      IWeaverForLocation w = (IWeaverForLocation) methodAddOnList.get(i);
      added = w.weave(cw, this, isWeavingDone);
      if (added != null) {
        target += p.patch(w, added);
        p.setTarget(target);
        bm.setMethod(mg.getMethod());
        bm.setMethodGen(mg);
        cw.getClassGen().addMethod(mg.getMethod());
        cw.updateMethod(bm.getMethod(), bm.getInd());
      }
    }
  }

  /**
    * add new addOn to this method
    * 
    * @param mao
    */
  public void addMethodAddOn(IWeaverForLocation lw) {
    methodAddOnList.add(lw);
  }

  /**
   * declared location
   * @return
   */
  public Location getLocation() {
    return location;
  }

  public void setAdditionalHelper(IWeaverForLocation wfl, int size) {
    addHelper = new AddHelper(wfl, methodWeaver.getClassWeaver(), this, size);
  }

  public AddHelper getAdditionalHelper() {
    return addHelper;
  }

  public InstructionList getInstructionList() {
    return added;
  }

  public MethodWeaver getMethodWeaver() {
    return methodWeaver;
  }

  public int getLocalVariableIndex(String name, String type) {
    if (bm != null) {
      return ClpLocalVariable.getLocalIndex(name, type, bm.getMethodGen());
    }
    return -1;
  }

  public Type getLocalVariableType(int ind) {
    if (bm != null) {
      return ClpLocalVariable.getVariableType(ind, bm.getMethodGen());
    }
    return null;
  }

  public String getLocalVariableSignature(String name) {
    if (bm != null) {
      return ClpLocalVariable.getVariableType(name, bm.getMethodGen());
    }
    return null;
  }

  // ===== PRIVATE METHODS =====================================================

  private IPatch getPatchLocation(Location loc, BCELMethodInfo bmi) {
    ClpLocVisitor vis = new ClpLocVisitor();
    loc.accept(vis);
    if (vis.getKind() == null) {
      if (vis.getPlace() == Place.TOP) {
        patchLocation = new PatchAtTop(bmi);
      }
      else if (vis.getPlace() == Place.BOTTOM) {
        patchLocation = new PatchAtBottom(bmi);
      }
    }
    else if (vis.getPosition() == Position.BEFORE) {
      patchLocation = new PatchBefore(bmi, vis.getName(), vis.getIndex(), vis.getKind(), vis.getOccurences(), false);
    }
    else if (vis.getPosition() == Position.AFTER) {
      patchLocation = new PatchAfter(bmi, vis.getName(), vis.getIndex(), vis.getKind(), vis.getOccurences(), 0, false);
    }
    else if (vis.isLoop()) {
      patchLocation = new CompoundPatch(bmi, vis.getName(), vis.getPosition(), vis.getOccurences(), vis.getKind(), vis.getLoopNumber(), this, true);
    }
    return patchLocation;
  }

  // ===========================================================================

  /**
   * inner class. for storing information concerning compound patches
   * 
   */
  public class AddHelper {

    private IWeaverForLocation weaveForLocation;
    private ClassWeaver classWeaver;
    private LocationWeaver locationWeaver;
    private int size;

    public ClassWeaver getClassWeaver() {
      return classWeaver;
    }

    public LocationWeaver getLocationWeaver() {
      return locationWeaver;
    }

    public IWeaverForLocation getWeaveForLocation() {
      return weaveForLocation;
    }

    public AddHelper(IWeaverForLocation wfl, ClassWeaver cw, LocationWeaver lw, int s) {
      weaveForLocation = wfl;
      classWeaver = cw;
      locationWeaver = lw;
      size = s;
    }

    public int getSize() {
      return size;
    }

  }
}
