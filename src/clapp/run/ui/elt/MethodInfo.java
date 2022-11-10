package clapp.run.ui.elt;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import clapp.run.util.ResourceUtility;
import clp.run.res.Variable;

public class MethodInfo implements Serializable {

  private static final long serialVersionUID = 5650805122386126137L;

  enum ArgType {
    CST, SIMPLEVAR, VAR_WITH_INDEX, VAR_WITH_2INDEXES;
  }

  private Class<?> agentClass;
  private String methodName;
  private Class<?> returnVariableType;
  private ArrayList<Class> vtypes;
  private ArrayList<Argument> arguments;
  private Method method;

  public MethodInfo(Class<?> c) {
    agentClass = c;
    arguments = new ArrayList<MethodInfo.Argument>();
  }

  public Class<?> getAgentClass() {
    return agentClass;
  }

  public void setMethodName(String n) {
    methodName = n;
  }

  public void setReturnType(Class<?> r) {
    returnVariableType = r;
  }

  public void setArgumentTypes(ArrayList<Class> t) {
    vtypes = t;
  }

  public String getMethodName() {
    return methodName;
  }

  public Class<?> getReturnVariableType() {
    return returnVariableType;
  }

  public ArrayList<Class> getVtypes() {
    return vtypes;
  }

  public void setMethod(Method m) {
    method = m;
  }

  public Method getMethod() {
    return method;
  }

  public void addArgument(String cst) {
    arguments.add(new Argument(ArgType.CST, cst));
  }

  public void addArgument(String var, int index, int index2) {
    arguments.add(new Argument(ArgType.VAR_WITH_2INDEXES, var, index, index2));
  }

  public void addArgumentRef(String var) {
    arguments.add(new Argument(ArgType.SIMPLEVAR, var));
  }

  public Object getValue(int i) {
    Argument arg = arguments.get(i);
    switch (arg.argType) {
      case CST:
        return arg.value;
      case SIMPLEVAR:
        return ResourceUtility.getInstance().getValue(arg.value);
      case VAR_WITH_2INDEXES:
        Variable v = ResourceUtility.getInstance().getVariable(arg.value);
        Object value = ResourceUtility.getInstance().getValue(v);
        if (value instanceof ArrayList) {
          Object val = ((ArrayList)value).get(arg.index);
          if (val instanceof Hashtable) {
            int ind = arg.index2;
            for (Iterator it=((Hashtable)val).values().iterator(); it.hasNext();) {
              Object obj = it.next();
              ind--;
              if (ind < 0) {
                return obj;
              }
            }
          }
        }
        break;
      case VAR_WITH_INDEX:
        break;
    }
    return null;
  }

  public void setValue(int i, Object newval) {
    Argument arg = arguments.get(i);
    switch (arg.argType) {
      case CST:
        break;
      case SIMPLEVAR:
        ResourceUtility.getInstance().setValue(arg.value, newval);
        break;
      case VAR_WITH_2INDEXES:
        Variable v = ResourceUtility.getInstance().getVariable(arg.value);
        Object value = ResourceUtility.getInstance().getValue(v);
        if (value instanceof ArrayList) {
          Object val = ((ArrayList)value).get(arg.index);
          if (val instanceof Hashtable) {
            int ind = arg.index2;
            for (Iterator it=((Hashtable)val).keySet().iterator(); it.hasNext();) {
              Object obj = it.next();
              ind--;
              if (ind < 0) {
                ((Hashtable)val).put(obj, newval);
                break;
              }
            }
          }
        }
        break;
      case VAR_WITH_INDEX:
        break;
    }
  }

  //========================================================

  public class Argument {

    private ArgType argType;
    private String value;
    private int index;
    private int index2;

    public Argument(ArgType at, String cst) {
      argType = at;
      value = cst;
    }

    public Argument(ArgType at, String v, int i1, int i2) {
      argType = at;
      value = v;
      index = i1;
      index2 = i2;
    }
    
  }
}
