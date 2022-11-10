package clp.run.cel;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Heap  implements java.io.Serializable {

  private static final long serialVersionUID = 34L;

  //=== Attributes =============================================================

  private String name;
  private clp.run.act.Actor actor;
  private boolean isUsedResources;
  private String res;
  private boolean isRes;
  private clp.run.res.Resources resources;
  private String load;
  private char ckey;
  private int activity;
  private boolean isActivity;
  private java.util.ArrayList<clp.run.cel.Cell> cells = new java.util.ArrayList<clp.run.cel.Cell>();
  private String act;


  //=== Constructor ============================================================

  public Heap() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.act.Actor getActor() {
    return actor;
  }

  public void setActor(clp.run.act.Actor x) {
    this.actor = x;
  }

  //----------------------------------------------------------------------------

  public boolean isUsedResources() {
    return isUsedResources;
  }

  public void setIsUsedResources(boolean x) {
    this.isUsedResources = x;
  }

  //----------------------------------------------------------------------------

  public String getRes() {
    return res;
  }

  public void setRes(String x) {
    this.res = x;
  }

  //----------------------------------------------------------------------------


  public boolean isRes() {
    return isRes;
  }

  public void setIsRes(boolean x) {
    this.isRes = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.Resources getResources() {
    return resources;
  }

  public void setResources(clp.run.res.Resources x) {
    this.resources = x;
  }

  //----------------------------------------------------------------------------

  public String getLoad() {
    return load;
  }

  public void setLoad(String x) {
    this.load = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public int getActivity() {
    return activity;
  }

  public void setActivity(int x) {
    this.activity = x;
  }

  //----------------------------------------------------------------------------


  public boolean isActivity() {
    return isActivity;
  }

  public void setIsActivity(boolean x) {
    this.isActivity = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasCells() {
    return cells != null && !cells.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.Cell> getCells() {
    return cells;
  }

  public void setCells(java.util.ArrayList<clp.run.cel.Cell> x) {
    cells = x;
  }

  public void addCell(clp.run.cel.Cell x) {
    cells.add( x );
  }

  public void removeCell(clp.run.cel.Cell x) {
    cells.remove( x );
  }

  //----------------------------------------------------------------------------

  public String getAct() {
    return act;
  }

  public void setAct(String x) {
    this.act = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
