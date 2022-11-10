package clapp.weave.res;

import java.util.HashMap;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.FieldGen;

import clapp.run.ui.util.ConsoleProvider;
import clapp.run.vis.ClpCstOrVarVisitor;
import clapp.weave.res.api.IWeaverForClass;
import clapp.weave.res.util.Utility;

public class FieldWeaver implements IWeaverForClass {

  static private HashMap<String, String[]> attributes = new HashMap<String, String[]>();

  private String value;
  private String attName;
  private String typeName;

  private AttributeReference attRef;

  
  // ===== CONSTRUCTORS ========================================================

 public FieldWeaver(AttributeReference attref) {
   setup(attref);
  }

  public FieldWeaver(AttributeReference attref, String val) {
    value = val;
    setup(attref);
  }

  public FieldWeaver(String name, String type, String val) {
    value = val;
    attName = name;
    typeName = type;
    String[] str = new String[2];
    str[0] = typeName;
    str[1] = value;
    attributes.put(attName, str);
  }

  private void setup(AttributeReference attref) {
    this.attRef = attref;
    ClpCstOrVarVisitor vis = new ClpCstOrVarVisitor();
    vis.accept(attref.getAttribute());
    attName = vis.getName();
    vis = new ClpCstOrVarVisitor();
    vis.accept(attref.getType());
    typeName = vis.getName();
    String[] str = new String[2];
    str[0] = typeName;
    str[1] = value;
    attributes.put(attName, str);
  }

  // ===== PUBLIC METHODS ======================================================


  @Override
  public void weave(ClassWeaver cw, boolean isWeavingDone) {
    if (!isWeavingDone) {
      short modifier = getModifier(attRef);
      FieldGen fg = new FieldGen(modifier,
                                 Utility.getInstance().getBCELType(typeName, true),
                                 attName,
                                 cw.getConstantPoolGen());
      addField(fg, value, cw.getClassGen(), cw.getJavaClass());
    }
  }


  // ===== STATIC PUBLIC METHODS ===============================================

  /**
   * this is called by the export stuff, as the "listenTo" is treated.
   */
  static public void weaveIndirect(ClassWeaver cw, String type, String att, String val) {
    String attName = att + String.valueOf(attributes.size());
    String[] str = new String[2];
    str[0] = type;
    str[1] = val;
    attributes.put(attName, str);
    short modifier = Constants.ACC_PRIVATE | Constants.ACC_STATIC | Constants.ACC_FINAL;
    FieldGen fg = new FieldGen(modifier,
                               Utility.getInstance().getBCELType(type, true),
                               attName,
                               cw.getConstantPoolGen());
    addField(fg, val, cw.getClassGen(), cw.getJavaClass());
  }


  private static void addField(FieldGen fg, String val, ClassGen classGen, JavaClass javaClass) {
    Field[] flds = classGen.getFields();
    if (fg.isFinal()) {
      fg.setInitValue(val);
    }
    boolean found = false;
    for (int i=0; !found && i<flds.length; i++) {
      if (flds[i].getName().equals(fg.getName())) {
        flds[i] = fg.getField();
        found = true;
      }
    }
    if (!found) {
      classGen.addField(fg.getField());
    }
    javaClass.setFields(flds);
  }


  // ===== PRIVATE METHODS =====================================================

  private short getModifier(AttributeReference ref) {
    if (ref == null) {
      return Constants.ACC_PRIVATE;
    }
    short modifier = 0;
    String str = "-> ";
    if (ref.isFinal()) {
      modifier |= Constants.ACC_FINAL;
      str += "final ";
    }
    if (ref.isStatic()) {
      modifier |= Constants.ACC_STATIC;
      str += "static ";
    }
    if (ref.isVisibility()) {
      str += ref.getVisibility().getVal() + " ";
      switch (ref.getVisibility()) {
        case Private:
          modifier |= Constants.ACC_PRIVATE;
          break;
        case Protected:
          modifier |= Constants.ACC_PROTECTED;
          break;
        case Public:
          modifier |= Constants.ACC_PUBLIC;
          break;

        default:
          break;
      }
    }
    ClpCstOrVarVisitor vis = new ClpCstOrVarVisitor();
    vis.accept(ref.getType());
    attName = vis.getName();
    str += vis.getName()+" ";
    vis = new ClpCstOrVarVisitor();
    vis.accept(ref.getAttribute());
    ConsoleProvider.getInstance().print(str + vis.getName());

    return modifier;
  }
}
