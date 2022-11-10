//package clapp.run.token;
//
//import java.util.ArrayList;
//import java.util.Hashtable;
//import java.util.Vector;
//
//import clapp.run.Activator;
//import clapp.run.ui.util.ConsoleProvider;
//import clapp.run.util.CellChainLink;
//import clapp.run.util.CellChainLink.ActivationInfo;
//import clapp.run.util.CellQueueHandler;
//import clapp.run.util.ClpSemaphore;
//import clp.run.act.Actor;
//import clp.run.cel.Cell;
//import clp.run.cel.Weightings;
//import clp.run.cel.log.LogicalFactor;
//import clp.run.cel.log.LogicalTerm;
//import clp.run.res.Resources;
//import clp.run.res.Weighting;
//
//public class TokenHandlerCopy {
//
//  private enum Activity {
//    UP, DOWN;
//  }
//
//  private class CellsInfo {
//    private Cell cell;
//    private Activity activity;
//    private ActivationInfo tokenInfo1;
//    private ActivationInfo tokenInfo2;
//    private ActivationInfo tokenInfo3;
//    private boolean isFull;
//    private ArrayList<Weighting> expected;
//  }
//
//  private static Hashtable<Actor, TokenHandlerCopy> instances = new Hashtable<>();
//
//  private Actor act;
//  private Hashtable<String, CellsInfo> table;
//  private Hashtable<Integer, ActivationInfo> transition;
//  private Hashtable<Integer, Resources> resources;
//  private Hashtable<Integer, ArrayList<CellsInfo>> cells;
//
//  /**
//   * PRIVATE CONSTRUCTOR
//   */
//  private TokenHandlerCopy() {
//    table = new Hashtable<>();
//    transition = new Hashtable<>();
//    resources = new Hashtable<>();
//    cells = new Hashtable<>();
//  }
//
//  public static TokenHandlerCopy getInstance(Actor act) {
//    TokenHandlerCopy instance = instances.get(act);
//    if (instance == null) {
//      if (isWeighted(act)) {
//        instance = new TokenHandlerCopy();
//        instances.put(act, instance);
//      }
//    }
//    return instance;
//  }
//
//  //
//  private static boolean isWeighted(Actor act) {
//    ArrayList<Resources> lres = act.getScenario().getMetaScenario().getMetaScenarioBody().getResourcess();
//    if (lres != null) {
//      for (Resources res : lres) {
//        if (res.isMarks()) {
//          return true;
//        }
//      }
//    }
//    return false;
//  }
//
//  /**
//   * initialize all cells
//   * 
//   * @param act
//   * @param inactive_queue
//   * @param active_queue
//   */
//  public void initialize(Actor act, CellQueueHandler inactive_queue, CellQueueHandler active_queue) {
//    this.act = act;
//    fillTable(active_queue, true);
//    fillTable(inactive_queue, false);
//  }
//
//  //
//  private void fillTable(CellQueueHandler queue, boolean isActive) {
//    CellChainLink ccl = queue.getFirstCell();
//    if (ccl != null) {
//      while (ccl != null) {
//        CellsInfo ci = new CellsInfo();
//        ci.activity = isActive ? Activity.UP : Activity.DOWN;
//        ci.tokenInfo1 = ccl.getActivationInfo1();
//        ci.tokenInfo2 = ccl.getActivationInfo2();
//        ci.tokenInfo3 = ccl.getActivationInfo3();
//        ci.cell = ccl.getCell();
////        checkCellWeights(ci);
//        table.put(ccl.getName(), ci);
//        addToTransitions(ci);
//        ccl = ccl.getNext();
//      }
//    }
//  }
//
//  //
//  private void addToTransitions(CellsInfo ci) {
//    addToTransitions(ci, ci.tokenInfo1);
//    addToTransitions(ci, ci.tokenInfo2);
//    addToTransitions(ci, ci.tokenInfo3);
//  }
//
//  //
//  private void addToTransitions(CellsInfo ci, ActivationInfo ti) {
//    if (ti != null) {
//      int h = ti.getHashCode();
//      ArrayList<CellsInfo> list = cells.get(h);
//      if (list == null) {
//        list = new ArrayList<>();
//        cells.put(h, list);
//        transition.put(h, ti);
//        resources.put(h, ci.cell.getBlock().getResources());
//      }
//      list.add(ci);
//    }
//  }
//
//  /**
//   * loop over all transitions and update correspondent tokens
//
//   * @param a
//   * @param logiclevel 
//   * @param negativelogic 
//   * @param toBeMoved 
//   * @param ccl 
//   */
//  public void updateTokens(Activator a, CellChainLink ccl, Vector<CellChainLink> toBeMoved, boolean negativelogic,
//      int logiclevel) {
//
//    if (ClpSemaphore.isStructureLocked()) {
//      return;
//    }
//    for (int h : transition.keySet()) {
//      ActivationInfo ai = transition.get(h);
//      if (isCrossable(h, a, ai)) {
//        ArrayList<CellsInfo> list = cells.get(h);
//        for (CellsInfo ci : list) {
//          updateCellsTokens(ci, ai);
//          checkForRemoval(ci.cell, ccl, toBeMoved, negativelogic, logiclevel);
//        }
//      }
//    }
//  }
//
//  //
//  private boolean isCrossable(int h, Activator a, ActivationInfo ai) {
//    boolean isActivable = false;
//    Resources res = resources.get(h);
//    if (ai.logicalTerms != null) {
//      for (LogicalTerm term : ai.logicalTerms) {
//        isActivable |= isTermTrue(a, res, term, false);
//      }
//    }
//    else {
//      if (ai.transportEntries != null) {
//        isActivable = MarkHandler.getInstance().checkMarks(ai.transportEntries);
//      }
//      else {
//        isActivable = true;
//      }
//      if (isActivable && ai.logicalFactors != null) {
//        for (LogicalFactor fact : ai.logicalFactors) {
//          isActivable &= isFactTrue(a, res, fact, false);
//        }
//      }
//    }
//    return isActivable;
//  }
//
//  //
//  private void updateCellsTokens(CellsInfo ci, ActivationInfo ai) {
//    MarkHandler.getInstance().generateTokens(act, ci.cell.getName(), ai.transportOutputs);
//    ci.activity = Activity.UP;
//    ArrayList<Cell> touched = MarkHandler.getInstance().consumeTokens(act, ai.transportEntries);
//    if (touched != null) {
//      for (Cell cell : touched) {
//        if (isEmpty(cell)) {
//          setInactive(cell.getName());
//        }
//      }
//    }
//  }
//
//  //
//  private boolean isEmpty(Cell cell) {
//    Weightings wts = cell.getWeightings();
//    if (wts != null) {
//      Weighting wt = wts.getWeighting();
//      ArrayList<Weighting> list = new ArrayList<>();
//      if (wt != null) {
//        list.add(wt);
//      }
//      list.addAll(wts.getWeightings());
//      for (Weighting w : list) {
//        if (w.getWeight() > 0) {
//          return false;
//        }
//      }
//    }
//    return true;
//  }
//
//  // check whether cell has to be removed from inactive queue (and put in the active one
//  private void checkForRemoval(Cell cell, CellChainLink ccl, Vector<CellChainLink> toBeMoved, boolean negativelogic,
//      int logiclevel) {
//
//    while (ccl != null) {
//      if (ccl.getCell() == cell) {  // cell found in inactive queue
//        if (negativelogic) {
//          cell.setActivity((cell.getActivity()+1)%logiclevel);
//        }
//        else {
//          cell.setActivity(logiclevel-1);
//        }
//        if (cell.getActivity() >= logiclevel-1) {
//          ConsoleProvider.getInstance().print("activated: " + ccl.getName());
//          toBeMoved.add(ccl);
//          EventHandler.getInstance().markCellUp(cell.getName());
//        }
//        break;
//      }
//      ccl = ccl.getNext();
//    }
//  }
//
//  //
//  private boolean isTermTrue(Activator a, Resources res, LogicalTerm lterm, boolean isForAlreadyActiveCell) {
//    boolean result = isFactTrue(a, res, lterm.getLogicalFactor(), isForAlreadyActiveCell);
//    if ( result && lterm.hasLogicalFactors() ) {
//      for (LogicalFactor lfact : lterm.getLogicalFactors()) {
//        if (!isFactTrue(a, res, lfact, isForAlreadyActiveCell)) {
//          return false;
//        }
//      }
//    }
//    return result;
//  }
//
//  //
//  private boolean isFactTrue(Activator a, Resources res, LogicalFactor lfact, boolean isForAlreadyActiveCell) {
//    ClpTokenLogicVisitor vis = new ClpTokenLogicVisitor(res, a, isForAlreadyActiveCell);
//    vis.accept(lfact);
//    return vis.isTrue();
//  }
//
//  /**
//   * take over activator's job: returns activated cells to be moved
//   * @param ccl
//   * @param toBeMoved
//   * @param negativelogic
//   * @param logiclevel
//   */
//  public void treatCell(CellChainLink ccl, Vector<CellChainLink> toBeMoved, boolean negativelogic,
//      int logiclevel) {
//
//    if (ClpSemaphore.isStructureLocked()) {
//      return;
//    }
//
//    Cell cell = ccl.getCell();
//    if (activable(ccl.getCell())) {  // CHECK ACTIVITY ENABLING
//      if (negativelogic) {
//        cell.setActivity((cell.getActivity()+1)%logiclevel);
//      }
//      else {
//        cell.setActivity(logiclevel-1);
//      }
//      if (cell.getActivity() >= logiclevel-1) {
//        ConsoleProvider.getInstance().print("activated: " + ccl.getName());
//        toBeMoved.add(ccl);
//        EventHandler.getInstance().markCellUp(cell.getName());
//      }
//    }
//  }
//
//  //
//  private boolean activable(Cell cell) {
//    return table.get(cell.getName()).activity == Activity.UP;
//  }
//
//  public boolean isActive(String name) {
//    CellsInfo ci = table.get(name);
//    if (ci != null) {
//      return ci.activity == Activity.UP;
//    }
//    return true;
//  }
//
//  public boolean isInactive(String name) {
//    CellsInfo ci = table.get(name);
//    if (ci != null) {
//      return ci.activity == Activity.DOWN;
//    }
//    return true;
//  }
//
//  public void setInactive(String name) {
//    CellsInfo ci = table.get(name);
//    if (ci != null) {
//      ci.activity = Activity.DOWN;
//    }
//  }
//
//  public boolean isFull(String name) {
//    CellsInfo ci = table.get(name);
//    if (ci != null) {
//      return ci.isFull;
//    }
//    return true;
//  }
//
//  public static void clearAll() {
//    for (TokenHandlerCopy t : instances.values()) {
//      t.table.clear();
//      t.transition.clear();
//      t.resources.clear();
//      t.cells.clear();
//    }
//  }
//
//  public ArrayList<Weighting> getExpected(String name) {
//    CellsInfo ci = table.get(name);
//    if (ci != null) {
//      return ci.expected;
//    }
//    return null;
//  }
//}
