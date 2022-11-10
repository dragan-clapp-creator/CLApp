/*
 * Created on Feb 17, 2005
 */
package clapp.run.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.ResourceUtility.TypedValue;
import clp.run.cel.exp.Expression;
import clp.run.cel.java.JavaExportPart;
import clp.run.res.IVar;
import clp.run.res.Resources;
import clp.run.res.SVar;
import clp.run.res.Variable;


/**
 * @author Dragan Matic
 */
public class JavaInvoker {

  static private JavaInvoker _instance;
  static private Hashtable<String, Thread> hthreads = new Hashtable<String, Thread>();

  static {
    _instance = new JavaInvoker();
  }

  public JavaInvoker() {
  }

  static public JavaInvoker getInstance() {
    return _instance;
  }

  /**
   * start the referenced Thread
   * @param imp 
   * @param res 
   */
  public void javaStart(String clname, String method, JavaExportPart exp, String returnVariable, Resources res) {
    Thread ref = new ThreadWrapper(Thread.currentThread().getThreadGroup(), clname, method, exp, returnVariable, res);
    ref.start();
    hthreads.put(ref.getName(), ref);
  }

  @SuppressWarnings("rawtypes")
  public void javaNew(String clname, JavaExportPart exp, String imp, Resources res) {
    try {
      Class cl = ClpClassHandler.getInstance().loadClass(clname);
      Object[] args = getArguments(exp, getArgumentTypes(exp, res), res);
      Variable retvar = ResourceUtility.getInstance().getVariable(res, imp);
      invokeSpecial(cl, args, retvar, imp);
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void javaCall(String clname, String method, JavaExportPart exp, String imp, Resources res) {
    try {
      Class cl = ClpClassHandler.getInstance().loadClass(clname);
//      ClassLoader loader = new CommonClassLoader(null, ClpClassLoader.getUrl());
//      Thread.currentThread().setContextClassLoader(loader);
//      Class cl = loader.loadClass(clname);
      Class[] argTps;
      Object[] args;
      if ("main".equals(method)) {
        argTps = getMainArgumentTypes();
        args = getMainArguments(exp, argTps, res);
      }
      else {
        argTps = getArgumentTypes(exp, res);
        args = getArguments(exp, argTps, res);
      }
      Method m = cl.getMethod(method, argTps);
      Variable retvar = ResourceUtility.getInstance().getVariable(res, imp);
      if ((m.getModifiers() & Modifier.STATIC) != 0) {
        invokeStatic(m, args, retvar, imp);
      }
      else {
        invokeVirtual(cl, m, args, retvar, imp);
      }
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
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  //
  private Object[] getMainArguments(JavaExportPart exp, Class<?>[] argTps, Resources res) {
    Object[] obj = new Object[1];
    if (exp != null) {
      ResourceUtility util = ResourceUtility.getInstance();
      Expression jexp = exp.getExpression();
      if (jexp == null) {
        return obj;
      }
      ExpressionEvaluator ee = new ExpressionEvaluator(res);
      String arg = ee.getVariableName(jexp);
      if (arg != null) {
        Variable r = util.getVariable(res, arg);
        if (r != null) {
          Object[] o = util.getValues(r);
          if (r instanceof SVar) {
            TypedValue<String[]> tv = util.new TypedValue<String[]>(o);
            String[] iarray = new String[o.length];
            for (int j=0; j<o.length; j++) {
              iarray[j] = tv.getValue(j);
            }
            obj[0] = iarray;
          }
          else if (r instanceof IVar) {
            TypedValue<Integer[]> tv = util.new TypedValue<Integer[]>(o);
            Integer[] iarray = new Integer[o.length];
            for (int j=0; j<o.length; j++) {
              iarray[j] = tv.getValue(j);
            }
            obj[0] = iarray;
          }
        }
        else {
          obj[0] = arg.split(" ");
        }
      }
    }
    return obj;
  }

  //
  private Class<?>[] getMainArgumentTypes() {
    Class<?>[] cls = new Class[1];
    cls[0] = String[].class;
    return cls;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void javaCall(String clname, Resources res) {
    try {
      Class cl = ClpClassHandler.getInstance().loadClass(clname);
//      ClassLoader loader = new CommonClassLoader(null, ClpClassLoader.getUrl());
//      Thread.currentThread().setContextClassLoader(loader);
//      Class cl = loader.loadClass(clname);
      Class[] argTps = new Class[1];
      argTps[0] = String[].class;
      Object[] args = {null};
      Method m = cl.getMethod("main", argTps);
      Variable retvar = ResourceUtility.getInstance().getVariable(res, (String)null);
      invokeStatic(m, args, retvar, null);
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
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings({ "rawtypes" })
  private void invokeSpecial(Class cl, Object[] args, Variable retvar, String name) {
    Object ref = getConstructor(cl, args);
    ResourceUtility.getInstance().setValue(name, retvar, ref);
  }

  @SuppressWarnings("rawtypes")
  private void invokeVirtual(Class cl, Method m, Object[] args, Variable retvar, String name)
      throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

    Object ref = getConstructor(cl, args);
    if (retvar != null) {
      Object ret = m.invoke(ref, args);
      ResourceUtility.getInstance().setValue(name, retvar, ret);
    }
    else {
      m.invoke(ref, args);
    }
  }

  private void invokeVirtual(Object ref, Method m, Object[] args, Variable retvar, String name)
      throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    if (retvar != null) {
      Object ret = m.invoke(ref, args);
      ResourceUtility.getInstance().setValue(name, retvar, ret);
    }
    else {
      m.invoke(ref, args);
    }
  }

  @SuppressWarnings("rawtypes")
  private Object getConstructor(Class cl, Object[] args) {
    Constructor[] cs = cl.getConstructors();
    for (int i=0; i<cs.length; i++) {
      Constructor con = cs[i];
      Object obj = null;
      try {
        if (args == null && con.getParameterCount() == 0 ||
            con.getParameterCount() == args.length) {
          args = adaptArgs(args);
          obj = con.newInstance(args);
        }
      }
      catch (Exception e) {
      }
      if (obj != null) {
        return obj;
      }
    }
    ConsoleProvider.getInstance().eprint("no instance for class " + cl.getSimpleName() + " found");
    return null;
  }

  private Object[] adaptArgs(Object[] args) {
    if (args != null) {
      for (int i=0; i<args.length; i++) {
        if ("null".equals(args[i])) {
          args[i] = null;
        }
        else if ("true".equals(args[i]) || "false".equals(args[i])) {
          args[i] = Boolean.valueOf((String) args[i]);
        }
      }
    }
    return args;
  }

  private void invokeStatic(Method m, Object[] args, Variable retvar, String name)
      throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    
    if (retvar != null) {
      Object ret = m.invoke(null, args);
      ResourceUtility.getInstance().setValue(name, retvar, ret);
    }
    else {
      m.invoke(null, args);
    }
  }

  @SuppressWarnings({ "rawtypes", "deprecation" })
  private Object[] getArguments(JavaExportPart exp, Class[] argTps, Resources res) {
    if (exp != null) {
      ResourceUtility util = ResourceUtility.getInstance();
      ArrayList<Expression> list = new ArrayList<Expression>();
      list.addAll( exp.getExpressions() );
      Expression e = exp.getExpression();
      if (e != null) {
        list.add(0, e);
      }
      Object[] obj = new Object[list.size()];
      for (int i=0; i<list.size(); i++) {
        Expression jexp = list.get(i);
        ExpressionEvaluator ee = new ExpressionEvaluator(res);
        String arg = ee.getVariableName(jexp);
        if (arg != null) {
          Variable r = util.getVariable(res, arg);
          if (r == null) {
            if (argTps[i] == boolean.class) {
              obj[i] = Boolean.valueOf(arg);
            }
            else if (argTps[i] == int.class) {
              obj[i] = Integer.valueOf(arg);
            }
            else if (argTps[i] == double.class) {
              obj[i] = Double.valueOf(arg);
            }
            else {
              obj[i] = arg;
            }
          }
          else if (util.hasArray(r)) {
            if (argTps[i] == Integer[].class) {
              Object[] o = util.getValues(r);
              TypedValue<Integer[]> tv = util.new TypedValue<Integer[]>(o);
              Integer[] iarray = new Integer[o.length];
              for (int j=0; j<o.length; j++) {
                iarray[j] = tv.getValue(j);
              }
              obj[i] = iarray;
            }
          }
          else {
            Object o = util.getValue(r);
            if (argTps[i] == Integer.class) {
              TypedValue<Integer> tv = util.new TypedValue<Integer>(o);
              Integer ival = new Integer((Integer) tv.getValue());
              obj[i] = ival;
            }
            else if (argTps[i] == String.class) {
              TypedValue<String> tv = util.new TypedValue<String>(o);
              String sval = new String((String) tv.getValue());
              obj[i] = sval;
            }
            else if (argTps[i] == Long.class) {
              TypedValue<Long> tv = util.new TypedValue<Long>(o);
              Long lval = new Long((Long) tv.getValue());
              obj[i] = lval;
            }
            else if (argTps[i] == Double.class) {
              TypedValue<Double> tv = util.new TypedValue<Double>(o);
              Double fval = new Double((Double) tv.getValue());
              obj[i] = fval;
            }
            else if (argTps[i] == Object.class) {
              TypedValue<Object> tv = util.new TypedValue<Object>(o);
              obj[i] = tv.getValue();
            }
          }
        }
        else {
          obj[i] = ee.getVisitorValue();
        }
      }
      return obj;
    }
    return null;
  }

  @SuppressWarnings("rawtypes")
  private Class[] getArgumentTypes(JavaExportPart exp, Resources res) {
    if (exp != null) {
      ArrayList<Expression> list = new ArrayList<Expression>();
      list.addAll( exp.getExpressions() );
      Expression e = exp.getExpression();
      if (e != null) {
        list.add(0, e);
      }
      ExpressionEvaluator ee = new ExpressionEvaluator(res);
      Class[] cls = new Class[list.size()];
      for (int i=0; i<list.size(); i++) {
        Expression jexp = list.get(i);
        String arg = ee.getVariableName(jexp);
        if (arg != null) {
          Variable var = ResourceUtility.getInstance().getVariable(res, arg);
          if (var != null) {
            cls[i] = ResourceUtility.getInstance().getClass(var);
          }
          else if (arg != null) {
            if ("true".equalsIgnoreCase(arg) || "false".equalsIgnoreCase(arg)) {
              cls[i] = boolean.class;
            }
            else {
              try {
                int x = Integer.valueOf(arg);
                cls[i] = int.class;
              }
              catch (RuntimeException rte1) {
                try {
                  double x = Double.valueOf(arg);
                  cls[i] = double.class;
                }
                catch (RuntimeException rte2) {
                  cls[i] = String.class;
                }
              }
            }
          }
        }
        else {
          cls[i] = ee.getVisitorClass();
        }
      }
      return cls;
    }
    return null;
  }

  @SuppressWarnings({ "rawtypes" })
  public void javaCall(Object ref, String method, JavaExportPart exp, String imp, Resources res) {
    if (ref == null) {
      return;
    }
    Class cl = null;
    try {
      cl = ref.getClass();
      Class[] argTps = getArgumentTypes(exp, res);
      Object[] args = getArguments(exp, argTps, res);
      Variable retvar = ResourceUtility.getInstance().getVariable(res, imp);
      Method m = findMethod(cl, method, argTps);
      invokeVirtual(ref, m, args, retvar, imp);
    }
    catch (SecurityException e) {
      e.printStackTrace();
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    catch (NullPointerException e) {
      ConsoleProvider.getInstance().eprint("try to call method '"+method+getSignature(exp)+"' within a null class reference");
      e.printStackTrace();
    }
  }

  private Method findMethod(Class<?> cl, String method, Class<?>[] argTps) {
    Method[] mtds = cl.getMethods();
    for (Method m : mtds) {
      if (m.getName().equals(method)) {
        if (argTps == null && m.getParameterCount() == 0) {
          return m;
        }
        if (m.getParameterCount() == argTps.length && areParametersCompatible(m.getParameterTypes(), argTps)) {
          return m;
        }
      }
    }
    return null;
  }

  private boolean areParametersCompatible(Class<?>[] parameterTypes, Class<?>[] argTps) {
    for (int i=0; i<argTps.length; i++) {
      if (!parameterTypes[i].getName().equals(argTps[i].getName())) {
        switch (parameterTypes[i].getName()) {
          case "int":
            if (!argTps[i].getName().equals("java.lang.Integer")) {
              return false;
            }
            break;
          case "java.lang.Integer":
            if (!argTps[i].getName().equals("int")) {
              return false;
            }
            break;
          case "long":
            if (!argTps[i].getName().equals("java.lang.Long")) {
              return false;
            }
            break;
          case "java.lang.Long":
            if (!argTps[i].getName().equals("long")) {
              return false;
            }
            break;
          case "float":
            if (!argTps[i].getName().equals("java.lang.Float")) {
              return false;
            }
            break;
          case "java.lang.Float":
            if (!argTps[i].getName().equals("float")) {
              return false;
            }
            break;
          case "double":
            if (!argTps[i].getName().equals("java.lang.Float")) {
              return false;
            }
            break;
          case "java.lang.Double":
            if (!argTps[i].getName().equals("float")) {
              return false;
            }
            break;

          default:
            break;
        }
      }
    }
    return true;
  }

  private String getSignature(JavaExportPart exp) {
    List<Expression> lexp = exp.getExpressions();
    Expression e = exp.getExpression();
    if (e != null) {
      lexp.add(0, e);
    }
    String str = "(";
    for (int i=0; i<lexp.size(); i++) {
      str += lexp.get(i) + ";";
    }
    return str+")";
  }

  public static void stopAll() {
    for (Enumeration<Thread> en = hthreads.elements(); en.hasMoreElements();) {
      Thread t = en.nextElement();
      t.interrupt();
    }
  }

  public ClassInfo javaCall(String clname, String mname, Class<?>[] argTps, Object[] args) {
    ClassInfo clinfo = new ClassInfo();
    try {
      Class<?> cl = Class.forName(clname, false, ClpClassHandler.getInstance().getLoader());
      Constructor<?> cons = cl.getConstructor(argTps);
      Object inst = cons.newInstance(args);
      Method method = cl.getMethod(mname, (Class[])null);
      Object ret = method.invoke(inst, (Object[])null);
      clinfo.setClass(cl);
      clinfo.setInstance(inst);
      clinfo.setMethod(method);
      clinfo.setReturn(ret);
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
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (InstantiationException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return clinfo;
  }

  public ClassInfo javaStaticCall(String clname, String mname, Class<?>[] argTps, Object[] args) {
    ClassInfo clinfo = new ClassInfo();
    try {
      Class<?> cl = Class.forName(clname, false, ClpClassHandler.getInstance().getLoader());
      Method method = cl.getMethod(mname, argTps);
      Object ret = method.invoke(cl, args);
      clinfo.setClass(cl);
      clinfo.setMethod(method);
      clinfo.setReturn(ret);
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
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return clinfo;
  }

  public class ClassInfo {
    private Class<?> clazz;
    private Object instance;
    private Method method;
    private Object returnValue;

    public void setClass(Class<?> cl) {
      this.clazz = cl;
    }
    public void setInstance(Object inst) {
      this.instance = inst;
    }
    public void setMethod(Method method) {
      this.method = method;
    }
    public void setReturn(Object ret) {
      this.returnValue = ret;
    }
    public Class<?> getClazz() {
      return clazz;
    }
    public Object getInstance() {
      return instance;
    }
    public Method getMethod() {
      return method;
    }
    public Object getReturnValue() {
      return returnValue;
    }
  }

  public Object callMethod(Object instance, String mname,
                           Class<?>[] argTps, Object[] args) {
    Class<?> cl = instance.getClass();
    try {
      Method method = cl.getMethod(mname, argTps);
      return method.invoke(instance, args);
    }
    catch (SecurityException e) {
      e.printStackTrace();
    }
    catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Object newInstance(String pack, String clname, Class[] argTypes, Object[] args) {
    Class cl;
    try {
      String name = pack+"."+clname;
      cl = Class.forName(name, false, ClpClassHandler.getInstance().getLoader());
      Constructor<?> cons = cl.getConstructor(argTypes);
      return cons.newInstance(args);
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
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (InstantiationException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  @SuppressWarnings("rawtypes")
  public Object findRTObject(Object obj, Class exclude) {
    Class<? extends Object> cl = obj.getClass();
    String name = cl.getSimpleName();
    try {
      Method method = cl.getMethod("get"+name, (Class[])null);
      return method.invoke(obj, (Object[])null);
    }
    catch (SecurityException e) {
      e.printStackTrace();
    }
    catch (NoSuchMethodException e) {
      Field[] fields = cl.getDeclaredFields();
      for (int i=0; i<fields.length; i++) {
        Field field = fields[i];
        if (!field.getType().equals(ArrayList.class) &&
            !field.getType().equals(String.class) &&
            !field.getType().equals(StringBuffer.class) &&
            !field.getType().equals(int.class) &&
            !field.getType().equals(Stack.class) &&
            !field.getType().equals(exclude)) {
          return field;
        }
      }
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * looks for an instance in 'obj' returning reference of type 'any'
   * @param obj
   * @param any
   * @return
   */
  public Object findRTObject(Object obj, Object any) {
    Class<? extends Object> cl = obj.getClass();
    String retTypeName = any.getClass().getName();
    for (Method method : cl.getDeclaredMethods()) {
      String name = method.getName();
      if (name.startsWith("get") &&
          method.getReturnType().getName().equals(retTypeName)) {
        try {
          return method.invoke(obj, (Object[])null);
        }
        catch (IllegalArgumentException e) {
          e.printStackTrace();
        }
        catch (IllegalAccessException e) {
          e.printStackTrace();
        }
        catch (InvocationTargetException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }
}
