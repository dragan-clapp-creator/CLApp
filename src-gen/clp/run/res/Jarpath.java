package clp.run.res;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Jarpath  implements UsedJava, java.io.Serializable {

  private static final long serialVersionUID = 41L;


  //=== Attributes =============================================================

  private String jar;


  //=== Constructor ============================================================

  public Jarpath() {
  }

  //=== Methods ================================================================

  public String getJar() {
    return jar;
  }

  public void setJar(String x) {
    this.jar = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(UsedJavaVisitor visitor) {
    visitor.visitJarpath(this);
  }



}
