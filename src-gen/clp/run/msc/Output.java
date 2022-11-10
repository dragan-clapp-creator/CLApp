package clp.run.msc;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Output  implements java.io.Serializable {

  private static final long serialVersionUID = 10L;

  //=== Attributes =============================================================

  private clp.run.msc.OutputTarget outputTarget;
  private boolean isOut;
  private clp.run.msc.Out out;
  private String color;
  private String background;


  //=== Constructor ============================================================

  public Output() {
  }

  //=== Methods ================================================================

  public clp.run.msc.OutputTarget getOutputTarget() {
    return outputTarget;
  }

  public void setOutputTarget(clp.run.msc.OutputTarget x) {
    this.outputTarget = x;
  }

  //----------------------------------------------------------------------------

  public boolean isOut() {
    return isOut;
  }

  public void setIsOut(boolean x) {
    this.isOut = x;
  }

  //----------------------------------------------------------------------------


  public clp.run.msc.Out getOut() {
    return out;
  }

  public void setOut(clp.run.msc.Out x) {
    this.out = x;
  }

  //----------------------------------------------------------------------------

  public String getColor() {
    return color;
  }

  public void setColor(String x) {
    this.color = x;
  }

  //----------------------------------------------------------------------------

  public String getBackground() {
    return background;
  }

  public void setBackground(String x) {
    this.background = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
