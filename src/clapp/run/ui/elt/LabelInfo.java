package clapp.run.ui.elt;

import java.awt.Component;

import javax.swing.JLabel;

import clapp.run.ui.Window;
import clapp.run.ui.elt.api.UIRefInfo;
import clp.run.res.Resources;

public class LabelInfo extends UIRefInfo {

  private static final long serialVersionUID = 1884654848562138243L;

  public LabelInfo() {
    super();
  }

  @Override
  public String toString() {
    return "LabelInfo [value=" + getValue() + "]";
  }

  @Override
  public Component getUIElement() {
    return new JLabel((String) getValue());
  }


  @Override
  public boolean checkAndUpdate(Window win) {
    return true;
  }

  @Override
  public void rollBack() {
  }

  @Override
  public void setupVariable(Resources res) {
  }
}
