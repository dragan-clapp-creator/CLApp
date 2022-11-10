package clapp.run.api;

import clapp.run.util.CellQueueHandler;

public interface IScenarioTask {

  public void proceed();

  public CellQueueHandler getOperatingQueue();

  public CellQueueHandler getPassingQueue();

  public boolean isPassingTo();

  public Object getName();

}
