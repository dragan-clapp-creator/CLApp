/*
 * Created on Dec 7, 2004
 *
 */
package clapp.run;

import java.util.ArrayList;
import java.util.HashMap;

import clapp.cmp.ClappMain;
import clapp.run.api.IMetaScenarioTask;
import clapp.run.token.EventHandler;
import clapp.run.util.AInterpreter;
import clapp.run.util.CellQueueHandler;
import clapp.weave.res.CLAppResourceHandler;
import clp.run.act.Actor;
import clp.run.cel.Heap;
import clp.run.scn.Scenario;



/**
 * @author Dragan Matic
 *
 */
public class ScheduleHandler extends Thread implements IMetaScenarioTask {

  private ArrayList<Scheduler> scheds;
  private ClappMain clapp;
  private boolean isSuspended;

  //============================================================================

  /**
   * constructor
   * @param clapp 
   */
  public ScheduleHandler(ClappMain clapp) {
    scheds = new ArrayList<>();
    this.clapp = clapp;
  }


  //============================================================================

  public void register(Scheduler s) {
    scheds.add(s);
  }

  public void initialize(ClappMain clp) {
    scheds.clear();
    HashMap<String, CellQueueHandler> cqueues = Scheduler.getCqueues();
    if (cqueues != null) {
      cqueues.clear();
    }
    clapp = clp;
  }

  //============================================================================

  @Override
  public void run() {
    isSuspended = false;
    boolean isTrue = true;
    int current = 0;
    while (isTrue) {
      if (!scheds.isEmpty() && !isSuspended()) {
        if (current < scheds.size()) {
          scheds.get(current).proceed();
          current++;
        }
        else if (isAllStopped()) {
          return;
        }
        else {
          notifyAllActivities();
          EventHandler.getInstance().resetEvents();
          EventHandler.getInstance().performPeriodicAction();
          CLAppResourceHandler.getInstance().clearVariables();
          current = 0;
        }
      }
      AInterpreter.waitfor(50);
    }
  }

  //
  private void notifyAllActivities() {
    for (Scenario scn : clapp.getMetaScenario().getMetaScenarioBody().getScenarios()) {
      for (Actor act : scn.getScenarioBody().getActors()) {
        String key = scn.getName() + act.getName();
        for (Heap h : act.getHeaps()) {
          if (h.getActivity() > 0) {
            Supervisor.getInstance().notifyActivity(key, Scheduler.getCellQueue(act.getName(), h.getLoad()));
          }
        }
      }
    }
  }

  //
  private boolean isAllStopped() {
    for (Scheduler s : scheds) {
      if (!s.isStop()) {
        return false;
      }
    }
    return true;
  }

  /**
   * @return the isSuspended
   */
  public synchronized boolean isSuspended() {
    return isSuspended;
  }

  /**
   * @param isSuspended the isSuspended to set
   */
  public synchronized void setSuspended(boolean isSuspended) {
    this.isSuspended = isSuspended;
  }
}
