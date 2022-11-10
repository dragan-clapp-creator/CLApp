package clapp.weave.res.stmt;

import java.util.Hashtable;

import clapp.run.util.JavaInvoker;
import clapp.weave.res.ClassWeaver;
import clapp.weave.res.ControlCallWeaver;
import clapp.weave.res.FieldWeaver;
import clapp.weave.res.LocationWeaver;
import clapp.weave.res.NewMethodWeaver;
import clapp.weave.res.stmt.util.ClpWeaverConstants;
import clp.run.res.PVar;

/**
 * this should create, if not already existing within this class, a new method called
 * "private boolean clappSuspend(int interval)" and a global field "private int clappControl".
 * within the current method, "clappSuspend" is called.
 * if it returns true
 *   - a RETURN is called (and the rest of the original method is not executed); the RETURN can have an
 *     argument (one of the following: false, 0, null)
 * the "clappSuspend" method sleeps for the given interval, and, as it awakes, asks for the value of
 * "clappControl".
 * if it's 0, it goes back sleeping;
 * if it's 1, it returns false (allowing thus the process to continue),
 * if it's 2, it returns true (causing the original method to return without executing the code underneath)
 * and if it's -1, it exits the program.
 * 
 */
public class JavaControlCmd implements ClpWeaverConstants {

  private static Hashtable<String, JavaControlCmd> controls = new Hashtable<String, JavaControlCmd>();

  /**
   * static method allows method to be registered for suspension
   * @param lw
   * @param interval
   */
  public static void addSuspendAction(LocationWeaver lw, int interval) {
    JavaControlCmd control = new JavaControlCmd(lw, interval);
    String key = lw.getMethodWeaver().getMethodName() + lw.getLocation().toString();
    controls.put(key, control);
  }

  /**
   * operate weaving
   * @param lw
   * @param interval
   */
  public JavaControlCmd(LocationWeaver lw, int interval) {
    ClassWeaver cw = lw.getMethodWeaver().getClassWeaver();
    if (!cw.isControlImplemented()) {
      declareControllingArtifacts(cw);
      cw.setControlImplemented();
    }
    lw.addMethodAddOn(new ControlCallWeaver());
  }

  //
  private void declareControllingArtifacts(ClassWeaver cw) {
    // add a field named "clappControl"
    cw.addClassItem(new FieldWeaver(CONTROL_FIELD, INT, null));

    // add a method called "setClappControl" and "clappSuspend"
    cw.addClassItem(new NewMethodWeaver());
  }

  //
  private static void setControl(PVar object, Integer val) {
    Class<?>[] argTps = new Class[1];
    argTps[0] = int.class;
    Object[] args = new Object[1];
    args[0] = val;
    JavaInvoker.getInstance().callMethod(object.getValue(), CONTROL_SETTER, argTps, args);
  }

}
