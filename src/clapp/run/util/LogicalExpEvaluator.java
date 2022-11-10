package clapp.run.util;

import clapp.run.vis.ClpLogicalFactorVisitor;
import clp.run.cel.log.LogicalExp;
import clp.run.cel.log.LogicalFactor;
import clp.run.cel.log.LogicalTerm;
import clp.run.res.Resources;

public class LogicalExpEvaluator {

  private Resources res;
  private AConditionInterpreter interpreter;

  public LogicalExpEvaluator(Resources res, AConditionInterpreter i) {
    this.res = res;
    interpreter = i;
  }

  public boolean getBoolExpression(LogicalExp logicalExp) {
    boolean b = getBoolTerm(logicalExp.getLogicalTerm());
    if (!b && logicalExp.hasLogicalTerms()) {
      for (LogicalTerm ltem : logicalExp.getLogicalTerms()) {
        b = getBoolTerm(ltem);
        if (b) {
          return true;
        }
      }
    }
    return b;
  }

  private boolean getBoolTerm(LogicalTerm logicalTerm) {
    boolean b = getBoolFactor(logicalTerm.getLogicalFactor());
    if (b && logicalTerm.hasLogicalFactors()) {
      for (LogicalFactor lfact : logicalTerm.getLogicalFactors()) {
        b = getBoolFactor(lfact);
        if (!b) {
          return false;
        }
      }
    }
    return b;
  }

  private boolean getBoolFactor(LogicalFactor logicalFactor) {
    ClpLogicalFactorVisitor vis = new ClpLogicalFactorVisitor(res, interpreter);
    vis.accept(logicalFactor);
    return vis.isTrue();
  }

}
