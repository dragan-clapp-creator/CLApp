/*
 * Created on Dec 7, 2004
 *
 */
package clapp.run;

import java.util.ArrayList;
import java.util.Vector;

import clapp.run.exe.ExecutingUnit;
import clapp.run.exe.ExecutorsRegistry;
import clapp.run.token.EventHandler;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.AConditionInterpreter;
import clapp.run.util.CellChainLink;
import clapp.run.util.CellQueueHandler;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.log.LogicalExpression;
import clp.run.res.Resources;
import clp.run.scn.Ltype;
import clp.run.scn.Scenario;
import clp.run.scn.ScnLogBody;
import clp.run.scn.ScnTask;
import clp.run.scn.ScnTaskName;



/**
 * @author Dragan Matic
 *
 */
public class Deactivator extends AConditionInterpreter {

  private Scenario scn;
  private CellQueueHandler source_queue;
  private CellQueueHandler dest_queue;
  private Vector<CellChainLink> toBeMoved;
  private boolean positivelogic;
  private boolean isInitialized;

  public Deactivator(Actor a, String n, boolean b) {
    super(a, n, b);
    toBeMoved = new Vector<CellChainLink>();
    isInitialized = initialize();
  }

  @Override
  public void proceed() {
    if (isInitialized) {
      tryToDeactivate();
    }
  }

  public CellQueueHandler getPassingQueue() {
    return dest_queue;
  }

  public CellQueueHandler getOperatingQueue() {
    return source_queue;
  }

  //
  private boolean initialize() {
    Actor act = getActor();
    if (act != null && act.getActive()) {
      scn = act.getScenario();
      ScnLogBody logic = scn.getScenarioBody().getScnPropBody().getScnLogic().getScnLogBody();
      positivelogic = logic.getScnLtype() == null || logic.getScnLtype().getLtype() == Ltype.POSITIVE;

      ArrayList<ScnTask> ts = scn.getScenarioBody().getScnPropBody().getScnTasks().getScnTasks();
      for (ScnTask t : ts) {
        if (t.getScnTaskName().equals(ScnTaskName.DEACTIVATOR)) {
          source_queue = Scheduler.getCellQueue(act.getName(), t.getOperOn());
          dest_queue = Scheduler.getCellQueue(act.getName(), t.getPassTo());
          return true;
        }
      }
    }
    return false;
  }

  //
  private void tryToDeactivate() {
    CellChainLink ccl = source_queue.getFirstCell();
    if (ccl != null) {
      while (ccl != null) {
        if (!scn.getActive() || !getActor().getActive()) {
          return;
        }
       ArrayList<LogicalExpression> expressions = ccl.getDeactivationExp();
        if (expressions != null) {
          treatCell(ccl, expressions);
        }
        ccl = ccl.getNext();
      }
      moveAll();
    }
  }

  //
  private void moveAll() {
    for (int i=0; i<toBeMoved.size(); i++) {
      CellChainLink ccl = (CellChainLink) toBeMoved.elementAt(i);
      CellQueueHandler.deactivateCell(ccl, source_queue, dest_queue);
    }
    toBeMoved.clear();
  }

  //
  public void treatCell(CellChainLink ccl, ArrayList<LogicalExpression> expressions) {
    Cell cell = ccl.getCell();
    Resources res = cell.getBlock().getResources();
    for (LogicalExpression expression : expressions) {
      if (deactivable(ccl, res, expression, cell.getActivity())) {
        if (positivelogic && cell.getActivity() > 1) {
          cell.setActivity(cell.getActivity()-1);
        }
        else {
          cell.setActivity(0);
        }
        if (cell.getActivity() <= 0) {
          ExecutingUnit unit = ExecutorsRegistry.getInstance().getUnit(ccl);
          if (unit != null) {
            ConsoleProvider.getInstance().print("interrupted: " + unit.toString());
            unit.interrupt();
          }
          ConsoleProvider.getInstance().print("deactivated: " + ccl.getName());
          toBeMoved.add(ccl);
          EventHandler.getInstance().markCellDown(cell.getName());
        }
        return;
      }
    }
  }

  //
  private boolean deactivable(CellChainLink ccl, Resources res, LogicalExpression exp, int cellActivity) {
    return isDeactivationConditionTrue(ccl, res, exp, cellActivity);
  }

  @Override
  public boolean isOpQueueActive() {
    return true;
  }

  @Override
  public boolean isActiveInQueue(String name) {
    Boolean isUp = EventHandler.getInstance().isUp(name);
    if (isUp == null) {
      return getOperatingQueue().foundInQueue(name);
    }
    return isUp;
  }

  @Override
  public boolean isInactiveInQueue(String name) {
    Boolean isDown = EventHandler.getInstance().isDown(name);
    if (isDown != Boolean.TRUE) {
      return !getOperatingQueue().foundInQueue(name);
    }
    return isDown;
  }
}
