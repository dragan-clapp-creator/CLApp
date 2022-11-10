/**
 * 
 */
package clapp.struct.res.queue;

import java.util.ArrayList;


/**
 * @author Dragan Matic
 *
 */
public class ArrayQueue {

  private ArrayObject top;
  private ArrayObject bottom;

  public ArrayQueue() {
    top = new ArrayObject(new ArrayList());	// dummy first element
    bottom = top;
  }

  /**
   * enqueue a new ArrayList element
   * @param b
   */
  public void add(ArrayList b) {
    ArrayObject bo = bottom;
    bottom = new ArrayObject(b);
    bo.setNext(bottom);
  }

  /**
   * dequeue an ArrayList element
   * @param avalues
   */
  public ArrayList get(ArrayList b) {
    if (top == bottom) {
      return b;		// in that case, queue is empty
    }
    ArrayObject bo = top.getNext();
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
  class ArrayObject {
    private ArrayList value;
    private ArrayObject next;

    ArrayObject(ArrayList val) {
      value = val;
    }

    public ArrayList getValue() {
      return value;
    }

    public ArrayObject getNext() {
      return next;
    }

    public void setNext(ArrayObject next) {
      this.next = next;
    }
  }
}
