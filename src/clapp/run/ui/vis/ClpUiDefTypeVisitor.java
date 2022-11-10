package clapp.run.ui.vis;

import clapp.run.ui.ClpVisualizer;
import clapp.run.ui.elt.ButtonInfo;
import clapp.run.ui.elt.InputFieldInfo;
import clapp.run.ui.elt.LabelInfo;
import clapp.run.ui.elt.TableInfo;
import clapp.run.ui.elt.TextAreaInfo;
import clapp.run.ui.elt.api.UIInitInfo;
import clapp.run.ui.elt.api.UIRefInfo;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.ResourceUtility;
import clp.run.res.Event;
import clp.run.res.Variable;
import clp.run.res.ui.CstOrVarIdentifier;
import clp.run.res.ui.CstOrVarIdentifierVisitor;
import clp.run.res.ui.Literal;
import clp.run.res.ui.UiButton;
import clp.run.res.ui.UiDefTypeVisitor;
import clp.run.res.ui.UiInputField;
import clp.run.res.ui.UiLabel;
import clp.run.res.ui.UiTable;
import clp.run.res.ui.UiTextArea;
import clp.run.res.ui.VarIdentifier;

public class ClpUiDefTypeVisitor implements UiDefTypeVisitor {

  public enum DefType {
    LABEL, FIELD, TEXT, TABLE, BUTTON;
  }

  private ClpVisualizer visualizer;
  private UIRefInfo info;
  private DefType definitionType;

  public ClpUiDefTypeVisitor(ClpVisualizer visualizer) {
    this.visualizer = visualizer;
  }

  @Override
  public void visitUiLabel(UiLabel x) {
    definitionType = DefType.LABEL;
    info = new LabelInfo();
    visualizer.setDisplayInfo(x.getName(), info);
    CstOrVarIdentifier value = x.getValue();
    value.accept(new CstOrVarIdentifierVisitor() {
      @Override
      public void visitVarIdentifier(VarIdentifier x) {
        String vname = x.getVar();
        Variable v = ResourceUtility.getInstance().getVariable(visualizer.getRes(), vname);
        if (v == null) {
          ConsoleProvider.getInstance().eprint("ERROR: variable "+vname+" not found");
          return;
        }
        if (ResourceUtility.getInstance().hasArray(v) && x.isIndex()) {
          Object[] obj = ResourceUtility.getInstance().getValues(v);
          if (obj != null && obj.length > x.getIndex() && obj[x.getIndex()] != null) {
              info.setInitialValue(obj[x.getIndex()]);
          }
          else {
            ConsoleProvider.getInstance().eprint("ERROR: inconsistence found around variable "+vname);
          }
        }
        else {
          Object val = ResourceUtility.getInstance().getValue(v);
          if (val != null) {
            info.setInitialValue(val);
          }
        }
      }
      @Override
      public void visitLiteral(Literal x) {
        String val = x.getValue();
        info.setInitialValue(val);
      }
    });
  }

  @Override
  public void visitUiInputField(UiInputField x) {
    definitionType = DefType.FIELD;
    info = new InputFieldInfo();
    visualizer.setDisplayInfo(x.getName(), info);
    InputFieldInfo field = (InputFieldInfo)info;
    field.setName(x.getName());
    field.setIsRequired(x.isIsRequired());
    field.setIsEnabled(x.isIsEnabled());
    field.setIsPassword(x.isIsPassword());
    VarIdentifier ident = x.getVarIdentifier();
    String vname = ident == null ? x.getName() : ident.getVar();
    Variable v = ResourceUtility.getInstance().getVariable(visualizer.getRes(), vname);
    if (v != null) {
      // reference is needed if we want to write to the variable
      info.setReference(v);
      info.setReferenceName(ResourceUtility.getInstance().getName(v));
      Object val = ResourceUtility.getInstance().getValue(v);
      if (val != null) {
        info.setInitialValue(val);
      }
    }
  }

