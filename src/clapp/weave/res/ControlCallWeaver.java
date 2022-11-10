package clapp.weave.res;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Type;

import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.stmt.util.ClpWeaverConstants;

public class ControlCallWeaver implements IWeaverForLocation, ClpWeaverConstants {


  @Override
  public InstructionList weave(ClassWeaver cw, LocationWeaver lw, boolean isWeavingDone) {
    if (!isWeavingDone) {
      return implementControlCall(cw);
    }
    return null;
  }


  //
  private InstructionList implementControlCall(ClassWeaver cw) {
    InstructionList il = new InstructionList();
    il.append(InstructionFactory.createLoad(Type.OBJECT, 0));
    il.append(new PUSH(cw.getConstantPoolGen(), 200));
    il.append(cw.getInstructionFactory().createInvoke(cw.getClassName(), SUSPENDER, Type.BOOLEAN, new Type[] { Type.INT }, Constants.INVOKESPECIAL));
    BranchInstruction ifeq_24 = InstructionFactory.createBranchInstruction(Constants.IFEQ, null);
    il.append(ifeq_24);
    il.append(InstructionFactory.createReturn(Type.VOID));
    InstructionHandle ih_28 = il.append(InstructionFactory.createLoad(Type.OBJECT, 0));
    il.append(new PUSH(cw.getConstantPoolGen(), 1));
    il.append(cw.getInstructionFactory().createInvoke(cw.getClassName(), CONTROL_SETTER, Type.VOID, new Type[] { Type.INT }, Constants.INVOKEVIRTUAL));
    ifeq_24.setTarget(ih_28);
    return il;
  }



  @Override
  public String getName() {
    return null;
  }


  @Override
  public Kind getKind() {
    return null;
  }
}
