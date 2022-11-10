package clapp.weave.res;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.Type;

import clapp.run.util.ClpClassHandler;
import clapp.run.vis.ClpCstOrVarVisitor;
import clapp.weave.res.api.IWeaverForClass;
import clapp.weave.res.util.BCELMethodInfo;
import clapp.weave.res.util.ClpWeavePersistence;
import clapp.weave.res.util.Utility;
import clapp.weave.res.util.Weaver;
import clapp.weave.res.vis.ClpLocVisitor;
import clp.run.res.Resources;
import clp.run.res.weave.ArgumentPair;
import clp.run.res.weave.Location;
import clp.run.res.weave.MethodEnhancement;
import clp.run.res.weave.WeaveVar;

public class ClassWeaver extends Weaver {

  private static Hashtable<String, ClassWeaver> wclasses = new Hashtable<String, ClassWeaver>();

  public static void reset() {
    wclasses.clear();
  }

  public static void prepareDesignWeaving(WeaveVar v, Resources res) {
    ClpCstOrVarVisitor vis = new ClpCstOrVarVisitor();
    vis.accept(v.getPack());
    String pack = vis.getName();
    vis = new ClpCstOrVarVisitor();
    vis.accept(v.getClazz());
    String key = pack + "." + vis.getName();
    ClassWeaver cw;
    if (wclasses.containsKey(key)) {
      cw = wclasses.get(key);
      cw.clearClassItemList();
    }
    else {
      cw = new ClassWeaver(key, res);
      wclasses.put(key, cw);
    }
    cw.addVarItems(v.getMethodEnhancements());
  }

