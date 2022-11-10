package clapp.cmp;

import java.util.ArrayList;

import clapp.run.token.EventHandler;
import clapp.run.token.MarkHandler;
import clapp.run.util.ClpClassHandler;
import clapp.run.util.ClpUpdateFromSettingVisitor;
import clapp.run.util.ResourceUtility;
import clapp.weave.res.CLAppResourceHandler;
import clapp.weave.res.ClassWeaver;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.Heap;
import clp.run.msc.MetaScenario;
import clp.run.res.Binpath;
import clp.run.res.CellEvent;
import clp.run.res.Event;
import clp.run.res.EventVisitor;
import clp.run.res.Events;
import clp.run.res.Jarpath;
import clp.run.res.Marks;
import clp.run.res.Resources;
import clp.run.res.Setter;
import clp.run.res.Setting;
import clp.run.res.Unit;
import clp.run.res.UsedJava;
import clp.run.res.UsedJavaVisitor;
import clp.run.res.UsedLib;
import clp.run.res.VarEvent;
import clp.run.res.Variable;
import clp.run.res.weave.WeaveVar;
import clp.run.scn.Scenario;

public class ClappBinder {

  public void chainAll(MetaScenario msc) {
    ArrayList<Resources> rlist = msc.getMetaScenarioBody().getResourcess();
    for (Scenario scn : msc.getMetaScenarioBody().getScenarios()) {
      for (Actor act : scn.getScenarioBody().getActors()) {
        for (Heap heap : act.getHeaps()) {
          for (Cell cell : heap.getCells()) {
            cell.setBlock(heap);
          }
          Resources r = getResources(rlist, heap.getRes());
          heap.setResources(r);
        }
        act.setScenario(scn);
      }
      scn.setMetaScenario(msc);
    }
    for (Resources res : rlist) {
      res.setMetaScenario(msc);
      updateUsedLib(res);
      updateVariables(res, res.getVariables(), true);
      weaveResources(res);
      registerEvents(res.getEvents(), true);
      setMarks(msc, res.getMarks());
    }
  }

  public void updateAll(ArrayList<Scenario> scenarios, ArrayList<Resources> rlist) {
    for (Scenario scn : scenarios) {
      for (Actor act : scn.getScenarioBody().getActors()) {
        for (Heap heap : act.getHeaps()) {
          for (Cell cell : heap.getCells()) {
            cell.setBlock(heap);
          }
          Resources r = getResources(rlist, heap.getRes());
          heap.setResources(r);
        }
      }
    }
    for (Resources res : rlist) {
      updateUsedLib(res);
      updateVariables(res, res.getVariables(), false);
      weaveResources(res);
      registerEvents(res.getEvents(), false);
      setMarks(res.getMetaScenario(), res.getMarks());
    }
  }


  //
  private void updateUsedLib(Resources res) {
    final ClpClassHandler handler = ClpClassHandler.getInstance();
    if (res.isUsedLib() || res.getUsedLib() != null) {
      UsedLib lib = res.getUsedLib();
      UsedJava uj = lib.getUsedJava();
      ArrayList<UsedJava> jlist = new ArrayList<>();
      if (uj != null) {
        jlist.add(uj);
      }
      jlist.addAll(lib.getUsedJavas());
      for (UsedJava java : jlist) {
        registerJava(handler, java);
      }
    }
    handler.initializeLoader();
  }

  private void registerJava(final ClpClassHandler handler, UsedJava java) {
    java.accept(new UsedJavaVisitor() {
      @Override
      public void visitJarpath(Jarpath x) {
        handler.registerJar( x.getJar() );
      }
      @Override
      public void visitBinpath(Binpath x) {
        handler.registerBin( x.getDir() );
      }
    });
  }

  //
  private void updateVariables(Resources res, ArrayList<Variable> vars, boolean isInit) {
    ResourceUtility util = ResourceUtility.getInstance();
    for (Variable var : vars) {
      Variable v = util.getVariable(res, var);
      if (v == null) {
        util.resetValue(var);
        res.addVariable(var);
      }
      else if (v == var) {
        if (isInit) {
          util.resetValue(v);
        }
      }
      else {
        Object init = util.getInitialValue(var);
        util.setInitialValue(v, init);
        util.resetValue(v);
      }
    }
  }

  //
  private void weaveResources(Resources res) {
    for (Variable v : res.getVariables()) {
      if (v instanceof WeaveVar) {
        WeaveVar wv = (WeaveVar)v;
        ClassWeaver.prepareDesignWeaving(wv, res);
        ClassWeaver.weaveAndPersist();
      }
    }
  }

  //
  private void registerEvents(Events evts, boolean isInit) {
    if (evts != null) {
      ArrayList<Event> list = new ArrayList<>();
      if (evts.getEvent() != null) {
        list.add(evts.getEvent());
      }
      list.addAll(evts.getEvents());
      for (Event event : list) {
        if (isInit || !EventHandler.getInstance().isRegistered(event)) {
          event.accept(new EventVisitor() {
            @Override
            public void visitVarEvent(VarEvent x) {
              EventHandler.getInstance().registerVar(x.getName());
            }
            @SuppressWarnings("incomplete-switch")
            @Override
            public void visitCellEvent(CellEvent x) {
              if (x.isTime()) {
                long delta = x.getDelay();
                Unit unit = x.getUnit();
                switch (unit) {
                  case HOURS:
                    delta *= 3600000;
                    break;
                  case SECONDS:
                    delta *= 1000;
                    break;
                  case MILLIS:
                    break;
                }
                EventHandler.getInstance().registerEventCell(x.getCellName(), x.getTime(), delta, x.getCycle());
              }
              else {
                EventHandler.getInstance().registerEventCell(x.getCellName(), null, 0, x.getCycle());
              }
            }
          });
        }
      }
    }
  }

  //
  private void setMarks(MetaScenario msc, Marks marks) {
    if (marks != null) {
      MarkHandler.getInstance().setInitialMarks(msc, marks);
    }
  }


  public void deploySetters(MetaScenario msc, ArrayList<Setter> setters) {
    ArrayList<Resources> rlist = msc.getMetaScenarioBody().getResourcess();
    for (Setter setter : setters) {
      Resources res = getResources(rlist, setter.getRes());
      if (res != null) {
        updateSettings( res, setter.getSettings() );
      }
    }
    CLAppResourceHandler.getInstance().setNbCycles(3);
  }

  //
  private void updateSettings(final Resources res, ArrayList<Setting> settings) {
    for (int i=0; i<settings.size(); i++) {
      Setting set = settings.get(i);
      ClpUpdateFromSettingVisitor vis = new ClpUpdateFromSettingVisitor(res);
      set.accept(vis);
    }
  }

  //
  private Resources getResources(ArrayList<Resources> rlist, String name) {
    for (Resources r : rlist) {
      if (name.equals(r.getName())) {
        return r;
      }
    }
    return null;
  }
}
