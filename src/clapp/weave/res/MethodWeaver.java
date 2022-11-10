package clapp.weave.res;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.MethodGen;

import clapp.weave.res.api.IWeaverForClass;
import clapp.weave.res.patch.APatch;
import clapp.weave.res.util.BCELMethodInfo;
import clapp.weave.res.vis.ClpArgListApproxVisitor;
import clapp.weave.res.vis.ClpMethodAddOnVisitor;
import clp.run.res.Resources;
import clp.run.res.weave.ArgListApprox;
import clp.run.res.weave.ArgumentPair;
import clp.run.res.weave.AtLocation;
import clp.run.res.weave.Location;
import clp.run.res.weave.MethodAddOn;
import clp.run.res.weave.MethodAddOnVisitor;
import clp.run.res.weave.Place;

public class MethodWeaver implements IWeaverForClass {

  // all locations from current method, that will be woven
  private Hashtable<String, LocationWeaver> locations = new Hashtable<String, LocationWeaver>();

  // weaving stuff
  private BCELMethodInfo bm;
  private ClassWeaver classWeaver;
  private Resources res;

  private int count;
  private List<ArgumentPair> argList;
  private String methodName;

  public MethodWeaver(String name, int argCount, Resources r, ClassWeaver cw) {
    super();
    argList = null;
    count = argCount;
    setup(r, cw, name);
  }

  public MethodWeaver(String name, List<ArgumentPair> args, Resources r, ClassWeaver cw) {
    super();
    argList = args;
    count = -1;
    setup(r, cw, name);
  }

  //
  private void setup(Resources r, ClassWeaver cw, String name) {
    res = r;
    classWeaver = cw;
    methodName = name;
    bm = BCELMethodInfo.find(classWeaver.getClassName(), methodName);
    if (bm == null) {
      bm = classWeaver.getBCELMethodInfo(methodName, argList, count);
      BCELMethodInfo.cache(classWeaver.getClassName(), methodName, bm);
    }
  }

  /**
   * 
   * @param classWeaver
   * @param name
   * @param argListApprox
   * @param loadLoc
   * @param res
   * @return
   */
  public static MethodWeaver getMethodWeaver(ClassWeaver cw,
      String name, ArgListApprox approx, Location loc,
      Resources res) {
    ClpArgListApproxVisitor alv = null;
    if (approx != null) {
      alv = new ClpArgListApproxVisitor();
      approx.accept(alv);
    }
    if (alv != null && alv.isCount()) {
      MethodWeaver mw = cw.getMethodWeaver(name, alv.getCount() , loc);
      if (mw == null) {
        mw = new MethodWeaver(name, alv.getCount(), res, cw);
        cw.add(name, alv.getCount(), mw, loc);
      }
      return mw;
    }
    List<ArgumentPair> argList = (alv == null ? null : alv.getArglist());
    MethodWeaver mw = cw.getMethodWeaver(name,  argList);
    if (mw == null) {
      mw = new MethodWeaver(name, argList, res, cw);
      cw.add(name, argList, mw);
    }
    return mw;
  }

  /**
   * 
   * @param methodAddOns
   * @param loadLoc
   */
  public void addMethodAddOns(ArrayList<MethodAddOn> ml, Location loc) {
    LocationWeaver lw = findInLocations(loc);
    for (MethodAddOn mao : ml) {
      MethodAddOnVisitor mav = new ClpMethodAddOnVisitor(lw, res);
      mao.accept(mav);
    }
  }

  @Override
  public void weave(ClassWeaver cw, boolean isWeavingDone) {
    TreeMap<String, LocationWeaver> slocations = new TreeMap<String, LocationWeaver>(locations);

    if (bm != null && slocations.size() > 0) {
      APatch.resetIsDone();
      for (Iterator<Entry<String, LocationWeaver>> it = slocations.entrySet().iterator(); it.hasNext(); ) {
        LocationWeaver w = it.next().getValue();
        w.weave(getClassWeaver(), isWeavingDone);
      }
    }
  }


  /**
   * returns reference to parent
   * @return
   */
  public ClassWeaver getClassWeaver() {
    return classWeaver;
  }


  public LocationWeaver findInLocations(Location loc) {
    if (loc == null) {
      // create default weave location (on TOP)
      loc = new AtLocation();
      ((AtLocation)loc).setPlace(Place.TOP);
    }
    LocationWeaver lw = new LocationWeaver(this, loc);
    locations.put(new Integer(locations.size()).toString(), lw);
    return lw;
  }

  public BCELMethodInfo getBm() {
    return bm;
  }

  public MethodGen getMethodGen() {
    return bm.getMethodGen();
  }

  public LocalVariableGen[] getLocalVariables() {
    if (bm != null) {
      return bm.getMethodGen().getLocalVariables();
    }
    return null;
  }

  public String getMethodName() {
    return methodName;
  }

  public List<ArgumentPair> getArgList() {
    return argList;
  }

  public Method getMethod() {
    return bm.getMethod();
  }
}
