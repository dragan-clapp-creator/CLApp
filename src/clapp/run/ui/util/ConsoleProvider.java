package clapp.run.ui.util;

import clp.run.msc.MscOutput;
import clp.run.msc.OutputTarget;

public class ConsoleProvider {

  private static final ConsoleProvider instance = new ConsoleProvider();

  private AConsoleHandler consoleHandler;

  // PRIVATE CONSTRUCTOR
  private ConsoleProvider() {
    consoleHandler = new DefaultConsoleHandler();
  }

  /**
   * singleton provider
   * @return
   */
  public static ConsoleProvider getInstance() {
    return instance;
  }

  /**
   * with this method, the default console handler is overwritten
   * 
   * @param ch
   */
  public void register(AConsoleHandler ch) {
    consoleHandler = ch;
  }

  /**
   * @return the consoleHandler
   */
  public AConsoleHandler getConsoleHandler() {
    return consoleHandler;
  }

  /**
   * delegate
   * @param scnOutput
   */
  public void initialize(MscOutput mscOutput) {
    consoleHandler.initialize(mscOutput);
  }

  /**
   * delegate
   * @param string
   */
  public void eprint(String string) {
    consoleHandler.eprint(string);
  }

  /**
   * delegate
   * @param string
   */
  public void print(String string) {
    consoleHandler.print(string);
  }

  /**
   * delegate
   * 
   * @param sb
   * @param outputTarget
   */
  public void print(StringBuffer sb, OutputTarget outputTarget) {
    consoleHandler.print(sb, outputTarget);
  }

}
