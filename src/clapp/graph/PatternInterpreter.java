package clapp.graph;

import clapp.run.util.JavaInvoker;
import clapp.run.util.JavaInvoker.ClassInfo;

public class PatternInterpreter {

  public static class Build {

    private PatternInterpreter instance;
    private String modifier;
    private String types;
    private String ret;
    private String cl;
    private String meth;
    private Object[] args;

    public Build() {
      instance = new PatternInterpreter();
      instance.setBuilder(this);
    }

    public PatternInterpreter getInstance() {
      if (modifier == null ||
          types == null ||
          ret == null ||
          cl == null ||
          meth == null) {
        return null;
      }
      return instance;
    }
    public Build setModifier(String modifier) {
      this.modifier = modifier;
      return this;
    }
    public Build setTypes(String t) {
      this.types = t;
      return this;
    }
    public Build setReturnType(String ret) {
      this.ret = ret;
      return this;
    }
    public Build setClassName(String cc) {
      this.cl = cc;
      return this;
    }
    public Build setMethodName(String meth) {
      this.meth = meth;
      return this;
    }
    public Build setArguments(Object[] args) {
      this.args = args;
      return this;
    }
    public String getModifier() {
      return modifier;
    }
    public String getTypes() {
      return types;
    }
    public String getReturnType() {
      return ret;
    }
    public String getClassName() {
      return cl;
    }
    public String getMethodName() {
      return meth;
    }
    public Object[] getArguments() {
      return args;
    }
  }

  private Build builder;
  private String argString;

  public double calculate() {
    if ("S".equalsIgnoreCase(builder.getModifier())) {
      Object[] args = builder.getArguments();
      Class[] types = new Class[args.length];
      argString = builder.getTypes();
      for (int i=0; i<args.length; i++) {
        types[i] = getType(i, false);
      }
      ClassInfo info = JavaInvoker.getInstance()
        .javaStaticCall(builder.getClassName(), builder.getMethodName(), types, args);
      return (Double) info.getReturnValue();
    }
    return 0;
  }

  /**
   * a java type is held out of the following basic type codes:
   *    B byte signed byte
   *    C char character
   *    D double double precision IEEE float
   *    F float single precision IEEE float
   *    I int integer
   *    J long long integer
   *    L; ... an object of the given class (ex. "Ljava/lang/System;")
   *    S short signed short
   *    Z boolean true or false
   *    [ ... array (ex. "[Ljava/lang/String;")
   * @param c
   */
  private Class getType(int i, boolean isArray) {
    char c = argString.charAt(i);
    switch(c) {
      case 'b':
        if (isArray) {
          return byte[].class;
        }
        return byte.class;
      case 'B':
        if (isArray) {
          return Byte[].class;
        }
        return Byte.class;
      case 'c':
        if (isArray) {
          return char[].class;
        }
        return char.class;
      case 'C':
        if (isArray) {
          return Character[].class;
        }
        return Character.class;
      case 'd':
        if (isArray) {
          return double[].class;
        }
        return double.class;
      case 'D':
        if (isArray) {
          return Double[].class;
        }
        return Double.class;
      case 'f':
        if (isArray) {
          return float[].class;
        }
        return float.class;
      case 'F':
        if (isArray) {
          return Float[].class;
        }
        return Float.class;
      case 'i':
        if (isArray) {
          return int[].class;
        }
        return int.class;
      case 'I':
        if (isArray) {
          return Integer[].class;
        }
        return Integer.class;
      case 'j':
        if (isArray) {
          return long[].class;
        }
        return long.class;
      case 'J':
        if (isArray) {
          return Long[].class;
        }
        return Long.class;
      case 'L':
      {
        int j = argString.indexOf(';', i);
        String str = argString.substring(i+1, j).replace("/", ".");
        argString = argString.substring(0, i)+argString.substring(j+1);
        try {
          if (isArray) {
            str += "[]";
          }
          return Class.forName(str);
        }
        catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
      case 's':
        if (isArray) {
          return short[].class;
        }
        return short.class;
      case 'S':
        if (isArray) {
          return Short[].class;
        }
        return Short.class;
      case 'z':
        if (isArray) {
          return boolean[].class;
        }
        return boolean.class;
      case 'Z':
        if (isArray) {
          return Boolean[].class;
        }
        return Boolean.class;
      case '[':
        argString = argString.substring(0, i)+argString.substring(i+1);
        return getType(i, true);
    }
    return null;
  }

  public void setBuilder(Build build) {
    this.builder = build;
  }
}
