package clapp.run.vis;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import clp.run.res.BVar;
import clp.run.res.DVar;
import clp.run.res.FVar;
import clp.run.res.HVar;
import clp.run.res.IVar;
import clp.run.res.LVar;
import clp.run.res.PVar;
import clp.run.res.SVar;
import clp.run.res.TVar;
import clp.run.res.VariableVisitor;
import clp.run.res.WebVariable;
import clp.run.res.graph.Graph;
import clp.run.res.ui.UiVar;
import clp.run.res.weave.WeaveVar;

public class ClpSetVariableVisitor implements VariableVisitor {

  private Object value;
  private int index;

  public ClpSetVariableVisitor(Object val) {
    value = val;
    index = -1;
  }

  public ClpSetVariableVisitor(Object val, int i) {
    value = val;
    index = i;
  }

  @Override
  public void visitBVar(BVar x) {
    if (value instanceof String) {
      x.setValue(Boolean.getBoolean((String) value));
    }
    else {
      x.setValue((Boolean) value);
    }
  }

  @Override
  public void visitFVar(FVar x) {
    if (value instanceof String) {
      x.setValue(Double.parseDouble((String) value));
    }
    else {
      x.setValue((Double) value);
    }
  }

  @Override
  public void visitHVar(HVar x) {
  }

  @Override
  public void visitIVar(IVar x) {
    if (value instanceof String) {
      x.setValue(Integer.parseInt((String) value));
    }
    else {
      x.setValue((Integer) value);
    }
  }

  @Override
  public void visitLVar(LVar x) {
    if (value instanceof String) {
      x.setValue(Long.getLong((String) value));
    }
    else {
      x.setValue((Long) value);
    }
  }

  @Override
  public void visitPVar(PVar x) {
    x.setValue(value);
  }

  @Override
  public void visitSVar(SVar x) {
    if (value instanceof String[]) {
      String[] vals = (String[]) value;
      ArrayList<String> a = new ArrayList<String>();
      for (String v : vals) {
        a.add(v);
      }
      x.setValues(a);
    }
    else {
      if (index < 0) {
        x.setValue((String) value);
      }
      else {
        ArrayList<String> array = x.getValues();
        if (array == null) {
          array = new ArrayList<String>();
          x.setValues(array);
        }
        if (index < array.size()) {
          array.set(index, (String) value);
        }
        
        else {
          for (int i=array.size(); i<index; i++) {
            array.add("");
          }
          array.add((String) value);
        }
      }
    }
  }

  @Override
  public void visitWebVariable(WebVariable x) {
  }

  @Override
  public void visitGraph(Graph x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitWeaveVar(WeaveVar x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitDVar(DVar x) {
    if (value instanceof java.util.Date) {
      Date sqldate = new Date(((java.util.Date)value).getTime());
      x.setValue(sqldate);
    }
    else {
      x.setValue((Date) value);
    }
  }

  @Override
  public void visitTVar(TVar x) {
    x.setValue((Time) value);
  }

  @Override
  public void visitUiVar(UiVar x) {
    // TODO Auto-generated method stub
    
  }
}
