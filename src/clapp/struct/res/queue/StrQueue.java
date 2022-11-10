/**
 * 
 */
package clapp.struct.res.queue;



/**
 * @author Dragan Matic
 *
 */
public class StrQueue {

  private StrObject top;
  private StrObject bottom;

  public StrQueue() {
    top = new StrObject(null);	// dummy first element
    bottom = top;
  }

  /**
   * enqueue a new boolean element
   * @param s
   */
  public void add(String s) {
    StrObject bo = bottom;
    bottom = new StrObject(s);
    bo.setNext(bottom);
  }

  /**
   * enqueue a new boolean element
   * @param s
   */
  public String get(String s) {
    if (top == bottom) {
      return s;		// in that case, queue is empty
    }
    StrObject bo = top.getNext();
    top.setNext(null);	// give a chance to the GC to work
    top = bo;
    return bo.getValue();
  }

  /**
   * true if empty queue
   * @return
   */
  public boolean isEmpty() {
    return top == bottom;
  }

  /**
   * inner class representing a boolean object that will be enqueued
   * @author Dragan Matic
   */
  class StrObject {
    private String value;
    private StrObject next;

    StrObject(String val) {
      value = val;
    }

    public String getValue() {
      return value;
    }

    public StrObject getNext() {
      return next;
    }

    public void setNext(StrObject next) {
      this.next = next;
    }
  }
}
