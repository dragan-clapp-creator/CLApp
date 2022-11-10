package clapp.run.vis;

import clapp.run.Supervisor;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.ClpJavaEnqueuer;
import clapp.run.util.JavaInvoker;
import clapp.run.util.ResourceUtility;
import clp.run.cel.java.ClassName;
import clp.run.cel.java.JavaCallNew;
import clp.run.cel.java.JavaCallStart;
import clp.run.cel.java.JavaCallee;
import clp.run.cel.java.JavaExportPart;
import clp.run.cel.java.JavaStatementInterfaceVisitor;
import clp.run.cel.java.MethodName;
import clp.run.res.Resources;
import clp.run.res.Variable;

public class ClpJavaStatementInterfaceVisitor implements JavaStatementInterfaceVisitor {

  private Resources res;
  private String cname;
  private String mname;
  private Object pvar;

  public ClpJavaStatementInterfaceVisitor(Resources res) {
    this.res = res;
  }

  @Override
  public void visitJavaCallNew(JavaCallNew x) {
    String returnVariable = null;
    JavaExportPart exp = null;
    if (!extractClassName(x.getClassName())) {
      ConsoleProvider.getInstance().eprint("JAVA: class name not found");
    }
    else {
      exp = x.getJavaExportPart();
      returnVariable = x.getVariable();
    }
    // je ne sais plus pourquoi c'est, mais je le garde pour le moment
    if (Supervisor.hasJavaEnqueuer()) {
      Object mutex = "JAVANEW";
      ClpJavaEnqueuer enqueuer = Supervisor.getJavaEnqueuer();
      synchronized (mutex) {
        enqueuer.enqueue(res);
        enqueuer.enqueue(returnVariable);
        enqueuer.enqueue(exp);
        enqueuer.enqueue(cname);
        enqueuer.enqueue("JAVANEW");
      }
    }
    else {
      JavaInvoker.getInstance().javaNew(cname, exp, returnVariable, res);
    }
  }

  @Override
  public void visitJavaCallStart(JavaCallStart x) {
    if (x.isStart()) {
      String returnVariable = null;
      JavaExportPart exp = null;
      String method = null;
      if (!extractClassName(x.getJavaCall().getClassName())) {
        ConsoleProvider.getInstance().eprint("JAVA: class name not found");
      }
      else {
        JavaCallee callee = x.getJavaCall().getJavaCallee();
        method = extractMethodName(callee.getMethodName());
        exp = callee.getJavaExportPart();
        returnVariable = callee.getVariable();
      }
      JavaInvoker.getInstance().javaStart(cname, method, exp, returnVariable, res);
    }
    else {
      String returnVariable = null;
      JavaExportPart exp = null;
      String method = null;
      boolean isName= (extractClassName(x.getJavaCall().getClassName()));

      if (pvar == null) {
        ConsoleProvider.getInstance().eprint("JAVA: class name not found");
        return;
      }
      JavaCallee callee = x.getJavaCall().getJavaCallee();
      method = extractMethodName(callee.getMethodName());
      exp = callee.getJavaExportPart();
      returnVariable = callee.getVariable();

      if (Supervisor.hasJavaEnqueuer()) {
        Object mutex = "JAVACALL";
        ClpJavaEnqueuer enqueuer = Supervisor.getJavaEnqueuer();
        synchronized (mutex) {
          enqueuer.enqueue(res);
          enqueuer.enqueue(returnVariable);
          enqueuer.enqueue(exp);
          enqueuer.enqueue(method);
          if (isName) {
            enqueuer.enqueue(cname);
            enqueuer.enqueue("JAVACALL");
          }
          else {
            enqueuer.enqueue(pvar);
            enqueuer.enqueue("JAVAREFCALL");
          }
        }
      }
      else if (isName) {
        if (method != null) {
          JavaInvoker.getInstance().javaCall(cname, method, exp, returnVariable, res);
        }
        else {
          JavaInvoker.getInstance().javaCall(cname, res);
        }
      }
      else {
        if (method != null) {
          JavaInvoker.getInstance().javaCall(pvar, method, exp, returnVariable, res);
        }
        else {
          ConsoleProvider.getInstance().eprint("JAVA: wrong class call "+pvar);
        }
      }
    }
  }

  //============================================================================

  //
  private String extractMethodName(MethodName method) {
    if (method == null) {
      mname = "main";
    }
    else {
      if (method.getMethod() == null) {
        mname = method.getMethodString();
      }
      else {
        mname = method.getMethod();
      }
    }
    return mname;
  }

  //
  private boolean extractClassName(ClassName clname) {
    cname = null;
    if (clname.getClname() == null) {
      cname = clname.getClassString();
    }
    else {
      Variable var = ResourceUtility.getInstance().getVariable(res, clname.getClname());
      pvar = ResourceUtility.getInstance().getValue(var);
    }
    return cname != null;
  }

  public Object getPvar() {
    return pvar;
  }
}
