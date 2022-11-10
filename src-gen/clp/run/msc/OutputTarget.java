package clp.run.msc;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class OutputTarget  implements java.io.Serializable {

  private static final long serialVersionUID = 11L;

  //=== Attributes =============================================================

  private String sCONSOLE;
  private boolean isStringCONSOLE;
  private String name;
  private clp.run.msc.SendFile sendFile;


  //=== Constructor ============================================================

  public OutputTarget() {
  }

  //=== Methods ================================================================

  public String getStringCONSOLE() {
    return sCONSOLE;
  }

  public void setStringCONSOLE(String x) {
    this.sCONSOLE = x;
  }

  //----------------------------------------------------------------------------


  public boolean isStringCONSOLE() {
    return isStringCONSOLE;
  }

  public void setIsStringCONSOLE(boolean x) {
    this.isStringCONSOLE = x;
  }

  //----------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.msc.SendFile getSendFile() {
    return sendFile;
  }

  public void setSendFile(clp.run.msc.SendFile x) {
    this.sendFile = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
