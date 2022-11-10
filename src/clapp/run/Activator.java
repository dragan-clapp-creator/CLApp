/*
 * Created on Dec 7, 2004
 *
 */
package clapp.run;

import java.util.ArrayList;
import java.util.Vector;

import clapp.run.token.EventHandler;
import clapp.run.token.TokenHandler;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.AConditionInterpreter;
import clapp.run.util.CellChainLink;
import clapp.run.util.CellChainLink.ActivationLine;
import clapp.run.util.CellQueueHandler;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.log.LogicalTerm;
import clp.run.res.Resources;
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
public class Activator extends AConditionInterpreter {

  private Scenario scn;
  private CellQueueHandler source_queue;
  private CellQueueHandler dest_queue;
  private Vector<CellChainLink> toBeMoved;
  private boolean negativelogic;
  private int logiclevel;
  private boolean isInitialized;
  private TokenHandler tokenHandler;

  public Activator(Actor a, String n, boolean b) {
    super(a, n, b);
    toBeMoved = new Vector<CellChainLink>();
    tokenHandler = TokenHandler.getInstance(a);
  }

  @Override
  public void proceed() {
    if (isInitialized) {
      if (tokenHandler != null && tokenHandler.isInitialized()) {
        tokenHandler.proceed();
      }
      else {
        tryToActivate();
      }
    }
    else {
      isInitialized = initialize();
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
    if (getActor() != null && getActor().getActive()) {
      scn = getActor().getScenario();
      ScnPropBody sprop = scn.getScenarioBody().getScnPropBody();
      ScnLogBody logic = sprop.getScnLogic().getScnLogBody();
      negativelogic = (logic == null ||
                       (logic.isScnLtype() &&
                        logic.getScnLtype().getLtype() == Ltype.NEGATIVE));
      logiclevel = logic.getScnLevel().getLevel();

      ArrayList<ScnTask> ts = sprop.getScnTasks().getScnTasks();
      String aname = getActor().getName();
      for (ScnTask t : ts) {
        ScnTaskName task = t.getScnTaskName();
        if (task == ScnTaskName.ACTIVATOR) {
          String operOn = t.getOperOn();
          String passTo = t.getPassTo();
          source_queue = Scheduler.getCellQueue(aname, operOn);
          dest_queue = Scheduler.getCellQueue(aname, passTo);
          if (tokenHandler != null) {
            ArrayList<Resources> lres = scn.getMetaScenario().getMetaScenarioBody().getResourcess();
            if (lres != null) {
              for (Resources res : lres) {
                if (res.isMarks()) {
                  tokenHandler.initialize(getActor(), source_queue, dest_queue);
                  break;
                }
              }
            }
          }
          return true;
        }
      }
    }
    return false;
  }

  //
  private void tryToActivate() {
    if (!scn.getActive() || !getActor().getActive()) {
      return;
    }
    else {
      CellChainLink ccl = source_queue.getFirstCell();
      while (ccl != null) {
        treatCell(ccl);
        ccl = ccl.getNext();
      }
      moveAll();
    }
  }

  //
  private void moveAll() {
    for (int i=0; i<toBeMoved.size(); i++) {
      CellChainLink ccl = (CellChainLink) toBeMoved.elementAt(i);
      CellQueueHandler.activateCell(ccl, source_queue, dest_queue);
    }
    toBeMoved.clear();
  }

  //
  private void treatCell(CellChainLink ccl) {
    Cell cell = ccl.getCell();

    if (ccl.getActivationLines() != null) {
      Resources res = cell.getBlock().getResources();
      for (ActivationLine al : ccl.getActivationLines()) {
        if ((al.getLevel() == 0 || al.getLevel() == cell.getActivity()) && isActivable(al.getLogicalTerms(), res)) {
          if (negativelogic) {
            cell.setActivity((cell.getActivity()+1)%logiclevel);
          }
          else if (logiclevel > 1) {
            cell.setActivity(logiclevel-1);
          }
          else {
            cell.setActivity(al.getNext());
          }
          if (cell.getActivity() >= logiclevel-1) {
            ConsoleProvider.getInstance().print("activated: " + ccl.getName() + " level: " + cell.getActivity());
            toBeMoved.add(ccl);
            EventHandler.getInstance().markCellUp(cell.getName());
          }
          break;
        }
      }
    }
  }

  //
  private boolean isActivable(ArrayList<LogicalTerm> terms, Resources res) {
    boolean isActivable = false;
    if (terms != null) {
      for (int i=0; !isActivable && i<terms.size(); i++) {
        LogicalTerm term = terms.get(i);
        isActivable = isTermTrue(res, term);
      }
    }
    return isActivable;
  }

  @Override
  public boolean isOpQueueActive() {
    return false;
  }

  @Override
  public boolean isActiveInQueue(String name) {
    return getPassingQueue().foundInQueue(name);
  }

  @Override
  public boolean isInactiveInQueue(String name) {
    return !getPassingQueue().foundInQueue(name);
  }
}
