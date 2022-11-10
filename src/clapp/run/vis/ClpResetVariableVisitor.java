package clapp.run.vis;

import java.util.ArrayList;

import clapp.run.http.ResponseHandler;
import clapp.run.util.SignUtil;
import clp.run.res.ArrayBInitVisitor;
import clp.run.res.ArrayBVar;
import clp.run.res.ArrayFInitVisitor;
import clp.run.res.ArrayFVar;
import clp.run.res.ArrayIInitVisitor;
import clp.run.res.ArrayIVar;
import clp.run.res.ArraySVar;
import clp.run.res.BInitList;
import clp.run.res.BInitSegment;
import clp.run.res.BVar;
import clp.run.res.BoolVarVisitor;
import clp.run.res.DVar;
import clp.run.res.FInitList;
import clp.run.res.FInitSegment;
import clp.run.res.FVar;
import clp.run.res.FloatVarVisitor;
import clp.run.res.Fsigned;
import clp.run.res.HVar;
import clp.run.res.IInitList;
import clp.run.res.IInitSegment;
import clp.run.res.IVar;
import clp.run.res.IntVarVisitor;
import clp.run.res.Isigned;
import clp.run.res.LVar;
import clp.run.res.PVar;
import clp.run.res.SInitList;
import clp.run.res.SVar;
import clp.run.res.SimpleBVar;
import clp.run.res.SimpleFVar;
import clp.run.res.SimpleIVar;
import clp.run.res.SimpleSVar;
import clp.run.res.StringVarVisitor;
import clp.run.res.TVar;
import clp.run.res.VariableVisitor;
import clp.run.res.WebVariable;
import clp.run.res.graph.Graph;
import clp.run.res.ui.UiVar;
import clp.run.res.weave.WeaveVar;

public class ClpResetVariableVisitor implements VariableVisitor {

  private ResponseHandler responseHandler;

  public ClpResetVariableVisitor(ResponseHandler responseHandler) {
    this.responseHandler = responseHandler;
  }

  @Override
  public void visitBVar(final BVar v) {
    BoolVarVisitor vis = new BoolVarVisitor() {
      @Override
      public void visitSimpleBVar(SimpleBVar x) {
        v.setValue(x.getInitial());
        if (responseHandler != null) {
          responseHandler.notifyDone(x.getName(), x.getInitial());
        }
      }
      @Override
      public void visitArrayBVar(ArrayBVar x) {
        ArrayBInitVisitor avis = new ArrayBInitVisitor() {
          @Override
          public void visitBInitSegment(BInitSegment x) {
          }
          @Override
          public void visitBInitList(BInitList x) {
            v.setValues(x.getInitials());
          }
        };
        if (x.isArrayBInit()) {
          x.getArrayBInit().accept(avis);
        }
      }
    };
    v.getBoolVar().accept(vis);
  }

  @Override
  public void visitFVar(final FVar v) {
    FloatVarVisitor vis = new FloatVarVisitor() {
      @Override
      public void visitSimpleFVar(SimpleFVar x) {
        Double init = SignUtil.getFInitial(x.getFsigned());
        if (init != null) {
        	v.setValue(init);
            if (responseHandler != null) {
              responseHandler.notifyDone(x.getName(), init);
            }
        }
      }
      @Override
      public void visitArrayFVar(ArrayFVar x) {
        ArrayFInitVisitor avis = new ArrayFInitVisitor() {
          @Override
          public void visitFInitSegment(FInitSegment x) {
          }
          @Override
          public void visitFInitList(FInitList x) {
            ArrayList<Double> vals = new ArrayList<Double>();
            vals.add( SignUtil.getFInitial(x.getFsigned()) );
            ArrayList<Fsigned> list = x.getFsigneds();
            for (int i=0; i<list.size(); i++) {
              Double init = SignUtil.getFInitial(x.getFsigned());
              if (init != null) {
              	vals.add(init);
              }
            }
            v.setValues(vals);
          }
        };
        if (x.isArrayFInit()) {
          x.getArrayFInit().accept(avis);
        }
      }
    };
    v.getFloatVar().accept(vis);
  }

  @Override
  public void visitHVar(HVar v) {
  }

  @Override
  public void visitIVar(final IVar v) {
    IntVarVisitor vis = new IntVarVisitor() {
      @Override
      public void visitSimpleIVar(SimpleIVar x) {
        Integer init = SignUtil.getIInitial(x.getIsigned());
        if (init != null) {
        	v.setValue(init);
            if (responseHandler != null) {
              responseHandler.notifyDone(x.getName(), init);
            }
        }
      }
      @Override
      public void visitArrayIVar(ArrayIVar x) {
        ArrayIInitVisitor avis = new ArrayIInitVisitor() {
          @Override
          public void visitIInitSegment(IInitSegment x) {
            ArrayList<Integer> a = new ArrayList<Integer>();
            Integer init1 = SignUtil.getIInitial(x.getInit1());
            Integer init2 = SignUtil.getIInitial(x.getInit2());
            if (init1 != null && init2 != null) {
              for (int i=init1; i<=init2; i++) {
                a.add(i);
              }
            }
            v.setValues(a);
          }
          @Override
          public void visitIInitList(IInitList x) {
            ArrayList<Integer> vals = new ArrayList<Integer>();
            vals.add( SignUtil.getIInitial(x.getIsigned()) );
            ArrayList<Isigned> list = x.getIsigneds();
            for (int i=0; i<list.size(); i++) {
              Integer init = SignUtil.getIInitial(x.getIsigned());
              if (init != null) {
              	vals.add(init);
              }
            }
            v.setValues(vals);
          }
        };
        if (x.isArrayIInit()) {
          x.getArrayIInit().accept(avis);
        }
      }
    };
    v.getIntVar().accept(vis);
  }

  @Override
  public void visitLVar(LVar v) {
  }

  @Override
  public void visitPVar(PVar v) {
  }

  @Override
  public void visitSVar(final SVar v) {
    StringVarVisitor vis = new StringVarVisitor() {
      @Override
      public void visitSimpleSVar(SimpleSVar x) {
        v.setValue(x.getInitial());
        if (responseHandler != null) {
          responseHandler.notifyDone(x.getName(), x.getInitial());
        }
      }
      @Override
      public void visitArraySVar(ArraySVar x) {
        SInitList list = x.getSInitList();
        if (list != null) {
          v.setValues(list.getInitials());
        }
      }
    };
    v.getStringVar().accept(vis);
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
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitTVar(TVar x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitUiVar(UiVar x) {
    // TODO Auto-generated method stub
    
  }
}
