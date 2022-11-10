package clapp.run.util;

import java.util.Stack;

import clapp.run.ui.util.ConsoleProvider;
import clp.run.cel.java.JavaExportPart;
import clp.run.res.Resources;

public class ClpJavaEnqueuer extends Thread {

  @SuppressWarnings("rawtypes")
  private Stack queue;

  @SuppressWarnings("rawtypes")
  public ClpJavaEnqueuer() {
    queue = new Stack();
  }

  public void run() {
    while (true) {
      if (!queue.isEmpty()) {
        treatCommand();
      }
      waitfor(50);
    }
  }

  private void treatCommand() {
    String cmd = (String) queue.pop();
    if (cmd.equalsIgnoreCase("JAVACALL")) {
      String cname = (String) queue.pop();
      String method = (String) queue.pop();
      JavaExportPart exp = (JavaExportPart) queue.pop();
      String returnVariable = (String) queue.pop();
      final Resources res = (Resources) queue.pop();
      if (method != null) {
        JavaInvoker.getInstance().javaCall(cname, returnVariable, exp, method, res);
      }
      else {
        JavaInvoker.getInstance().javaCall(cname, res);
      }
    }
    else {
      String cname = (String) queue.pop();
      JavaExportPart exp = (JavaExportPart) queue.pop();
      String returnVariable = (String) queue.pop();
      final Resources res = (Resources) queue.pop();
      JavaInvoker.getInstance().javaNew(cname, exp, returnVariable, res);
    }
  }

  @SuppressWarnings("unchecked")
  public void enqueue(Object obj) {
    queue.push(obj);
  }

  private void waitfor(int millisec) {
    try {
      sleep(millisec);
    }
    catch (Throwable t) {
      ConsoleProvider.getInstance().eprint("wait did not work");
    }
  }
}