  @Override
  public void visitUiTextArea(UiTextArea x) {
    definitionType = DefType.TEXT;
    info = new TextAreaInfo();
    visualizer.setDisplayInfo(x.getName(), info);
    TextAreaInfo tarea = (TextAreaInfo) info;
    tarea.setName(x.getName());
    CstOrVarIdentifier value = x.getValue();
    value.accept(new CstOrVarIdentifierVisitor() {
      @Override
      public void visitVarIdentifier(VarIdentifier x) {
        String vname = x.getVar();
        Variable v = ResourceUtility.getInstance().getVariable(visualizer.getRes(), vname);
        if (v == null) {
          ConsoleProvider.getInstance().eprint("ERROR: variable "+vname+" not found");
          return;
        }
        if (ResourceUtility.getInstance().hasArray(v) && x.isIndex()) {
          Object[] obj = ResourceUtility.getInstance().getValues(v);
          if (obj != null && obj.length > x.getIndex() && obj[x.getIndex()] != null) {
              info.setInitialValue(obj[x.getIndex()]);
          }
        }
        else {
          Object val = ResourceUtility.getInstance().getValue(v);
          if (val != null) {
            info.setInitialValue(val);
          }
        }
      }
      @Override
      public void visitLiteral(Literal x) {
        String val = x.getValue();
        info.setInitialValue(val);
      }
    });
  }

  @Override
  public void visitUiTable(UiTable x) {
    definitionType = DefType.TABLE;
    info = new TableInfo();
    visualizer.setDisplayInfo(x.getName(), info);
    VarIdentifier ident = x.getVarIdentifier();
    String vname = ident.getVar();
    Variable v = ResourceUtility.getInstance().getVariable(visualizer.getRes(), vname);
    // reference is needed if we want to write to the variable
    info.setReference(v);
    info.setReferenceName(ResourceUtility.getInstance().getName(v));
    if (ResourceUtility.getInstance().hasArray(v)) {
      Object[] obj = ResourceUtility.getInstance().getValues(v);
      if (obj != null) {
        info.setInitialValue(obj);
      }
    }
    else {
      Object val = ResourceUtility.getInstance().getValue(v);
      if (val != null) {
        info.setInitialValue(val);
      }
    }
    if (x.isSEL()) {
      ((TableInfo)info).setSelType( x.getSelType() );
    }
  }

  @Override
  public void visitUiButton(UiButton x) {
    definitionType = DefType.BUTTON;
    info = new ButtonInfo();
    visualizer.setDisplayInfo(x.getName(), info);
    ButtonInfo button = (ButtonInfo)info;
    button.setTitle(x.getTitle());
    button.setName(x.getName());
    if (x.isIsRollback()) {
      visualizer.getRoot().addButtonInfo(button, true);
    }
    else {
      visualizer.getRoot().addButtonInfo(button, false);
    }
    button.setRoot(visualizer.getRoot());
    VarIdentifier ident = x.getVarIdentifier();
    if (ident != null) {
      String vname = ident.getVar();
      Variable v = ResourceUtility.getInstance().getVariable(visualizer.getRes(), vname);
      if (v != null) {
        info.setReferenceName(vname);
        if (ResourceUtility.getInstance().hasArray(v) && ident.isIndex()) {
          Object[] obj = ResourceUtility.getInstance().getValues(v);
          if (obj != null && obj.length > ident.getIndex()) {
            // reference is needed if we want to write to the variable
            info.setReference(v, ident.getIndex());
          }
          else {
            ConsoleProvider.getInstance().eprint("ERROR: inconsistence found around variable "+vname);
          }
        }
        else {
          // reference is needed if we want to write to the variable
          info.setReference(v);
        }
      }
      Event ev = ResourceUtility.getInstance().getEvent(visualizer.getRes(), vname);
      info.setEventReference(ev);
    }
  }

  public UIInitInfo getInfo() {
    return info;
  }

  public DefType getDefinitionType() {
    return definitionType;
  }
}
