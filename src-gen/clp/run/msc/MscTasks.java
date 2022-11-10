package clp.run.msc;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MscTasks  implements java.io.Serializable {

  private static final long serialVersionUID = 4L;

  //=== Attributes =============================================================

  private java.util.ArrayList<clp.run.msc.MscTaskName> mscTaskNames = new java.util.ArrayList<clp.run.msc.MscTaskName>();
  private char ckey;


  //=== Constructor ============================================================

  public MscTasks() {
  }

  //=== Methods ================================================================

  public boolean hasMscTaskNames() {
    return mscTaskNames != null && !mscTaskNames.isEmpty();
  }

  public java.util.ArrayList<clp.run.msc.MscTaskName> getMscTaskNames() {
    return mscTaskNames;
  }

  public void setMscTaskNames(java.util.ArrayList<clp.run.msc.MscTaskName> x) {
    mscTaskNames = x;
  }

  public void addMscTaskName(clp.run.msc.MscTaskName x) {
    mscTaskNames.add( x );
  }

  public void removeMscTaskName(clp.run.msc.MscTaskName x) {
    mscTaskNames.remove( x );
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
