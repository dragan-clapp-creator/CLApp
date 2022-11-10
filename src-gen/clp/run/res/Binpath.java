package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Binpath  implements UsedJava, java.io.Serializable {

  private static final long serialVersionUID = 40L;


  //=== Attributes =============================================================

  private String dir;


  //=== Constructor ============================================================

  public Binpath() {
  }

  //=== Methods ================================================================

  public String getDir() {
    return dir;
  }

  public void setDir(String x) {
    this.dir = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(UsedJavaVisitor visitor) {
    visitor.visitBinpath(this);
  }



}
