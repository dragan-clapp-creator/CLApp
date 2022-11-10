package clapp.weave.res.api;

import org.apache.bcel.generic.InstructionList;

import clapp.weave.res.ClassWeaver;
import clapp.weave.res.LocationWeaver;

public interface IWeaverForLocation {
  public enum Kind {
    SYS, LOC, GLOB, CALL, INSTR;
  }

  public InstructionList weave(ClassWeaver cw, LocationWeaver lw, boolean isWeavingDone);
  public String getName();
  public Kind getKind();
}
