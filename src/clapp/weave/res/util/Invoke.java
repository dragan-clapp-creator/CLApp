package clapp.weave.res.util;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Type;

import clapp.weave.res.ClassWeaver;
import clapp.weave.res.LocationWeaver;
import clapp.weave.res.api.IWeaverForLocation;
import clp.run.res.weave.ArgumentPair;

abstract public class Invoke implements IWeaverForLocation {

  private String fullClassName;
  private String method;
  private List<ArgumentPair> args;
  private String retType;
  private Invoke childRef;
  private String varName;
  private boolean canWeave;

  public Invoke(String fc, String m, List<ArgumentPair> lst, String rt) {
    fullClassName = fc;
    method = m;
    args = lst;
    retType = rt;
  }

  public Invoke(String fc, String m, List<ArgumentPair> lst, String var, String rt) {
    fullClassName = fc;
    method = m;
    args = lst;
    retType = rt;
    varName = var;
  }

  public List<ArgumentPair> getArgs() {
    return args;
  }

  public String getFullClassName() {
    return fullClassName;
  }

  public String getMethod() {
    return method;
  }

  public InstructionList weave(ClassWeaver cw, LocationWeaver lw, short invokeType) {
    InstructionList patch = lw.getInstructionList();
    LocalVariableGen[] lvs = lw.getMethodWeaver().getLocalVariables();
    Type[] types = getSignature(lvs, args);
    if (types != null) {
      if (childRef != null) {
        String cfcname = childRef.getFullClassName();
        String cmethod = childRef.getMethod();
        types = getSignature(lvs, childRef.getArgs());
        patch.append(InstructionFactory.createLoad(cw.getReturnType(cfcname),
                                                   getIndex(cw.getMethodWeaver(method, args)
                                                               .getLocalVariables(), varName)));
        for (int i=0; i<types.length; i++) {
          patch.append(new PUSH(cw.getConstantPoolGen(), childRef.getName(cw, i)));
          types[i] = Type.OBJECT;
        }
        patch.append(cw.getInstructionFactory()
                        .createInvoke(cfcname,
                                      cmethod,
                                      getReturnType(cw, cfcname, cmethod, types),
                                      types,
                                      invokeType));
        patch.append(InstructionConstants.POP);
      }
      else {
        for (int i=0; i<types.length; i++) {
          int ind = getIndex(lw.getMethodWeaver().getLocalVariables(), types[i].getSignature());
          patch.append(InstructionFactory
                .createLoad( Utility.getInstance().getSimpleType(types[i]), ind));
        }
        patch.append(cw.getInstructionFactory()
                        .createInvoke(getClassName(cw),
                                      getMethod(cw, method, varName, types),
                                      getReturnType(invokeType),
                                      types,
                                      invokeType));
      }
    }
    return patch;
  }

  private String getMethod(ClassWeaver cw, String meth, String name, Type[] types) {
    if (cw.isMethod(meth, types)) {
      return meth;
    }
    if (cw.isMethod(name, types)) {
      return name;
    }
    return (meth != null ? meth : name);
  }

  private String getClassName(ClassWeaver cw) {
    if (fullClassName != null) {
      return fullClassName;
    }
//    String cn = getClassName()
    if (retType != null) {
      return retType;
    }
    return cw.getClassName();
  }

  public InstructionList weave(ClassWeaver cw, LocalVariableGen[] lv, short invokeType) {
    InstructionList patch = new InstructionList();
    if (!canWeave) {
      return patch;
    }
    int ind = getIndex(lv, varName);
    if (ind < 0) {
      return patch;
    }
    fullClassName = lv[ind].getType().toString();
    patch.append(InstructionFactory.createLoad(lv[ind].getType(), ind));
    Invoke ref = childRef;
    Invoke parent = this;
    while (ref != null) {
      Type[] types = getSignature(lv, ref.getArgs());
      if (types != null) {
        for (int i=0; i<types.length; i++) {
          patch.append(new PUSH(cw.getConstantPoolGen(), ref.getName(cw, i)));
          types[i] = Type.OBJECT;
        }
        patch.append(cw.getInstructionFactory()
                        .createInvoke(parent.getFullClassName(),
                                      ref.getMethod(),
                                      getReturnType(invokeType),
                                      types,
                                      invokeType));
      }
      parent = ref;
      ref = ref.getChildRef();
    }
    return patch;
  }

