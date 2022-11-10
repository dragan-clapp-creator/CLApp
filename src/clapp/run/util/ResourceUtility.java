package clapp.run.util;

import java.util.ArrayList;

import clapp.run.Supervisor;
import clapp.run.http.ResponseHandler;
import clapp.run.token.EventHandler;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.vis.ClpGetVariableVisitor;
import clapp.run.vis.ClpResetVariableVisitor;
import clapp.run.vis.ClpSetInitVariableVisitor;
import clapp.run.vis.ClpSetVariableVisitor;
import clp.run.cel.exp.Expression;
import clp.run.msc.MetaScenario;
import clp.run.res.BVar;
import clp.run.res.CellEvent;
import clp.run.res.DVar;
import clp.run.res.Event;
import clp.run.res.EventVisitor;
import clp.run.res.FVar;
import clp.run.res.IVar;
import clp.run.res.Resources;
import clp.run.res.SVar;
import clp.run.res.TVar;
import clp.run.res.VarEvent;
import clp.run.res.Variable;


public class ResourceUtility {


  private ResponseHandler responseHandler;

  private ResourceUtility() {
  }

  private static class SingletonHolder {
    public static ResourceUtility instance = new ResourceUtility();
  }

  public static ResourceUtility getInstance() {
    return SingletonHolder.instance;
  }

  public Resources findResources(ArrayList<Resources> rs, String name) {
    for (Resources r : rs) {
      if (r.getName().equals(name)) {
        return r;
      }
    }
    return null;
  }

  public Variable getVariable(Resources res, String vname) {
    if (vname != null && res != null) {
      for (Variable v : res.getVariables()) {
        if (vname.equals(getName(v))) {
          return v;
        }
      }
    }
    return null;
  }

  public Event getEvent(Resources res, String vname) {
    if (vname != null && res != null) {
      ArrayList<Event> events = new ArrayList<>();
      events.add(res.getEvents().getEvent());
      events.addAll(res.getEvents().getEvents());
      ClpEventVisitor vis = new ClpEventVisitor(vname);
      for (Event ev : events) {
        ev.accept(vis);
        if (vis.isDone()) {
          return ev;
        }
      }
    }
   return null;
  }

  public void setupEvent(Resources res, String vname, boolean b) {
    if (vname != null && res != null) {
      ArrayList<Event> events = new ArrayList<>();
      events.add(res.getEvents().getEvent());
      events.addAll(res.getEvents().getEvents());
      ClpEventVisitor vis = new ClpEventVisitor(vname);
      for (Event ev : events) {
        ev.accept(vis);
        if (vis.isDone()) {
          if (vis.isCell()) {
            if (b) {
              EventHandler.getInstance().markCellUp(vname);
            }
            else {
              EventHandler.getInstance().markCellDown(vname);
            }
          }
          else {
            EventHandler.getInstance().markVarEvent(vname, b);
          }
          return;
        }
      }
    }
  }

  public Variable getVariable(Resources r, Variable var) {
    String name = getName(var);
    return getVariable(r, name);
  }

  public Variable getVariable(String key) {
    MetaScenario msc = Supervisor.getInstance().getMetaScenario();
    for(Resources res : msc.getMetaScenarioBody().getResourcess()) {
      Variable v = getVariable(res, key);
      if (v != null) {
        return v;
      }
    }
    return null;
  }

  //
  public Class<?> getClass(Variable v) {
    ClpGetVariableVisitor vis = new ClpGetVariableVisitor();
    v.accept(vis);
    return vis.getType();
  }

  //
  public String getName(Variable v) {
    ClpGetVariableVisitor vis = new ClpGetVariableVisitor();
    v.accept(vis);
    return vis.getName();
  }

  public boolean hasArray(Variable v) {
    ClpGetVariableVisitor vis = new ClpGetVariableVisitor();
    v.accept(vis);
    return vis.hasArray();
  }

  public void setValue(String name, Variable v, Object val) {
    ClpSetVariableVisitor vis = new ClpSetVariableVisitor(val);
    v.accept(vis);
    if (responseHandler != null) {
      responseHandler.notifyDone(name, val);
    }
  }

  public void notifyEvent(String name, Boolean val) {
    if (responseHandler != null) {
      responseHandler.notifyDone(name, val);
    }
  }

  public void setValue(Variable v, int index, Object val) {
    ClpSetVariableVisitor vis = new ClpSetVariableVisitor(val, index);
    v.accept(vis);
  }

