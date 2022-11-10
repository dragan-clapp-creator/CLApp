package clapp.weave.res.util;

import java.util.Hashtable;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.Type;

import clapp.debug.Logger;
import clapp.debug.Severity;


public class BCELMethodInfo {

  private static Hashtable<String, BCELMethodInfo> infos = new Hashtable<String, BCELMethodInfo>();

  private String className;
  private ConstantPoolGen constantPoolGen;
  private Method method;
  private int ind;
  private MethodGen mg;

  public BCELMethodInfo(String cn, ConstantPoolGen cp, Method m, int i) {
    className = cn;
    constantPoolGen = cp;
    method = m;
    ind = i;
    mg = new MethodGen(method, className, constantPoolGen);
  }

  public int getInd() {
    return ind;
  }

  public Method getMethod() {
    return method;
  }

  public Type getReturnType() {
    return method.getReturnType();
  }

  public Type[] getTypes() {
    return method.getArgumentTypes();
  }

  public MethodGen getMethodGen() {
    return mg;
  }

  public void setMethodGen(MethodGen g) {
    mg = g;
  }

  public void setMethod(Method m) {
    method = m;
  }

  public static BCELMethodInfo find(String cl, String meth) {
    return infos.get(cl+"/"+meth);
  }

  public static void cache(String cl, String meth, BCELMethodInfo bm) {
    if (bm != null) {
      Logger.getInstance().log("BCELMethodInfo:cache "+cl+"/"+meth, Severity.INFO);
      Logger.getInstance().log("BCELMethodInfo:cache "+bm.toString(), Severity.INFO);
      infos.put(cl+"/"+meth, bm);
    }
    else {
      Logger.getInstance().log("BCELMethodInfo:cache "+cl+"/"+meth, Severity.ERROR);
    }
  }
}
