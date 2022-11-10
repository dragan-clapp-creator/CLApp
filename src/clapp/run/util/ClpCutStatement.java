package clapp.run.util;

import java.util.ArrayList;

import clapp.run.Scheduler;
import clapp.run.Supervisor;
import clapp.run.ui.util.ConsoleProvider;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.Heap;
import clp.run.msc.MetaScenario;
import clp.run.msc.MetaScenarioBody;
import clp.run.res.Resources;
import clp.run.scn.Scenario;
import clp.run.scn.ScnQueue;

public class ClpCutStatement {

  private MetaScenario msc;
  private String node;

  /**
   * CONSTRUCTOR
   * @param m
   * @param n
   */
  public ClpCutStatement(MetaScenario m, String n) {
    msc = m;
    node = n;
  }

  /**
   * given a node name, this method seeks for a matching node and cuts it from
   * the structure after having stopped all running stuff around it
   */
  public void cut() {
    MetaScenarioBody mscb = msc.getMetaScenarioBody();

    ArrayList<Resources> lres = mscb.getResourcess();
    for (int i=0; i<lres.size(); i++) {
      Resources res = lres.get(i);
      if (res.getName().equals(node)) {
        cutResources(mscb, res);
        return;
      }
    }
    ArrayList<Scenario> lscn = mscb.getScenarios();
    for (int k=0; k<lscn.size(); k++) {
      Scenario scn = lscn.get(k);
      ArrayList<Actor> la = scn.getScenarioBody().getActors();
      if (scn.getName().equals(node)) {
        cutAllActors(scn, la);
        cutScenario(msc, scn);
        break;
      }
      else {
        findAndCutActor(scn, la);
      }
    }
  }

  //
  private void findAndCutActor(Scenario scn, ArrayList<Actor> la) {
    for (int i=0; i<la.size(); i++) {
      Actor a = la.get(i);
      if (a.getName().equals(node)) {
        cutActor(scn, a);
      }
      else {
        ArrayList<Heap> lh = a.getHeaps();
        findAndCutHeap(scn, a, lh);
      }
    }
  }

  //
  private void findAndCutHeap(Scenario scn, Actor a, ArrayList<Heap> lh) {
    for (int i=0; i<lh.size(); i++) {
      Heap h = lh.get(i);
      if (h.getName().equals(node)) {
        cutHeap(scn, a, h);
      }
      else {
        ArrayList<Cell> lc = h.getCells();
        findAndCutCell(scn, h, lc);
      }
    }
  }

  //
  private void findAndCutCell(Scenario scn, Heap h, ArrayList<Cell> lc) {
    for (int i=0; i<lc.size(); i++) {
      Cell c = lc.get(i);
      if (c.getName().equals(node)) {
        cutCell(scn, h, c);
      }
    }
  }

  //
  private void cutAllActors(Scenario scn, ArrayList<Actor> la) {
    for (int i=0; i<la.size(); i++) {
      Actor a = la.get(i);
      cutActor(scn, a);
    }
  }

  //
  private void cutCell(Scenario scn, Heap h, Cell c) {
    Scheduler sched = Supervisor.getInstance().getScheduler(scn.getName());
    ArrayList<ScnQueue> qlist =
      scn.getScenarioBody().getScnPropBody().getScnQueues().getScnQueues();
    if (!sched.remove(qlist, h.getActor().getName(), c)) {
      ConsoleProvider.getInstance().eprint("ERROR: on removing cell "+c.getName());
    }
    h.removeCell(c);
  }

  //
  private void cutHeap(Scenario scn, Actor a, Heap h) {
    Scheduler sched = Supervisor.getInstance().getScheduler(scn.getName());
    ArrayList<ScnQueue> qlist =
      scn.getScenarioBody().getScnPropBody().getScnQueues().getScnQueues();
    ArrayList<Cell> lc = h.getCells();
    for (int i=0; i<lc.size(); i++) {
      Cell c = lc.get(i);
      if (!sched.remove(qlist, a.getName(), c)) {
        ConsoleProvider.getInstance().eprint("ERROR: on removing cell "+c.getName());
      }
    }
    a.removeHeap(h);
  }

  //
  private void cutActor(Scenario scn, Actor a) {
    ArrayList<Heap> lheap = a.getHeaps();
    for (int i=0; i<lheap.size(); i++) {
      Heap h = lheap.get(i);
      cutHeap(scn, a, h);
    }
    Supervisor.getInstance().remove(scn, a);
    scn.getScenarioBody().removeActor(a);
  }

  //
  private void cutScenario(MetaScenario msc, Scenario scn) {
    ArrayList<Actor> la = scn.getScenarioBody().getActors();
    for (int i=0; i<la.size(); i++) {
      Actor a = la.get(i);
      cutActor(scn, a);
    }
    Supervisor.getInstance().remove(scn);
    msc.getMetaScenarioBody().removeScenario(scn);
  }

  //
  private void cutResources(MetaScenarioBody mscb, Resources res) {
    ArrayList<Scenario> lscn = mscb.getScenarios();
    for (int k=0; k<lscn.size(); k++) {
      Scenario scn = lscn.get(k);
      ArrayList<Actor> lact = scn.getScenarioBody().getActors();
      for (int i=0; i<lact.size(); i++) {
        Actor act = lact.get(i);
        ArrayList<Heap> lheap = act.getHeaps();
        for (int j=0; j<lheap.size(); j++) {
          Heap h = lheap.get(j);
          if (h.isRes() && h.getResources().equals(res)) {
            cutHeap(scn, act, h);
          }
        }
      }
    }
    mscb.removeResources(res);
  }

}
