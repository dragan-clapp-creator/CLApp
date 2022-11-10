package clapp.run.token;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;

import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.CellChainLink;
import clapp.run.util.CellChainLink.ActivationLine;
import clapp.run.util.CellQueueHandler;
import clapp.run.util.ResourceUtility;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.Weightings;
import clp.run.cel.log.LogicalFactor;
import clp.run.cel.log.LogicalTerm;
import clp.run.res.CellMarkCheck;
import clp.run.res.CellMarkSet;
import clp.run.res.CellMarks;
import clp.run.res.Resources;
import clp.run.res.Weighting;

public class TokenHandler {

  //== STATIC =======================================================

  private static class TransitionInfo {
    public Resources res;
    private List<LogicalFactor> logicalFactors;
    private List<LogicalTerm> logicalTerms;
    private Hashtable<String, CellWeightingsInfo> consumption;
    private ProducedWeightingsInfo production;
    public void add(ProducedWeightingsInfo prod) {
      if (production == null) {
        production = prod;
      }
      else {
        production.add(prod);
      }
    }
  }

  public static class CellWeightingsInfo {
    private List<Weighting> weightings;

    public List<Weighting> getWeightings() {
      return weightings;
    }
  }

  public static class ProducedWeightingsInfo {
    private Cell cell;
    private List<Weighting> weightings;
    private ProducedWeightingsInfo next;
    public void add(ProducedWeightingsInfo prod) {
      if (next == null) {
        next = prod;
      }
      else {
        next.add(prod);
      }
    }
    public Cell getCell() {
      return cell;
    }
    public List<Weighting> getWeightings() {
      return weightings;
    }
    public ProducedWeightingsInfo getNext() {
      return next;
    }
  }

  private static Hashtable<Actor, TokenHandler> instances = new Hashtable<>();

  public static void loadMarks(String name, String marks) {
    for (TokenHandler th : instances.values()) {
      CellChainLink ccl = th.findCellLink(name);
      if (ccl != null) {
        th.addMarksToCell(ccl, marks);
        return;
      }
    }
  }

  //== INSTANCE =====================================================

  private Actor act;
  private Hashtable<String, TransitionInfo> transitions;

  private CellQueueHandler inactive_queue;
  private CellQueueHandler active_queue;

  private boolean isInitialized;
  private int count;
  private TreeSet<String> lastActivated;


  /**
   * PRIVATE CONSTRUCTOR
   */
  private TokenHandler() {
    transitions = new Hashtable<>();
  }

  public static TokenHandler getInstance(Actor act) {
    TokenHandler instance = instances.get(act);
    if (instance == null) {
      instance = new TokenHandler();
      instances.put(act, instance);
    }
    return instance;
  }

  /**
   * initialize all cells
   * 
   * @param act
   * @param iq inactive_queue
   * @param aq active_queue
   */
  public void initialize(Actor act, CellQueueHandler iq, CellQueueHandler aq) {
    this.act = act;
    this.inactive_queue = iq;
    this.active_queue = aq;
    Resources res = aq.getRes();
    fillTransitionInfo(iq, res);
    fillTransitionInfo(aq, res);
    count = 0;
    for (String tr : transitions.keySet()) {
      TransitionInfo ti = transitions.get(tr);
      if (ti.consumption != null && !ti.consumption.isEmpty() ||
          ti.production != null && ti.production.weightings != null && !ti.production.weightings.isEmpty())
        isInitialized = true;
      return;
    }
  }

  /**
   * entry point
   */
  public void proceed() {
    TreeSet<String> toBeActivated = new TreeSet<>();
    TreeSet<String> toBeDeactivated = new TreeSet<>();
    boolean isAccepted = true;
    for (String tr : transitions.keySet()) {
      TransitionInfo ti = transitions.get(tr);
      if (isActivable(ti.res, ti.logicalFactors, ti.logicalTerms) && isEoughMarks(ti.consumption)) {
        MarkHandler.getInstance().consumeTokens(act, ti.consumption, toBeDeactivated);
        if (!MarkHandler.getInstance().produceTokens(ti.production, toBeActivated, lastActivated)) {
          isAccepted = false;
        }
      }
    }
    deactivateAll(toBeDeactivated);
    moveAll(toBeActivated);
    if (isAccepted) {
      lastActivated = toBeActivated;
    }
  }

