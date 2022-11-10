package clapp.run.ui.elt;

import java.awt.Component;

import javax.swing.JTextArea;

import clapp.run.ui.Window;
import clapp.run.ui.elt.api.UIRefInfo;
import clapp.run.util.ResourceUtility;
import clp.run.res.Resources;

public class TextAreaInfo extends UIRefInfo {

  private static final long serialVersionUID = 437183189353369838L;

  private String name;
  private String text;


  public TextAreaInfo() {
    super();
  }

  @Override
  public Component getUIElement() {
    initText();
    return new JTextArea(text);
  }

  //
  private void initText() {
    if (text == null) {
      text = (String) getValue();
      text = replacePlaceHolders(text);
    }
  }

  //
  private String replacePlaceHolders(String text) {
    if (text == null) {
      text = "";
    }
    int i1 = text.indexOf('<');
    while (i1 >= 0) {
      int i2 = text.indexOf('>');
      String varname = text.substring(i1+1, i2);
      Object v = ResourceUtility.getInstance().getValue(varname);
      if (v != null) {
        text = text.replace("<" + varname + ">", v.toString());
      }
      else {
        text = text.replace("<" + varname + ">", varname);
      }
      i1 = text.indexOf('<');
    }
    return text;
  }


  @Override
  public boolean checkAndUpdate(Window win) {
    return true;
  }

  @Override
  public void rollBack() {
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setupVariable(Resources res) {
    // TODO Auto-generated method stub
    
  }
}
