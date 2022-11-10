package clp.run.cel.log;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LogicalFactor  implements java.io.Serializable {

  private static final long serialVersionUID = 231L;

  //=== Attributes =============================================================

  private boolean isNOT;
  private clp.run.cel.log.Tautology tautology;
  private clp.run.cel.log.CellFunction cellFunction;
  private clp.run.cel.log.SysFunction sysFunction;
  private clp.run.cel.log.CheckEvent checkEvent;
  private clp.run.cel.log.Lexpression lexpression;
  private clp.run.cel.log.Comparison comparison;
  private clp.run.cel.log.Bvariable bvariable;


  //=== Constructor ============================================================

  public LogicalFactor() {
  }

  //=== Methods ================================================================

  public boolean isNOT() {
    return isNOT;
  }

  public void setIsNOT(boolean x) {
    this.isNOT = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.log.Tautology getTautology() {
    return tautology;
  }

  public void setTautology(clp.run.cel.log.Tautology x) {
    this.tautology = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.log.CellFunction getCellFunction() {
    return cellFunction;
  }

  public void setCellFunction(clp.run.cel.log.CellFunction x) {
    this.cellFunction = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.log.SysFunction getSysFunction() {
    return sysFunction;
  }

  public void setSysFunction(clp.run.cel.log.SysFunction x) {
    this.sysFunction = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.log.CheckEvent getCheckEvent() {
    return checkEvent;
  }

  public void setCheckEvent(clp.run.cel.log.CheckEvent x) {
    this.checkEvent = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.log.Lexpression getLexpression() {
    return lexpression;
  }

  public void setLexpression(clp.run.cel.log.Lexpression x) {
    this.lexpression = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.log.Comparison getComparison() {
    return comparison;
  }

  public void setComparison(clp.run.cel.log.Comparison x) {
    this.comparison = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.log.Bvariable getBvariable() {
    return bvariable;
  }

  public void setBvariable(clp.run.cel.log.Bvariable x) {
    this.bvariable = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
