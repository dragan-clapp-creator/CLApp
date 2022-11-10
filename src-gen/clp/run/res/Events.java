package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Events  implements java.io.Serializable {

  private static final long serialVersionUID = 43L;

  //=== Attributes =============================================================

  private clp.run.res.Event event;
  private char ckey;
  private java.util.ArrayList<clp.run.res.Event> events = new java.util.ArrayList<clp.run.res.Event>();


  //=== Constructor ============================================================

  public Events() {
  }

  //=== Methods ================================================================

  public clp.run.res.Event getEvent() {
    return event;
  }

  public void setEvent(clp.run.res.Event x) {
    this.event = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasEvents() {
    return events != null && !events.isEmpty();
  }

  public java.util.ArrayList<clp.run.res.Event> getEvents() {
    return events;
  }

  public void setEvents(java.util.ArrayList<clp.run.res.Event> x) {
    events = x;
  }

  public void addEvent(clp.run.res.Event x) {
    events.add( x );
  }

  public void removeEvent(clp.run.res.Event x) {
    events.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
