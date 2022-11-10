package clapp.run.sim.api;

import clapp.run.util.CellQueueHandler;

public interface ISimulator {
  public void onExecution(String key, CellQueueHandler activeQueue);
  public void onFinish();
  public boolean getActivity(String name);
  public void setActivity(String name, boolean b);
}
