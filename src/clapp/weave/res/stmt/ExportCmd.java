package clapp.weave.res.stmt;

import org.apache.bcel.classfile.Field;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Type;

import clapp.run.util.ResourceUtility;
import clapp.weave.res.CLAppResourceHandler;
import clapp.weave.res.ClassWeaver;
import clapp.weave.res.FieldWeaver;
import clapp.weave.res.LocationWeaver;
import clapp.weave.res.api.IWeaverForLocation;
import clapp.weave.res.stmt.util.AExport;
import clapp.weave.res.util.ClpLocalVariable;
import clapp.weave.res.util.HelpClassWeaver;
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

public class ExportCmd extends AExport implements IWeaverForLocation {

  private Attribute attribute;  // source
  private String name;          // source variable name
  private Kind kind;         // source variable kind
  private Variable target;    // target
  private String targetName;
  private boolean hasToExtractValue;
  private boolean isGlobal;

  private InstructionList patch;   // will contain code to be added


  // ===== CONSTRUCTORS ========================================================

  public ExportCmd(Attribute att, String ret, Resources r) {
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
        @Override
        public void visitSysAttribute(SysAttribute x) {
          kind = Kind.SYS;
        }
        @Override
        public void visitLocalAttribute(LocalAttribute la) {
          name = la.getAttribute();
          kind = Kind.LOC;
        }
        @Override
        public void visitGlobalAttribute(GlobalAttribute ga) {
          name = ga.getAttribute();
          kind = Kind.GLOB;
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
                      null, boolean.class.getName(), targetName );
      return export(cw, lw, key, null, isWeavingDone);
    }
    attribute.accept(new AttributeVisitor() {

      // CASE OF LOCAL VARIABLE TO BE EXPORTED IN A CLAPP RESOURCE
      public void visitLocalAttribute(LocalAttribute la) {
        VariableFinder vf = new VariableFinder(la, lw.getMethodWeaver().getMethodGen());
        String name = (target != null ? targetName : vf.getAttributeName());
        String key = Utility.getInstance().getAttributeRefKey(
                                cw.getClassName(),
                                lw.getMethodWeaver().getMethodName(),
                                null,
                                vf.getTypeName(),
                                name );
        patch = export(cw, lw, key, vf, isWeavingDone);
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
          patch = export(cw, lw, key, new VariableFinder(field.getType()), isWeavingDone);
        }
        else {
          ClassGen cg = new HelpClassWeaver(ga.getType()).getClassGen();
          if ((index = foundInFields(cg, ga.getAttribute())) >= 0) {
            Field field = cg.getFields()[index];
            String key = Utility.getInstance().getAttributeRefKey(
                                  ga.getType(), ga.getAttribute(),
                                  null,
                                  field.getType().toString(),
                                  field.getName() );
            patch = export(cw, ga, index, key, field, isWeavingDone);
          }
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

  public void setName(String n) {
    name = n;
  }

  public Kind getKind() {
    return kind;
  }


  // ===== PRIVATE METHODS =====================================================

//  private String findType(final MethodWeaver mw, final String attr) {
//    if (mw.getArgList() != null) {
//      for (int i=0; i<mw.getArgList().size(); i++) {
//        final ArgumentPair ap = (ArgumentPair) mw.getArgList().get(i);
//        ap.getArgument().accept(new ArgumentVisitor () {
//          public void visitArgumentBlock(ArgumentBlock ab) {
//            List al = ab.getArgumentList();
//            for (int j=0; j<al.size(); j++) {
//              ArgumentName an = (ArgumentName) al.get(j);
//              if (an.hasCst()) {
//                break;
//              }
//              if (an.getName().equalsIgnoreCase(attr)) {
//                type = ap.getType();
//              }
//            }
//          }
//          public void visitArgumentRefVariable(ArgumentRefVariable arv) {
//            Resource var = ResourceUtility
//                            .getInstance()
//                              .getVariable(mw.getResources(),
//                                           arv.getName().getName());
//            if (var != null && var instanceof StrResource) {
//              if (attr.equalsIgnoreCase(((StrResource)var).getValue())) {
//                type = ap.getType();
//              }
//            }
//          }
//        });
//      }
//    }
//    return type;
//  }

  //
  private int foundInFields(ClassGen cg, String attribute) {
    if (cg != null) {
      Field[] fields = cg.getFields();
      for (int index = 0; index< fields.length; index++) {
        if (fields[index].getName().equals(attribute)) {
          return index;
        }
      }
    }
    return -1;
  }

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
                                 String key, VariableFinder vf, boolean isWeavingDone) {
    // clapp resource will be first registered as a listener
    CLAppResourceHandler.getInstance().register(key, target);
    if (isWeavingDone) {
      return null;
    }
    InstructionList patch = new InstructionList();
    if (cw.alreadyExists()) {
      return patch;
    }
    // then, target code will be enhanced to notify it
    FieldWeaver.weaveIndirect(cw, String.class.getName(), "_k_", key);
    if (hasToExtractValue) {
      patch.append(cw.getInstructionFactory()
          .createInvoke(RESOURCE_HANDLER, GET_INSTANCE, new ObjectType(RESOURCE_HANDLER), Type.NO_ARGS, INVOKESTATIC));
      patch.append(new PUSH(cw.getConstantPoolGen(), key));
      Type tp = Type.OBJECT;
      int ind = (isGlobal ? -1 : lw.getLocalVariableIndex(getName(), vf.getTypeName()));
      if (ind >= 0) {
        tp = (vf.isClpVariable() ? lw.getLocalVariableType(ind) : vf.getType());
        patch.append(InstructionFactory.createLoad(Utility.getInstance().getSimpleType(tp), ind));
      }
      else {
        ind = cw.getFieldIndex(getName());
        if (ind >= 0) {
          Field field = cw.getField(ind);
          if (field.isStatic()) {
            if (field.getType() == Type.STRING) {
              patch.append(new PUSH(cw.getConstantPoolGen(), getFieldValue(field)));
            }
            else {
              patch.append(cw.getInstructionFactory()
                               .createFieldAccess(cw.getClassName(),
                                                  getName(),
                                                  field.getType(),
                                                  GETSTATIC));
            }
          }
          else {
            patch.append(InstructionFactory.createLoad(Type.OBJECT, 0));
            patch.append(cw.getInstructionFactory()
                              .createFieldAccess(cw.getClassName(),
                                                 getName(),
                                                 field.getType(),
                                                 GETFIELD));
          }
        }
      }
      patch.append(cw.getInstructionFactory()
          .createInvoke(RESOURCE_HANDLER, EXPORT, Type.VOID, Utility.getInstance().getSignature(target, vf.isArgument()), INVOKEVIRTUAL));
    }
    return patch;
  }


  //
  private InstructionList export(ClassWeaver cw, GlobalAttribute ga, int ind,
                                 String key, Field field, boolean isWeavingDone) {
    // clapp resource will be first registered as a listener
    CLAppResourceHandler.getInstance().register(key, target);
    if (isWeavingDone) {
      return null;
    }
    InstructionList patch = new InstructionList();
    if (cw.alreadyExists()) {
      return patch;
    }
    // then, target code will be enhanced to notify it
    FieldWeaver.weaveIndirect(cw, String.class.getName(), "_k_", key);
    patch.append(new PUSH(cw.getConstantPoolGen(), key));
    if (field.isStatic()) {
      if (field.getType() == Type.STRING) {
        patch.append(new PUSH(cw.getConstantPoolGen(), getFieldValue(field)));
      }
      else {
        patch.append(cw.getInstructionFactory()
                         .createFieldAccess(ga.getType(),
                                            getName(),
                                            field.getType(),
                                            GETSTATIC));
      }
    }
    else {
      patch.append(InstructionFactory.createLoad(Type.OBJECT, 0));
      patch.append(cw.getInstructionFactory()
                        .createFieldAccess(cw.getClassName(),
                                           getName(),
                                           field.getType(),
                                           GETFIELD));
    }
    patch.append(cw.getInstructionFactory()
        .createInvoke(RESOURCE_HANDLER, EXPORT, Type.VOID, Utility.getInstance().getSignature(target, true), INVOKEVIRTUAL));
    return patch;
  }

  
  class VariableFinder {
    private Type type;
    private String attr;
    private String typeName;
    private boolean isClpVariable;
    private boolean isArgument;

    public VariableFinder(LocalAttribute la, MethodGen mg) {
      isArgument = la != null && la.getAttribute() != null;
      if (isArgument) {
        attr = la.getAttribute();
        if (!findInClpVariables(la)) {
          type = ClpLocalVariable.getOriginalVariableType(attr, mg);
          typeName = type.toString();
        }
      }
    }

    public VariableFinder(Type tp) {
      type = tp;
      typeName = tp.toString();
      isArgument = true;
    }

    public boolean isArgument() {
      return isArgument;
    }

    public Type getType() {
      return type;
    }

    public String getTypeName() {
      return typeName;
    }

    public String getAttributeName() {
      return attr;
    }

    public boolean isClpVariable() {
      return isClpVariable;
    }

    private boolean findInClpVariables(LocalAttribute la) {
      ClpLocalVariable lv = ClpLocalVariable.getVariable(la.getAttribute());
      if (lv != null) {
        typeName = lv.getType();
        isClpVariable = true;
      }
      if (typeName != null && typeName.startsWith("null.")) {
        typeName = typeName.substring(5);
      }
      return lv != null;
    }
    
  }
}
