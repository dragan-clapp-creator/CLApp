package clp.run.msc;



/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MscOutput  implements java.io.Serializable {

  private static final long serialVersionUID = 9L;

  //=== Attributes =============================================================

  private clp.run.msc.Output output;
  private char ckey;
  private java.util.ArrayList<clp.run.msc.Output> outputs = new java.util.ArrayList<clp.run.msc.Output>();


  //=== Constructor ============================================================

  public MscOutput() {
  }

  //=== Methods ================================================================

  public clp.run.msc.Output getOutput() {
    return output;
  }

  public void setOutput(clp.run.msc.Output x) {
    this.output = x;
  }

  //----------------------------------------------------------------------------

  public char getChar() {
    return ckey;
  }

  public void setChar(char x) {
    this.ckey = x;
  }

  //----------------------------------------------------------------------------

  public boolean hasOutputs() {
    return outputs != null && !outputs.isEmpty();
  }

  public java.util.ArrayList<clp.run.msc.Output> getOutputs() {
    return outputs;
  }

  public void setOutputs(java.util.ArrayList<clp.run.msc.Output> x) {
    outputs = x;
  }

  public void addOutput(clp.run.msc.Output x) {
    outputs.add( x );
  }

  public void removeOutput(clp.run.msc.Output x) {
    outputs.remove( x );
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================



}