  public static void weaveAndPersist() {
    for (Enumeration<ClassWeaver> en = wclasses.elements(); en.hasMoreElements();) {
      ClassWeaver cw = en.nextElement();
      cw.applyWeavings();   // at least, register all variables that have to be exported
      if (cw.alreadyExists()) {
        continue;  // no weaver persistence for already existing woven classes
      }
      if (!cw.isWeavingDone()) {
        try {
          cw.persist(cw.getClassName());
          cw.setWeavingDone(true);
        }
        catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  private boolean isWeavingDone;
  private boolean isControlImplemented;
  private String className;
  private Resources res;
  private ArrayList<IWeaverForClass> classItemList;  // item list to add to a class

  // all methods from current class, that will be woven
  private Hashtable<String, IWeaverForClass> methods;

  public ClassWeaver(String key, Resources r) {
    super(key);
    res = r;
    className = key;
    classItemList = new ArrayList<IWeaverForClass>();
    methods = new Hashtable<String, IWeaverForClass>();
  }

  /**
   * returns class name of class to be woven
   * @return
   */
  public String getClassName() {
    return className;
  }

  //
  private void addVarItems(ArrayList<MethodEnhancement> loadItems) {
    for (MethodEnhancement me : loadItems) {
      // an item attached to a class is a method enhancement
     ClpCstOrVarVisitor vis = new ClpCstOrVarVisitor();
      vis.accept(me.getMethodTarget());
      MethodWeaver mw = MethodWeaver.getMethodWeaver(ClassWeaver.this,
                                                     vis.getName(),
                                                     me.getArgListApprox(),
                                                     me.getLoadLoc(),
                                                     res);
      if (me.hasMethodAddOns()) {
        mw.addMethodAddOns(me.getMethodAddOns(), me.getLoadLoc());
        if (!classItemList.contains(mw)) {
          classItemList.add(mw);
        }
      }
    }
  }

  //
  private void persist(String toClass)
  throws FileNotFoundException, IOException {
    getJavaClass().setConstantPool(getConstantPoolGen().getFinalConstantPool());
    getJavaClass().setFields(getClassGen().getFields());
    getJavaClass().setMethods(getClassGen().getMethods());
    ClpWeavePersistence wp = new ClpWeavePersistence((String)null,
                                                     (String)null,
                                                     ClpClassHandler.MYCLASSES,
                                                     toClass,
                                                     getJavaClass());
    wp.persist();
  }

  //
  private void applyWeavings() {
    for (int i=0; i<classItemList.size(); i++) {
      IWeaverForClass w = (IWeaverForClass) classItemList.get(i);
      if (checkClass()) {
        w.weave(this, isWeavingDone);
      }
    }
  }

  //
  public boolean isControlImplemented() {
    return isControlImplemented;
  }

  //
  public void setControlImplemented() {
    isControlImplemented = true;
  }

  //
  private boolean checkClass() {
    if (getClassGen() == null) {
      setup(getClassName());
      return getClassGen() != null;
    }
    return true;
  }

  //
  private boolean isWeavingDone() {
    return isWeavingDone;
  }

  public void setWeavingDone(boolean b) {
    isWeavingDone = b;
  }

  public MethodWeaver getMethodWeaver(String name, int count, Location loc) {
    MethodWeaver mw = null;
    String key = Utility
                  .getInstance()
                    .getMethodRefKey(name, count, getLocation(loc));
    if (methods.containsKey(key)) {
      mw = (MethodWeaver) methods.get(key);
    }
    else {
      for (Enumeration<String> en = methods.keys(); en.hasMoreElements(); ) {
        String k = en.nextElement();
        if (k.startsWith(key)) {
          mw = (MethodWeaver) methods.get(k);
          methods.remove(k);
          methods.put(key, mw);
        }
        else if (key.startsWith(k)) {
          mw = (MethodWeaver) methods.get(k);
        }
      }
    }
    return mw;
  }

  public MethodWeaver getMethodWeaver(String name, List<ArgumentPair> argList) {
    MethodWeaver mw = null;
    String key = Utility.getInstance().getMethodRefKey(name, argList);
    if (methods.containsKey(key)) {
      mw = (MethodWeaver) methods.get(key);
    }
    else {
      for (Enumeration<String> en = methods.keys(); en.hasMoreElements(); ) {
        String k = en.nextElement();
        if (k.startsWith(key)) {
          mw = (MethodWeaver) methods.get(k);
          methods.remove(k);
          methods.put(key, mw);
        }
        else if (key.startsWith(k)) {
          mw = (MethodWeaver) methods.get(k);
        }
      }
    }
    return mw;
  }

  public void add(String name, int count, MethodWeaver mw, Location loc) {
    String key = Utility.getInstance().getMethodRefKey(name, count, getLocation(loc));
    methods.put(key, mw);
  }

  public void add(String name, List<ArgumentPair> argList, MethodWeaver mw) {
    String key = Utility.getInstance().getMethodRefKey(name, argList);
    methods.put(key, mw);
  }

  public void addClassItem(IWeaverForClass fw) {
    classItemList.add(fw);
  }

  public void clearClassItemList() {
    classItemList.clear();
  }

  public BCELMethodInfo getBCELMethodInfo(String name, List<ArgumentPair> argList, int count) {
    Method[] ms = getJavaClass().getMethods();
    for (int j = 0; j < ms.length; j++) {
      Method m = ms[j];
      if (!m.isNative() && !m.isAbstract() &&
          (m.getCode() != null) && name.equalsIgnoreCase(m.getName())) {
        Type[] types = m.getArgumentTypes();
        if (match(m, name, argList, types, count)) {
          return new BCELMethodInfo(getClassName(), getConstantPoolGen(), m, j);
        }
        if (argList != null && types != null && argList.size() == types.length) {
          boolean isok = true;
          for (int i=0; isok && i<argList.size(); i++) {
            isok = match(argList.get(i).getType(), types[i].getSignature());
          }
          if (isok) {
            return new BCELMethodInfo(getClassName(), getConstantPoolGen(), m, j);
          }
        }
      }
    }
    return null;
  }

  public void updateMethod(Method method, int ind) {
    Method[] methods = getJavaClass().getMethods();
    methods[ind] = method;
    getJavaClass().setMethods(methods);
  }

  public int getFieldIndex(String name) {
    Field[] fld = getClassGen().getFields();
    for (int i=0; i<fld.length; i++) {
      if (fld[i].getName().equalsIgnoreCase(name)) {
        return i;
      }
    }
    return -1;
  }

  public Field getField(int ind) {
    if (ind >= 0 && ind < getClassGen().getFields().length) {
      return getClassGen().getFields()[ind];
    }
    return null;
  }

  public Type getReturnType(String name) {
    if (name != null) {
      if (name.length() == 1) {
        if ("Z".equals(name)) {
          return Type.BOOLEAN;
        }
        if ("I".equals(name)) {
          return Type.INT;
        }
        if ("J".equals(name)) {
          return Type.LONG;
        }
        if ("B".equals(name)) {
          return Type.BYTE;
        }
        if ("C".equals(name)) {
          return Type.CHAR;
        }
        if ("D".equals(name)) {
          return Type.DOUBLE;
        }
        if ("F".equals(name)) {
          return Type.FLOAT;
        }
        if ("S".equals(name)) {
          return Type.SHORT;
        }
      }
      else {
        if ("boolean".equals(name)) {
          return Type.BOOLEAN;
        }
        if ("int".equals(name)) {
          return Type.INT;
        }
        if ("long".equals(name)) {
          return Type.LONG;
        }
        if ("byte".equals(name)) {
          return Type.BYTE;
        }
        if ("char".equals(name)) {
          return Type.CHAR;
        }
        if ("double".equals(name)) {
          return Type.DOUBLE;
        }
        if ("float".equals(name)) {
          return Type.FLOAT;
        }
        if ("short".equals(name)) {
          return Type.SHORT;
        }
      }
      return Type.getReturnType("L"+name+";");
    }
    return null;
  }

  public boolean isMethod(String meth, Type[] types) {
    Method[] ms = getJavaClass().getMethods();
    boolean isok = isMethod(ms, meth, types);
    if (!isok) {
      try {
        JavaClass[] jcs = getJavaClass().getSuperClasses();
        for (int i=0; i<jcs.length; i++) {
          isok = isMethod(jcs[i].getMethods(), meth, types);
          if (isok) {
            return true;
          }
        }
      }
      catch (ClassNotFoundException e) {
      }
    }
    return isok;
  }


  private String getLocation(Location loc) {
    if (loc != null) {
      ClpLocVisitor vis = new ClpLocVisitor();
      loc.accept(vis);
      return vis.getKey();
    }
    return "";
  }

  private boolean match(Method m, String name, List<ArgumentPair> args, Type[] types, int argcount) {
    boolean ok = args == null &&
                 (types == null || types.length == 0 || types.length == argcount) ||
                 name.equalsIgnoreCase("main");
    return ok;
  }

  /**
   * The meaning of the base types is as follows:
   *    B byte signed byte
   *    C char character
   *    D double double precision IEEE float
   *    F float single precision IEEE float
   *    I int integer
   *    J long long integer
   *    L; ... an object of the given class
   *    S short signed short
   *    Z boolean true or false
   *    [ ... array
   */
  private boolean match(String clappType, String signature) {
    if (clappType != null) {
      if (signature.length() == 1) {
        if ("Z".equals(signature) && "boolean".equalsIgnoreCase(clappType)) {
          return true;
        }
        if ("I".equals(signature) && ("integer".equalsIgnoreCase(clappType) || "int".equalsIgnoreCase(clappType))) {
          return true;
        }
        if ("J".equals(signature) && "long".equalsIgnoreCase(clappType)) {
          return true;
        }
        if ("B".equals(signature) && "byte".equalsIgnoreCase(clappType)) {
          return true;
        }
        if ("C".equals(signature) && "char".equalsIgnoreCase(clappType)) {
          return true;
        }
        if ("D".equals(signature) && "double".equalsIgnoreCase(clappType)) {
          return true;
        }
        if ("F".equals(signature) && "float".equalsIgnoreCase(clappType)) {
          return true;
        }
        if ("S".equals(signature) && "short".equalsIgnoreCase(clappType)) {
          return true;
        }
      }
      String type = signature.substring(2, signature.length()-1).replaceAll("/", ".");
      return (clappType.endsWith(type));
    }
    return true;
  }


  private boolean isMethod(Method[] ms, String meth, Type[] types) {
    if (meth == null) {
      return false;
    }
    for (int j = 0; j < ms.length; j++) {
      Method m = ms[j];
      if (!m.isNative() && !m.isAbstract() &&
          (m.getCode() != null) && meth.equalsIgnoreCase(m.getName())) {
        Type[] tps = m.getArgumentTypes();
        if (tps != null && types != null && tps.length == types.length) {
          boolean isok = true;
          for (int i=0; isok && i<tps.length; i++) {
            isok = types[i].getSignature().contains( tps[i].getSignature() );
          }
          return isok;
        }
      }
    }
    return false;
  }

  public boolean alreadyExists() {
    return exists("", className);
  }

  //
  private boolean exists(String pack, String clazz) {
    File f = new File(ClpClassHandler.MYCLASSES +
                      pack.replace('.', '/') + "/" +
                      clazz.replace('.', '/') + ".class");
    return f.exists();
  }

}
