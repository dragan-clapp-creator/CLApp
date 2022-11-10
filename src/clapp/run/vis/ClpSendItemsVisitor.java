package clapp.run.vis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import clapp.debug.Logger;
import clapp.debug.Severity;
import clapp.run.util.ClpClassHandler;
import clapp.run.util.ResourceUtility;
import clp.run.cel.web.SendItemsVisitor;
import clp.run.cel.web.SendObject;
import clp.run.cel.web.SendStream;
import clp.run.msc.ClassReference;
import clp.run.msc.SendFile;
import clp.run.res.Encryption;
import clp.run.res.Resources;
import clp.run.res.Variable;

public class ClpSendItemsVisitor implements SendItemsVisitor {

  private Resources res;
  private Class<?> cryptClass;
  private boolean isFile;
  private String fileName;
  private StringBuffer buffer;

  public ClpSendItemsVisitor(Resources r, Encryption enc) {
    res = r;
    if (enc != null) {
      ClassReference cr = enc.getClazz();
      String cl = cr.getPack() + "." + cr.getClazz();
      try {
        cryptClass = ClpClassHandler.getInstance().loadClass(cl);
      }
      catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      catch (SecurityException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void visitSendFile(SendFile x) {
    isFile = true;
    fileName = x.getFileName();
  }

  @Override
  public void visitSendStream(SendStream x) {
    isFile = false;
    buffer = new StringBuffer();
    SendObject o = x.getSendObject();
    if (o != null) {
      ArrayList<SendObject> list = new ArrayList<>();
      list.add(x.getSendObject());
      list.addAll(x.getSendObjects());
      for (SendObject obj : list) {
        String svar = obj.getVar();
        if (svar != null) {
          Variable var = ResourceUtility.getInstance().getVariable(res, svar);
          if (var != null) {
            if (cryptClass != null) {
              buffer.append( useCryption(var) );
            }
            else {
              buffer.append( directBuffering(var) );
            }
            buffer.append("\n");
          }
        }
        else {
          String item = obj.getItem();
          if (item != null) {
            if (!item.startsWith("set")) {
              buffer.append("//");
            }
            buffer.append(item);
          }
        }
      }
    }
  }

  public boolean isFile() {
    return isFile;
  }

  public String getFileName() {
    return fileName;
  }

  public String getItems() {
    return buffer.toString();
  }

  //----------------------------------------------------------------------------

  @SuppressWarnings({ "rawtypes", "null" })
  private StringBuffer useCryption(Variable var) {
    ResourceUtility util = ResourceUtility.getInstance();
    String name = util.getName(var);
    Class[] argTps = new Class[2];
    argTps[0] = String.class;
    argTps[1] = util.getClass( var );
    Object[] args = new Object[2];
    args[0] = name;
    Variable r = util.getVariable(res, name);
    try {
      if (util.hasArray(r)) {
        args[1] = ResourceUtility.getInstance().getValue(r);
      }
      else {
        args[1] = ResourceUtility.getInstance().getValue(r);
      }
      Method cryptMethod = null; // TODO: add interface //cryptClass.getMethod(method, argTps);
      StringBuffer ret;
      if ((cryptMethod.getModifiers() & Modifier.STATIC) != 0) {
        ret = (StringBuffer) cryptMethod.invoke(cryptClass, args);
      }
      else {
        Method instanceMethod = cryptClass.getMethod("getInstance", (Class[])null);
        Object ref = instanceMethod.invoke(cryptClass, (Object[])null);
        ret = (StringBuffer) cryptMethod.invoke(ref, args);
      }
      return ret;
    }
    catch (SecurityException e) {
      e.printStackTrace();
      Logger.getInstance().log("ClpSendItemsVisitor:useCryption (exc) "+e, Severity.ERROR);
    }
    catch (NoSuchMethodException e) {
      e.printStackTrace();
      Logger.getInstance().log("ClpSendItemsVisitor:useCryption (exc) "+e, Severity.ERROR);
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
      Logger.getInstance().log("ClpSendItemsVisitor:useCryption (exc) "+e, Severity.ERROR);
    }
    catch (IllegalAccessException e) {
      e.printStackTrace();
      Logger.getInstance().log("ClpSendItemsVisitor:useCryption (exc) "+e, Severity.ERROR);
    }
    catch (InvocationTargetException e) {
      e.printStackTrace();
      Logger.getInstance().log("ClpSendItemsVisitor:useCryption (exc) "+e, Severity.ERROR);
    }
    catch (RuntimeException e) {
      e.printStackTrace();
      Logger.getInstance().log("ClpSendItemsVisitor:useCryption (exc) "+e, Severity.ERROR);
    }
    return null;
  }

  private StringBuffer directBuffering(Variable var) {
    ResourceUtility util = ResourceUtility.getInstance();
    StringBuffer buf = new StringBuffer();
    buf.append("/");
    buf.append(util.getName(var));
    buf.append("/");
    if (util.hasArray(var)) {
      buf.append(util.getValues(var));
    }
    else {
      buf.append(util.getValue(var));
    }
    buf.append("\n\n");
    return buf;
  }
}
