package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class WebVariable  implements Setting, Variable, java.io.Serializable {

  private static final long serialVersionUID = 151L;


  //=== Attributes =============================================================

  private clp.run.res.VarType TWEB;
  private String name;
  private boolean isEncryption;
  private clp.run.res.Encryption encryption;
  private boolean isAddress;
  private clp.run.res.Address address;
  private clp.run.res.Port port;


  //=== Constructor ============================================================

  public WebVariable() {
  }

  //=== Methods ================================================================

  public clp.run.res.VarType getTWEB() {
    return TWEB;
  }

  public void setTWEB(clp.run.res.VarType x) {
    this.TWEB = x;
  }

  //----------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public boolean isEncryption() {
    return isEncryption;
  }

  public void setIsEncryption(boolean x) {
    this.isEncryption = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.Encryption getEncryption() {
    return encryption;
  }

  public void setEncryption(clp.run.res.Encryption x) {
    this.encryption = x;
  }

  //----------------------------------------------------------------------------

  public boolean isAddress() {
    return isAddress;
  }

  public void setIsAddress(boolean x) {
    this.isAddress = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.Address getAddress() {
    return address;
  }

  public void setAddress(clp.run.res.Address x) {
    this.address = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.Port getPort() {
    return port;
  }

  public void setPort(clp.run.res.Port x) {
    this.port = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(VariableVisitor visitor) {
    visitor.visitWebVariable(this);
  }

  public void accept(SettingVisitor visitor) {
    visitor.visitWebVariable(this);
  }



}
