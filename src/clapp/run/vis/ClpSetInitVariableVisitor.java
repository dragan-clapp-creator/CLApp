package clapp.run.vis;

import clapp.run.http.ResponseHandler;
import clp.run.res.ArrayBInitVisitor;
import clp.run.res.ArrayBVar;
import clp.run.res.BInitList;
import clp.run.res.BInitSegment;
import clp.run.res.BVar;
import clp.run.res.BoolVarVisitor;
import clp.run.res.DVar;
import clp.run.res.FVar;
import clp.run.res.HVar;
import clp.run.res.IVar;
import clp.run.res.LVar;
import clp.run.res.PVar;
import clp.run.res.SVar;
import clp.run.res.SimpleBVar;
import clp.run.res.TVar;
import clp.run.res.VariableVisitor;
import clp.run.res.WebVariable;
import clp.run.res.graph.Graph;
import clp.run.res.ui.UiVar;
import clp.run.res.weave.WeaveVar;

public class ClpSetInitVariableVisitor implements VariableVisitor {

  private Object value;
  private ResponseHandler responseHandler;

  public ClpSetInitVariableVisitor(Object val, ResponseHandler responseHandler) {
    value = val;
    this.responseHandler = responseHandler;
  }

  @Override
  public void visitBVar(BVar v) {
    BoolVarVisitor vis = new BoolVarVisitor() {
      @Override
      public void visitSimpleBVar(SimpleBVar x) {
        if (value instanceof String) {
          x.setInitial(Boolean.getBoolean((String) value));
        }
        else {
          x.setInitial((Boolean) value);
        }
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
          }
        };
        x.getArrayBInit().accept(avis);
      }
    };
    v.getBoolVar().accept(vis);
  }

  @Override
  public void visitFVar(FVar x) {
  }

  @Override
  public void visitHVar(HVar x) {
  }

  @Override
  public void visitIVar(IVar x) {
  }

  @Override
  public void visitLVar(LVar x) {
  }

  @Override
  public void visitPVar(PVar x) {
  }

  @Override
  public void visitSVar(SVar x) {
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
