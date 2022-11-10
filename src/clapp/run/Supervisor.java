package clapp.run;
/*
 * Created on Dec 7, 2004
 *
 */


import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import clapp.cmp.ClappBinder;
import clapp.cmp.ClappMain;
import clapp.debug.ATrace;
import clapp.run.api.IMetaScenarioTask;
import clapp.run.http.ClappSender;
import clapp.run.http.ClappServer;
import clapp.run.http.DefaultEncrypter;
import clapp.run.http.IKrypter;
import clapp.run.sim.api.ISimulator;
import clapp.run.token.EventHandler;
import clapp.run.token.TokenHandler;
import clapp.run.ui.ClpVisualizer;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.AInterpreter;
import clapp.run.util.CellQueueHandler;
import clapp.run.util.ClpClassHandler;
import clapp.run.util.ClpJavaEnqueuer;
import clapp.run.util.JavaInvoker;
import clapp.run.util.ResourceUtility;
import clapp.weave.res.ClassWeaver;
import clp.run.CLApp;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.Heap;
import clp.run.msc.ClassReference;
import clp.run.msc.Decryption;
import clp.run.msc.MetaScenario;
import clp.run.msc.MetaScenarioBody;
import clp.run.msc.MscTaskName;
import clp.run.msc.Port;
import clp.run.res.Resources;
import clp.run.res.Variable;
import clp.run.scn.Scenario;


/**
 * @author Dragan Matic
 * 
 */
public class Supervisor {

  static private ISimulator registeredListener;

  static private Supervisor      instance;
  static private ClpJavaEnqueuer javaEnqueuer;

