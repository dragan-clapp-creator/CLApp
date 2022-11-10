package clapp.run.token;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import clapp.run.http.ResponseHandler;
import clapp.run.token.TokenHandler.CellWeightingsInfo;
import clapp.run.token.TokenHandler.ProducedWeightingsInfo;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.Heap;
import clp.run.cel.Weightings;
import clp.run.msc.MetaScenario;
import clp.run.res.CellMarks;
import clp.run.res.Marks;
import clp.run.res.Weighting;
import clp.run.scn.Scenario;

public class MarkHandler {

  private static final MarkHandler instance = new MarkHandler();

  private Hashtable<String, Cell> markedCells;

  private ResponseHandler responseHandler; 
  /**
   * SINGLETON provider
   * 
   * @return instance
   */
  static public MarkHandler getInstance() {
    return instance;
  }

  // PRIVATE CONSTRUCTOR
  private MarkHandler() {
    markedCells = new Hashtable<>();
  }

  /**
   * checks whether given marks are present within already marked cells
   * 
   * @param transportEntries
   * @return
   */
  public boolean checkMarks(Hashtable<String, ArrayList<Weighting>> transportEntries) {
    if (transportEntries != null) {
      for (String name : transportEntries.keySet()) {
        Cell cell = markedCells.get(name);
        if (cell == null) {
          return false;
        }
        ArrayList<Weighting> elist = transportEntries.get(name);
        for (Weighting w : elist) {
          boolean found = false;
          
          ArrayList<Weighting> clist = new ArrayList<>();
          Weighting cw = cell.getWeightings().getWeighting();
          if (cw != null) {
            clist.add(cw);
          }
          clist.addAll(cell.getWeightings().getWeightings());
          for (Weighting wc : clist) {
            if (wc.getMark() == w.getMark()) {
              found = true;
              if (wc.getWeight() < w.getWeight()) {
                return false;
              }
            }
          }
          if (!found) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * checks whether given marks are present within already marked cells
   * 
   * @param cell
   * @param entries
   * @return true if enough marks present
   */
  public boolean isEoughMarks(Cell cell, List<Weighting> entries) {
    if (cell.getWeightings() == null) {
      return false;
    }
    for (Weighting w : entries) {
      boolean found = false;
      ArrayList<Weighting> clist = new ArrayList<>();
      Weighting cw = cell.getWeightings().getWeighting();
      if (cw != null) {
        clist.add(cw);
      }
      clist.addAll(cell.getWeightings().getWeightings());
      for (Weighting wc : clist) {
        if (wc.getMark() == w.getMark()) {
          found = true;
          if (wc.getWeight() < w.getWeight()) {
            return false;
          }
        }
      }
      if (!found) {
        return false;
      }
    }
    return true;
  }

  /**
   * consumes tokens of cells from above of this crossing and mark them as deactivated
   * 
   * @param act
   * @param consumption
   * @param toBeDeactivated 
   */
  public void consumeTokens(Actor act, Hashtable<String, CellWeightingsInfo> consumption, TreeSet<String> toBeDeactivated) {
    if (consumption != null) {
      for (String name : consumption.keySet()) {
        Cell cell = markedCells.get(name);
        if (cell == null) {
          cell = findCell(act, name);
          if (cell == null) {
            break;
          }
          System.out.println(name+" unmarked");
          markedCells.put(name, cell);
          if (responseHandler != null) {
            responseHandler.notifyDone(cell.getName(), false);
          }
        }
        List<Weighting> elist = consumption.get(name).getWeightings();
        if (cell != null && elist != null) {
          updateCellsWeights(elist, cell, toBeDeactivated);
        }
      }
    }
  }

  //
  private void updateCellsWeights(List<Weighting> elist, Cell cell, TreeSet<String> toBeDeactivated) {
    for (Weighting w : elist) {
      ArrayList<Weighting> clist = new ArrayList<>();
      Weighting cw = cell.getWeightings().getWeighting();
      if (cw != null) {
        clist.add(cw);
      }
      clist.addAll(cell.getWeightings().getWeightings());
      for (Weighting wc : clist) {
        if (wc.getMark() == w.getMark()) {
          int delta = wc.getWeight() - w.getWeight();
          if (delta >= 0) {
            wc.setWeight(delta);
            toBeDeactivated.add(cell.getName());
          }
        }
      }
    }
  }

  /**
   * produces tokens for activated cells and mark them as activated
   * 
   * @param production
   * @param activated 
   * @param lastActivated 
   * @return 
   */
  public boolean produceTokens(ProducedWeightingsInfo production, Set<String> activated, TreeSet<String> lastActivated) {
     if (production != null) {
      Cell cell = production.getCell();
      if (cell == null || lastActivated != null && lastActivated.contains(cell.getName())) {
        return false;
      }
      Weightings cws = cell.getWeightings();
      if (cws == null) {
        cws = new Weightings();
        cell.setWeightings(cws);
        for (Weighting w : production.getWeightings()) {
          Weighting cw = new Weighting();
          cws.addWeighting(cw);
          cw.setMark(w.getMark());
          cw.setWeight(w.getWeight());
        }
        cell.setActivity(1);
        activated.add(cell.getName());
        if (responseHandler != null) {
          responseHandler.notifyDone(cell.getName(), true);
        }
      }
      else {
        Hashtable<Character, Integer> h = new Hashtable<>();
        Weighting cw = cws.getWeighting();
        if (cw != null) {
          h.put(cw.getMark(), cw.getWeight());
        }
        for (Weighting lcw : cws.getWeightings()) {
          h.put(lcw.getMark(), lcw.getWeight());
        }
        for (Weighting w : production.getWeightings()) {
          Integer v = h.get(w.getMark());
          if (v != null) {
            h.put(w.getMark(), w.getWeight() + v);
          }
          else {
            h.put(w.getMark(), w.getWeight());
          }
        }
        cws = new Weightings();
        cell.setWeightings(cws);
        for (Character c : h.keySet()) {
          cw = new Weighting();
          cws.addWeighting(cw);
          cw.setMark(c);
          cw.setWeight(h.get(c));
        }
        cell.setActivity(1);
        activated.add(cell.getName());
        if (responseHandler != null) {
          responseHandler.notifyDone(cell.getName(), true);
        }
      }
      produceTokens(production.getNext(), activated, lastActivated);
    }
    return true;
  }

  //
  protected Cell findCell(Actor act, String cellName) {
    for (Heap heap : act.getHeaps()) {
      for (Cell cell : heap.getCells()) {
        if (cell.getName().equals(cellName)) {
          return cell;
        }
      }
    }
    return null;
  }

  /**
   * @return the markedCells
   */
  public Hashtable<String, Cell> getMarkedCells() {
    return markedCells;
  }

  /**
   * sets initial marks to corresponding cells (they have to be found within the clapp structure)
   * these cells will also be activated at that point
   * 
   * @param msc
   * @param marks
   */
  public void setInitialMarks(MetaScenario msc, Marks marks) {
    ArrayList<CellMarks> list = new ArrayList<>();
    list.add( marks.getCellMarkSet().getCellMarks() );
    list.addAll( marks.getCellMarkSet().getCellMarkss() );
    for (CellMarks cm : list) {
      String name = cm.getCellName();
      Cell cell = findCell(msc, name);
      if (cell != null) {
        Weightings weightings = cell.getWeightings();
        if (weightings == null) {
          weightings = new Weightings();
          cell.setWeightings(weightings);
        }
        markedCells.put(name, cell);
        weightings.setWeighting(cm.getCellMarkCheck().getWeighting());
        weightings.setWeightings(cm.getCellMarkCheck().getWeightings());
      }
    }
  }

  //
  private Cell findCell(MetaScenario msc, String name) {
    for (Scenario scn : msc.getMetaScenarioBody().getScenarios()) {
      for (Actor act : scn.getScenarioBody().getActors()) {
        for (Heap heap : act.getHeaps()) {
          for (Cell cell : heap.getCells()) {
            if (cell.getName().equals(name)) {
              return cell;
            }
          }
        }
      }
    }
    return null;
  }

  public void register(ResponseHandler rh) {
    this.responseHandler = rh;
  }
}
