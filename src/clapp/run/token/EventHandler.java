package clapp.run.token;

import java.util.ArrayList;
import java.util.Hashtable;

import clapp.run.util.ResourceUtility;
import clp.run.cel.asm.Modifier;
import clp.run.res.BVar;
import clp.run.res.CellEvent;
import clp.run.res.EventVisitor;
import clp.run.res.VarEvent;

public class EventHandler {

  private enum Event {
    NONE, UP, DOWN;
  }

  private class Delay {
    private Event current;
    private long time;
    private long delta;
    private boolean isCycle;
    private int nbCycles;
    private String tname;
  }

  private class EventCell {
    private Event current;
    private Delay delay1;
    private Delay delay2;
    private Delay delay3;
    private int nbCycles;
    private boolean isOutput;
  }

  private class Var {
    private Event current;
    private int nbCycles;
    private boolean isOutput;
  }

  private class ConditionalAssignment {
    private boolean isSet;
    private BVar v;
    private Modifier modifier;
    private boolean isKeeping;
    public String cname;
  }

  private static final EventHandler instance = new EventHandler();

  private Hashtable<String, Var> variables;
  private Hashtable<String, Delay> delays;
  private Hashtable<BVar, ConditionalAssignment> conditionalAssignment;
  private ArrayList<EventCell> cqueue;
  private Hashtable<Var, Event> vqueue;
  private Hashtable<String, EventCell> eventCells;

  private boolean isMarking;


  /**
   * SINGLETON provider
   * 
   * @return instance
   */
  static public EventHandler getInstance() {
    return instance;
  }

  // PRIVATE CONSTRUCTOR
  private EventHandler() {
    variables = new Hashtable<>();
    delays = new Hashtable<>();
    conditionalAssignment = new Hashtable<>();
    cqueue = new ArrayList<>();
    vqueue = new Hashtable<>();
    eventCells = new Hashtable<>();
  }

  /**
   * register a cell (its name) to check its state changes and, optionally, a delay
   * 
   * @param cellName
   * @param tname
   * @param delay
   * @param isCycle
   */
  public void registerEventCell(String cellName, String tname, long delay, boolean cycle) {
    EventCell c = eventCells.get(cellName);
    if (c == null) {
      c = new EventCell();
      c.current = Event.NONE;
      eventCells.put(cellName, c);
    }
    if (tname != null) {
      Delay d = delays.get(tname);
      if (d == null) {
        d = new Delay();
        delays.put(tname, d);
      }
      else if (d == c.delay1 || d == c.delay2 || d == c.delay3) {
        return;
      }
      d.current = Event.NONE;
      d.isCycle = cycle;
      d.delta = delay;
      d.tname = tname;
      if (c.delay1 == null) {
        c.delay1 = d;
      }
      else if (c.delay2 == null) {
        c.delay2 = d;
      }
      else if (c.delay3 == null) {
        c.delay3 = d;
      }
      else {
        System.err.println("FULL CAPACITY already reached!");
      }
    }
  }

  /**
   * register a variable (its name) to check its state changes
   * 
   * @param name
   */
  public void registerVar(String name) {
    Var v = new Var();
    v.current = Event.NONE;
    variables.put(name, v);
  }

  public boolean isCell(String name) {
    return eventCells.containsKey(name);
  }

  public boolean isRegistered(clp.run.res.Event event) {
    ClpEventVisitor vis = new ClpEventVisitor();
    event.accept(vis);
    return (variables.containsKey(vis.getName())) || eventCells.containsKey(vis.getName());
  }

  /**
   * register structure that will handle periodically with given variable v according to
   * ifv and modifier until cell named cname is deactivated
   * 
   * @param v
   * @param ifv
   * @param modifier
   * @param isKeeping 
   * @param cname 
   * @param key 
   */
  public void registerConditionalAssignment(BVar v, BVar ifv, Modifier modifier, boolean isKeeping, String cname) {
    if (!conditionalAssignment.containsKey(ifv)) {
      ConditionalAssignment ca = new ConditionalAssignment();
      ca.modifier = modifier;
      ca.isKeeping = isKeeping;
      ca.v = v;
      ca.cname = cname;
      EventCell c = eventCells.get(cname);
      if (c == null) {
        c = new EventCell();
        c.current = Event.NONE;
        eventCells.put(cname, c);
      }
      conditionalAssignment.put(ifv, ca);
    }
    
  }

