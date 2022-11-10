package clp.run.res.weave;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class AboutLocation  implements Location, java.io.Serializable {

  private static final long serialVersionUID = 178L;


  //=== Attributes =============================================================

  private clp.run.res.weave.Position position;
  private clp.run.res.weave.KindOf kind;
  private String name;
  private char ckey;
  private String type;
  private boolean isType;
  private int index;
  private boolean isIndex;
  private int occurNumber;
  private boolean isOccurNumber;


  //=== Constructor ============================================================

  public AboutLocation() {
  }

  //=== Methods ================================================================

  public clp.run.res.weave.Position getPosition() {
    return position;
  }

  public void setPosition(clp.run.res.weave.Position x) {
    this.position = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.res.weave.KindOf getKind() {
    return kind;
  }

  public void setKind(clp.run.res.weave.KindOf x) {
    this.kind = x;
  }

  //----------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String x) {
    this.name = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public String getType() {
    return type;
  }

  public void setType(String x) {
    this.type = x;
  }

  //----------------------------------------------------------------------------


  public boolean isType() {
    return isType;
  }

  public void setIsType(boolean x) {
    this.isType = x;
  }

  //----------------------------------------------------------------------------

  public int getIndex() {
    return index;
  }

  public void setIndex(int x) {
    this.index = x;
  }

  //----------------------------------------------------------------------------


  public boolean isIndex() {
    return isIndex;
  }

  public void setIsIndex(boolean x) {
    this.isIndex = x;
  }

  //----------------------------------------------------------------------------

  public int getOccurNumber() {
    return occurNumber;
  }

  public void setOccurNumber(int x) {
    this.occurNumber = x;
  }

  //----------------------------------------------------------------------------


  public boolean isOccurNumber() {
    return isOccurNumber;
  }

  public void setIsOccurNumber(boolean x) {
    this.isOccurNumber = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(LocationVisitor visitor) {
    visitor.visitAboutLocation(this);
  }



}
