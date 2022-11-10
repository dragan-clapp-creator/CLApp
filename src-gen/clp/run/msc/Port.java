package clp.run.msc;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Port  implements java.io.Serializable {

  private static final long serialVersionUID = 6L;

  //=== Attributes =============================================================

  private int num;
  private boolean isDecryption;
  private clp.run.msc.Decryption decryption;


  //=== Constructor ============================================================

  public Port() {
  }

  //=== Methods ================================================================

  public int getNum() {
    return num;
  }

  public void setNum(int x) {
    this.num = x;
  }

  //----------------------------------------------------------------------------

  public boolean isDecryption() {
    return isDecryption;
  }

  public void setIsDecryption(boolean x) {
    this.isDecryption = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.msc.Decryption getDecryption() {
    return decryption;
  }

  public void setDecryption(clp.run.msc.Decryption x) {
    this.decryption = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
