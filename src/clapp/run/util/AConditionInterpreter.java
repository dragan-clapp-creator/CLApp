/*
 * Created on Dec 7, 2004
 *
 */
package clapp.run.util;

import java.util.ArrayList;

import clapp.run.vis.ClpLogicalFactorVisitor;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.Weightings;
import clp.run.cel.log.LogicalExp;
import clp.run.cel.log.LogicalExpression;
import clp.run.cel.log.LogicalFactor;
import clp.run.cel.log.LogicalTerm;
import clp.run.res.Resources;
import clp.run.res.Weighting;


/**
 * @author Dragan Matic
 *
 */
public abstract class AConditionInterpreter extends AInterpreter {


  public AConditionInterpreter(Actor a, String n, boolean b) {
    super(a, n, b);
  }


  public boolean isDeactivationConditionTrue(CellChainLink ccl, Resources res, LogicalExpression exp, int cellActivity) {
    if ((cellActivity == 0 || exp.getLevel() == cellActivity) || hasNoToken(ccl.getCell())) {
      if (isTermTrue(res, exp.getLogicalTerms().getLogicalTerm())) {
        return true;
      }
      if (exp.getLogicalTerms().getLogicalTerms() != null) {
        for (LogicalTerm term : exp.getLogicalTerms().getLogicalTerms()) {
          if (isTermTrue(res, term)) {
            return true;
          }
        }
      }
    }
    return false;
  }


  //
  private boolean hasNoToken(Cell cell) {
    Weightings wts = cell.getWeightings();
    if (wts != null) {
      ArrayList<Weighting> ws = new ArrayList<>();
      Weighting w = wts.getWeighting();
      if (w != null) {
        ws.add(w);
      }
      ws.addAll(wts.getWeightings());
      for (Weighting cw : ws) {
        if (cw.getWeight() > 0) {
          return false;
        }
      }
    }
    return true;
  }


  public boolean isExpTrue(Resources res, LogicalExp lexp) {
    boolean result = isTermTrue(res, lexp.getLogicalTerm());
    if ( !result && lexp.hasLogicalTerms() ) {
      for (LogicalTerm lterm : lexp.getLogicalTerms()) {
        if (isTermTrue(res, lterm)) {
          return true;
        }
      }
    }
    return result;
  }


  public boolean isTermTrue(Resources res, LogicalTerm lterm) {
    boolean result = isFactTrue(res, lterm.getLogicalFactor());
    if ( result && lterm.hasLogicalFactors() ) {
      for (LogicalFactor lfact : lterm.getLogicalFactors()) {
        if (!isFactTrue(res, lfact)) {
          return false;
        }
      }
    }
    return result;
  }


  public boolean isFactTrue(Resources res, LogicalFactor lfact) {
    ClpLogicalFactorVisitor vis = new ClpLogicalFactorVisitor(res, this);
    vis.accept(lfact);
    return vis.isTrue();
  }

  abstract public boolean isOpQueueActive();

  abstract public CellQueueHandler getOperatingQueue();

  abstract public boolean isActiveInQueue(String name);

  abstract public boolean isInactiveInQueue(String name);
}
