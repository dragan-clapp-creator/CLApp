package clapp.run.ui.elt;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import clapp.run.token.EventHandler;
import clapp.run.ui.Window;
import clapp.run.ui.elt.api.UIRefInfo;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.ResourceUtility;
import clp.run.res.BVar;
import clp.run.res.Resources;
import clp.run.res.Variable;

public class ButtonInfo extends UIRefInfo {

  private static final long serialVersionUID = -2942088841653093397L;

  private String title;
  private String name;
  private boolean isRollback;
  private JButton button;
  private BundleInfo uiroot;

  public ButtonInfo() {
    super();
  }

  public void setRoot(BundleInfo ui) {
    uiroot = ui;
  }

  public void setTitle(String t) {
    title = t;
  }

  public void setName(String n) {
    name = n;
  }

  public void setIsRollback(boolean b) {
    isRollback = b;
  }

  public String getTitle() {
    return title;
  }

  public String getName() {
    return name;
  }

  public boolean isRollback() {
    return isRollback;
  }

  public void setVariableValue() {
    if (getReference() != null) {
      ConsoleProvider.getInstance().print("variable "+getRefName()+" was set to true");
      ResourceUtility.getInstance().setValue(getRefName(), getReference(), Boolean.TRUE);
    }
    if (getRefEvent() != null) {
      ConsoleProvider.getInstance().print("event "+getRefName()+" was set to true");
      EventHandler.getInstance().markVarEvent(getRefName(), true);
    }
  }

  @Override
  public Component getUIElement() {
    if (button == null) {
      String text = (title == null ? name : title);
      button = new JButton(text);
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (uiroot != null && !isSelected()) {
            button.setSelected(true);
         }
        }
      });
    }
    return button;
  }

  @Override
  public String toString() {
    return "ButtonInfo [title=" + title + ", name=" + name + ", isRollback=" + isRollback
        + ", refName=" + getRefName() + "]";
  }

  @Override
  public boolean checkAndUpdate(Window win) {
    return true;
  }

  @Override
  public void rollBack() {
  }

  public boolean isSelected() {
    return button.isSelected();
  }

  public void setSelected(boolean b) {
    button.setSelected(b);
  }

  @Override
  public void setupVariable(Resources res) {
    Variable var = ResourceUtility.getInstance().getVariable(res, name);
    if (var instanceof BVar) {
      System.out.printf("button %s reset\n", name);
      ((BVar)var).setValue(false);
      button.setSelected(false);
    }
  }
}
