package clapp.weave.res.api;

import clapp.weave.res.ClassWeaver;

public interface IWeaverForClass {

  public void weave(ClassWeaver cw, boolean isWeavingDone);
}
