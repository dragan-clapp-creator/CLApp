/*
 * Created on Dec 7, 2004
 *
 */
package clapp.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import clapp.run.api.IMetaScenarioTask;
import clapp.run.api.IScenarioTask;
import clapp.run.sim.api.ISimulator;
import clapp.run.token.EventHandler;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.AInterpreter;
import clapp.run.util.CellChainLink;
import clapp.run.util.CellQueueHandler;
import clapp.run.util.ResourceUtility;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.Heap;
import clp.run.msc.MetaScenarioBody;
import clp.run.res.Resources;
import clp.run.scn.Scenario;
import clp.run.scn.ScenarioBody;
import clp.run.scn.ScnPropBody;
import clp.run.scn.ScnQueue;
import clp.run.scn.ScnTask;
import clp.run.scn.ScnTaskName;



/**
 * @author Dragan Matic
 *
 */
public class Scheduler implements IMetaScenarioTask {

  static private HashMap<String, CellQueueHandler> cqueues;

  private Scenario scn;

  private ArrayList<AInterpreter> taskSequence;

  private boolean isStop;

  //============================================================================

  /**
   * constructor
   * @param sim 
   */
  public Scheduler(Supervisor parent, Scenario scn, ISimulator sim) {
    Thread.currentThread().setName(scn.getName());
    initializeCellQueues(scn, sim);
    this.scn = scn;
    taskSequence = new ArrayList<>();
    isStop = false;
  }


  //============================================================================

  static public CellQueueHandler getCellQueue(String aname, String queue) {
    if (queue == null) {
      return null;
    }
    return cqueues.get(getKey(aname, queue));
  }

  //============================================================================


  /**
   * create the whole scenario actors conform to the distribution rules
   * @param body 
   * @param scn 
   * @param sim 
   */
  public void createScenarioTasks(MetaScenarioBody body, Scenario scn, ISimulator sim) {
    if (scn != null) {
      ScenarioBody scnb = scn.getScenarioBody();
      ScnPropBody props = scnb.getScnPropBody();
      ConsoleProvider.getInstance().print("\nRUN " + scn.getName() + "...");
      System.out.printf("SCHEDULER for %s started\n", scn.getName());
      ArrayList<Actor> actors = scnb.getActors();
      ArrayList<ScnTask> tasks = props.getScnTasks().getScnTasks();
      for (ScnTask task : tasks) {
        for (Actor act : actors) {
          act.setScenario(scn);
          createTask(body, scn, act, task, sim);
        }
      }
      scn.setActive(true);
    }
  }

  public void proceed() {
    if (!isStop) {
      for (AInterpreter task : taskSequence) {
        task.proceed();
        Supervisor.getInstance().flushActivity(getCurrentKey(task));
      }
    }
  }

  //
  private String getCurrentKey(AInterpreter task) {
    if (task instanceof Activator) {
      Actor act = task.getActor();
      String key = scn.getName() + act.getName();
      for (Heap h : act.getHeaps()) {
        if (h.getActivity() > 0) {
          return key;
        }
      }
    }
    return null;
  }


  /**
   * removes the whole scenario actors that were fired
   */
  public void unfire(Scenario scn) {
    if (scn != null) {
      ScenarioBody scnb = scn.getScenarioBody();
      ScnPropBody props = scnb.getScnPropBody();
      ArrayList<Actor> actors = scnb.getActors();
      ArrayList<ScnTask> tasks = props.getScnTasks().getScnTasks();
      for (Actor act : actors) {
        for (ScnTask task : tasks) {
          String name = act.getName() + "_" + task.getScnTaskName().getVal();
          removeTask(name);
        }
      }
    }
  }

  //
  private void removeTask(String name) {
    for (IScenarioTask t : taskSequence) {
      if (name.equals(t.getName())) {
        taskSequence.remove(t);
      }
    }
  }


  /**
   * removes the actor that was fired
   */
  public void unfireActor(Scenario scn, Actor act) {
    if (scn != null) {
      ScenarioBody scnb = scn.getScenarioBody();
      ScnPropBody props = scnb.getScnPropBody();
      ArrayList<ScnTask> tasks = props.getScnTasks().getScnTasks();
      for (ScnTask task : tasks) {
        String name = act.getName() + "_" + task.getScnTaskName().getVal();
        removeTask(name);
      }
      List<ScnQueue> qlist = props.getScnQueues().getScnQueues();
      for (ScnQueue q : qlist) {
        String key = getKey(act.getName(), q.getName());
        if (cqueues.containsKey(key)) {
          cqueues.remove(key);
        }
      }
    }
  }

  //
  private void createTask(MetaScenarioBody body, Scenario scn, Actor act, ScnTask task, ISimulator sim) {
    if (sim == null) {
      act.setActive(true);
    }
    else {
      act.setActive(sim.getActivity(act.getName()));
    }
    String name = act.getName() + "_" + task.getScnTaskName().getVal();
    AInterpreter t = instanciateTask(scn, act, name, task);
    taskSequence.add(t);
    ResourceUtility util = ResourceUtility.getInstance();
    ArrayList<Resources> rs = body.getResourcess();
    ArrayList<Heap> lheap = act.getHeaps();
    for (int h=0; h<lheap.size(); h++) {
      Heap heap = lheap.get(h);
      heap.setActor(act);
      Resources res = util.findResources(rs, heap.getRes());
      heap.setResources(res);
      ArrayList<Cell> cells = heap.getCells();
      if (isQueueNamed(t.getOperatingQueue(), heap)) {
        updateCellsActivity(cells, heap.getActivity());
        t.getOperatingQueue().addToCellList(cells);
      }
      else if (t.isPassingTo() && isQueueNamed(t.getPassingQueue(), heap)) {
        if (heap.getActivity() !=  0) {
          updateCellsActivity(cells, heap.getActivity());
        }
        t.getPassingQueue().addToCellList(cells);
      }
    }
  }

