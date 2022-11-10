package clapp.graph;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Stack;

import clapp.run.util.JavaInvoker;


abstract public class Extractor {

  private Stack<Object> stack;
  private ArrayList<Object> buffer;
  private JavaInvoker ji;
  private Object dc;

  public Extractor(JavaInvoker ji, Object dc) {
    stack = new Stack<Object>();
    this.ji = ji;
    this.dc = dc;
  }

  abstract public void handleEvaluation(Object rtobj, ArrayList<Object> buf, JavaInvoker ji, Object dc);

  /**
   * entry point: will be used to extract buffer out of the given RT object
   * @param rtobj
   * @param dc 
   * @param ji 
   * @return 
   */
  public boolean extract(Object rtobj) {
    buffer = getBuffer(rtobj);
    if (buffer == null) {
      return false;
    }
    if (buffer.isEmpty()) {
      fillStack(rtobj);
      if (stack.isEmpty()) {
        return false;
      }
    }
    else {
      handleEvaluation(rtobj, buffer, ji, dc);
      return true;
    }
    boolean ret = false;
    while (!stack.isEmpty()) {
      rtobj = stack.pop();
      ret |= extract(rtobj);
    }
    return ret;
  }

  public Object extractValue(Object obj) {
    Object val = extractValue(obj, "getValue", false);
    if (val == obj) {
      return extractValue(obj, "getPattern", true);
    }
    return val;
  }

  private void fillStack(Object obj) {
    Class<? extends Object> cl = obj.getClass();
    Method[] methods = cl.getDeclaredMethods();
    for (int i=0; i<methods.length; i++) {
      Method method = methods[i];
      if (method.getName().startsWith("get") &&
          (method.getModifiers() & Modifier.PUBLIC) > 0 &&
          (method.getModifiers() & Modifier.STATIC) == 0 &&
          !(method.getReturnType().equals(ArrayList.class))) {
        
        try {
          stack.add(method.invoke(obj, (Object[])null));
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
  }

  @SuppressWarnings("unchecked")
  private ArrayList<Object> getBuffer(Object rtobj) {
    Object val = extractValue(rtobj, "getBuffer", true);
    if (val == rtobj) {
      return null;
    }
    return (ArrayList<Object>) val;
  }

  private Object extractValue(Object obj, String mname, boolean isStrict) {
    Class<? extends Object> cl = obj.getClass();
    try {
      Method method = cl.getMethod(mname, (Class[])null);
      return method.invoke(obj, (Object[])null);
    }
    catch (SecurityException e) {
      e.printStackTrace();
    }
    catch (NoSuchMethodException e) {
      if (!isStrict) {
        for (Method method : cl.getDeclaredMethods()) {
          if (method.getName().startsWith("get") &&
              method.getParameterTypes() == null) {
            try {
              return method.invoke(obj, (Object[])null);
            }
            catch (IllegalArgumentException e1) {
              e1.printStackTrace();
            }
            catch (IllegalAccessException e1) {
              e1.printStackTrace();
            }
            catch (InvocationTargetException e1) {
              e1.printStackTrace();
            }
          }
        }
      }
      return obj;
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
}
