package clapp.run.exe;

import java.util.ArrayList;

import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.CellChainLink;
import clapp.run.vis.ClpCommandVisitor;
import clp.run.cel.Cell;
import clp.run.cel.dom.CommandLine;

public class Runner extends Thread {

  private ExecutingUnit executingUnit;

  private CellChainLink ccl;
  private Cell cell;
  private ArrayList<CommandLine> cmdls;
  private boolean positivelogic;
  private boolean autodeact;
  private ArrayList<CellChainLink> toBeMoved;

  private boolean finished;

  private boolean isInterruptable;

  private int logicLevel;

  public Runner(ExecutingUnit eu, CellChainLink ccl, int logicLevel, boolean positivelogic, boolean autodeact, ArrayList<CellChainLink> toBeMoved) {
    this.executingUnit = eu;
    this.ccl = ccl;
    this.cell = ccl.getCell();
    this.cmdls = ccl.getCommands();
    this.logicLevel = logicLevel;
    this.positivelogic = positivelogic;
    this.autodeact = autodeact;
    this.toBeMoved = toBeMoved;
    this.setName("Runner_"+ccl.getName());
    System.out.println(getName()+" created");
    this.setDaemon(true);
  }

  public void run() {
    if (cell.getActivity() > 0 && cmdls != null) {
      int nextLevel = -1;
      for (CommandLine cmdl : cmdls) {
        nextLevel = execute(cmdl, cell.getActivity());
        if (logicLevel == 0 && nextLevel > -1) {
          break;
        }
      }
      if (logicLevel == 0) {
        if (nextLevel > -1) {
          cell.setActivity(nextLevel);
        }
      }
      else {
        if (positivelogic && cell.getActivity() > 1) {
          cell.setActivity(cell.getActivity()-1);
        }
        else {
          cell.setActivity(0);
        }
      }
      if (autodeact && cell.getActivity() <= 0) {
        toBeMoved.add(ccl);
      }
    }
    setFinished(true);
  }

  //
  private int execute(CommandLine cmdl, int cellActivity) {
    if ( cellActivity > 0 &&
        (cmdl.getLevel() == 0 || cmdl.getLevel() == cellActivity)) {

      interpret(cell, cmdl);
      if (cell.getActivity() == 0) {
        ConsoleProvider.getInstance().print("execution interrupted: " + cell.getName());
      }
      else {
        ConsoleProvider.getInstance().print("executed: " + cell.getName() + " at level " + cell.getActivity());
        return cmdl.getNext();
      }
    }
    else {
      ConsoleProvider.getInstance().print("NOT executed: " + cell.getName() + " at level " + cell.getActivity());
    }
    return -1;
  }

  //
  public void interpret(Cell cell, CommandLine cmd) {
    ClpCommandVisitor visitor = new ClpCommandVisitor(cell, executingUnit);
    isInterruptable = visitor.isInterruptable();
    cmd.getCommand().accept(visitor);
  }

  /**
   * @return the finished
   */
  public synchronized boolean isFinished() {
    return finished;
  }

  /**
   * @param finished the finished to set
   */
  public synchronized void setFinished(boolean finished) {
    this.finished = finished;
  }

  public synchronized boolean isInterruptable() {
    return isInterruptable;
  }
}
