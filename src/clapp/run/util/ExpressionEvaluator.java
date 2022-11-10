/*
 * Created on Feb 11, 2005
 *
 */
package clapp.run.util;

import java.sql.Date;
import java.sql.Time;

import clapp.run.ui.util.ConsoleProvider;
import clapp.run.vis.ClpFactorVisitor;
import clp.run.cel.exp.Expression;
import clp.run.cel.exp.Factor;
import clp.run.cel.exp.Term;
import clp.run.res.Resources;
import clp.run.res.Variable;



/**
 * @author Dragan Matic
 *
 */
public class ExpressionEvaluator {

  private ClpFactorVisitor visitor;
  private Resources res;

  public ExpressionEvaluator(Resources r) {
    res = r;
  }

  public Date getDateExpression(Expression exp) {
    Term t = exp.getTerm();
    if (t != null) {
      return getDateTerm(t);
    }
    return null;
  }

  public Time getTimeExpression(Expression exp) {
    Term t = exp.getTerm();
    if (t != null) {
      return getTimeTerm(t);
    }
    return null;
  }

  public int getIntExpression(Expression exp) {
    int iexp = 0;
    Term t = exp.getTerm();
    if (t != null) {
      iexp += getIntTerm(t);
    }
    if (exp.isTermOperator()) {
      switch (exp.getOp()) {
        case MINUS:
          iexp -= getIntExpression(exp.getExpression());
          break;
        case PLUS:
          iexp += getIntExpression(exp.getExpression());
          break;
      }
    }
    return iexp;
  }

  private Date getDateTerm(Term t) {
    Factor f = t.getFactor();
    visitor = new ClpFactorVisitor(res);
    f.accept(visitor);
    return visitor.getDate();
  }

  private Time getTimeTerm(Term t) {
    Factor f = t.getFactor();
    visitor = new ClpFactorVisitor(res);
    f.accept(visitor);
    return visitor.getTime();
  }

  private int getIntTerm(Term t) {
    int iterm = 0;
    Factor f = t.getFactor();
    visitor = new ClpFactorVisitor(res);
    f.accept(visitor);
    if (visitor.getVar() != null) {
      Variable var = visitor.getVar();
      Object obj = ResourceUtility.getInstance().getValue(var);
      if (obj instanceof Integer) {
        iterm = ((Integer)obj).intValue();
      }
    }
    else if (visitor.isInt()) {
      iterm = visitor.getIntVal();
    }
    if (t.isFactOperator()) {
      switch (t.getOp()) {
        case DIVIDE:
          iterm /= getIntTerm(t.getTerm());
          break;
        case MULTIPLY:
          iterm *= getIntTerm(t.getTerm());
          break;
      }
    }
    return iterm;
  }

  public double getFloatExpression(Expression exp) {
    double iexp = 0;
    Term t = exp.getTerm();
    if (t != null) {
      iexp += getFloatTerm(t);
    }
    if (exp.isTermOperator()) {
      switch (exp.getOp()) {
        case MINUS:
          iexp -= getFloatExpression(exp.getExpression());
          break;
        case PLUS:
          iexp += getFloatExpression(exp.getExpression());
          break;
      }
    }
    return iexp;
  }

  private double getFloatTerm(Term t) {
    double iterm = 0;
    Factor f = t.getFactor();
    visitor = new ClpFactorVisitor(res);
    f.accept(visitor);
    if (visitor.getVar() != null) {
      Variable var = visitor.getVar();
      Object obj = ResourceUtility.getInstance().getValue(var);
      if (obj instanceof Double) {
        iterm = ((Double)obj).intValue();
      }
    }
    else if (visitor.isFloat()) {
      iterm = visitor.getIntVal();
    }
    if (t.isFactOperator()) {
      switch (t.getOp()) {
        case DIVIDE:
          iterm /= getIntTerm(t.getTerm());
          break;
        case MULTIPLY:
          iterm *= getIntTerm(t.getTerm());
          break;
      }
    }
    return iterm;
  }

