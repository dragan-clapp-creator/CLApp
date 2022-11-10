package clapp.weave.res.stmt;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Type;

import clapp.run.util.ResourceUtility;
import clapp.weave.res.ClassWeaver;
import clapp.weave.res.FieldWeaver;
import clapp.weave.res.LocationWeaver;
import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.stmt.util.AExport;
import clapp.weave.res.stmt.util.ClpLocalAttribute;
import clapp.weave.res.stmt.util.ClpWeaverConstants;
import clapp.weave.res.util.ClpLocalVariable;
import clapp.weave.res.util.Utility;
import clp.run.res.PVar;
import clp.run.res.Resources;
import clp.run.res.Variable;
import clp.run.res.weave.Attribute;
import clp.run.res.weave.AttributeVisitor;
import clp.run.res.weave.GlobalAttribute;
import clp.run.res.weave.LocalAttribute;
import clp.run.res.weave.SysAttribute;
import clp.run.res.weave.SysRef;


public class CopyFromCmd extends AExport implements IWeaverForLocation {

  private Attribute attribute;  // source
  private String name;          // source variable name
  private Kind kind;            // source variable kind
  private Variable target;      // target
  private String targetName;
  private boolean hasToExtractValue;
  private boolean isGlobal;

  private InstructionList patch;   // will contain code to be added


  // ===== CONSTRUCTORS ========================================================

  public CopyFromCmd(Attribute att, String ret, Resources r) {
    attribute = att;
    targetName = ret;
    target = ResourceUtility.getInstance().getVariable(r, ret);
    if (att == null) {
      kind = Kind.LOC;
      hasToExtractValue = false;
    }
    else {
      hasToExtractValue = true;
      attribute.accept(new AttributeVisitor() {
        public void visitLocalAttribute(LocalAttribute la) {
          name = la.getAttribute();
          kind = Kind.LOC;
        }
        public void visitGlobalAttribute(GlobalAttribute ga) {
          name = ga.getAttribute();
          kind = Kind.GLOB;
        }
        public void visitSysAttribute(SysAttribute sa) {
          kind = Kind.SYS;
        }
      });
    }
  }


  // ===== PUBLIC METHODS ======================================================

  public InstructionList weave(final ClassWeaver cw, final LocationWeaver lw, boolean isWeavingDone) {

    if (attribute == null) {
      // it's a simple notification in a boolean target 
      String key = Utility.getInstance().getAttributeRefKey(
                      cw.getClassName(), lw.getMethodWeaver().getMethodName(),
                      null, "boolean", targetName );
      return export(cw, lw, key, null);
    }
    attribute.accept(new AttributeVisitor() {



      // CASE OF LOCAL VARIABLE TO BE EXPORTED IN A CLAPP RESOURCE
      public void visitLocalAttribute(LocalAttribute la) {
        String type = null;
        String attr = null;
        ClpLocalVariable lv = ClpLocalVariable.getVariable(la.getAttribute());
        if (lv == null) {
          if (la instanceof ClpLocalAttribute) {
            type = ((ClpLocalAttribute)la).getType();
            attr = la.getAttribute();
          }
          else {
            attr = la.getAttribute();
            type = la.getType();
          }
        }
        else {
          type = lv.getType();
          attr = lv.getAttribute();
        }
        if (type != null && type.startsWith("null.")) {
          type = type.substring(5);
        }
        String name = (target != null ? targetName : attr);
        String key = Utility.getInstance().getAttributeRefKey(
                                cw.getClassName(),
                                lw.getMethodWeaver().getMethodName(),
                                null,
                                type,
                                name );
        patch = export(cw, lw, key, type);
      }

      // CASE OF GLOBAL VARIABLE (FIELD) TO BE EXPORTED IN A CLAPP RESOURCE
      public void visitGlobalAttribute(GlobalAttribute ga) {
        int index = -1;
        if ((index = foundInFields(cw, ga.getAttribute())) >= 0) {
          Field field = cw.getClassGen().getFields()[index];
          isGlobal = true;
          String key = Utility.getInstance().getAttributeRefKey(
                                  cw.getClassName(),
                                  lw.getMethodWeaver().getMethodName(),
                                  null,
                                  field.getType().toString(),
                                  field.getName() );
          patch = export(cw, lw, key, field.getType().toString());
        }
      }

      // CASE OF SYSTEM REFERENCE TO BE EXPORTED IN A CLAPP RESOURCE
      public void visitSysAttribute(SysAttribute sa) {
        Object obj;
        if (sa.getAttribute() == SysRef.CODE) {  // export Code
          obj = cw.getMethodWeaver(lw.getMethodWeaver().getMethodName(),
                                   lw.getMethodWeaver().getArgList())
                  .getMethod()
                  .getCode();
        }
        else {                                // export Instruction List
          obj = lw.getMethodWeaver().getMethodGen().getInstructionList();
        }
        // directly set to clapp resource
        if (target instanceof PVar) {
          ((PVar)target).setValue(obj);
        }
      }
    });
    return patch;
  }

  public String getName() {
    return name;
  }

  public Kind getKind() {
    return kind;
  }


  // ===== PRIVATE METHODS =====================================================

  //
  private int foundInFields(ClassWeaver cw, String attribute) {
    if (cw.getClassGen() != null) {
      Field[] fields = cw.getClassGen().getFields();
      for (int index = 0; index< fields.length; index++) {
        if (fields[index].getName().equals(attribute)) {
          return index;
        }
      }
    }
    return -1;
  }

  //
  private String getFieldValue(Field field) {
    String val = field.getConstantValue().toString();
    // string begins with an unwanted '"'
    return val.substring(1, val.length()-1);
  }

  //
  private InstructionList export(ClassWeaver cw, LocationWeaver lw,
                                 String key, String type) {
    // clapp resource will be first registered as a listener
//    ResourceHandler.register(key, target);
    InstructionList patch = new InstructionList();
    if (cw.alreadyExists()) {
      return patch;
    }
    // then, target code will be enhanced to notify it
    FieldWeaver.weaveIndirect(cw, String.class.getName(), "_k_", key);
    patch.append(new PUSH(cw.getConstantPoolGen(), key));
    int ind = 0;
    Type tp = Type.OBJECT;
    if (hasToExtractValue) {
      ind = (isGlobal ? -1 : lw.getLocalVariableIndex(name, type));
      if (ind >= 0) {
        tp = Utility.getInstance().getSimpleType(lw.getLocalVariableType(ind));
        patch.append(InstructionFactory.createLoad(tp, ind));
      }
      else {
        ind = cw.getFieldIndex(name);
        if (ind >= 0) {
          Field field = cw.getField(ind);
          if (field.isStatic()) {
            patch.append(new PUSH(cw.getConstantPoolGen(), getFieldValue(field)));
          }
          else {
            patch.append(InstructionFactory.createLoad(Type.OBJECT, 0));
            patch.append(cw.getInstructionFactory()
                              .createFieldAccess(cw.getClassName(),
                                                 name,
                                                 field.getType(),
                                                 Constants.GETFIELD));
          }
        }
      }
    }
    patch.append(cw.getInstructionFactory()
              .createInvoke(ClpWeaverConstants.RESOURCE_HANDLER,
                            getSetter(),
                            Utility.getInstance().getBCELType(type, true),
                            getSignature(target, type),
                            Constants.INVOKESTATIC));
    patch.append(InstructionFactory.createStore(tp, ind));
    return patch;
  }

  /**
   * overwrite that from super class
   */
  public String getSetter() {
    return ClpWeaverConstants.COPY_FROM_STREAM;
  }
}
