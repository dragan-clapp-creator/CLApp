/**
 * 
 */
package clapp.struct.res.queue;

import java.util.Map;



/**
 * @author Dragan Matic
 *
 */
public class HashQueue {

  private HashObject top;
  private HashObject bottom;

  public HashQueue() {
    top = new HashObject(null);	// dummy first element
    bottom = top;
  }

  /**
   * enqueue a new Object element
   * @param o
   */
  public void add(Map o) {
    HashObject bo = bottom;
    bottom = new HashObject(o);
    bo.setNext(bottom);
  }

  /**
   * gequeue an Object element
   * @param o
   */
  public Map get(Map o) {
    if (top == bottom) {
      return o;		// in that case, queue is empty
    }
    HashObject bo = top.getNext();
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
  class HashObject {
    private Map value;
    private HashObject next;

    HashObject(Map val) {
      value = val;
    }

    public Map getValue() {
      return value;
    }

    public HashObject getNext() {
      return next;
    }

    public void setNext(HashObject next) {
      this.next = next;
    }
  }
}
