/*
 * Created on Dec 7, 2004
 *
 */
package clapp.run;

import java.util.ArrayList;

import clapp.run.exe.ExecutingUnit;
import clapp.run.exe.ExecutorsRegistry;
import clapp.run.util.AInterpreter;
import clapp.run.util.CellChainLink;
import clapp.run.util.CellQueueHandler;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.scn.DeactType;
import clp.run.scn.Ltype;
import clp.run.scn.Scenario;
import clp.run.scn.ScnLogBody;
import clp.run.scn.ScnPropBody;
import clp.run.scn.ScnTask;
import clp.run.scn.ScnTaskName;

/**
 * @author Dragan Matic
 *
 */
public class Executor extends AInterpreter {

  private Scenario scn;
  private CellQueueHandler source_queue;
  private CellQueueHandler dest_queue;
  private ArrayList<CellChainLink> toBeMoved;
  private boolean autodeact;
  private boolean positivelogic;
  private boolean isInitialized;
  private int logicLevel;

  public Executor(Actor a, String n, boolean b) {
    super(a, n, b);
    toBeMoved = new ArrayList<CellChainLink>();
    isInitialized = initialize();
  }

  @Override
  public void proceed() {
    if (isInitialized) {
     execute();
    }
  }

  //
  private boolean initialize() {
    Actor act = getActor();
    if (act != null && act.getActive()) {
      scn = act.getScenario();
      ScnPropBody sprop = scn.getScenarioBody().getScnPropBody();
      ScnLogBody logic = sprop.getScnLogic().getScnLogBody();
      autodeact = logic.getScnDeact().getDeactType() == DeactType.AUTO;
      positivelogic = logic.getScnLtype() == null ||
                      logic.getScnLtype().getLtype() == Ltype.POSITIVE;
      logicLevel = logic.getScnLevel().getLevel();

      ArrayList<ScnTask> ts = sprop.getScnTasks().getScnTasks();
      String aname = act.getName();
      for (ScnTask t : ts) {
        if (t.getScnTaskName().equals(ScnTaskName.EXECUTOR)) {
          String operOn = t.getOperOn();
          String passTo = t.getPassTo();
          source_queue = Scheduler.getCellQueue(aname, operOn);
          dest_queue = Scheduler.getCellQueue(aname, passTo);
          return true;
        }
      }
    }
    return false;
  }

  public CellQueueHandler getPassingQueue() {
    return dest_queue;
  }

  public CellQueueHandler getOperatingQueue() {
    return source_queue;
  }

  //
  public void execute() {
    CellChainLink ccl = source_queue.getFirstCell();
    if (ccl != null) {
      while (ccl != null) {
        if (!scn.getActive() || !getActor().getActive()) {
          return;
        }
        if (ccl.getCommands() == null) {
          Cell cell = ccl.getCell();
          if (positivelogic && cell.getActivity() > 1) {
            cell.setActivity(cell.getActivity()-1);
          }
          else {
            cell.setActivity(0);
          }
        }
        else {
          ExecutingUnit unit = ExecutorsRegistry.getInstance().getUnit(ccl, logicLevel, positivelogic, autodeact, toBeMoved);
          if (unit != null) {
            unit.progress();
          }
        }
        ccl = ccl.getNext();
      }
      if (autodeact) {
        moveAll();  // in case of automatic deactivation
      }
    }
  }

  //
  private void moveAll() {
    if (dest_queue != null) {
      for (int i=0; i<toBeMoved.size(); i++) {
        CellChainLink ccl = (CellChainLink) toBeMoved.get(i);
        CellQueueHandler.deactivateCell(ccl, source_queue, dest_queue);
      }
    }
    toBeMoved.clear();
  }
}