  //
  private boolean isQueueNamed(CellQueueHandler queue, Heap heap) {
    if (queue != null) {
      String otname = queue.getName();
      return heap.getLoad().equals(otname);
    }
    return false;
  }


  /**
   * if a new actor is assigned to a scenario, it will be activated here
   * @param body 
   * @param scn
   * @param act
   */
  public void createAdditionalTasks(MetaScenarioBody body, Scenario scn, Actor act) {
    if (scn != null) {
      ScenarioBody scnb = scn.getScenarioBody();
      ScnPropBody props = scnb.getScnPropBody();
      ArrayList<ScnTask> tasks = props.getScnTasks().getScnTasks();
      for (ScnTask task : tasks) {
        createTask(body, scn, act, task, null);
      }
    }
  }

  public Scenario getScenario() {
    return scn;
  }

  //
  public void updateCellQueues(Scenario scn, Actor act) {
    List<ScnQueue> qlist =
      scn.getScenarioBody().getScnPropBody().getScnQueues().getScnQueues();
    for (ScnQueue q : qlist) {
      String key = getKey(act.getName(), q.getName());
      CellQueueHandler qh = cqueues.get(key);
      if (qh == null) {
        qh = new CellQueueHandler(q);
        cqueues.put(key, qh);
      }
      fillCellQueues(act, qh);
    }
  }

  //
  public void updateCellQueues(Scenario scn, Actor act, Heap heap) {
    List<ScnQueue> qlist =
      scn.getScenarioBody().getScnPropBody().getScnQueues().getScnQueues();
    for (ScnQueue q : qlist) {
      String key = getKey(act.getName(), q.getName());
      CellQueueHandler qh = cqueues.get(key);
      if (qh != null) {
        fillCellQueues(act, heap, qh);
      }
    }
  }


  public boolean remove(ArrayList<ScnQueue> qlist, String aname, Cell c) {
    for (ScnQueue q : qlist) {
      CellQueueHandler qh = cqueues.get(getKey(aname, q.getName()));
      if (qh.remove(c)) {
        CellChainLink.remove(c.getName());
        return true;
      }
    }
    return false;
  }

  //============================================================================

  //
  private static String getKey(String aname, String qname) {
    return aname + "_" + qname;
  }

  //============================================================================

  //
  private void initializeCellQueues(Scenario scn, ISimulator sim) {
    CellChainLink.init();
    List<ScnQueue> ql = scn.getScenarioBody().getScnPropBody().getScnQueues().getScnQueues();
    List<Actor> lact = scn.getScenarioBody().getActors();
    for (Actor act : lact) {
      if (sim == null || sim.getActivity(act.getName())) {
        for (ScnQueue q : ql) {
          CellQueueHandler qh = new CellQueueHandler(q);
          cqueues.put(getKey(act.getName(), q.getName()), qh);
          fillCellQueues(act, qh);
        }
      }
    }
  }

  //
  private void fillCellQueues(Actor act, CellQueueHandler qh) {
    for (Heap h : act.getHeaps()) {
      fillCellQueues(act, h, qh);
    }
  }

  //
  private void fillCellQueues(Actor act, Heap h, CellQueueHandler qh) {
    int activity = h.getActivity();
    if (activity > 0) {
      if (h.getLoad().equals(qh.getName())) {
        
        for (Cell cell : h.getCells()) {
          cell.setActivity(activity);
          qh.addToCellList(cell);
          qh.addRelatedResources(h.getResources());
          EventHandler.getInstance().markCellUp(cell.getName());
        }
      }
    }
    else {
      if (h.getLoad().equals(qh.getName())) {
        qh.addToCellList(h.getCells());
      }
    }
  }

  private void updateCellsActivity(ArrayList<Cell> cellList, int activity) {
    for (Cell cell : cellList) {
      cell.setActivity(activity);
    }
  }

  //
  public AInterpreter instanciateTask(Scenario scn, Actor act, String name, ScnTask task) {
    AInterpreter adx = null;
    if (task.getScnTaskName() == ScnTaskName.ACTIVATOR) {
      // case "A" (Activator)
      adx = new Activator(act, name, task.isPassingTo());
    }
    else if (task.getScnTaskName() == ScnTaskName.DEACTIVATOR) {
      // case "D" (Deactivator)
      adx = new Deactivator(act, name, task.isPassingTo());
    }
    else {
      // case "X" (eXecutor)
      adx = new Executor(act, name, task.isPassingTo());
    }
    return adx;
  }

  public static HashMap<String, CellQueueHandler> getCqueues() {
    return cqueues;
  }

  /**
   * @param cqueues the cqueues to set
   */
  public static void setCqueues(HashMap<String, CellQueueHandler> cqueues) {
    Scheduler.cqueues = cqueues;
  }


  /**
   * @return the taskSequence
   */
  public ArrayList<AInterpreter> getTaskSequence() {
    return taskSequence;
  }


  /**
   * @return the isStop
   */
  public boolean isStop() {
    return isStop;
  }


  /**
   * @param isStop the isStop to set
   */
  public void setStop(boolean isStop) {
    this.isStop = isStop;
  }
}
