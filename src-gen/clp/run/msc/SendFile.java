package clp.run.msc;

import clp.run.cel.web.SendItems;
import clp.run.cel.web.SendItemsVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SendFile  implements SendItems, java.io.Serializable {

  private static final long serialVersionUID = 12L;


  //=== Attributes =============================================================

  private String fileName;


  //=== Constructor ============================================================

  public SendFile() {
  }

  //=== Methods ================================================================

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String x) {
    this.fileName = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(SendItemsVisitor visitor) {
    visitor.visitSendFile(this);
  }



}
