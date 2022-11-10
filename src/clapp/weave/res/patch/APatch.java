package clapp.weave.res.patch;

import java.util.StringTokenizer;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ARETURN;
import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.DLOAD;
import org.apache.bcel.generic.DRETURN;
import org.apache.bcel.generic.FLOAD;
import org.apache.bcel.generic.FRETURN;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.IRETURN;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LLOAD;
import org.apache.bcel.generic.LRETURN;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.Type;

import clapp.weave.res.api.IPatch;
import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.util.BCELMethodInfo;
import clapp.weave.res.util.ClpLocalVariable;
import clp.run.res.weave.KindOf;


abstract public class APatch implements IPatch {

  static private boolean isdone;
  static private int index;

  private int target;
  private InstructionList il;
  private MethodGen mg;
  private BCELMethodInfo bm;
  private Code code;
  private String name;
  private KindOf kind;
  private int occurences;
  private int offset;
  private boolean isInLoop;

  /**
   * CONSTRUCTOR
   */
  public APatch(BCELMethodInfo bmi, String n, int i, KindOf of, int occ, int off, boolean b) {
    kind = of;
    occurences = occ;
    offset = off;
    index = i;
    isInLoop = b;
    setup(bmi, n);
  }

  /**
   * CONSTRUCTOR
   */
  public APatch(BCELMethodInfo bmi) {
    setup(bmi, null);
  }

  /**
   * CONSTRUCTOR
   */
  public APatch(BCELMethodInfo bmi, String n, int i) {
    occurences = i;
    setup(bmi, n);
  }

  /**
   * CONSTRUCTOR
   */
  public APatch(BCELMethodInfo bmi, String n, boolean b, int i) {
    occurences = i;
    isInLoop = b;
    setup(bmi, n);
  }

  private void setup(BCELMethodInfo bmi, String n) {
    bm = bmi;
    name = n;
    mg = bm.getMethodGen();
    code = bm.getMethod().getCode();
    /*
     * Stack size must be at least 10.
     */
//    if (code.getMaxStack() < 10)
//      mg.setMaxStack(10);
    il = mg.getInstructionList();
    setInitialTarget();
  }

  /* (non-Javadoc)
   * @see com.sap.clapp.weave.api.IPatch#patch(com.sap.clapp.weave.api.IWeaveForLocation)
   */
  public int patch(IWeaverForLocation w, InstructionList added) {
    added = insertAt(target, added);
    return added.getLength();
  }

  /**
   * inserts pil instructions at handle position of il, given by its index,
   * and returns back a copy of pil (in order to eventually do the same insert
   * at another place
   * @param index
   * @param pil
   * @return
   */
  public InstructionList insertAt(int index,
                                  InstructionList pil) {
    InstructionHandle handle = il.getInstructionHandles()[index];
    InstructionList p = pil.copy();
    il.insert(handle, pil);
    return p;
  }

  public boolean isPointcut0(String name0, String name1) {
    return name0.contains("invoke") && !name1.contains("store");
  }

  public boolean isPointcut1(String name) {
    return name.contains("store") || name.contains("ifeq");
  }

  public boolean isBranche(String name) {
    return name.contains("goto");
  }

  public int indexInList(InstructionHandle[] handles, String str, int index) {
    for (int i=0, ind=index; i<handles.length; i++) {
      if (handles[i].toString().trim().startsWith(str)) {
        if (ind > 0) {
          ind--;
        }
        else {
          return i;
        }
      }
    }
    return 0;
  }

  public int indexInList(InstructionHandle[] handles, String str) {
    for (int i=0; i<handles.length; i++) {
      if (handles[i].toString().trim().indexOf(str) > 0) {
        return i;
      }
    }
    return 0;
  }

  public int findInList(String string, String name, int ind) {
    StringTokenizer st = new StringTokenizer(string, "\n");
    st.nextToken(); // ignored
    int count = st.countTokens();
    for (int i=0; i<count; i++) {
      String line = st.nextToken();
      String str = getCodePart(line);
      if (str.indexOf(name) > 0) {
        if (ind > 1) {
          ind--;
        }
        else {
          return i;
        }
      }
    }
    return -1;
  }

  private String getCodePart(String line) {
    StringTokenizer st = new StringTokenizer(line, " \t");
    if (st.countTokens() > 2) {
      st.nextElement();
      st.nextElement();
      return st.nextToken();
    }
    return "";
  }

