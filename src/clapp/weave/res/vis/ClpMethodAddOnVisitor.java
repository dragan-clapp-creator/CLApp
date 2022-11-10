package clapp.weave.res.vis;

import clapp.run.util.ResourceUtility;
import clapp.weave.res.LocationWeaver;
import clapp.weave.res.stmt.CopyFromCmd;
import clapp.weave.res.stmt.ExportCmd;
import clapp.weave.res.stmt.NotificationCmd;
import clp.run.res.Resources;
import clp.run.res.weave.CopyFromStream;
import clp.run.res.weave.Export;
import clp.run.res.weave.MethodAddOnVisitor;
import clp.run.res.weave.Notification;

public class ClpMethodAddOnVisitor implements MethodAddOnVisitor {

  private LocationWeaver locationWeaver;
  private Resources res;

  public ClpMethodAddOnVisitor(LocationWeaver lw, Resources r) {
    locationWeaver = lw;
    res = r;
  }

  @Override
  public void visitExport(Export x) {
    if (isResource(x.getClappVariable())) {
      locationWeaver
        .addMethodAddOn(new ExportCmd(x.getAttribute(), x.getClappVariable(), res));
    }
  }

  @Override
  public void visitNotification(Notification x) {
    if (isResource(x.getClappVariable())) {
      locationWeaver
        .addMethodAddOn(new NotificationCmd(x.getClappVariable(), res));
    }
  }

  @Override
  public void visitCopyFromStream(CopyFromStream x) {
    if (isResource(x.getClappVariable())) {
      locationWeaver
        .addMethodAddOn(new CopyFromCmd(x.getAttribute(), x.getClappVariable(), res));
    }
  }


  // ===== PRIVATE METHODS =====================================================

  private boolean isResource(String ret) {
    return (ResourceUtility.getInstance().getVariable(res, ret) != null);
  }
}
