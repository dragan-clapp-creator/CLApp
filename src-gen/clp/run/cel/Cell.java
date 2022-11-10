package clp.run.cel;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Cell  implements java.io.Serializable {

  private static final long serialVersionUID = 220L;

  //=== Attributes =============================================================

  private String name;
  private clp.run.cel.Heap block;
  private clp.run.cel.Weightings weightings;
  private boolean isUsedHeap;
  private String heapName;
  private boolean isHeapName;
  private int activity;
  private boolean isActivity;
  private boolean isAdomain;
  private clp.run.cel.dom.Adomain adomain;
  private boolean isDdomain;
  private clp.run.cel.dom.Ddomain ddomain;
  private boolean isXdomain;
  private clp.run.cel.dom.Xdomain xdomain;


  //=== Constructor ============================================================

  public Cell() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.Heap getBlock() {
    return block;
  }

  public void setBlock(clp.run.cel.Heap x) {
    this.block = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.Weightings getWeightings() {
    return weightings;
  }

  public void setWeightings(clp.run.cel.Weightings x) {
    this.weightings = x;
  }

  //----------------------------------------------------------------------------

  public boolean isUsedHeap() {
    return isUsedHeap;
  }

  public void setIsUsedHeap(boolean x) {
    this.isUsedHeap = x;
  }

  //----------------------------------------------------------------------------

  public String getHeapName() {
    return heapName;
  }

  public void setHeapName(String x) {
    this.heapName = x;
  }

  //----------------------------------------------------------------------------


  public boolean isHeapName() {
    return isHeapName;
  }

  public void setIsHeapName(boolean x) {
    this.isHeapName = x;
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

  public boolean isAdomain() {
    return isAdomain;
  }

  public void setIsAdomain(boolean x) {
    this.isAdomain = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.dom.Adomain getAdomain() {
    return adomain;
  }

  public void setAdomain(clp.run.cel.dom.Adomain x) {
    this.adomain = x;
  }

  //----------------------------------------------------------------------------

  public boolean isDdomain() {
    return isDdomain;
  }

  public void setIsDdomain(boolean x) {
    this.isDdomain = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.dom.Ddomain getDdomain() {
    return ddomain;
  }

  public void setDdomain(clp.run.cel.dom.Ddomain x) {
    this.ddomain = x;
  }

  //----------------------------------------------------------------------------

  public boolean isXdomain() {
    return isXdomain;
  }

  public void setIsXdomain(boolean x) {
    this.isXdomain = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.cel.dom.Xdomain getXdomain() {
    return xdomain;
  }

  public void setXdomain(clp.run.cel.dom.Xdomain x) {
    this.xdomain = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
