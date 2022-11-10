package clapp.weave.res.util;

import java.util.Hashtable;

import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Type;

import clapp.weave.res.ClassWeaver;
import clapp.weave.res.LocationWeaver;
import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.vis.ClpLocVisitor;
import clp.run.res.Array;
import clp.run.res.weave.LocalAttribute;
import clp.run.res.weave.Location;
import clp.run.res.weave.Position;

public class ClpLocalVariable implements IWeaverForLocation {

  static private Hashtable<String, ClpLocalVariable> clpLocalVariables =
    new Hashtable<String, ClpLocalVariable>();

  private String type;
  private String attribute;
  private Object value;

  private boolean isArray;
  private int size;

  private Type tp;

  private int containers[][];
  private int ind;


  // ===== CONSTRUCTORS ========================================================

  public ClpLocalVariable(String tp, String att, Array ar, Object val) {
    type = tp;
    attribute = att;
    value = val;
    if (ar != null) {
      isArray = true;
      size = ar.getSize();
    }
  }

  public ClpLocalVariable(String tp, String att, Array ar) {
    type = tp;
    attribute = att;
    value = null;
    if (ar != null) {
      isArray = true;
      size = ar.getSize();
    }
  }


  // ===== PUBLIC METHODS ======================================================

  /**
   * process weaving by adding a new local variable in the method
   */
  public InstructionList weave(ClassWeaver cw, LocationWeaver lw, boolean isWeavingDone) {
    if (!isWeavingDone) {
      if (value == null) {  // used to load this variable
        if (!clpLocalVariables.containsKey(attribute)) {
          clpLocalVariables.put(attribute, this);
        }
        return loadVariable(lw);
      }
                            // used to store in this variable
      if (isArray) {
        return storeLocalArray(cw, lw);
      }
      return storeLocalVariable(cw, lw);
    }
    return null;
  }

  /**
   * called for each assignement of an array item
   * @param cw
   * @param mw
   * @param patch
   * @param index
   */
  public InstructionList assignVariable(ClassWeaver cw, LocationWeaver lw, int index) {
    InstructionList patch = lw.getInstructionList();
    patch.append(InstructionFactory.createLoad(Type.OBJECT, ind));
    patch.append(new PUSH(cw.getConstantPoolGen(), index));
    if (tp == Type.STRING) {
      if (value instanceof Invoke) {
        copy(patch, ((Invoke)value).weave(cw, lw, false));
      }
      else {
        patch.append(new PUSH(cw.getConstantPoolGen(), (String) value));
      }
      patch.append(InstructionConstants.AASTORE);
    }
    else if (tp == Type.INT) {
      if (value instanceof Invoke) {
        copy(patch, ((Invoke)value).weave(cw, lw, false));
      }
      else {
        patch.append(new PUSH(cw.getConstantPoolGen(), Integer.parseInt((String) value)));
      }
      patch.append(InstructionConstants.IASTORE);
    }
    else if (tp == Type.LONG) {
      if (value instanceof Invoke) {
        ((Invoke)value).weave(cw, lw, false);
      }
      else {
        patch.append(new PUSH(cw.getConstantPoolGen(), Long.parseLong((String) value)));
      }
      patch.append(InstructionConstants.LASTORE);
    }
    return patch;
  }

  public String getAttribute() {
    return attribute;
  }

  public String getType() {
    return type;
  }

  public Object getValue() {
    return value;
  }

  public String getName() {
    return attribute;
  }

  public IWeaverForLocation.Kind getKind() {
    return Kind.LOC;
  }


  // ===== STATIC PUBLIC METHODS ===============================================

  public static ClpLocalVariable getVariable(String att) {
    return clpLocalVariables.get(att);
  }

  public static LocalVariableGen getOriginalVariable(String att, MethodGen mg) {
    LocalVariableGen[] lvg = mg.getLocalVariables();
    for (int i=lvg.length-1; i>=0; i--) {
      if (lvg[i].getName().equals(att)) {
        return lvg[i];
      }
    }
    return null;
  }