  public void setValue(String key, Object val) {
    MetaScenario msc = Supervisor.getInstance().getMetaScenario();
    for(Resources res : msc.getMetaScenarioBody().getResourcess()) {
      Variable v = getVariable(res, key);
      if (v != null) {
        setValue(key, v, val);
        return;
      }
      Event ev = getEvent(res, key);
      if (ev != null) {
        setupEvent(res, key, (boolean)val);
      }
    }
  }

  public Object getValue(Variable v) {
    ClpGetVariableVisitor vis = new ClpGetVariableVisitor();
    v.accept(vis);
    if (vis.hasArray()) {
      return vis.getValues();
    }
    return vis.getValue();
  }

  public Object getValue(String key) {
    MetaScenario msc = Supervisor.getInstance().getMetaScenario();
    for(Resources res : msc.getMetaScenarioBody().getResourcess()) {
      Variable v = getVariable(res, key);
      if (v != null) {
        return getValue(v);
      }
      else {
        Event ev = getEvent(res, key);
        if (ev != null) {
          System.out.printf("[%d] event %s TESTED\n", (System.currentTimeMillis()/1000), key);
          if (EventHandler.getInstance().isUp(key) == Boolean.TRUE) {
            System.out.printf("\tevent %s is UP\n", key);
            return true;
          }
          if (EventHandler.getInstance().isDown(key) == Boolean.TRUE) {
            System.out.printf("\tevent %s is DOWN\n", key);
            return false;
          }
        }
      }
    }
    return null;
  }

  public Object getInitialValue(Variable v) {
    ClpGetVariableVisitor vis = new ClpGetVariableVisitor();
    v.accept(vis);
    return vis.getInitial();
  }

  public Object[] getValues(Variable v) {
    ClpGetVariableVisitor vis = new ClpGetVariableVisitor();
    v.accept(vis);
    return vis.getValues();
  }

  public void setValueFromExpression(Resources res, Variable var, Expression x) {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(res);
    if (var instanceof IVar) {
      ((IVar)var).setValue(evaluator.getIntExpression(x));
    }
    else if (var instanceof BVar) {
      ((BVar)var).setValue(evaluator.getBoolExpression(x));
    }
    else if (var instanceof FVar) {
      ((FVar)var).setValue(evaluator.getFloatExpression(x));
    }
    else if (var instanceof SVar) {
      ((SVar)var).setValue(evaluator.getStringExpression(x));
    }
    else if (var instanceof DVar) {
      ((DVar)var).setValue(evaluator.getDateExpression(x));
    }
    else if (var instanceof TVar) {
      ((TVar)var).setValue(evaluator.getTimeExpression(x));
    }
    else {
      ConsoleProvider.getInstance().eprint("error in setValueFromExpression");
    }
  }

  public void resetValue(Variable v) {
    ClpResetVariableVisitor vis = new ClpResetVariableVisitor(responseHandler);
    v.accept(vis);
  }

  public void setInitialValue(Variable v, Object init) {
    ClpSetInitVariableVisitor vis = new ClpSetInitVariableVisitor(init, responseHandler);
    v.accept(vis);
  }

  // INNER CLASS

  public class TypedValue<T> {
    private Object value;
    private Object[] values;
    public TypedValue(Object v) {
      value = v;
    }
    public TypedValue(Object[] v) {
      values = v;
    }
    @SuppressWarnings({ "unchecked", "hiding" })
    public <T> T getValue() {
      return (T) value;
    }
    @SuppressWarnings({ "hiding", "unchecked" })
    public <T> T getValue(int i) {
     return (T) values[i];
    }
  }

  static public class ClpEventVisitor implements EventVisitor {
    private String name;
    private boolean isDone;
    private boolean isCell;
    public ClpEventVisitor(String vname) {
      name = vname;
    }

    @Override
    public void visitCellEvent(CellEvent x) {
      if (name.equals(x.getCellName())) {
        isCell = true;
        isDone = true;
      }
    }

    @Override
    public void visitVarEvent(VarEvent x) {
      if (name.equals(x.getName())) {
        isDone = true;
      }
    }
    public boolean isCell() {
      return isCell;
    }
    public boolean isDone() {
      return isDone;
    }
  }

  public void register(ResponseHandler responseHandler) {
    this.responseHandler = responseHandler;
    System.out.printf("[%d] ResponseHandler %sregistered\n", (System.currentTimeMillis()/1000), (responseHandler == null ? "un" : ""));
  }
}
