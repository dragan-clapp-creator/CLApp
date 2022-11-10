package clapp.run.ui.elt.api;

import java.awt.Component;
import java.io.Serializable;

import clapp.run.ui.Window;
import clp.run.res.Resources;

abstract public class UIInfo implements Serializable {

  private static final long serialVersionUID = 8230828913272132700L;

  abstract public Component getUIElement();
  abstract public boolean checkAndUpdate(Window win);
  abstract public void setupVariable(Resources res);
  abstract public void rollBack();
}
