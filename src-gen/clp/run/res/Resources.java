package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Resources  implements java.io.Serializable {

  private static final long serialVersionUID = 36L;

  //=== Attributes =============================================================

  private String name;
  private clp.run.msc.MetaScenario metaScenario;
  private boolean isUsedLib;
  private clp.run.res.UsedLib usedLib;
  private boolean isEvents;
  private clp.run.res.Events events;
  private boolean isMarks;
  private clp.run.res.Marks marks;
  private java.util.ArrayList<clp.run.res.Variable> variables = new java.util.ArrayList<clp.run.res.Variable>();
  private String msc;


  //=== Constructor ============================================================

  public Resources() {
  }

  //=== Methods ================================================================

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.msc.MetaScenario getMetaScenario() {
    return metaScenario;
  }

  public void setMetaScenario(clp.run.msc.MetaScenario x) {
    this.metaScenario = x;
  }

  //----------------------------------------------------------------------------

  public boolean isUsedLib() {
    return isUsedLib;
  }

  public void setIsUsedLib(boolean x) {
    this.isUsedLib = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.UsedLib getUsedLib() {
    return usedLib;
  }

  public void setUsedLib(clp.run.res.UsedLib x) {
    this.usedLib = x;
  }

  //----------------------------------------------------------------------------

  public boolean isEvents() {
    return isEvents;
  }

  public void setIsEvents(boolean x) {
    this.isEvents = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.Events getEvents() {
    return events;
  }

  public void setEvents(clp.run.res.Events x) {
    this.events = x;
  }

  //----------------------------------------------------------------------------

  public boolean isMarks() {
    return isMarks;
  }

  public void setIsMarks(boolean x) {
    this.isMarks = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.Marks getMarks() {
    return marks;
  }

  public void setMarks(clp.run.res.Marks x) {
    this.marks = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasVariables() {
    return variables != null && !variables.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Variable> getVariables() {
    return variables;
  }

  public void setVariables(java.util.ArrayList<clp.run.res.Variable> x) {
    variables = x;
  }

  public void addVariable(clp.run.res.Variable x) {
    variables.add( x );
  }

  public void removeVariable(clp.run.res.Variable x) {
    variables.remove( x );
  }

  //----------------------------------------------------------------------------

  public String getMsc() {
    return msc;
  }

  public void setMsc(String x) {
    this.msc = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
