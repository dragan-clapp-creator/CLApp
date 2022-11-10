package clapp.weave.res.stmt.util;

import org.apache.bcel.Constants;
import org.apache.bcel.generic.Type;

import clapp.weave.res.util.Utility;
import clp.run.res.ArrayBVar;
import clp.run.res.ArrayIVar;
import clp.run.res.ArrayLVar;
import clp.run.res.ArraySVar;
import clp.run.res.BVar;
import clp.run.res.BoolVarVisitor;
import clp.run.res.IVar;
import clp.run.res.IntVarVisitor;
import clp.run.res.LVar;
import clp.run.res.LongVarVisitor;
import clp.run.res.PVar;
import clp.run.res.SVar;
import clp.run.res.SimpleBVar;
import clp.run.res.SimpleIVar;
import clp.run.res.SimpleLVar;
import clp.run.res.SimpleSVar;
import clp.run.res.StringVarVisitor;
import clp.run.res.Variable;

public abstract class AExport implements Constants, ClpWeaverConstants {

  private String tp;

  public Type[] getSignature(Variable var, String type) {
    int sz = (type == null) ? 1 : 2;
    Type[] rtps = new Type[sz];
    rtps[0] = Utility.getInstance().getBCELType(STRING, true);
    if (sz > 1) {
      rtps[1] = getReturnType(var);
    }
    return rtps;
  }

  private Type getReturnType(Variable var) {
    tp = VOID;
    if (var instanceof BVar) {
      ((BVar)var).getBoolVar().accept(new BoolVarVisitor() {
        @Override
        public void visitSimpleBVar(SimpleBVar x) {
          tp = BOOLEAN;
        }
        @Override
        public void visitArrayBVar(ArrayBVar x) {
          tp = OBJECT;
        }
      });
    }
    else if (var instanceof IVar) {
      ((IVar)var).getIntVar().accept(new IntVarVisitor() {
        @Override
        public void visitSimpleIVar(SimpleIVar x) {
          tp = INT;
        }
        @Override
        public void visitArrayIVar(ArrayIVar x) {
          tp = OBJECT;
        }
      });
    }
    else if (var instanceof LVar) {
      ((LVar)var).getLongVar().accept(new LongVarVisitor() {
        @Override
        public void visitSimpleLVar(SimpleLVar x) {
          tp = LONG;
        }
        @Override
        public void visitArrayLVar(ArrayLVar x) {
          tp = OBJECT;
        }
      });
    }
    else if (var instanceof SVar) {
      ((SVar)var).getStringVar().accept(new StringVarVisitor() {
        @Override
        public void visitSimpleSVar(SimpleSVar x) {
          tp = STRING;
        }
        @Override
        public void visitArraySVar(ArraySVar x) {
          tp = OBJECT;
        }
      });
    }
    else if (var instanceof PVar) {
      tp = OBJECT;
    }
    return Utility.getInstance().getBCELType(tp, true);
  }
}
