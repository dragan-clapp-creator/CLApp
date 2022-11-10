package clp.run.cel.web;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SendStream  implements SendItems, java.io.Serializable {

  private static final long serialVersionUID = 309L;


  //=== Attributes =============================================================

  private boolean isSTREAM;
  private clp.run.cel.web.SendObject sendObject;
  private char ckey;
  private java.util.ArrayList<clp.run.cel.web.SendObject> sendObjects = new java.util.ArrayList<clp.run.cel.web.SendObject>();


  //=== Constructor ============================================================

  public SendStream() {
  }

  //=== Methods ================================================================

  public boolean isSTREAM() {
    return isSTREAM;
  }

  public void setIsSTREAM(boolean x) {
    this.isSTREAM = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.web.SendObject getSendObject() {
    return sendObject;
  }

  public void setSendObject(clp.run.cel.web.SendObject x) {
    this.sendObject = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasSendObjects() {
    return sendObjects != null && !sendObjects.isEmpty();
  }

  public java.util.ArrayList<clp.run.cel.web.SendObject> getSendObjects() {
    return sendObjects;
  }

  public void setSendObjects(java.util.ArrayList<clp.run.cel.web.SendObject> x) {
    sendObjects = x;
  }

  public void addSendObject(clp.run.cel.web.SendObject x) {
    sendObjects.add( x );
  }

  public void removeSendObject(clp.run.cel.web.SendObject x) {
    sendObjects.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(SendItemsVisitor visitor) {
    visitor.visitSendStream(this);
  }



}
