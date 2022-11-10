package clapp.struct.util;

import clp.run.cel.log.Bvariable;
import clp.run.cel.log.CellFunction;
import clp.run.cel.log.CheckEvent;
import clp.run.cel.log.Comparison;
import clp.run.cel.log.Lexpression;
import clp.run.cel.log.LogicalFactor;
import clp.run.cel.log.SysFunction;
import clp.run.cel.log.Tautology;

public class LogicalFactorVisitorRenderer {

  private String string = "";
  private ReflectUtility ru;

  public LogicalFactorVisitorRenderer() {
    ru = ReflectUtility.getInstance();
  }

  public String getRendering() {
    return string;
  }

  private void visitBvariable(Bvariable x, boolean isNOT) {
    if (isNOT) {
      string += "NOT ";
    }
    string += x.getVar();
  }

  private void visitCellFunction(CellFunction x, boolean isNOT) {
    if (isNOT) {
      string += "NOT ";
    }
    switch(x.getFunction()) {
      case ACTIVE:
        string += "activated(" + x.getCellName() + ")";
        if (x.isSINCE()) {
          string += " SINCE " + x.getTime();
        }
        break;
      case INACTIVE:
        string += "deactivated(" + x.getCellName() + ")";
        break;
    }
  }

  private void visitCheckEvent(CheckEvent x, boolean isNOT) {
    if (isNOT) {
      string += "NOT ";
    }
    switch(x.getFunction()) {
      case UP:
        string += "isSetUp(" + x.getName() + ")";
        break;
      case DOWN:
        string += "isSetDown(" + x.getName() + ")";
        break;
    }
  }

  private void visitLexpression(Lexpression x, boolean isNOT) {
    if (isNOT) {
      string += "NOT ";
    }
    string += "(" + ru.getExp( x.getLogicalExp() ) + ")";
  }

  private void visitSysFunction(SysFunction x, boolean isNOT) {
    if (isNOT) {
      string += "NOT ";
    }
  }

  private void visitComparison(Comparison x) {
    string += ru.getExpression( x.getExp1() );
    string += " " + x.getOpComp().getVal() + " ";
    string += ru.getExpression( x.getExp2() );
  }

  public void accept(LogicalFactor f) {
    Bvariable bvar = f.getBvariable();
    if (bvar != null) {
      visitBvariable(bvar, f.isNOT());
      return;
    }
    CellFunction cf = f.getCellFunction();
    if (cf != null) {
      visitCellFunction(cf, f.isNOT());
      return;
    }
    Comparison cmp = f.getComparison();
    if (cmp != null) {
      visitComparison(cmp);
      return;
    }
    Lexpression lexp = f.getLexpression();
    if (lexp != null) {
      visitLexpression(lexp, f.isNOT());
      return;
    }
    Tautology tau = f.getTautology();
    if (tau != null) {
      string += "TRUE";
      return;
    }
    CheckEvent ce = f.getCheckEvent();
    if (ce != null) {
      visitCheckEvent(ce, f.isNOT());
      return;
    }
    SysFunction sf = f.getSysFunction();
    if (sf != null) {
      visitSysFunction(sf, f.isNOT());
    }
  }
}