  public static Type getOriginalVariableType(String att, MethodGen mg) {
    LocalVariableGen[] lvg = mg.getLocalVariables();
    for (int i=lvg.length-1; i>=0; i--) {
      if (lvg[i].getName().equals(att)) {
        return lvg[i].getType();
      }
    }
    return null;
  }

  public static int getSize(MethodGen mg) {
    return mg.getLocalVariables().length;
  }

  public static boolean isLocalVariableVisible(MethodGen mg, int ind, InstructionHandle handle) {
    LocalVariableGen[] lvgs = mg.getLocalVariables();
    LocalVariableGen lthis = lvgs[0];
    InstructionHandle start = lthis.getStart();
    if (ind < 0 || ind >= lvgs.length) {
      return false;
    }
    LocalVariableGen lvg = lvgs[ind];
    if (lvg == null) {
      return false;
    }
    InstructionHandle current = lvg.getStart();
    InstructionHandle cursor = handle;
    boolean found = (current == handle);
    while (!found && cursor != null && cursor != start) {
      cursor = cursor.getPrev();
      found = (cursor == current);
    }
    return found;
  }

  public static int getLocalIndex(String name, String type, MethodGen mg) {
    LocalVariableGen[] lvg = mg.getLocalVariables();
    LocalVariableGen lthis = lvg[0];
    for (int i=0; i<lvg.length; i++) {
      if (lthis.getEnd() == lvg[i].getEnd()) {
        if (lvg[i].getName().equals(name)) {
          return lvg[i].getIndex();
        }
      }
    }
    if (type != null) {
      type = type.replace('.', '/');
      for (int i=0; i<lvg.length; i++) {
        if (lvg[i].getType().getSignature().indexOf(type) >= 0) {
          return lvg[i].getIndex();
        }
      }
    }
    return -1;
  }

  public static Type getVariableType(int ind, MethodGen mg) {
    LocalVariableGen[] lvg = mg.getLocalVariables();
    if (ind >= 0) {
      for (int i=lvg.length-1; i>=0; i--) {
        if (lvg[i].getIndex() == ind) {
          return lvg[i].getType();
        }
      }
    }
    return Type.UNKNOWN;
  }

  public static String getVariableType(String name, MethodGen mg) {
    if (name != null) {
      LocalVariableGen[] lvg = mg.getLocalVariables();
      for (int i=lvg.length-1; i>=0; i--) {
        if (name.equalsIgnoreCase(lvg[i].getName())) {
          return Utility.getInstance().getStringType(lvg[i].getType());
        }
      }
    }
    return null;
  }


  public int getTarget(int i, int j) {
    return containers[i][j];
  }

  // ===== PRIVATE METHODS =====================================================

  //
  private void copy(InstructionList p, InstructionList list) {
    Instruction[] ilst = list.getInstructions();
    for (int i=0; i<ilst.length; i++) {
      p.append(ilst[i]);
    }
  }

  //
  private InstructionList loadVariable(LocationWeaver lw) {
    InstructionList patch = lw.getInstructionList();
    MethodGen mg = lw.getMethodWeaver().getMethodGen();
    int ind = getLocalIndex(attribute, type, mg);
    if (ind >= 0) { // local variable
      Type tp = Utility.getInstance().getBCELType(type, true);
      patch.append(InstructionFactory.createLoad(Utility.getInstance().getSimpleType(tp), ind));
//      patch.append(InstructionConstants.DUP);
    }
    return patch;
  }

