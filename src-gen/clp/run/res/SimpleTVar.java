package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleTVar  implements TimeVar, java.io.Serializable {

  private static final long serialVersionUID = 138L;


  //=== Attributes =============================================================

  private String name;
  private char ckey;
  private boolean isTInit;
  private clp.run.res.TInit tInit;


  //=== Constructor ============================================================

  public SimpleTVar() {
  }

  //=== Methods ================================================================

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

  public boolean isTInit() {
    return isTInit;
  }

  public void setIsTInit(boolean x) {
    this.isTInit = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.res.TInit getTInit() {
    return tInit;
  }

  public void setTInit(clp.run.res.TInit x) {
    this.tInit = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(TimeVarVisitor visitor) {
    visitor.visitSimpleTVar(this);
  }



}
