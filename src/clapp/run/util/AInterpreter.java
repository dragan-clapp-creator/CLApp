package clapp.run.util;

import clapp.run.api.IScenarioTask;
import clapp.run.ui.util.ConsoleProvider;
import clp.run.act.Actor;

public abstract class AInterpreter implements IScenarioTask {

  private Actor actor;
  private String name;
  private boolean isPassingTo;

  public AInterpreter(Actor a, String n, boolean b) {
    actor = a;
    name = n;
    isPassingTo = b;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the actor
   */
  public Actor getActor() {
    return actor;
  }

  public boolean isPassingTo() {
    return isPassingTo;
  }

  // wait for method loops for a given time of milliseconds
  public static void waitfor(int millisec) {
    try {
      Thread.sleep(millisec);
    }
    catch (Throwable t) {
      ConsoleProvider.getInstance().eprint("wait did not work");
    }
  }
}
