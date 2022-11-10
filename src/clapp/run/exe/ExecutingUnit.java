package clapp.run.exe;

import java.util.ArrayList;

import clapp.run.ui.ClpVisualizer;
import clapp.run.util.CellChainLink;

public class ExecutingUnit {

  private enum Automaton {
    START, RUNNING, INTERRUPT, FINISHED;
  }

  private Automaton automaton;

  private CellChainLink ccl;

  private Runner runner;

  private ClpVisualizer currentVisualizer;

  public ExecutingUnit(CellChainLink ccl, int logicLevel, boolean positivelogic, boolean autodeact, ArrayList<CellChainLink> toBeMoved) {
    this.ccl = ccl;
    this.runner = new Runner(this, ccl, logicLevel, positivelogic, autodeact, toBeMoved);
    automaton = Automaton.START;
  }

  public void progress() {
    switch (automaton) {
      case START:
        runner.start();
        automaton = Automaton.RUNNING;
        break;
      case RUNNING:
        if (runner.isFinished()) {
          automaton = Automaton.FINISHED;
        }
        break;
      case INTERRUPT:
        stop(currentVisualizer);
        automaton = Automaton.FINISHED;
        break;
      case FINISHED:
        ExecutorsRegistry.getInstance().unregister(ccl);
        break;

      default:
        break;
    }
  }

  public synchronized void interrupt() {
    if (isInterruptable()) {
      automaton = Automaton.INTERRUPT;
      if (currentVisualizer != null) {
        currentVisualizer.interrupt();
      }
      progress();
    }
  }

  private synchronized boolean isInterruptable() {
    if (currentVisualizer != null) {
      return currentVisualizer.isInterruptable();
    }
    return runner.isInterruptable();
  }

  private synchronized void stop(ClpVisualizer visualizer) {
    if (visualizer != null) {
      visualizer.interrupt();
    }
  }

  /**
   * @param visualizer the visualizer to set
   */
  public synchronized void setVisualizer(ClpVisualizer visualizer) {
    currentVisualizer = visualizer;
  }

  @Override
  public String toString() {
    if (currentVisualizer != null) {
      return currentVisualizer.getRoot().getTitle();
    }
    return runner.getName();
  }
}
