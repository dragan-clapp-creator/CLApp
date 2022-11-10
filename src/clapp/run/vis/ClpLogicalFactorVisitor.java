package clapp.run.vis;

import clapp.run.token.EventHandler;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.AConditionInterpreter;
import clapp.run.util.ExpressionEvaluator;
import clapp.run.util.LogicalExpEvaluator;
import clapp.run.util.ResourceUtility;
import clp.run.cel.log.Bvariable;
import clp.run.cel.log.CellFunction;
import clp.run.cel.log.CheckEvent;
import clp.run.cel.log.Comparison;
import clp.run.cel.log.Lexpression;
import clp.run.cel.log.LogicalFactor;
import clp.run.cel.log.SysFunction;
import clp.run.cel.log.Tautology;
import clp.run.res.BVar;
import clp.run.res.Resources;
import clp.run.res.Variable;


public class ClpLogicalFactorVisitor {

  private boolean istrue;
  private Resources res;
  private AConditionInterpreter interpreter;

  public ClpLogicalFactorVisitor(Resources r, AConditionInterpreter i) {
    super();
    res = r;
    interpreter = i;
  }

  public void visitCellFunction(CellFunction x, boolean isNOT) {
    String name = x.getCellName();
    switch (x.getFunction()) {
      case ACTIVE:
        istrue = interpreter.isActiveInQueue(name) || EventHandler.getInstance().isUp(name) == Boolean.TRUE;
        break;
      case INACTIVE:
        istrue = interpreter.isInactiveInQueue(name) || EventHandler.getInstance().isDown(name) == Boolean.TRUE;
        break;
    }
    if (isNOT) {
      istrue = !istrue;
    }
    if (istrue && x.isSINCE()) {
      istrue = EventHandler.getInstance().isTimeUp(x.getTime());
    }
  }

  public void visitLexpression(Lexpression x, boolean isNOT) {
    LogicalExpEvaluator eval = new LogicalExpEvaluator(res, interpreter);
    istrue = eval.getBoolExpression(x.getLogicalExp());
    if (isNOT) {
      istrue = !istrue;
    }
  }

  public void visitSysFunction(SysFunction x, boolean isNOT) {
    // TODO: implement it
    if (isNOT) {
      istrue = !istrue;
    }
  }

  public void visitCheckEvent(CheckEvent x, boolean isNOT) {
    switch(x.getFunction()) {
      case UP:
        istrue = EventHandler.getInstance().isUp(x.getName()) == Boolean.TRUE;
        break;
      case DOWN:
        istrue = EventHandler.getInstance().isDown(x.getName()) == Boolean.TRUE;
        break;
    }
    if (isNOT) {
      istrue = !istrue;
    }
  }

  public boolean isTrue() {
    return istrue;
  }

  public void visitBvariable(Bvariable x, boolean isNOT) {
    Variable v = ResourceUtility.getInstance().getVariable(res, x.getVar());
    if (v instanceof BVar) {
      boolean val = ((BVar)v).getValue();
      if (isNOT) {
        istrue = !val;
      }
      else {
        istrue = val;
      }
    }
  }

  public void visitComparison(Comparison x) {
    ExpressionEvaluator evaluator = new ExpressionEvaluator(res);
    if (!checkString(evaluator, x) && !checkInt(evaluator, x) &&
        !checkFloat(evaluator, x)) {
      ConsoleProvider.getInstance().eprint("error in comparison");
    }
  }

  private boolean checkInt(ExpressionEvaluator evaluator, Comparison x) {
    try {
      int i1 = evaluator.getIntExpression(x.getExp1());
      int i2 = evaluator.getIntExpression(x.getExp2());
      switch (x.getOpComp()) {
        case DIFF:
          istrue = (i1 != i2);
          break;
        case EQ:
          istrue = (i1 == i2);
          break;
        case INF:
          istrue = (i1 < i2);
          break;
        case SUP:
          istrue = (i1 > i2);
          break;
        default:
          return false;
      }
      return true;
    }
    catch(RuntimeException e) {
      e.printStackTrace();
    }
    return false;
  }

  private boolean checkFloat(ExpressionEvaluator evaluator, Comparison x) {
    try {
      double d1 = evaluator.getFloatExpression(x.getExp1());
      double d2 = evaluator.getFloatExpression(x.getExp2());
      switch (x.getOpComp()) {
        case DIFF:
          istrue = (d1 != d2);
          break;
        case EQ:
          istrue = (d1 == d2);
          break;
        case INF:
          istrue = (d1 < d2);
          break;
        case SUP:
          istrue = (d1 > d2);
          break;
        default:
          return false;
      }
      return true;
    }
    catch(RuntimeException e) {
      e.printStackTrace();
    }
    return false;
  }

  private boolean checkString(ExpressionEvaluator evaluator, Comparison x) {
    try {
      String s1 = evaluator.getStringExpression(x.getExp1());
      String s2 = evaluator.getStringExpression(x.getExp2());
      if (s1 == null || s2 == null) {
        return false;
      }
      switch (x.getOpComp()) {
        case DIFF:
          istrue = (s1.compareTo(s2) != 0);
          break;
        case EQ:
          istrue = (s1.compareTo(s2) == 0);
          break;
        case INF:
          istrue = (s1.compareTo(s2) < 0);
          break;
        case SUP:
          istrue = (s1.compareTo(s2) > 0);
          break;
        default:
          return false;
      }
      return true;
    }
    catch(RuntimeException e) {
      e.printStackTrace();
    }
    return false;
  }

  public void accept(LogicalFactor lfact) {
    Tautology tau = lfact.getTautology();
    if (tau != null) {
      istrue = !lfact.isNOT();
    }
    Bvariable bvar = lfact.getBvariable();
    if (bvar != null) {
      visitBvariable(bvar, lfact.isNOT());
      return;
    }
    CellFunction cf = lfact.getCellFunction();
    if (cf != null) {
      visitCellFunction(cf, lfact.isNOT());
      return;
    }
    Comparison cmp = lfact.getComparison();
    if (cmp != null) {
      visitComparison(cmp);
      return;
    }
    Lexpression lexp = lfact.getLexpression();
    if (lexp != null) {
      visitLexpression(lexp, lfact.isNOT());
      return;
    }
    SysFunction sf = lfact.getSysFunction();
    if (sf != null) {
      visitSysFunction(sf, lfact.isNOT());
    }
    CheckEvent ce = lfact.getCheckEvent();
    if (ce != null) {
      visitCheckEvent(ce, lfact.isNOT());
      return;
    }
  }
}
