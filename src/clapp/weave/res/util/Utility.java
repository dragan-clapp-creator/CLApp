package clapp.weave.res.util;

import java.util.List;

import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;

import clapp.run.ui.util.ConsoleProvider;
import clp.run.res.BVar;
import clp.run.res.FVar;
import clp.run.res.IVar;
import clp.run.res.LVar;
import clp.run.res.PVar;
import clp.run.res.SVar;
import clp.run.res.Variable;
import clp.run.res.weave.ArgumentPair;


public class Utility {

  static private class SingletonProvider {
    static private Utility instance = new Utility();
  }
  static public Utility getInstance() {
    return SingletonProvider.instance;
  }

  public String getMethodRefKey(String name, List a) {
    String key = name;
    if (a != null) {
      for (int i=0; i<a.size(); i++) {
        ArgumentPair ap = (ArgumentPair) a.get(i);
        key += "/" + ap.getType();
      }
      key += "/";
    }
    return key;
  }

  public String getMethodRefKey(String name, int count, String sloc) {
    String key = name + "[" + count + "]";
    if (sloc != null) {
      return key + sloc;
    }
    return key;
  }

  public String getAttributeRefKey(String cl, String meth, List a, String tp, String att) {
    String key = tp;
    key += ":" + getMethodRefKey(cl + "." + meth, a);
    key += ":" + att;
    if (key.length() > 21) {
      key = key.substring(key.length()-21);
    }
    return key;
  }

  /**
   * return BCEL Type reference corresponding to java type
   * @param type
   * @param silent
   * @return
   */
  public Type getBCELType(String type, boolean silent) {
    if (type != null) {
      Type tp = null;
      boolean isArray = false;
      if (type.endsWith("[]")) {
	isArray = true;
	type = type.substring(0, type.length()-2);
      }
      if (type.equalsIgnoreCase("boolean")) {
        tp = Type.BOOLEAN;
      }
      else if (type.equalsIgnoreCase("int")) {
        tp = Type.INT;
      }
      else if (type.equalsIgnoreCase("double")) {
        tp = Type.DOUBLE;
      }
      else if (type.equalsIgnoreCase(String.class.getName())) {
        tp = Type.STRING;
      }
      else if (type.equalsIgnoreCase(StringBuffer.class.getName())) {
        tp = Type.STRINGBUFFER;
      }
      else if (type.equalsIgnoreCase("long")) {
        tp = Type.LONG;
      }
      else if (type.equalsIgnoreCase("byte")) {
        tp = Type.BYTE;
      }
      else if (type.equalsIgnoreCase("void")) {
        tp = Type.VOID;
      }
      else if (type.equals("OBJECT")) {
        tp = Type.OBJECT;
      }
      else {
        tp = new ObjectType(type);
      }
      if (!silent) {
        ConsoleProvider.getInstance().print("Type." + type.substring(type.lastIndexOf(".")+1).toUpperCase());
      }
      if (isArray) {
	return new ArrayType(tp, 1);
      }
      return tp;
    }
    if (!silent) {
      ConsoleProvider.getInstance().print("Type.VOID");
    }
    return Type.VOID;
  }

  //
  public Type getReturnType(Variable res) {
    String tp = "void";
    if (res instanceof BVar) {
      tp = "boolean";
    }
    else if (res instanceof IVar) {
      tp = "int";
    }
    else if (res instanceof LVar) {
      tp = "long";
    }
    else if (res instanceof FVar) {
      tp = "double";
    }
    else if (res instanceof SVar) {
      tp = String.class.getName();
    }
    else if (res instanceof PVar) {
      tp = Object.class.getName();
    }
    return getBCELType(tp, true);
  }

  public Type[] getSignature(Variable res, boolean isArgument) {
    int sz = isArgument ? 2 : 1;
    Type[] rtps = new Type[sz];
    rtps[0] = getBCELType(String.class.getName(), true);
    if (sz > 1) {
      rtps[1] = getReturnType(res);
    }
    return rtps;
  }

  public Class getJavaType(Type tp) throws ClassNotFoundException {
    if (tp == null) {
      return Object.class;
    }
    return Class.forName(getStringType(tp));
  }

  public Type getSimpleType(Type type) {
    if (type == Type.BOOLEAN ||
        type == Type.BYTE ||
        type == Type.CHAR ||
        type == Type.DOUBLE ||
        type == Type.FLOAT ||
        type == Type.INT ||
        type == Type.LONG ||
        type == Type.SHORT) {
      return type;
    }
    return Type.OBJECT;
  }

  public String getStringType(Type tp) {
    String sig = tp.getSignature();
    if ("J".equalsIgnoreCase(sig)) {
      return Type.LONG.toString();
    }
    if ("Z".equalsIgnoreCase(sig)) {
      return Type.BOOLEAN.toString();
    }
    if ("I".equalsIgnoreCase(sig)) {
      return Type.INT.toString();
    }
    return sig.substring(1, sig.length()-1).replaceAll("/", ".");
  }
}
