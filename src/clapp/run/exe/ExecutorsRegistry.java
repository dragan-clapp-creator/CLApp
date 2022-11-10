package clapp.run.exe;

import java.util.ArrayList;
import java.util.Hashtable;

import clapp.run.util.CellChainLink;

public class ExecutorsRegistry {

  static private ExecutorsRegistry instance = new ExecutorsRegistry();

  static public ExecutorsRegistry getInstance() {
    return instance;
  }

  private Hashtable<CellChainLink, ExecutingUnit> units;

  // PRIVATE CONSTRUCTOR
  private ExecutorsRegistry() {
    units = new Hashtable<>();
  }

  public ExecutingUnit getUnit(CellChainLink ccl, int logicLevel, boolean positivelogic, boolean autodeact, ArrayList<CellChainLink> toBeMoved) {
    ExecutingUnit unit = units.get(ccl);
    if (unit == null) {
      if (logicLevel == 0 || ccl.getCell().getActivity() > 0) {
        unit = new ExecutingUnit(ccl, logicLevel, positivelogic, autodeact, toBeMoved);
        units.put(ccl, unit);
      }
    }
    return unit;
  }

  public ExecutingUnit getUnit(CellChainLink ccl) {
    return units.get(ccl);
  }

  public void unregister(CellChainLink ccl) {
    units.remove(ccl);
  }
}