  private Class<?>[] getJavaTypes(Type[] types) throws ClassNotFoundException {
    if (types == null) {
      return null;
    }
    Class<?>[] tps = new Class[types.length];
    for (int i=0; i<tps.length; i++) {
      tps[i] = Utility.getInstance().getJavaType(types[i]);
    }
    return tps;
  }

  public void weaveUsingAttribute(ClassWeaver cw, InstructionList patch, short invokeType) {
    Type[] types = getSignature(getArgs());
    if (types != null) {
      if (belongToClass(cw)) {
        for (int i=0; i<types.length; i++) {
          patch.append(InstructionFactory.createLoad(Utility.getInstance().getSimpleType(types[i]), getIndex(cw, i)));
        }
      }
      else {
        for (int i=0; i<types.length; i++) {
          patch.append(new PUSH(cw.getConstantPoolGen(), getName(cw, i)));
        }
      }
      patch.append(cw.getInstructionFactory()
              .createInvoke(fullClassName,
                            method,
                            getReturnType(invokeType),
                            types,
                            invokeType));
    }
  }

  public void setCanWeave(boolean b) {
    canWeave = b;
  }

  public String getName() {
    return null;
  }

  public Kind getKind() {
    return Kind.SYS;  // should not be used
  }

  public Invoke getChildRef() {
    return childRef;
  }

  public void setChildRef(Invoke cref) {
    childRef = cref;
  }

  // ===== PRIVATE METHODS =====================================================

  private boolean belongToClass(ClassWeaver cw) {
    try {
      return cw.getMethodWeaver(method, args) != null;
    }
    catch (Exception e) {
    }
    return false;
  }

  protected Type getReturnType(short invokeType) {
    if (invokeType == Constants.INVOKESPECIAL) {
      return Type.VOID;
    }
    return Utility.getInstance().getBCELType(retType, true);
  }

  private Type getReturnType(ClassWeaver cw, String cfcname, String cmethod, Type[] types) {
    try {
      Class<?> cl = Class.forName(cfcname);
      Class<?>[] jtypes = getJavaTypes(types);
      Method m = cl.getMethod(cmethod, jtypes);
      return cw.getReturnType(m.getReturnType().getName());
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    catch (SecurityException e) {
      e.printStackTrace();
    }
    catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    return Type.VOID;
  }

  private int getIndex(ClassWeaver cw, int i) {
    ArgumentPair ap = (ArgumentPair) args.get(i);
    return getIndex(cw.getMethodWeaver(method, args).getLocalVariables(), ap.getArgumentSum().getArgumentName().getName());
  }

  private String getName(ClassWeaver cw, int i) {
    ArgumentPair ap = (ArgumentPair) args.get(i);
    return ap.getArgumentSum().getArgumentName().getName();
  }

  protected int getIndex(LocalVariableGen[] lvg, String lvar) {
    int ind = 0;
    LocalVariableGen lthis = lvg[0];
    for (int i=0; i<lvg.length; i++) {
      if (lthis.getEnd() == lvg[i].getEnd()) {
        ind++;
      }
      if (lvg[i].getName().equals(lvar)) {
        return lvg[i].getIndex();
      }
    }
    return ind;
  }

  private Type[] getSignature(List<ArgumentPair> args) {
    if (args == null) {
      return Type.NO_ARGS;
    }
    final Type[] tps = new Type[args.size()];
    for (int i=0; i<tps.length; i++) {
      ArgumentPair ap = args.get(i);
      String type = ap.getType();
      if (type != null) {
        tps[i] = Utility.getInstance().getBCELType(type, true);
      }
      else {
        tps[i] = Utility.getInstance().getBCELType("java.lang.String", true);
      }
    }
    return tps;
  }

  private Type[] getSignature(LocalVariableGen[] lvs, List<ArgumentPair> args) {
    Type[] tps = getSignature(args);
    if (tps != null) {
      return tps;
    }
    return tps;
  }
}
