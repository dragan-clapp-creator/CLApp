package clp.run.cel.graph.map;

import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandVisitor;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GMapper  implements Command, java.io.Serializable {

  private static final long serialVersionUID = 330L;


  //=== Attributes =============================================================

  private String gname;
  private clp.run.cel.graph.map.Mapping mapping;


  //=== Constructor ============================================================

  public GMapper() {
  }

  //=== Methods ================================================================

  public String getGname() {
    return gname;
  }

  public void setGname(String x) {
    this.gname = x;
  }

  //----------------------------------------------------------------------------

  public clp.run.cel.graph.map.Mapping getMapping() {
    return mapping;
  }

  public void setMapping(clp.run.cel.graph.map.Mapping x) {
    this.mapping = x;
  }

  //----------------------------------------------------------------------------





  //=== Acceptors ==============================================================

  public void accept(CommandVisitor visitor) {
    visitor.visitGraphMapStatement(this);
  }



}
