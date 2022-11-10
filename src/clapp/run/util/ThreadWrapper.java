package clapp.run.util;

import clp.run.cel.java.JavaExportPart;
import clp.run.res.Resources;

public class ThreadWrapper extends Thread {

  private String name;
  private String method;
  private JavaExportPart export;
  private Resources res;
  private String returnVariable;

  public ThreadWrapper(ThreadGroup parent, String n, String m, JavaExportPart e, String ret, Resources r) {
    super(parent, n.substring(n.lastIndexOf(".")+1));
    name = n;
    method = m;
    export = e;
    returnVariable = ret;
    res = r;
  }
  public void run() {
    Object mutex = "MUTEX";
    synchronized (mutex) {
      JavaInvoker ji = new JavaInvoker();
      if (method == null) {
        ji.javaCall(name, res);
      }
      else {
        ji.javaCall(name, method, export, returnVariable, res);
      }
    }
  }
}
