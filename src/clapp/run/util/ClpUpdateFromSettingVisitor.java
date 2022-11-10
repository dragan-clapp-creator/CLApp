package clapp.run.util;

import java.util.ArrayList;

import clapp.run.token.EventHandler;
import clp.run.res.ArrayBVariable;
import clp.run.res.ArrayFInitVisitor;
import clp.run.res.ArrayFVariable;
import clp.run.res.ArrayIInitVisitor;
import clp.run.res.ArrayIVariable;
import clp.run.res.ArraySVariable;
import clp.run.res.BVar;
import clp.run.res.BVariable;
import clp.run.res.BoolVariableVisitor;
import clp.run.res.DVariable;
import clp.run.res.EVariable;
import clp.run.res.FInitList;
import clp.run.res.FInitSegment;
import clp.run.res.FVar;
import clp.run.res.FVariable;
import clp.run.res.FloatVariableVisitor;
import clp.run.res.Fsigned;
import clp.run.res.HVariable;
import clp.run.res.IInitList;
import clp.run.res.IInitSegment;
import clp.run.res.IVar;
import clp.run.res.IVariable;
import clp.run.res.IntVariableVisitor;
import clp.run.res.Isigned;
import clp.run.res.LVariable;
import clp.run.res.PVariable;
import clp.run.res.Resources;
import clp.run.res.SInitList;
import clp.run.res.SVar;
import clp.run.res.SVariable;
import clp.run.res.SettingVisitor;
import clp.run.res.SimpleBVariable;
import clp.run.res.SimpleFVariable;
import clp.run.res.SimpleIVariable;
import clp.run.res.SimpleSVariable;
import clp.run.res.StringVariableVisitor;
import clp.run.res.TVariable;
import clp.run.res.Variable;
import clp.run.res.WebVariable;

public class ClpUpdateFromSettingVisitor implements SettingVisitor {

  private ResourceUtility util;
  private Resources res;

  public ClpUpdateFromSettingVisitor(Resources r) {
    util = ResourceUtility.getInstance();
    res = r;
  }

  @Override
  public void visitBVariable(BVariable x) {
    x.getBoolVariable().accept(new BoolVariableVisitor() {
      @Override
      public void visitSimpleBVariable(SimpleBVariable x) {
        String vname = x.getName();
        Variable v = util.getVariable(res, vname);
        if (v instanceof BVar) {
          ((BVar) v).setValue(x.getInitial());
          util.setupEvent(res, vname, x.getInitial());
        }
        else if (v == null) {
          util.setupEvent(res, vname, x.getInitial());
        }
      }
      @Override
      public void visitArrayBVariable(ArrayBVariable x) {
      }
    });
  }

  @Override
  public void visitFVariable(FVariable x) {
    x.getFloatVariable().accept(new FloatVariableVisitor() {
      @Override
      public void visitSimpleFVariable(SimpleFVariable x) {
        Variable v = util.getVariable(res, x.getName());
        if (v instanceof FVar) {
          ((FVar) v).setValue(SignUtil.getFInitial(x.getFsigned()));
        }
      }
      @Override
      public void visitArrayFVariable(ArrayFVariable x) {
        Variable v = util.getVariable(res, x.getName());
        x.getArrayFInit().accept(new ArrayFInitVisitor() {
          @Override
          public void visitFInitSegment(FInitSegment x) {
          }
          @Override
          public void visitFInitList(FInitList x) {
            ArrayList<Double> vals = new ArrayList<Double>();
            vals.add( SignUtil.getFInitial(x.getFsigned()) );
            ArrayList<Fsigned> list = x.getFsigneds();
            for (int i=0; i<list.size(); i++) {
              vals.add(SignUtil.getFInitial(list.get(i)));
            }
            ((FVar) v).setValues(vals);
          }
        });
      }
    });
  }

  @Override
  public void visitIVariable(IVariable x) {
    x.getIntVariable().accept(new IntVariableVisitor() {
      @Override
      public void visitSimpleIVariable(SimpleIVariable x) {
        Variable v = util.getVariable(res, x.getName());
        if (v instanceof IVar) {
          ((IVar) v).setValue(SignUtil.getIInitial(x.getIsigned()));
        }
      }
      @Override
      public void visitArrayIVariable(ArrayIVariable x) {
        Variable v = util.getVariable(res, x.getName());
        x.getArrayIInit().accept(new ArrayIInitVisitor() {
          
          @Override
          public void visitIInitSegment(IInitSegment x) {
            ArrayList<Integer> vals = new ArrayList<Integer>();
            int init1 = SignUtil.getIInitial(x.getInit1());
            int init2 = SignUtil.getIInitial(x.getInit2());
            for (int i=init1; i<=init2; i++) {
              vals.add(i);
            }
            ((IVar) v).setValues(vals);
          }
          @Override
          public void visitIInitList(IInitList x) {
            ArrayList<Integer> vals = new ArrayList<Integer>();
            vals.add( SignUtil.getIInitial(x.getIsigned()) );
            ArrayList<Isigned> list = x.getIsigneds();
            for (int i=0; i<list.size(); i++) {
              vals.add(SignUtil.getIInitial(list.get(i)));
            }
            ((IVar) v).setValues(vals);
          }
        });
      }
    });
  }

  @Override
  public void visitLVariable(LVariable x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitSVariable(SVariable x) {
    x.getStringVariable().accept(new StringVariableVisitor() {
      @Override
      public void visitSimpleSVariable(SimpleSVariable x) {
        Variable v = util.getVariable(res, x.getName());
        if (v instanceof SVar) {
          ((SVar) v).setValue(x.getInitial());
        }
      }
      @Override
      public void visitArraySVariable(ArraySVariable x) {
        Variable v = util.getVariable(res, x.getName());
        if (x.getSInitList() != null) {
          if (v instanceof SVar) {
            ArrayList<String> vals = new ArrayList<String>();
            SInitList list = x.getSInitList();
            if (list != null) {
              vals.add(x.getSInitList().getInitial());
              vals.addAll(x.getSInitList().getInitials());
              ((SVar) v).setValues(vals);
            }
          }
        }
      }
    });
  }

  @Override
  public void visitDVariable(DVariable x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitTVariable(TVariable x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitHVariable(HVariable x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitPVariable(PVariable x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitWebVariable(WebVariable x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitEVariable(EVariable x) {
    if (EventHandler.getInstance().isCell(x.getName())) {
      if (x.getInitial()) {
        EventHandler.getInstance().markCellUp(x.getName());
      }
      else {
        EventHandler.getInstance().markCellDown(x.getName());
      }
    }
    else {
      EventHandler.getInstance().markVarEvent(x.getName(), x.getInitial());
    }
  }
}