  /**
   * mark cell state as getting up
   * 
   * @param name
   */
  public void markCellUp(String name) {
    EventCell c = findEventCell(name);
    if (c != null) {
      c.current = Event.UP;
      armDelay(c.delay1, name);
      armDelay(c.delay2, name);
      armDelay(c.delay3, name);
      System.out.printf("[%d] cell %s marked UP\n", (System.currentTimeMillis()/1000), name);
      ResourceUtility.getInstance().notifyEvent(name, true);
      c.nbCycles = 3;
      c.isOutput = true;
    }
  }

  //
  private EventCell findEventCell(String name) {
    for (String cname : eventCells.keySet()) {
      if (cname.equals(name)) {
        return eventCells.get(name);
      }
    }
    return null;
  }

  //
  private void armDelay(Delay d, String name) {
    if (d != null) {  // activate timer delay
      d.current = Event.DOWN;
      d.nbCycles = 1;
      d.time = System.currentTimeMillis() + d.delta;
      System.out.printf("[%d] %s's delay %s armed\n", (System.currentTimeMillis()/1000), name, d.tname);
    }
  }

  /**
   * mark cell state as getting down
   * 
   * @param name
   */
  public boolean markCellDown(String name) {
    EventCell c = findEventCell(name);
    if (c != null) {
      c.current = Event.DOWN;
      System.out.printf("[%d] cell %s marked DOWN\n", (System.currentTimeMillis()/1000), name);
      c.nbCycles = 3;
      c.isOutput = true;
      ResourceUtility.getInstance().notifyEvent(name, false);
      return true;
    }
    return false;
  }

  public void markCellToReset(String name) {
    EventCell c = findEventCell(name);
    if (c != null) {
      cqueue.add(c);
    }
  }

  /**
   * mark variable state as getting up or down (according to given argument)
   * 
   * @param name
   * @param b
   */
  synchronized public void markVarEvent(String name, boolean b) {
    isMarking = true;
    Var v = variables.get(name);
    if (v != null) {
      vqueue.put(v, b ? Event.UP : Event.DOWN);
      v.nbCycles = 3;
      System.out.printf("[%d] var %s %s\n", (System.currentTimeMillis()/1000), name, (b ? "UP" : "DOWN"));
    }
    isMarking = false;
  }

  /**
   * check whether variable or cell with given name currently got up
   * 
   * @param name
   * @return
   */
  public Boolean isUp(String name) {
    Var v = variables.get(name);
    if (v != null) {
      return v.current == Event.UP;
    }
    EventCell c = findEventCell(name);
    if (c != null) {
      return c.current == Event.UP;
    }
    return null;
  }

  /**
   * check whether variable or cell with given name currently got down
   * 
   * @param name
   * @return
   */
  public Boolean isDown(String name) {
    Var v = variables.get(name);
    if (v != null) {
      return v.current == Event.DOWN;
    }
    EventCell c = findEventCell(name);
    if (c != null) {
      return c.current == Event.DOWN;
    }
    return null;
  }

  /**
   * check whether time delay currently got up
   * 
   * @param tname
   * @return
   */
  public boolean isTimeUp(String tname) {
    Delay d = delays.get(tname);
    if (d != null) {
      if (d.current == Event.UP) {
        return true;
      }
    }
    return false;
  }

  public void resetEvents() {
    for (String name : eventCells.keySet()) {
      EventCell c = eventCells.get(name);
      if (c.nbCycles < 0 && c.isOutput) {
        System.out.printf("[%d] cell %s RESET\n", (System.currentTimeMillis()/1000), name);
        c.current = Event.NONE;
        c.isOutput = false;
        if (c.delay1 != null) {
          c.isOutput = rearmCyclicDelay(c.delay1, name);
        }
        if (c.delay2 != null) {
          c.isOutput = rearmCyclicDelay(c.delay2, name);
        }
        if (c.delay3 != null) {
          c.isOutput = rearmCyclicDelay(c.delay3, name);
        }
      }
      else {
        c.nbCycles--;
      }
    }
    for (String name : variables.keySet()) {
      Var v = variables.get(name);
      if (v.nbCycles < 0 && v.isOutput) {
        System.out.printf("[%d] var %s RESET\n", (System.currentTimeMillis()/1000), name);
        v.current = Event.NONE;
        v.isOutput = false;
      }
      else {
        v.nbCycles--;
      }
    }
  }

