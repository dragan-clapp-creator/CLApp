package clapp.debug;

import clapp.run.ui.util.ConsoleProvider;

public class StdTrace extends ATrace {

  public StdTrace() {
  }

  @Override
  public void send(String str) {
    ConsoleProvider.getInstance().print(str);
  }

}
