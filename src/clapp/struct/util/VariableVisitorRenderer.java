package clapp.struct.util;

import java.util.ArrayList;

import clapp.run.util.SignUtil;
import clp.run.res.ArrayBInitVisitor;
import clp.run.res.ArrayBVar;
import clp.run.res.ArrayDVar;
import clp.run.res.ArrayFInitVisitor;
import clp.run.res.ArrayFVar;
import clp.run.res.ArrayIInitVisitor;
import clp.run.res.ArrayIVar;
import clp.run.res.ArrayLInitVisitor;
import clp.run.res.ArrayLVar;
import clp.run.res.ArraySVar;
import clp.run.res.ArrayTVar;
import clp.run.res.BInitList;
import clp.run.res.BInitSegment;
import clp.run.res.BVar;
import clp.run.res.BoolVarVisitor;
import clp.run.res.DVar;
import clp.run.res.DateVarVisitor;
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
import clp.run.res.LInitList;
import clp.run.res.LInitSegment;
import clp.run.res.LVar;
import clp.run.res.LongVarVisitor;
import clp.run.res.Lsigned;
import clp.run.res.PVar;
import clp.run.res.SVar;
import clp.run.res.SimpleBVar;
import clp.run.res.SimpleDVar;
import clp.run.res.SimpleFVar;
import clp.run.res.SimpleIVar;
import clp.run.res.SimpleLVar;
import clp.run.res.SimpleSVar;
import clp.run.res.SimpleTVar;
import clp.run.res.StringVarVisitor;
import clp.run.res.TVar;
import clp.run.res.TimeVarVisitor;
import clp.run.res.VariableVisitor;
import clp.run.res.WebVariable;
import clp.run.res.graph.Graph;
import clp.run.res.ui.UiVar;
import clp.run.res.weave.WeaveVar;

public class VariableVisitorRenderer implements VariableVisitor {

  private Object[] initials;
  private Object initial;
  private Object init2;
  private Object init1;
  private String name;
  private String type;
  private int port;

  private boolean isList;
  private boolean isArray;

  @Override
  public void visitBVar(BVar x) {
    type = x.getTBOOL().getVal();
    BoolVarVisitor vis = new BoolVarVisitor() {
      @Override
      public void visitSimpleBVar(SimpleBVar x) {
        name = x.getName();
        initial = x.getInitial();
      }
      @Override
      public void visitArrayBVar(ArrayBVar x) {
        name = x.getName();
        isArray = true;
        if (x.getArrayBInit() == null) {
          return;
        }
        ArrayBInitVisitor v = new ArrayBInitVisitor() {
          @Override
          public void visitBInitSegment(BInitSegment x) {
            init1 = x.getInit1();
            init2 = x.getInit2();
          }
          @Override
          public void visitBInitList(BInitList x) {
            initials = x.getInitials().toArray();
            isList = true;
          }
        };
        x.getArrayBInit().accept(v);
      }
    };
    x.getBoolVar().accept(vis);
  }

  @Override
  public void visitFVar(FVar x) {
    type = x.getTFLOAT().getVal();
    FloatVarVisitor vis = new FloatVarVisitor() {
      @Override
      public void visitSimpleFVar(SimpleFVar x) {
        name = x.getName();
        initial = SignUtil.getFInitial(x.getFsigned());
      }
      @Override
      public void visitArrayFVar(ArrayFVar x) {
        name = x.getName();
        isArray = true;
        if (x.getArrayFInit() == null) {
          return;
        }
        x.getArrayFInit().accept(new ArrayFInitVisitor() {
          @Override
          public void visitFInitSegment(FInitSegment x) {
            init1 = SignUtil.getFInitial(x.getInit1());
            init2 = SignUtil.getFInitial(x.getInit2());
          }
          @Override
          public void visitFInitList(FInitList x) {
            ArrayList<Double> vals = new ArrayList<Double>();
            vals.add( SignUtil.getFInitial(x.getFsigned()) );
            ArrayList<Fsigned> list = x.getFsigneds();
            for (int i=0; i<list.size(); i++) {
              vals.add(SignUtil.getFInitial(list.get(i)));
            }
            initials = vals.toArray();
            isList = true;
          }
        });
      }
    };
    x.getFloatVar().accept(vis);
  }

  @Override
  public void visitHVar(HVar x) {
    type = x.getTHASH().getVal();
    name = x.getName();
  }