  //
  private boolean rearmCyclicDelay(Delay d, String name) {
    if (d.current == Event.UP) {
      if (d.nbCycles < 1) {
        if (d.isCycle) {
          d.current = Event.DOWN; // timer reactivated
          d.nbCycles = 1;
          d.time = System.currentTimeMillis() + d.delta;
          System.out.printf("[%d] %s delay rearmed\n", (System.currentTimeMillis()/1000), d.tname);
        }
        else {
          d.current = Event.NONE; // timer deactivated
          d.nbCycles = 1;
          System.out.printf("[%d] %s delay deactivated\n", (System.currentTimeMillis()/1000), d.tname);
        }
      }
      else {
        d.nbCycles--;
        return !d.isCycle || d.isCycle && d.nbCycles > -1;
      }
    }
    return false;
  }

  public void performPeriodicAction() {
    if (isMarking) {
      return;
    }
    performPeriodicAssignments();
    for (String name : variables.keySet()) {
      Var v = variables.get(name);
      v.current = Event.NONE;
    }
    for (Var v : vqueue.keySet()) {
      v.current = vqueue.get(v);
    }
    vqueue.clear();
    for (EventCell c : cqueue) {
      c.current = Event.NONE;
      c.nbCycles = -1;
      c.isOutput = true;
    }
    cqueue.clear();
  }

  //
  private void performPeriodicAssignments() {
    ArrayList<BVar> toRemove = new ArrayList<>();
    for (BVar ifv : conditionalAssignment.keySet()) {
      ConditionalAssignment ca = conditionalAssignment.get(ifv);
      if (ca.modifier == null) {
        ca.v.setValue(ifv.getValue());
      }
      else {
        switch (ca.modifier) {
          case NOT:
            ca.v.setValue(!ifv.getValue());
            break;
          case UP:
            if (!ca.isSet) {
              if (ifv.getValue()) {
                ca.v.setValue(true);
                ca.isSet = true;
              }
            }
            else {
              ca.v.setValue(false);
              if (!ifv.getValue()) {
                ca.isSet = false;
              }
            }
            break;
          case DOWN:
            if (!ca.isSet) {
              if (!ifv.getValue()) {
                ca.v.setValue(false);
                ca.isSet = true;
              }
            }
            else {
              ca.v.setValue(false);
              if (ifv.getValue()) {
                ca.isSet = false;
              }
            }
            break;

          default:
            break;
        }
      }
      EventCell c = findEventCell(ca.cname);
      if (c != null && c.current == Event.DOWN) {
        if (!ca.isKeeping) {
          ca.v.setValue(false);
        }
        toRemove.add(ifv);
      }
    }
    for (BVar v : toRemove) {
      conditionalAssignment.remove(v);
    }
  }

  //
  public void updateDelays() {
    for (String tname : delays.keySet()) {
      Delay d = delays.get(tname);
      if (d.current == Event.DOWN) {
        long delta = System.currentTimeMillis() - d.time;
        if (delta >= 0) {
          d.current = Event.UP;
          System.out.printf("[%d] %s UP\n", (System.currentTimeMillis()/1000), tname);
          for (String name : eventCells.keySet()) {
            EventCell c = eventCells.get(name);
            if (d == c.delay1 || d == c.delay2 || d == c.delay3) {
              c.nbCycles = -1;
              c.isOutput = true;
              break;
            }
          }
        }
      }
    }
  }

  /**
   * done at finishing step
   */
  public void clearAll() {
    eventCells.clear();
    variables.clear();
    vqueue.clear();
    cqueue.clear();
    delays.clear();
    conditionalAssignment.clear();
  }

  public Boolean hasDelaysUp() {
    Boolean ret = null;
    for (String name : delays.keySet()) {
      Delay d = delays.get(name);
      if (d.current == Event.UP) {
        return true;
      }
      ret = false;
    }
    return ret;
  }

  static public class ClpEventVisitor implements EventVisitor {
    private String name;

    @Override
    public void visitCellEvent(CellEvent x) {
      name = x.getCellName();
    }

    @Override
    public void visitVarEvent(VarEvent x) {
      name = x.getName();
    }
    public String getName() {
      return name;
    }
  }
}