  public static void main(String[] args) {
    if (args != null && args.length > 0) {
      ClappMain clapp = new ClappMain();
      try {
        clapp.parse(args[0]);
        runIt(null, clapp);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  static public Supervisor getInstance() {
    return instance;
  }

  private ScheduleHandler handler;
  private String mscName;
  private Port port;

  private Hashtable<String, IMetaScenarioTask> mscTasks;

  private ClappMain clapp;

  /**
   * constructor
   * 
   * @param clapp 
   */
  private Supervisor(ClappMain clapp) {
    this.clapp = clapp;
    MetaScenario msc = clapp.getMetaScenario();
    if (msc != null && deploy(clapp.getResult(), false)) {
      this.mscName = msc.getName();
      this.port = msc.getMetaScenarioBody().getPort();
      mscTasks = new Hashtable<>();
      ClappBinder binder = new ClappBinder();
      binder.chainAll(msc);
      handler = new ScheduleHandler(clapp);
    }
    else {
      this.clapp = null;
    }
  }

  //
  private boolean deploy(CLApp result, boolean isAdditional) {
    MetaScenario msc = clapp.getMetaScenario();
    if (msc != null) {
      if (result != null) {
        MetaScenarioBody body = msc.getMetaScenarioBody();
        deployResources(msc, result.getResourcess());
        deployHeaps(body.getScenarios(), result.getActors(), result.getHeaps());
        deployActors(body.getScenarios(), result.getScenarios(), result.getActors());
        deployScenarios(msc, body.getScenarios(), result.getScenarios(), isAdditional);
        ClappBinder binder = new ClappBinder();
        binder.updateAll(result.getScenarios(), body.getResourcess());
        binder.deploySetters(msc, result.getSetters());
      }
      return true;
    }
    return false;
  }

  /**
   * restart runtime after having deployed all from result
   * 
   * @param result
   */
  public void deployAndRestart(CLApp result) {
    handler.setSuspended(true);
    while (!handler.isSuspended()) {
      try {
        Thread.sleep(125);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    if (deploy(result, true)) {
      MetaScenario msc = clapp.getMetaScenario();
      if (msc != null && msc.getName().equals(mscName)) {
        MetaScenarioBody body = msc.getMetaScenarioBody();
        for (MscTaskName tn : body.getMscTasks().getMscTaskNames()) {
          if (tn == MscTaskName.SCHEDULER) {
            if (Scheduler.getCqueues() == null) {
              Scheduler.setCqueues( new HashMap<String, CellQueueHandler>() );
            }
          }
        }
        handler.setSuspended(false);
      }
    }
  }

  //
  private void deployScenarios(MetaScenario msc, ArrayList<Scenario> slist, ArrayList<Scenario> scenarios, boolean isAdditional) {
    for (Scenario scn : scenarios) {
      if (!foundScenario(slist, scn.getName())) {
        slist.add(scn);
        scn.setMetaScenario(msc);
        if (isAdditional) {
          Scheduler s = createScheduler(scn, registeredListener);
          s.createScenarioTasks(msc.getMetaScenarioBody(), s.getScenario(), registeredListener);
          handler.register(s);
        }
      }
      else {
        deployActors(scn, scn.getScenarioBody().getActors());
      }
    }
  }

  //
  private void deployActors(Scenario scn, ArrayList<Actor> actors) {
    ArrayList<Actor> alist = scn.getScenarioBody().getActors();
    for (Actor act : actors) {
      if (!foundActor(alist, act.getName())) {
        alist.add(act);
        act.setScenario(scn);
      }
    }
  }

  //
  private boolean foundScenario(ArrayList<Scenario> slist, String name) {
    for (Scenario s : slist) {
      if (name.equals(s.getName())) {
        return true;
      }
    }
    return false;
  }

  //
  private void deployActors(ArrayList<Scenario> slist, ArrayList<Scenario> srlist, ArrayList<Actor> actors) {
    for (Actor act : actors) {
      Scenario scn = getScenario(slist, act.getScn());
      if (scn == null) {
        scn = getScenario(srlist, act.getScn());
      }
      if (scn != null) {
        removeOldActor(scn, act.getName());
        scn.getScenarioBody().addActor(act);
        act.setScenario(scn);
      }
    }
  }

  //
  private void removeOldActor(Scenario scn, String name) {
    for (Actor a : scn.getScenarioBody().getActors()) {
      if (name.equals(a.getName())) {
        scn.getScenarioBody().removeActor(a);
        break;
      }
    }
  }

  //
  private Scenario getScenario(ArrayList<Scenario> slist, String name) {
    for (Scenario s : slist) {
      if (name.equals(s.getName())) {
        return s;
      }
    }
    return null;
  }

  //
  private boolean foundActor(ArrayList<Actor> alist, String name) {
    for (Actor a : alist) {
      if (name.equals(a.getName())) {
        return true;
      }
    }
    return false;
  }

  //
  private void deployHeaps(ArrayList<Scenario> slist, ArrayList<Actor> alist, ArrayList<Heap> heaps) {
    for (Heap heap : heaps) {
      Actor act = getActorFromScenario(slist, heap.getAct());
      if (act == null) {
        act = getActor(alist, heap.getAct());
      }
      if (act != null) {
        removeOldHeap(act, heap.getName());
        act.addHeap(heap);
        heap.setActor(act);
      }
    }
  }

  //
  private void removeOldHeap(Actor act, String name) {
    for (Heap h : act.getHeaps()) {
      if (name.equals(h.getName())) {
        act.removeHeap(h);
        break;
      }
    }
  }

  //
  private Actor getActorFromScenario(ArrayList<Scenario> slist, String name) {
    for (Scenario s : slist) {
      for (Actor a : s.getScenarioBody().getActors()) {
        if (name.equals(a.getName())) {
          return a;
        }
      }
    }
    return null;
  }

  //
  private Actor getActor(ArrayList<Actor> alist, String name) {
    for (Actor a : alist) {
      if (name.equals(a.getName())) {
        return a;
      }
    }
    return null;
  }

  //
  private void deployResources(MetaScenario msc, ArrayList<Resources> resourcess) {
    ArrayList<Resources> rlist = msc.getMetaScenarioBody().getResourcess();
    for (Resources res : resourcess) {
      if (!foundResources(rlist, res.getName())) {
        rlist.add(res);
        res.setMetaScenario(msc);
      }
    }
  }

  //
  private boolean foundResources(ArrayList<Resources> rlist, String name) {
    for (Resources r : rlist) {
      if (name.equals(r.getName())) {
        return true;
      }
    }
    return false;
  }

  public void fire(ISimulator sim) {
    handler.initialize(clapp);
    MetaScenario msc = clapp.getMetaScenario();
    if (msc != null && msc.getName().equals(mscName)) {
      MetaScenarioBody body = msc.getMetaScenarioBody();
      if (body.isMscOutput()) {
        ConsoleProvider.getInstance().initialize(body.getMscOutput());
      }
      new ClappBinder().updateAll(body.getScenarios(), body.getResourcess());
      Port port = body.getPort();
      if (port != null) {
        ClappServer.start(port.getNum(), findEncrypter(port.getDecryption()));
      }
      registerSchedulersAndStartOthers(body, body.getMscTasks().getMscTaskNames(), sim);
      handler.start();
    }
  }

  //
  private IKrypter findEncrypter(Decryption decryption) {
    if (decryption != null && decryption.getClazz() != null) {
      ClassReference cr = decryption.getClazz();
      String name = cr.getPack() + "." + cr.getClazz();
      try {
        Class<?> cl = Class.forName(name, false, ClpClassHandler.getInstance().getLoader());
        Constructor<?> cons = cl.getConstructor();
        return (IKrypter) cons.newInstance();
      }
      catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException |
             IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    return new DefaultEncrypter();
  }

  /**
   * @return Returns the mscName.
   */
  public String getMscName() {
    return mscName;
  }

  /**
   * @param mscName
   *          The mscName to set.
   */
  public void setMscName(String mscName) {
    this.mscName = mscName;
  }

  /**
   * trigger runtime with compiled clapp code
   * 
   * @param sim
   * @param clapp
   */
  public static void startRunning(ISimulator sim, ClappMain clapp) {
    registeredListener = sim;
    runIt(sim, clapp);
  }

  //
  private static void runIt(ISimulator sim, ClappMain clapp) {
    ClassWeaver.reset();
    instance = new Supervisor(clapp);
    if (instance.clapp != null) {
      instance.fire(sim);
    }
  }

  public static ClpJavaEnqueuer getJavaEnqueuer() {
    return javaEnqueuer;
  }

  public static boolean hasJavaEnqueuer() {
    return javaEnqueuer != null;
  }

  public List<AInterpreter> getConsolidatedTasks() {
    List<AInterpreter> list = new ArrayList<>();
    for (IMetaScenarioTask s : mscTasks.values()) {
      if (s instanceof Scheduler) {
        list.addAll(((Scheduler) s).getTaskSequence());
      }
    }
    return list;
  }

  public Scheduler getScheduler(String sname) {
    return (Scheduler) mscTasks.get(sname);
  }

  public void remove(Scenario scn) {
    Scheduler s = (Scheduler) mscTasks.remove(scn.getName());
    s.unfire(scn);
  }

  public void remove(Scenario scn, Actor act) {
    Scheduler s = (Scheduler) mscTasks.get(scn.getName());
    s.unfireActor(scn, act);
  }

  //
  private void registerSchedulersAndStartOthers(MetaScenarioBody body, ArrayList<MscTaskName> list, ISimulator sim) {
    for (MscTaskName tn : list) {
      switch (tn) {
        case SCHEDULER:
          Scheduler.setCqueues( new HashMap<String, CellQueueHandler>() );
          ArrayList<Scenario> slist = body.getScenarios();
          for (Scenario scn : slist) {
            Scheduler s = createScheduler(scn, sim);
            s.createScenarioTasks(body, s.getScenario(), sim);
            handler.register(s);
          }
          break;
        case DEBUGGER:
          // NOT IMPLEMENTED YET
          break;
        case EXCHANGER:
          // NOT IMPLEMENTED YET
          break;
        case JAVA_ENQUEUER:
          javaEnqueuer = new ClpJavaEnqueuer();
          javaEnqueuer.start();
          break;

        default:
          break;
      }
    }
  }

  //
  private Scheduler createScheduler(Scenario scn, ISimulator sim) {
    Scheduler scheduler = new Scheduler(this, scn, sim);
    mscTasks.put(scn.getName(), scheduler);
    return scheduler;
  }

  public void stopAll(ISimulator listener, ClappSender sender) {
    if (handler == null) {
      return;
    }
    handler.setSuspended(true);
    for (IMetaScenarioTask s : mscTasks.values()) {
      if (s instanceof Scheduler) {
        ((Scheduler) s).setStop(true);
      }
    }
    handler.setSuspended(false);
    boolean isOk = stopServerIfPresent(sender);
    ClpVisualizer.stopAll();
    handler.initialize(null);
    mscTasks.clear();
    EventHandler.getInstance().clearAll();
    TokenHandler.clearAll();
    JavaInvoker.stopAll();
    if (registeredListener == null && listener == null) {
      System.exit(0);
    }
    else if (registeredListener != null) {
      registeredListener.onFinish();
      if (isOk) {
        registeredListener = null;
      }
      ConsoleProvider.getInstance().print("...STOPPED");
    }
  }

  //
  private boolean stopServerIfPresent(ClappSender sender) {
    if (port != null) {
      if (sender == null) {
        sender = new ClappSender();
      }
      if (sender.sendFinish(port.getNum()) != 200) {
        return false;
      }
      while (!ClappServer.isSocketClosed()) {
        try {
          Thread.sleep(123);
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      port = null;
    }
    return true;
  }

  public void inspect(ATrace trace) {
    staticalInspection(trace);
    dynamicalInspection(trace);
  }

  private void dynamicalInspection(ATrace trace) {
    trace.send("//DYNAMICAL INFORMATION");
//    Vector<ThreadInfo> seq = primerProcess.getSequence();
//    for (int i=0; i<seq.size(); i++) {
//      ThreadInfo ti = seq.elementAt(i);
//      CellQueueHandler aq = ((ADXThread)ti.getThread()).getOperatingQueue();
//      CellQueueHandler iq = ((ADXThread)ti.getThread()).getPassingQueue();
//      if (iq != null) {
//    	trace.send("    /"+((ADXThread)ti.getThread()).getName()+"/"+iq.getName());
//    	CellChainLink ccl = iq.getFirstCell();
//    	while (ccl != null) {
//    	  trace.send("      /"+ccl.getCell().getName()+"/"+ccl.getCell().getActivity());
//    	  ccl = ccl.getNext();
//    	}
//      }
//      if (aq != null && aq != iq) {
//    	trace.send("    /"+((ADXThread)ti.getThread()).getName()+"/"+aq.getName());
//    	CellChainLink ccl = aq.getFirstCell();
//    	while (ccl != null) {
//    	  trace.send("      /"+ccl.getCell().getName()+"/"+ccl.getCell().getActivity());
//    	  ccl = ccl.getNext();
//    	}
//      }
//    }
  }

  private void staticalInspection(ATrace trace) {
    trace.send("//STATICAL INFORMATION");
    MetaScenario msc = clapp.getMetaScenario();
    if (msc != null && msc.getName().equals(mscName)) {
      trace.send("  /MetaScenario/"+msc.getName());
      for (Scenario scn : msc.getMetaScenarioBody().getScenarios()) {
        trace.send("    /Scenario/"+scn.getName());
        for (Actor act : scn.getScenarioBody().getActors()) {
          trace.send("      /Actor/"+act.getName());
          for (Heap cb : act.getHeaps()) {
            trace.send("        /CellBlock/"+cb.getName());
            for (Cell cell : cb.getCells()) {
              trace.send("          /Cell/"+cell.getName());
            }
          }
        }
	    }
      for (Resources res : msc.getMetaScenarioBody().getResourcess()) {
        trace.send("    /Resources/"+res.getName());
        ResourceUtility util = ResourceUtility.getInstance();
        for (Variable v : res.getVariables()) {
          trace.send("      /"+util.getName(v)+"/"+util.getValue(v));
      	}
      }
    }
  }

  public void notifyActivity(String key, CellQueueHandler operatingQueue) {
    if (registeredListener != null) {
      registeredListener.onExecution(key, operatingQueue);
    }
  }

  public void flushActivity(String key) {
    if (registeredListener != null) {
      registeredListener.onExecution(key, null);
    }
    EventHandler.getInstance().updateDelays();
  }

  public MetaScenario getMetaScenario() {
    return clapp.getMetaScenario();
  }

  public ClappMain getClapp() {
    return clapp;
  }
}