  @Override
  public void visitIVar(IVar x) {
    type = x.getTINT().getVal();
    IntVarVisitor vis = new IntVarVisitor() {
      @Override
      public void visitSimpleIVar(SimpleIVar x) {
        name = x.getName();
        initial = SignUtil.getIInitial( x.getIsigned() );
      }
      @Override
      public void visitArrayIVar(ArrayIVar x) {
        name = x.getName();
        isArray = true;
        if (x.getArrayIInit() == null) {
          return;
        }
        x.getArrayIInit().accept(new ArrayIInitVisitor() {
          @Override
          public void visitIInitSegment(IInitSegment x) {
            init1 = SignUtil.getIInitial(x.getInit1());
            init2 = SignUtil.getIInitial(x.getInit2());
          }
          @Override
          public void visitIInitList(IInitList x) {
            ArrayList<Integer> vals = new ArrayList<Integer>();
            vals.add( SignUtil.getIInitial(x.getIsigned()) );
            ArrayList<Isigned> list = x.getIsigneds();
            for (int i=0; i<list.size(); i++) {
              vals.add(SignUtil.getIInitial(list.get(i)));
            }
            initials = vals.toArray();
            isList = true;
          }
        });
      }
    };
    x.getIntVar().accept(vis);
  }

  @Override
  public void visitLVar(LVar x) {
    type = x.getTLONG().getVal();
    LongVarVisitor vis = new LongVarVisitor() {
      @Override
      public void visitSimpleLVar(SimpleLVar x) {
        name = x.getName();
        initial = SignUtil.getLInitial(x.getLsigned());
      }
      @Override
      public void visitArrayLVar(ArrayLVar x) {
        name = x.getName();
        isArray = true;
        if (x.getArrayLInit() == null) {
          return;
        }
        x.getArrayLInit().accept(new ArrayLInitVisitor() {
          @Override
          public void visitLInitSegment(LInitSegment x) {
            init1 = SignUtil.getLInitial(x.getInit1());
            init2 = SignUtil.getLInitial(x.getInit2());
          }
          @Override
          public void visitLInitList(LInitList x) {
            ArrayList<Long> vals = new ArrayList<Long>();
            vals.add( SignUtil.getLInitial(x.getLsigned()) );
            ArrayList<Lsigned> list = x.getLsigneds();
            for (int i=0; i<list.size(); i++) {
              vals.add(SignUtil.getLInitial(list.get(i)));
            }
            initials = vals.toArray();
            isList = true;
          }
        });
      }
    };
    x.getLongVar().accept(vis);
  }

  @Override
  public void visitPVar(PVar x) {
    type = x.getTREF().getVal();
    name = x.getName();
    initial = x.getInitial();
  }

  @Override
  public void visitSVar(SVar x) {
    type = x.getTSTRING().getVal();
    StringVarVisitor vis = new StringVarVisitor() {
      @Override
      public void visitSimpleSVar(SimpleSVar x) {
        name = x.getName();
        if (x.getInitial() != null) {
          initial = "\""+x.getInitial()+"\"";
        }
      }
      @Override
      public void visitArraySVar(ArraySVar x) {
        name = x.getName();
        isArray = true;
        if (x.getSInitList() != null) {
          ArrayList<String> vals = x.getSInitList().getInitials();
          initials = new Object[vals.size()+1];
          initials[0] = x.getSInitList().getInitial();
          if (vals != null) {
            for (int i=0; i<vals.size(); i++) {
              initials[i+1] = vals.get(i);
            }
          }
          isList = true;
        }
      }
    };
    x.getStringVar().accept(vis);
  }

  @Override
  public void visitDVar(DVar x) {
    type = x.getTDATE().getVal();
    x.getDateVar().accept(new DateVarVisitor() {
      @Override
      public void visitSimpleDVar(SimpleDVar x) {
        name = x.getName();
        if (x.getDInit() != null) {
          
        }
      }
      @Override
      public void visitArrayDVar(ArrayDVar x) {
        name = x.getName();
        isArray = true;
      }
    });
  }

  @Override
  public void visitTVar(TVar x) {
    type = x.getTTIME().getVal();
    x.getTimeVar().accept(new TimeVarVisitor() {
      @Override
      public void visitSimpleTVar(SimpleTVar x) {
        name = x.getName();
      }
      @Override
      public void visitArrayTVar(ArrayTVar x) {
        name = x.getName();
        isArray = true;
      }
    });
  }

  @Override
  public void visitGraph(Graph x) {
    type = x.getTGRAPH().getVal();
    name = x.getGraphName();
  }

  @Override
  public void visitWeaveVar(WeaveVar x) {
    type = x.getTWEAVER().getVal();
    name = x.getName();
  }

  @Override
  public void visitUiVar(UiVar x) {
    type = x.getTUI().getVal();
    name = x.getName();
  }

  @Override
  public void visitWebVariable(WebVariable x) {
    type = x.getTWEB().getVal();
    name = x.getName();
    port = x.getPort().getNum();
  }

  public int getPort() {
    return port;
  }

  public Object[] getInitials() {
    return initials;
  }

  public Object getInit2() {
    return init2;
  }

  public Object getInit1() {
    return init1;
  }

  public Object getInitial() {
    return initial;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public boolean isList() {
    return isList;
  }

  public boolean isArray() {
    return isArray;
  }
}
