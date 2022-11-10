/**
 * 
 */
package clapp.struct.res.queue;



/**
 * @author Dragan Matic
 *
 */
public class BoolQueue {

  private BoolObject top;
  private BoolObject bottom;

  public BoolQueue() {
    top = new BoolObject(false);	// dummy first element
    bottom = top;
  }

  /**
   * enqueue a new boolean element
   * @param b
   */
  public void add(boolean b) {
    BoolObject bo = bottom;
    bottom = new BoolObject(b);
    bo.setNext(bottom);
  }

  /**
   * dequeue a boolean element
   * @param b
   */
  public boolean get(boolean b) {
    if (top == bottom) {
      return b;		// in that case, queue is empty
    }
    BoolObject bo = top.getNext();
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
  class BoolObject {
    private boolean value;
    private BoolObject next;

    BoolObject(boolean val) {
      value = val;
    }

    public boolean getValue() {
      return value;
    }

    public BoolObject getNext() {
      return next;
    }

    public void setNext(BoolObject next) {
      this.next = next;
    }
  }
}
