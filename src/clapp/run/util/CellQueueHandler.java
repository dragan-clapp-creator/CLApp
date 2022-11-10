package clapp.run.util;

import java.io.Serializable;
import java.util.List;

import clp.run.cel.Cell;
import clp.run.res.Resources;
import clp.run.scn.ScnQueue;
import clp.run.scn.SortOrder;

/**
 * this class provides us with queues
 * each queue has, as properties, a name and a sorting order
 * furthermore, it has a link to the first and last cell wrapper (CellChainLink)
 * enabling a navigation through these cells
 * 
 * @author Dragan Matic
 *
 */
public class CellQueueHandler implements Serializable {

  private static final long serialVersionUID = 6469996583665647166L;

  private CellChainLink firstCell;
  private CellChainLink lastCell;

  private String name;
  private SortOrder sortOrder;
  private int numberOfCells;

  private Resources res;

  // ===== CONSTRUCTOR =========================================================

  public CellQueueHandler(ScnQueue queue) {
    this.name = queue.getName();
    this.sortOrder = queue.getSortOrder();
  }

  // ===== PUBLIC METHODS ======================================================

  /**
   * add a cell list to cell list of the current queue
   */
  public void addToCellList(List<Cell> list) {
    for (int i=0; i<list.size(); i++) {
      addToCellList(list.get(i));
    }
  }

  /**
   * add a cell to cell list of the current queue
   * @param cell 
   */
  public void addToCellList(Cell cell) {
    if (CellChainLink.isAlreadyThere(cell)) {
      return;
    }
    CellChainLink ccl = new CellChainLink(cell);
    numberOfCells++;
    chain(ccl);
  }

  /**
   * check whether a cell exists in the queue by comparing its name to those
   * chained to that queue, if not returns null, otherwise true
   * @param cellName
   * @return
   */
  public boolean foundInQueue(String cellName) {
    CellChainLink current = firstCell;
    while (current != null) {
      if (current.getName().equals(cellName)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  /**
   * get queue name
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * get first cell wrapper
   * @return firstCell
   */
  public CellChainLink getFirstCell() {
    return firstCell;
  }

  /**
   * get last cell wrapper
   * @return lastCell
   */
  public CellChainLink getLastCell() {
    return lastCell;
  }

  /**
   * number of cells in this queue
   * @return
   */
  public int getNumberOfCells() {
    return numberOfCells;
  }

  /**
   * increases number of cells of this queue
   * @return
   */
  public void incNumberOfCells() {
    numberOfCells++;
  }

  /**
   * decreases number of cells of this queue
   * @return
   */
  public void decNumberOfCells() {
    numberOfCells--;
  }

  /**
   * removes a cell from the chain if found
   * @param c
   */
  public boolean remove(Cell c) {
    CellChainLink ccl = firstCell;
    while (ccl != null) {
      if (ccl.getName().equals(c.getName())) {
        CellChainLink next = ccl.getNext();
        CellChainLink prev = ccl.getPrevious();
        if (prev == null) {
          firstCell = next;
          if (firstCell == null) {
            lastCell = null;
          }
        }
        else {
          prev.setNext(next);
        }
        return true;
      }
      ccl = ccl.getNext();
    }
    return false;
  }

  // ===== STATIC PUBLIC METHODS ===============================================

  /**
   * logical move of a cell (in fact, from the cell wrapper) from source queue
   * to destination queue, according to the destination's sort order
   * as the cell is supposed to be activated, its activation count is increased
   */
  public static void activateCell(CellChainLink ccl,
                                  CellQueueHandler source_queue,
                                  CellQueueHandler dest_queue) {
    if (dest_queue != null) {
      moveCell(ccl, source_queue, dest_queue);
    }
    ccl.addNumberOfActivations();
  }

  /**
   * logical move of a cell (in fact, from the cell wrapper) from source queue
   * to destination queue, according to the destination's sort order
   */
  public static void deactivateCell(CellChainLink ccl,
                                    CellQueueHandler source_queue,
                                    CellQueueHandler dest_queue) {
    if (dest_queue != null) {
      moveCell(ccl, source_queue, dest_queue);
    }
  }


  // ===== PRIVATE METHODS =====================================================

  private static void moveCell(CellChainLink ccl,
                               CellQueueHandler source_queue,
                               CellQueueHandler dest_queue) {
    CellChainLink prev = ccl.getPrevious();
    CellChainLink next = ccl.getNext();
    if (prev != null) {
      prev.setNext(next);
    }
    else {
      source_queue.firstCell = next;
    }
    if (next != null) {
      next.setPrevious(prev);
    }
    else {
      source_queue.lastCell = prev;
    }
    dest_queue.chain(ccl);
    dest_queue.incNumberOfCells();
    source_queue.decNumberOfCells();
  }

  private void chain(CellChainLink ccl) {
    if (sortOrder == SortOrder.ISERIAL) {
      // TODO give a serial number to each cell
      // for time being, store at end
      storeAtEnd(ccl);
    }
    else if (sortOrder == SortOrder.DSERIAL) {
      // TODO give a serial number to each cell
      // for time being, store at end
      storeAtEnd(ccl);
    }
    else if (sortOrder == SortOrder.QUEUE_TOP) {
      storeAtTop(ccl);
    }
    else if (sortOrder == SortOrder.QUEUE_END) {
      storeAtEnd(ccl);
    }
  }

  private void storeAtTop(CellChainLink ccl) {
    if (firstCell != null) {
      firstCell.setPrevious(ccl);
      ccl.setNext(firstCell);
    }
    firstCell = ccl;
    if (lastCell == null) {
      lastCell = firstCell;
    }
  }

  private void storeAtEnd(CellChainLink ccl) {
    ccl.setPrevious(lastCell);
    if (lastCell != null) {
      lastCell.setNext(ccl);
    }
    lastCell = ccl;
    if (firstCell == null) {
      firstCell = lastCell;
    }
    ccl.setNext(null);
  }

  public void addRelatedResources(Resources r) {
    res = r;
  }

  /**
   * @return the res
   */
  public Resources getRes() {
    return res;
  }
}
