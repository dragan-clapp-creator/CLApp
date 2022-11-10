package clapp.weave.res.stmt;

import org.apache.bcel.generic.InstructionList;
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
import clapp.weave.res.stmt.util.ClpWeaverConstants;
import clapp.weave.res.util.Utility;
import clp.run.res.Resources;
import clp.run.res.Variable;


public class NotificationCmd extends AExport implements IWeaverForLocation {

  private String name;        // source variable name
  private Kind kind;          // source variable kind
  private Variable target;    // target
  private String targetName;



  // ===== CONSTRUCTORS ========================================================

  public NotificationCmd(String ret, Resources r) {
    target = ResourceUtility.getInstance().getVariable(r, ret);
    targetName = ret;
    kind = Kind.LOC;
  }


  // ===== PUBLIC METHODS ======================================================


  public InstructionList weave(final ClassWeaver cw, final LocationWeaver lw, boolean isWeavingDone) {

    // it's a simple notification in a boolean target 
    String key = Utility.getInstance().getAttributeRefKey(
                    cw.getClassName(), lw.getMethodWeaver().getMethodName(),
                    null, ClpWeaverConstants.BOOLEAN, targetName );
    return export(cw, lw, key, isWeavingDone);
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

  private Type[] getSignature() {
    int sz = 1;
    Type[] rtps = new Type[sz];
    rtps[0] = Utility.getInstance().getBCELType(ClpWeaverConstants.STRING, true);
    return rtps;
  }

  //
  private InstructionList export(ClassWeaver cw, LocationWeaver lw, String key, boolean isWeavingDone) {
    // clapp resource will be first registered as a listener
//    if (timestamp == null) {
    CLAppResourceHandler.getInstance().register(key, target);
//    }
//    else {
//      ResourceHandler.register(key, timestamp, free, total);
//    }
    if (!isWeavingDone) {
      InstructionList patch = new InstructionList();
      if (cw.alreadyExists()) {
        return patch;
      }
      // then, target code will be enhanced to notify it
      FieldWeaver.weaveIndirect(cw, ClpWeaverConstants.STRING, "_k_", key);

      patch.append(cw.getInstructionFactory()
          .createInvoke(RESOURCE_HANDLER, GET_INSTANCE, new ObjectType(RESOURCE_HANDLER), Type.NO_ARGS, INVOKESTATIC));
      patch.append(new PUSH(cw.getConstantPoolGen(), key));
      patch.append(cw.getInstructionFactory()
          .createInvoke(RESOURCE_HANDLER, EXPORT, Type.VOID, getSignature(), INVOKEVIRTUAL));
      return patch;
    }
    return null;
  }
}