  public String getVariableName(Expression exp) {
    String iexp = null;
    Term t = exp.getTerm();
    if (t != null) {
      iexp = getVarTerm(t);
      if (exp.isTermOperator()) {
        ConsoleProvider.getInstance().eprint("ERROR: no other terms admitted for a variable name");
      }
    }
    if (exp.isExpression()) {
      iexp += getVariableName(exp.getExpression());
    }
    return iexp;
  }

  private String getVarTerm(Term t) {
    String iterm = null;
    Factor f = t.getFactor();
    visitor = new ClpFactorVisitor(res);
    f.accept(visitor);
    if (visitor.isVar()) {
      iterm = visitor.getVarVal();
      if (t.isFactOperator()) {
        ConsoleProvider.getInstance().eprint("ERROR: String term cannot have any factor operation");
      }
    }
    return iterm;
  }

  public String getStringExpression(Expression exp) {
    String iexp = null;
    Term t = exp.getTerm();
    if (t != null) {
      iexp = getStringTerm(t);
    }
    if (exp.isTermOperator()) {
      switch (exp.getOp()) {
        case MINUS:
          String s = getStringExpression(exp.getExpression());
          if (iexp == null) {
            break;
          }
          int i = iexp.lastIndexOf(s);
          if (i >= 0) {
            iexp = iexp.substring(0, i) + iexp.substring(i+s.length());
          }
          break;
        case PLUS:
          iexp += getStringExpression(exp.getExpression());
          break;
      }
    }
    return iexp;
  }

  private String getStringTerm(Term t) {
    String iterm = null;
    Factor f = t.getFactor();
    visitor = new ClpFactorVisitor(res);
    f.accept(visitor);
    if (visitor.isConst()) {
      iterm = visitor.getConstVal();
      if (t.isFactOperator()) {
        ConsoleProvider.getInstance().eprint("ERROR: String term cannot have any factor operation");
      }
    }
    else if (visitor.isInt()) {
      iterm = ""+visitor.getIntVal();
    }
    return iterm;
  }

  public Class getVisitorClass() {
    if (visitor.isArray()) {
      return String[].class;
    }
    if (visitor.isInt()) {
      return int.class;
    }
    if (visitor.isConst()) {
      return String.class;
    }
    if (visitor.isFloat()) {
      return double.class;
    }
    return null;
  }

  public Object getVisitorValue() {
    if (visitor.isArray()) {
      return visitor.getAvalues();
    }
    if (visitor.isInt()) {
      return visitor.getIntVal();
    }
    if (visitor.isConst()) {
      return visitor.getConstVal();
    }
    if (visitor.isFloat()) {
      return visitor.getFloatValues();
    }
    if (visitor.isDate()) {
      return visitor.getDate();
    }
    if (visitor.isTime()) {
      return visitor.getTime();
    }
    return null;
  }

  public boolean getBoolExpression(Expression exp) {
    boolean bexp = false;
    Term t = exp.getTerm();
    if (t != null) {
      bexp |= getBoolTerm(t);
    }
    if (exp.isTermOperator()) {
      switch (exp.getOp()) {
        case MINUS:
          break;
        case PLUS:
          bexp |= getBoolExpression(exp.getExpression());
          break;
      }
    }
    return bexp;
  }

  private boolean getBoolTerm(Term t) {
    boolean bterm = true;
    Factor f = t.getFactor();
    visitor = new ClpFactorVisitor(res);
    f.accept(visitor);
    if (visitor.getVar() != null) {
      Variable var = visitor.getVar();
      Object obj = ResourceUtility.getInstance().getValue(var);
      if (obj instanceof Boolean) {
        bterm = ((Boolean)obj).booleanValue();
      }
    }
    if (t.isFactOperator()) {
      switch (t.getOp()) {
        case DIVIDE:
          break;
        case MULTIPLY:
          bterm &= getBoolTerm(t.getTerm());
          break;
      }
    }
    return bterm;
  }
}
