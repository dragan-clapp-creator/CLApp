package clapp.struct.util;

import clp.run.cel.exp.Expression;
import clp.run.cel.exp.Factor;
import clp.run.cel.exp.Term;
import clp.run.cel.log.LogicalExp;
import clp.run.cel.log.LogicalExpression;
import clp.run.cel.log.LogicalFactor;
import clp.run.cel.log.LogicalTerm;
import clp.run.cel.log.LogicalTerms;

public class ReflectUtility {

  private static ReflectUtility instance = new ReflectUtility();

  private ReflectUtility() {
  }

  public static ReflectUtility getInstance() {
    return instance;
  }

  public String getExpression(Expression exp) {
    String str = getTerm( exp.getTerm() );
    if (exp.isTermOperator()) {
      str += " " + exp.getOp().getVal() + " ";
      str += getExpression( exp.getExpression() );
    }
    return str;
  }

  private String getTerm(Term t) {
    String str = getFact( t.getFactor() );
    if (t.isFactOperator()) {
      str += " " + t.getOp().getVal() + " ";
      str += getTerm( t.getTerm() );
    }
    return str;
  }

  private String getFact(Factor f) {
    RFactorVisitor fvis = new RFactorVisitor();
    f.accept(fvis);
    return fvis.getString();
  }

  public StringBuffer getExp(LogicalExp e) {
    final StringBuffer sb = new StringBuffer();
    sb.append(getTerm(e.getLogicalTerm()));
    if (e.hasLogicalTerms()) {
      for (LogicalTerm t : e.getLogicalTerms()) {
        sb.append(" OR ");
        sb.append(getTerm(t));
      }
    }
    return sb;
  }

  public StringBuffer getTerm(LogicalTerm t) {
    final StringBuffer sb = new StringBuffer();
    sb.append(getFactor(t.getLogicalFactor()));
    if (t.hasLogicalFactors()) {
      for (LogicalFactor f : t.getLogicalFactors()) {
        sb.append(" AND ");
        sb.append(getFactor(f));
      }
    }
    return sb;
  }

  public StringBuffer getFactor(LogicalFactor f) {
    final StringBuffer sb = new StringBuffer();
    LogicalFactorVisitorRenderer vis = new LogicalFactorVisitorRenderer();
    vis.accept(f);
    sb.append(vis.getRendering());
    return sb;
  }

  public StringBuffer getLogicalExpression(LogicalExpression e) {
    final StringBuffer sb = new StringBuffer();
    LogicalTerms lt = e.getLogicalTerms();
    sb.append(getTerm(lt.getLogicalTerm()));
    if (lt.hasLogicalTerms()) {
      for (LogicalTerm t : lt.getLogicalTerms()) {
        sb.append(" OR ");
        sb.append(getTerm(t));
      }
    }
    return sb;
  }
}
