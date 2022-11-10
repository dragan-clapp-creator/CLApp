/**
 * 
 */
package clapp.struct.res.queue;



/**
 * @author Dragan Matic
 *
 */
public class LongQueue {

  private LongObject top;
  private LongObject bottom;

  public LongQueue() {
    top = new LongObject(0);	// dummy first element
    bottom = top;
  }

  /**
   * enqueue a new boolean element
   * @param b
   */
  public void add(long b) {
    LongObject bo = bottom;
    bottom = new LongObject(b);
    bo.setNext(bottom);
  }

  /**
   * enqueue a new boolean element
   * @param b
   */
  public long get(long b) {
    if (top == bottom) {
      return b;		// in that case, queue is empty
    }
    LongObject bo = top.getNext();
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
  class LongObject {
    private long value;
    private LongObject next;

    LongObject(long val) {
      value = val;
    }

    public long getValue() {
      return value;
    }

    public LongObject getNext() {
      return next;
    }

    public void setNext(LongObject next) {
      this.next = next;
    }
  }
}