  //
  private void deactivateAll(TreeSet<String> toBeDeactivated) {
    for (String name : toBeDeactivated) {
      EventHandler.getInstance().markCellDown(name);
      CellChainLink ccl = (CellChainLink) findCellLinkFromInactive(name);
      if (ccl != null) {
        ccl.getCell().setActivity(0);
        ConsoleProvider.getInstance().print("deactivated: " + name);
        CellQueueHandler.deactivateCell(ccl, inactive_queue, active_queue);
      }
    }
  }

  //
  private void moveAll(TreeSet<String> toBeActivated) {
    for (String name : toBeActivated) {
      EventHandler.getInstance().markCellUp(name);
      CellChainLink ccl = (CellChainLink) findCellLinkFromInactive(name);
      if (ccl != null) {
        ConsoleProvider.getInstance().print("activated: " + name + " with activity " + ccl.getCell().getActivity());
        CellQueueHandler.activateCell(ccl, inactive_queue, active_queue);
      }
    }
  }

  //
  private CellChainLink findCellLinkFromInactive(String name) {
    CellChainLink ccl = inactive_queue.getFirstCell();
    while (ccl != null && !ccl.getName().equals(name)) {
      ccl = ccl.getNext();
    }
    return ccl;
  }

  private CellChainLink findCellLink(String name) {
    CellChainLink ccl = findCellLinkFromInactive(name);
    if (ccl == null) {
      ccl = active_queue.getFirstCell();
      while (ccl != null && !ccl.getName().equals(name)) {
        ccl = ccl.getNext();
      }
    }
    return ccl;
  }

  //
  private void addMarksToCell(CellChainLink ccl, String marks) {
    ArrayList<Weighting> list = new ArrayList<>();
    Weightings wts = ccl.getCell().getWeightings();
    if (wts != null) {
      Weighting wt = wts.getWeighting();
      if (wt != null) {
        list.add(wt);
      }
      list.addAll(wts.getWeightings());
    }
    else {
      wts = new Weightings();
      ccl.getCell().setWeightings(wts);
    }
    extractWeightingsFrom(marks, list);
    if (!list.isEmpty()) {
      for (int i=0; i<list.size(); i++) {
        wts.addWeighting(list.get(i));
      }
    }
  }

  //
  private void extractWeightingsFrom(String marks, ArrayList<Weighting> list) {
    // pattern: <c>:<nb>, ...<c>:[<nb>|$<var>]
    String[] splist = marks.split(",");
    for (String str : splist) {
      String[] sp = str.split(":");
      if (sp.length == 2 && sp[0].length() == 1) {
        int nb;
        try {
          if (sp[1].charAt(0) == '$') {
            nb = (int) ResourceUtility.getInstance().getValue(sp[1].substring(1));
          }
          else {
            nb = Integer.parseInt(sp[1]);
          }
          char c = sp[0].charAt(0);
          Weighting wt = findWeighting(list, c);
          if (wt == null) {
            wt = new Weighting();
            wt.setMark(c);
            wt.setWeight(nb);
            list.add(wt);
          }
          else {
            wt.setWeight(wt.getWeight()+nb);
            list.remove(wt);
          }
        }
        catch (NumberFormatException e) {
          return;
        }
      }
    }
  }

  //
  private Weighting findWeighting(ArrayList<Weighting> list0, char c) {
    for (Weighting wt : list0) {
      if (c == wt.getMark()) {
        return wt;
      }
    }
    return null;
  }

  //
  private boolean isEoughMarks(Hashtable<String, CellWeightingsInfo> consumtion) {
    if (consumtion != null) {
      for (String name : consumtion.keySet()) {
        Cell cell = MarkHandler.getInstance().findCell(act, name);
        if (cell == null) {
          System.err.printf("cell %s not found\n", name);
          return false;
        }
        if (!MarkHandler.getInstance().isEoughMarks(cell, consumtion.get(name).weightings)) {
          return false;
        }
      }
    }
    return true;
  }

  //
  private boolean isActivable(Resources res, List<LogicalFactor> logicalFactors, List<LogicalTerm> logicalTerms) {
    boolean isCrossable = true;
    if (logicalFactors != null && !logicalFactors.isEmpty()) {
      for (LogicalFactor lfact : logicalFactors) {
        isCrossable &= isFactTrue(res, lfact);
      }
    }
    else if (logicalTerms != null && !logicalTerms.isEmpty()) {
      isCrossable = false;
      for (LogicalTerm lterm : logicalTerms) {
        isCrossable |= isTermTrue(res, lterm);
      }
    }
    return isCrossable;
  }

