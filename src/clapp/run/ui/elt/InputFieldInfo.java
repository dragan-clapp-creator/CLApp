package clapp.run.ui.elt;

import java.awt.Component;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import clapp.run.token.EventHandler;
import clapp.run.ui.Window;
import clapp.run.ui.elt.api.UIRefInfo;
import clapp.run.util.ResourceUtility;
import clp.run.res.Resources;
import clp.run.res.Variable;

public class InputFieldInfo extends UIRefInfo {

  private static final long serialVersionUID = 7982814957727357171L;

  private String name;
  private boolean isRequired;
  private boolean isEnabled;
  private boolean isPassword;
  private JTextField field;


  public InputFieldInfo() {
    super();
  }

  public void setName(String n) {
    name = n;
  }

  public void setIsRequired(boolean b) {
    isRequired = b;
  }

  public void setIsPassword(boolean b) {
    isPassword = b;
  }

  public String getName() {
    return name;
  }

  public boolean getIsRequired() {
    return isRequired;
  }

  public boolean getIsPassword() {
    return isPassword;
  }

  @Override
  public String toString() {
    return "InputFieldInfo [name=" + name + ", isRequired=" + isRequired + ", isPassword=" + isPassword
        + ", refName=" + getRefName() + ", value=" + getValue() + "]";
  }

  @Override
  public Component getUIElement() {
    if (field == null) {
      field = (isPassword ? new JPasswordField(10) : new JTextField(10));
      field.setEnabled(isEnabled);
    }
    return field;
  }


  @Override
  public boolean checkAndUpdate(Window win) {
    String text = field.getText();
    if (isRequired && (text == null || text.isEmpty())) {
      String msg = "<span style=\"color: #7f0000\">field " + name + " should not be empty</span>";
      win.setMessage(msg);
      return false;
    }
    else if (!text.isBlank()) {
      Variable v = getReference();
      if (v != null) {
        ResourceUtility.getInstance().setValue(getRefName(), v, text);
        String msg;
        if (isPassword) {
          msg = "<span style=\"color: #007f00\">value ****** was set to field " + name + "</span>";
        }
        else {
          msg = "<span style=\"color: #007f00\">value " + text + " was set to field " + name + " </span>";
        }
        win.setMessage(msg);
      }
      else if (getRefEvent() != null) {
        EventHandler.getInstance().markVarEvent(getRefName(), true);
      }
    }
    String msg = "<span style=\"color: #007f00\">field " + name + " was correctly checked</span>";
    win.setMessage(msg);
    return true;
  }

  @Override
  public void rollBack() {
    Variable v = getReference();
    if (v != null) {
      ResourceUtility.getInstance().resetValue(v);
    }
  }

  @Override
  public void setupVariable(Resources res) {
    Variable var = ResourceUtility.getInstance().getVariable(res, getRefName());
    if (var != null) {
      Object val = ResourceUtility.getInstance().getValue(var);
      if (val != null) {
        field.setText(""+val);
      }
    }
  }

  /**
   * @return the isEnabled
   */
  public boolean getIsEnabled() {
    return isEnabled;
  }

  /**
   * @param isEnabled the isEnabled to set
   */
  public void setIsEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
}
