package clapp.run.util;

import java.io.Serializable;
import java.util.ArrayList;

import clp.run.cel.Cell;
import clp.run.cel.dom.ActivationDomain;
import clp.run.cel.dom.CommandLine;
import clp.run.cel.dom.DeactivationDomain;
import clp.run.cel.dom.ExecutionDomain;
import clp.run.cel.log.InitialATokens;
import clp.run.cel.log.LogicalExpression;
import clp.run.cel.log.LogicalFactor;
import clp.run.cel.log.LogicalTerm;
import clp.run.cel.log.LogicalTerms;
import clp.run.cel.log.TokenAMarkingVisitor;
import clp.run.cel.log.TransposingLine;
import clp.run.res.CellMarkCheck;
import clp.run.res.CellMarkSet;


/**
 * the purpose of this class is to wrap a cell in order to
 * 1. create forward and backward links that enable a chaining of cells (this is 
 * an optimization to the alternative direct access to cells, that obliged, on
 * their move, to copy them from one list to another, removing them from the
 * source list)
 * 2. by doing this wrapping, it is convenient to prepare some direct access to
 * cells' activation and deactivation expressions and to the commands
 *  
 * @author Dragan Matic
 *
 */
public class CellChainLink implements Serializable {

  private static final long serialVersionUID = 5346463115359570773L;

  static private int totalNbOfCells;
  static private ArrayList<String> cellNames;

  //------------------
  private static class ClpTokenAMarkingVisitor implements TokenAMarkingVisitor {

    private CellMarkSet entries;
    private ArrayList<LogicalFactor> factors;
    private ArrayList<LogicalTerm> terms;
    private String trName;

    @Override
    public void visitInitialATokens(InitialATokens x) {
      entries = x.getEntries();
      factors = x.getLogicalFactors();
      trName = x.getTrName();
    }
    @Override
    public void visitLogicalTerms(LogicalTerms x) {
      terms = new ArrayList<>();
      terms.add(x.getLogicalTerm());
      terms.addAll( x.getLogicalTerms() );
    }
  }

  private Cell cell;
  private int loadOrder;
  private int numberOfActivations;
  private CellChainLink previous;
  private CellChainLink next;
  private String name;

  private ArrayList<ActivationLine> activationLines;
  private ArrayList<LogicalExpression> deactivationExp;
  private ArrayList<CommandLine> commands;

  // ===== CONSTRUCTOR =========================================================

  public CellChainLink(Cell c) {
    loadOrder = totalNbOfCells;
    totalNbOfCells++;
    cell = c;
    name = c.getName();
    cellNames.add(name);
    spread(c);
  }

  // ===== STAITC PUBLIC METHODS ===============================================

  static public boolean isAlreadyThere(Cell c) {
    return cellNames.contains(c.getName());
  }

  static public void init() {
    totalNbOfCells = 0;
    cellNames = new ArrayList<String>();
  }

  // ===== PUBLIC METHODS ======================================================

  public CellChainLink getNext() {
    return next;
  }

  public void setNext(CellChainLink next) {
    this.next = next;
  }

  public CellChainLink getPrevious() {
    return previous;
  }

  public void setPrevious(CellChainLink previous) {
    this.previous = previous;
  }

  public Cell getCell() {
    return cell;
  }

  public void setCell(Cell cell) {
    this.cell = cell;
  }

  /**
   * @return the commands
   */
  public ArrayList<CommandLine> getCommands() {
    return commands;
  }

  /**
   * @return the deactivationExp
   */
  public ArrayList<LogicalExpression> getDeactivationExp() {
    return deactivationExp;
  }

  /**
   * @return the activationLines
   */
  public ArrayList<ActivationLine> getActivationLines() {
    return activationLines;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLoadOrder() {
    return loadOrder;
  }

  public void setLoadOrder(int loadOrder) {
    this.loadOrder = loadOrder;
  }

  public int getNumberOfActivations() {
    return numberOfActivations;
  }

  public void addNumberOfActivations() {
    numberOfActivations++;
  }

  public static int getTotalNbOfCells() {
    return totalNbOfCells;
  }

  // ===== PRIVATE METHODS =====================================================

  private void spread(Cell c) {
    if (c.isAdomain()) {
      activationLines = new ArrayList<>();
      ActivationDomain adom = c.getAdomain().getAd();
      ArrayList<TransposingLine> tlines = adom.getTransposingLines();
      for (TransposingLine tline : tlines) {
        ClpTokenAMarkingVisitor vis = new ClpTokenAMarkingVisitor();
        tline.getTokenAMarking().accept(vis);
        ActivationLine al = new ActivationLine(tline.getLevel(), tline.getNext());
        al.trName = vis.trName;
        al.transportEntries = vis.entries;
        al.logicalFactors = vis.factors;
        al.logicalTerms = vis.terms;
        al.outputs = tline.getOutputs();
        activationLines.add(al);
      }
    }
    if (c.isDdomain()) {
      DeactivationDomain ddom = c.getDdomain().getDd();
      deactivationExp = ddom.getLogicalExpressions();
    }
    if (c.isXdomain()) {
      ExecutionDomain xdom = c.getXdomain().getXd();
      commands = xdom.getCommandLines();
    }
  }

  public static void remove(String name) {
    if (cellNames.remove(name)) {
      totalNbOfCells--;
    }
  }

  // ===== INNER CLASSES =====================================================

  public static class ActivationLine {
    private int level;
    private int next;
    //---- used for token concept -----------------
    private String trName;
    private CellMarkSet transportEntries;
    private ArrayList<LogicalFactor> logicalFactors;
    private CellMarkCheck outputs;
    //---- until here -----------------------------

    //---- this one is for classical activation ---
    private ArrayList<LogicalTerm> logicalTerms;
    //---- until here -----------------------------
    
    public ActivationLine(int level, int next) {
      this.level = level;
      this.next = next;
    }
    /**
     * @return the trName
     */
    public String getTrName() {
      return trName;
    }
    /**
     * @return the transportEntries
     */
    public CellMarkSet getTransportEntries() {
      return transportEntries;
    }
    /**
     * @return the logicalFactors
     */
    public ArrayList<LogicalFactor> getLogicalFactors() {
      return logicalFactors;
    }
    /**
     * @return the outputs
     */
    public CellMarkCheck getOutputs() {
      return outputs;
    }
    /**
     * @return the logicalTerms
     */
    public ArrayList<LogicalTerm> getLogicalTerms() {
      return logicalTerms;
    }
    public int getNext() {
      return next;
    }
    public int getLevel() {
      return level;
    }
  }
}
