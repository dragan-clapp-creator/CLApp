package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CellMarkSet  implements java.io.Serializable {

  private static final long serialVersionUID = 50L;

  //=== Attributes =============================================================

  private clp.run.res.CellMarks cellMarks;
  private char ckey;
  private java.util.ArrayList<clp.run.res.CellMarks> cellMarkss = new java.util.ArrayList<clp.run.res.CellMarks>();


  //=== Constructor ============================================================

  public CellMarkSet() {
  }

  //=== Methods ================================================================

  public clp.run.res.CellMarks getCellMarks() {
    return cellMarks;
  }

  public void setCellMarks(clp.run.res.CellMarks x) {
    this.cellMarks = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasCellMarkss() {
    return cellMarkss != null && !cellMarkss.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.CellMarks> getCellMarkss() {
    return cellMarkss;
  }

  public void setCellMarkss(java.util.ArrayList<clp.run.res.CellMarks> x) {
    cellMarkss = x;
  }

  public void addCellMarks(clp.run.res.CellMarks x) {
    cellMarkss.add( x );
  }

  public void removeCellMarks(clp.run.res.CellMarks x) {
    cellMarkss.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
