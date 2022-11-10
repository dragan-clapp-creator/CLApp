package clapp.weave.res;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Select;
import org.apache.bcel.generic.TABLESWITCH;
import org.apache.bcel.generic.Type;

import clapp.weave.res.api.IWeaverForClass;
import clapp.weave.res.stmt.util.ClpWeaverConstants;

public class NewMethodWeaver implements IWeaverForClass, Constants, ClpWeaverConstants {


  @Override
  public void weave(ClassWeaver cw, boolean isWeavingDone) {
    if (isWeavingDone && cw.alreadyExists()) {
      return;
    }
    addMethodSetClappControl(cw);
    addMethodClappSuspend(cw);
  }

  //
  private void addMethodSetClappControl(ClassWeaver cw) {
    InstructionList il = new InstructionList();
    MethodGen method = new MethodGen(ACC_PUBLIC, Type.VOID, new Type[] { Type.INT }, new String[] { ARG_NAME }, CONTROL_SETTER, cw.getClassName(), il, cw.getConstantPoolGen());
    il.append(new PUSH(cw.getConstantPoolGen(), "OBJECT"));
    il.append(InstructionConstants.DUP);
    il.append(InstructionFactory.createStore(Type.OBJECT, 2));
    il.append(InstructionConstants.MONITORENTER);
    InstructionHandle ih_5 = il.append(InstructionFactory.createLoad(Type.OBJECT, 0));
    il.append(InstructionFactory.createLoad(Type.INT, 1));
    il.append(cw.getInstructionFactory().createFieldAccess(cw.getClassName(), CONTROL_FIELD, Type.INT, PUTFIELD));
    il.append(InstructionFactory.createLoad(Type.OBJECT, 2));
    InstructionHandle ih_11 = il.append(InstructionConstants.MONITOREXIT);
    BranchInstruction goto_12 = InstructionFactory.createBranchInstruction(GOTO, null);
    il.append(goto_12);
    InstructionHandle ih_15 = il.append(InstructionFactory.createLoad(Type.OBJECT, 2));
    InstructionHandle ih_16 = il.append(InstructionConstants.MONITOREXIT);
    il.append(InstructionConstants.ATHROW);
    InstructionHandle ih_18 = il.append(InstructionFactory.createReturn(Type.VOID));
    goto_12.setTarget(ih_18);
    method.addExceptionHandler(ih_5, ih_11, ih_15, null);
    method.addExceptionHandler(ih_15, ih_16, ih_15, null);
    method.setMaxStack();
    method.setMaxLocals();
    cw.getClassGen().addMethod(method.getMethod());
    il.dispose();
  }

  //
  private void addMethodClappSuspend(ClassWeaver cw) {
     InstructionList il = new InstructionList();
     MethodGen method = new MethodGen(ACC_PRIVATE, Type.BOOLEAN, new Type[] { Type.INT }, new String[] { ARG_NAME }, SUSPENDER, cw.getClassName(), il, cw.getConstantPoolGen());
     InstructionHandle ih_0 = il.append(InstructionFactory.createLoad(Type.INT, 1));
     il.append(InstructionConstants.I2L);
     il.append(cw.getInstructionFactory().createInvoke("java.lang.Thread", "sleep", Type.VOID, new Type[] { Type.LONG }, INVOKESTATIC));
     il.append(new PUSH(cw.getConstantPoolGen(), "OBJECT"));
     il.append(InstructionConstants.DUP);
     il.append(InstructionFactory.createStore(Type.OBJECT, 2));
     il.append(InstructionConstants.MONITORENTER);
     InstructionHandle ih_10 = il.append(InstructionFactory.createLoad(Type.OBJECT, 0));
     il.append(cw.getInstructionFactory().createFieldAccess(cw.getClassName(), CONTROL_FIELD, Type.INT, GETFIELD));
     Select tableswitch_14 = new TABLESWITCH(new int[] { -1, 0, 1, 2 }, new InstructionHandle[] { null, null, null, null }, null);
     il.append(tableswitch_14);

     InstructionHandle ih_44 = il.append(cw.getInstructionFactory().createInvoke("java.lang.Thread", "currentThread", new ObjectType("java.lang.Thread"), Type.NO_ARGS, Constants.INVOKESTATIC));
     il.append(cw.getInstructionFactory().createInvoke("java.lang.Thread", "interrupt", Type.VOID, Type.NO_ARGS, Constants.INVOKEVIRTUAL));
     InstructionHandle ih_50 = il.append(InstructionFactory.createLoad(Type.OBJECT, 2));
     InstructionHandle ih_51 = il.append(InstructionConstants.MONITOREXIT);
     il.append(new PUSH(cw.getConstantPoolGen(), 1));
     il.append(InstructionFactory.createReturn(Type.INT));
     InstructionHandle ih_54 = il.append(InstructionFactory.createLoad(Type.OBJECT, 2));
     InstructionHandle ih_55 = il.append(InstructionConstants.MONITOREXIT);
     il.append(new PUSH(cw.getConstantPoolGen(), 0));
     il.append(InstructionFactory.createReturn(Type.INT));
     InstructionHandle ih_58 = il.append(InstructionFactory.createLoad(Type.OBJECT, 2));
     InstructionHandle ih_59 = il.append(InstructionConstants.MONITOREXIT);
     BranchInstruction goto_60 = InstructionFactory.createBranchInstruction(Constants.GOTO, ih_0);
     il.append(goto_60);
     InstructionHandle ih_63 = il.append(InstructionFactory.createLoad(Type.OBJECT, 2));
     InstructionHandle ih_64 = il.append(InstructionConstants.MONITOREXIT);
     InstructionHandle ih_65 = il.append(InstructionConstants.ATHROW);
     InstructionHandle ih_66 = il.append(InstructionFactory.createStore(Type.OBJECT, 2));
     il.append(InstructionFactory.createLoad(Type.OBJECT, 2));
     il.append(cw.getInstructionFactory().createInvoke("java.lang.InterruptedException", "printStackTrace", Type.VOID, Type.NO_ARGS, Constants.INVOKEVIRTUAL));
     il.append(new PUSH(cw.getConstantPoolGen(), 0));
     il.append(InstructionFactory.createReturn(Type.INT));
     tableswitch_14.setTarget(ih_58);
     tableswitch_14.setTarget(0, ih_44);
     tableswitch_14.setTarget(1, ih_58);
     tableswitch_14.setTarget(2, ih_54);
     tableswitch_14.setTarget(3, ih_50);
     method.addExceptionHandler(ih_10, ih_51, ih_63, null);
     method.addExceptionHandler(ih_54, ih_55, ih_63, null);
     method.addExceptionHandler(ih_58, ih_59, ih_63, null);
     method.addExceptionHandler(ih_63, ih_64, ih_63, null);
     method.addExceptionHandler(ih_0, ih_51, ih_66, new ObjectType("java.lang.InterruptedException"));
     method.addExceptionHandler(ih_54, ih_55, ih_66, new ObjectType("java.lang.InterruptedException"));
     method.addExceptionHandler(ih_58, ih_65, ih_66, new ObjectType("java.lang.InterruptedException"));

     method.setMaxStack();
     method.setMaxLocals();
     cw.getClassGen().addMethod(method.getMethod());
     il.dispose();

  }
}