  public int getTarget() {
    return target;
  }

  public void setTarget(int target) {
    this.target = target;
  }

  public InstructionList getIl() {
    return il;
  }

  public MethodGen getMg() {
    return mg;
  }

  public BCELMethodInfo getBm() {
    return bm;
  }

  public Code getCode() {
    return code;
  }

  static public void resetIsDone() {
    isdone = false;
  }

  protected boolean insertIntermediate(InstructionHandle[] ihs, InstructionHandle ih, int ind) {
    if (isdone) {
      return false;
    }
    Instruction predInst = ihs[ind-1].getInstruction();
    Instruction inst = ih.getInstruction();
    if (inst instanceof ARETURN && !(predInst instanceof ALOAD)) {
      return insertIntermediate(ihs, ind, Type.OBJECT);
    }
    else if (inst instanceof IRETURN && !(predInst instanceof ILOAD)) {
      return insertIntermediate(ihs, ind, Type.INT);
    }
    else if (inst instanceof DRETURN && !(predInst instanceof DLOAD)) {
      return insertIntermediate(ihs, ind, Type.DOUBLE);
    }
    else if (inst instanceof FRETURN && !(predInst instanceof FLOAD)) {
      return insertIntermediate(ihs, ind, Type.FLOAT);
    }
    else if (inst instanceof LRETURN && !(predInst instanceof LLOAD)) {
      return insertIntermediate(ihs, ind, Type.LONG);
    }
    return false;
  }

  //
  protected InstructionList conditionalInsertAt(InstructionList il,
                                              InstructionHandle handle,
                                              InstructionList pil) {
    InstructionList p = pil.copy();
    if (isAccepted(p.getInstructions(), handle)) {
      il.insert(handle, pil);
    }
    return p;
  }

  // PRIVATE METHODS ===========================================================

  //
  private boolean insertIntermediate(InstructionHandle[] ihs, int i, Type tp) {
    InstructionList patch = new InstructionList();
    int index = getMg().getLocalVariables().length;
    getMg().addLocalVariable("_ret_"+index, tp, null, null);
    patch.append(InstructionFactory.createStore(tp, index));
    patch.append(InstructionFactory.createLoad(tp, index));
    insertAt(i, patch);
    isdone = true;
    return true;
  }

  //
  private boolean isAccepted(Instruction[] instructions,
      InstructionHandle handle) {
    boolean accepted = true;
    for (int i = 0; accepted && i < instructions.length; i++) {
      if (instructions[i].getName().startsWith("aload_")) {
  String str = instructions[i].getName().substring(6);
  int ind = Integer.parseInt(str) - 1;
  if (ind < 0) {
    return false;
  }
  if (ClpLocalVariable.getSize(bm.getMethodGen()) > 0) {
    accepted = ClpLocalVariable.isLocalVariableVisible(bm.getMethodGen(),
        ind, handle);
  }
  else {
    return false;
  }
      }
      else if (instructions[i].getOpcode() == Constants.LDC_W) {
  return false;
      }
    }
    return accepted;
  }

  public KindOf getKind() {
    return kind;
  }

  public String getName() {
    return name;
  }

  public int getOccurences() {
    return occurences;
  }

  BranchHandle getBranchHandle(InstructionHandle[] ihs, int ln) {
    int nb = ln;
    int pos = 0;
    BranchHandle bh = null;
    boolean found = false;
    boolean stop = false;
    Instruction[] ils = getIl().getInstructions();
    while (!stop) {
      for (int i=pos+1; !found && i<ils.length; i++) {
  if (ils[i] instanceof GOTO) {
    BranchHandle ih = (BranchHandle)ihs[i];
    if (ih.getPosition() > ih.getTarget().getPosition()) {
      pos = ih.getTarget().getPosition();
      bh = ih;
      found = true;
    }
  }
      }
      if (found) {
        nb--;
      }
      stop = !found || (nb < 1);
    }
    return bh;
  }

  public int getOffset() {
    return offset;
  }

  static public int getIndex() {
    return index;
  }

  public InstructionHandle getCurrentInstructionHandle() {
    return mg.getInstructionList().getInstructionHandles()[target];
  }

  /**
   * @return the isInLoop
   */
  public boolean isInLoop() {
    return isInLoop;
  }
}
