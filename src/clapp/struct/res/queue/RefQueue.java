/**
 * 
 */
package clapp.struct.res.queue;



/**
 * @author Dragan Matic
 *
 */
public class RefQueue {

  private RefObject top;
  private RefObject bottom;

  public RefQueue() {
    top = new RefObject(null);	// dummy first element
    bottom = top;
  }

  /**
   * enqueue a new Object element
   * @param o
   */
  public void add(Object o) {
    RefObject bo = bottom;
    bottom = new RefObject(o);
    bo.setNext(bottom);
  }

  /**
   * gequeue an Object element
   * @param o
   */
  public Object get(Object o) {
    if (top == bottom) {
      return o;		// in that case, queue is empty
    }
    RefObject bo = top.getNext();
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
   * inner class representing an Object object that will be enqueued
   * @author Dragan Matic
   */
  class RefObject {
    private Object value;
    private RefObject next;

    RefObject(Object val) {
      value = val;
    }

    public Object getValue() {
      return value;
    }

    public RefObject getNext() {
      return next;
    }

    public void setNext(RefObject next) {
      this.next = next;
    }
  }
}