  //
  private boolean isFactTrue(Resources res, LogicalFactor lfact) {
    ClpTokenLogicVisitor vis = new ClpTokenLogicVisitor(res, this);
    vis.accept(lfact);
    return vis.isTrue();
  }

  //
  private void fillTransitionInfo(CellQueueHandler queue, Resources resources) {
    CellChainLink ccl = queue.getFirstCell();
    while (ccl != null) {
      if (ccl.getActivationLines() != null) {
        for (ActivationLine al : ccl.getActivationLines()) {
          createTransitionInfo(ccl.getName(), ccl.getCell(), resources, al);
        }
      }
      ccl = ccl.getNext();
    }
  }

  //
  private void createTransitionInfo(String name, Cell cell, Resources res, ActivationLine al) {
    String tr = al.getTrName();
    if (tr == null) {
      tr = "$tr"+count++;
    }
    TransitionInfo ti = transitions.get(tr);
    if (ti == null) {
      ti = new TransitionInfo();
      transitions.put(tr, ti);
      ti.res = res;
      ti.logicalFactors = al.getLogicalFactors();
      ti.logicalTerms = al.getLogicalTerms();
      populateConsumption(ti, al.getTransportEntries());
    }
    ProducedWeightingsInfo prod = generateProduction(cell, al.getOutputs());
    prod.cell = cell;
    ti.add(prod);
  }

  //
  private void populateConsumption(TransitionInfo ti, CellMarkSet transportEntries) {
    if (transportEntries != null) {
      CellMarks cm = transportEntries.getCellMarks();
      addWeightings(ti, cm);
      for (CellMarks acm : transportEntries.getCellMarkss()) {
        addWeightings(ti, acm);
      }
    }
  }

  //
  private void addWeightings(TransitionInfo ti, CellMarks cm) {
    if (cm != null) {
      CellWeightingsInfo info = null;
      Weighting w = cm.getCellMarkCheck().getWeighting();
      if (w != null) {
        info = getOrCreateInfo(ti, cm.getCellName());
        info.weightings = new ArrayList<>();
        info.weightings.add(w);
      }
      ArrayList<Weighting> wlist = cm.getCellMarkCheck().getWeightings();
      if (!wlist.isEmpty() && info == null) {
        info = getOrCreateInfo(ti, cm.getCellName());
        info.weightings = new ArrayList<>();
      }
      for (Weighting cw : wlist) {
        info.weightings.add(cw);
      }
    }
  }

  //
  private CellWeightingsInfo getOrCreateInfo(TransitionInfo ti, String cellName) {
    if (ti.consumption == null) {
      ti.consumption = new Hashtable<>();
      CellWeightingsInfo info = new CellWeightingsInfo();
      ti.consumption.put(cellName, info);
      return info;
    }
    CellWeightingsInfo info = ti.consumption.get(cellName);
    if (info == null) {
      info = new CellWeightingsInfo();
      ti.consumption.put(cellName, info);
    }
    return info;
  }

  //
  private ProducedWeightingsInfo generateProduction(Cell cell, CellMarkCheck outputs) {
    ProducedWeightingsInfo pwi = new ProducedWeightingsInfo();
    pwi.weightings = new ArrayList<>();
    if (outputs != null) {
      pwi.weightings.addAll(outputs.getWeightings());
      if (outputs.getWeighting() != null) {
        pwi.weightings.add(0, outputs.getWeighting());
      }
    }
    pwi.cell = cell;
    return pwi;
  }

  //
  public boolean isTermTrue(Resources res, LogicalTerm lterm) {
    boolean result = isFactTrue(res, lterm.getLogicalFactor());
    if ( result && lterm.hasLogicalFactors() ) {
      for (LogicalFactor lfact : lterm.getLogicalFactors()) {
        if (!isFactTrue(res, lfact)) {
          return false;
        }
      }
    }
    return result;
  }

  public static void clearAll() {
    for (TokenHandler t : instances.values()) {
      t.transitions.clear();
    }
    instances.clear();
  }

  public boolean isActiveInQueue(String name) {
    return active_queue.foundInQueue(name);
  }

  public boolean isInactiveInQueue(String name) {
    return !active_queue.foundInQueue(name);
  }

  /**
   * @return the isInitialized
   */
  public boolean isInitialized() {
    return isInitialized;
  }
}