  //
  private InstructionList storeLocalVariable(ClassWeaver cw, LocationWeaver lw) {
    InstructionList patch = lw.getInstructionList();
    MethodGen mg = lw.getMethodWeaver().getMethodGen();
    Type tp = Utility.getInstance().getBCELType(type, true);
    mg.addLocalVariable(attribute, tp, null, null);
    int ind = getLocalIndex(attribute, type, mg);
    if (value instanceof InvokeSpecial) {
      patch.append(cw.getInstructionFactory().createNew(Utility.getInstance().getStringType(tp)));
      patch.append(InstructionConstants.DUP);
      patch = ((Invoke)value).weave(cw, lw, false);
    }
    else if (value instanceof Invoke) {
      ((Invoke)value).setCanWeave(true);
      patch = ((Invoke)value).weave(cw, lw, false);
    }
    else if (value instanceof LocalAttribute) {
      LocalAttribute v = (LocalAttribute)value;
      ClpLocalVariable lv = new ClpLocalVariable(v.getType(), v.getAttribute(), null);
      patch = lv.weave(cw, lw, false);
    }
    patch.append(InstructionFactory.createStore(Utility.getInstance().getSimpleType(tp), ind));
    if (!clpLocalVariables.containsKey(attribute)) {
      clpLocalVariables.put(attribute, this);
    }
    return patch;
  }

  //
  private InstructionList storeLocalArray(ClassWeaver cw, LocationWeaver lw) {
    InstructionList patch = lw.getInstructionList();
    MethodGen mg = lw.getMethodWeaver().getMethodGen();
    tp = Utility.getInstance().getBCELType(type, true);
    mg.addLocalVariable(attribute, new ArrayType(tp, 1), null, null);
    ind = getLocalIndex(attribute, type, mg);
    if (!isInClpLocalVariables(attribute)) {
      if (size == 0) {
        size = getNeededSize(mg, lw);
      }
      if (size > 0) {
        patch.append(new PUSH(cw.getConstantPoolGen(), size));
        if (tp == Type.STRING) {
          patch.append(cw.getInstructionFactory().createNewArray(Type.STRING, (short)1));
        }
        else {
          patch.append(cw.getInstructionFactory().createNewArray(tp, (short)1));
        }
        patch.append(InstructionFactory.createStore(Type.OBJECT, ind));
      }
      assignVariable(cw, lw, 0);
      lw.setAdditionalHelper(this, size);
    }
    else {
      assignVariable(cw, lw, getIndex(lw.getLocation()));
    }
    if (!clpLocalVariables.containsKey(attribute)) {
      clpLocalVariables.put(attribute, this);
    }
    return patch;
  }

  private int getIndex(Location loc) {
    ClpLocVisitor vis = new ClpLocVisitor();
    loc.accept(vis);
    return vis.getOccurences();
  }

  private boolean isInClpLocalVariables(String att) {
    return getVariable(att) != null;
  }

  private int getNeededSize(MethodGen mg, LocationWeaver lw) {
    Location loc = lw.getLocation();
    if (loc == null) {
      return 1;
    }
    ClpLocVisitor vis = new ClpLocVisitor();
    loc.accept(vis);
    if (vis.getKind() == null) {
      return 1;
    }
    if (vis.getPosition() == Position.AFTER_EACH ||
        vis.getPosition() == Position.BEFORE_EACH) {
      return countOf(mg.getInstructionList().getInstructionHandles(), vis.getName());
    }
    return 0;
  }

  private int countOf(InstructionHandle[] ih, String name) {
    int tsize = 0;
    int targeters[] = new int[ih.length];
    for (int i=0; i<ih.length; i++) {
      if (ih[i].hasTargeters()) {
  targeters[tsize++] = i;
      }
    }
    int ind = 0;
    containers = new int[tsize][2];
    for (int i=0; i<tsize-1; i++) {
      int start = targeters[i];
      int end = targeters[i+1];
      for (int j=start; j<end; j++) {
  if (ih[j].toString().indexOf(name) >= 0) {
    containers[ind][0] = start;
    containers[ind++][1] = end;
    break;
  }
      }
    }
    return ind;
  }

  public boolean isArray() {
    return isArray;
  }

  public int getSize() {
    return size;
  }
}
