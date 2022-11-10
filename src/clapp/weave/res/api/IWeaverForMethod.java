package clapp.weave.res.api;

import clapp.weave.res.ClassWeaver;

public interface IWeaverForMethod {

  public void weave(ClassWeaver classWeaver, boolean isWeavingDone);
}
