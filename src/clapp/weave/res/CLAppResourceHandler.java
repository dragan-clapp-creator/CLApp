package clapp.weave.res;

import java.util.HashMap;

import clapp.run.util.ResourceUtility;
import clp.run.res.BVar;
import clp.run.res.FVar;
import clp.run.res.IVar;
import clp.run.res.LVar;
import clp.run.res.PVar;
import clp.run.res.SVar;
import clp.run.res.Variable;

public class CLAppResourceHandler {

  private static final CLAppResourceHandler instance = new CLAppResourceHandler();

  private HashMap<String, Variable> variables;

  private int nbCycles;

  private CLAppResourceHandler() {
    variables = new HashMap<String, Variable>();
  }

  public static CLAppResourceHandler getInstance() {
    return instance;
  }

  public void register(String key, Variable var) {
    variables.put(key, var);
  }

  public void export(String key) {  // this export is a notification
    Variable var = variables.get(key);
    if (var instanceof BVar) {
      ((BVar)var).setValue(true);
      nbCycles = 3;
    }
  }

  public void export(String key, boolean val) {
    Variable var = variables.get(key);
    if (var instanceof BVar) {
      ((BVar)var).setValue(val);
    }
  }

  public void export(String key, int val) {
    Variable var = variables.get(key);
    if (var instanceof IVar) {
      ((IVar)var).setValue(val);
    }
  }

  public void export(String key, long val) {
    Variable var = variables.get(key);
    if (var instanceof LVar) {
      ((LVar)var).setValue(val);
    }
  }

  public void export(String key, float val) {
    Variable var = variables.get(key);
    if (var instanceof FVar) {
      ((FVar)var).setValue(val);
    }
  }

  public void export(String key, double val) {
    Variable var = variables.get(key);
    if (var instanceof FVar) {
      ((FVar)var).setValue(val);
    }
  }

  public void export(String key, String val) {
    Variable var = variables.get(key);
    if (var instanceof SVar) {
      ((SVar)var).setValue(val);
    }
  }

  public void export(String key, Object val) {
    Variable var = variables.get(key);
    if (var instanceof PVar) {
      ((PVar)var).setValue(val);
    }
  }

  synchronized public void clearVariables() {
    if (nbCycles < 0) {
      for (String key : variables.keySet()) {
        Variable var = variables.get(key);
        ResourceUtility.getInstance().resetValue(var);
      }
    }
    else {
      nbCycles--;
    }
  }

  /**
   * @param nbCycles the nbCycles to set
   */
  public void setNbCycles(int nbCycles) {
    this.nbCycles = nbCycles;
  }
}
