package clapp.run.vis;

import java.sql.Time;
import java.sql.Date;

import clp.run.cel.exp.Expression;
import clp.run.cel.exp.S_constant;
import clp.run.cel.exp.S_dval;
import clp.run.cel.exp.S_exp;
import clp.run.cel.exp.S_ival;
import clp.run.cel.exp.S_tval;
import clp.run.cel.exp.S_var;
import clp.run.cel.exp.SfactorVisitor;
import clp.run.res.TermOperator;

public class ClpSimpleFactorVisitor implements SfactorVisitor {

  private int ival;
  private double fval;
  private Date dval;
  private Time tval;
  private boolean isInt;
  private boolean isDate;
  private boolean isTime;
  private String sconst;
  private boolean isConst;
  private String svar;
  private boolean isVariableName;

  public ClpSimpleFactorVisitor() {
  }

  public boolean isFloat() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void visitS_dval(S_dval x) {
    dval = new Date(x.getYear()-1900, x.getMonth()-1, x.getDay());
    isDate = true;
  }

  @Override
  public void visitS_tval(S_tval x) {
    tval = new Time(x.getHour(), x.getMin(), x.getSec());
    isTime = true;
  }

  @Override
  public void visitS_ival(S_ival x) {
    TermOperator op = x.getTermOperator();
    if (op != null && op.getVal().equalsIgnoreCase("-")) {
      ival = - x.getIval();
    }
    else {
      ival = x.getIval();
    }
    isInt = true;
  }

  @Override
  public void visitS_constant(S_constant x) {
    sconst = x.getConstant();
    isConst = true;
  }

  @Override
  public void visitS_var(S_var x) {
    svar = x.getVar();
    isVariableName = true;
  }

  @Override
  public void visitS_exp(S_exp x) {
    Expression exp = x.getExpression();
    if (exp != null && exp.isTermOperator()) {
      // TODO
    }
  }

  public int getIval() {
    return ival;
  }

  public void setIval(int ival) {
    this.ival = ival;
  }

  public boolean isInt() {
    return isInt;
  }

  public void setIsInt(boolean isInt) {
    this.isInt = isInt;
  }

  public String getSconst() {
    return sconst;
  }

  public void setSconst(String sconst) {
    this.sconst = sconst;
  }

  public boolean isConst() {
    return isConst;
  }

  public void setIsConst(boolean isConst) {
    this.isConst = isConst;
  }

  public String getSvar() {
    return svar;
  }

  public void setSvar(String svar) {
    this.svar = svar;
  }

  public boolean isVariableName() {
    return isVariableName;
  }

  public void setIsVariableName(boolean isString) {
    this.isVariableName = isString;
  }

  /**
   * @return the fval
   */
  public double getFval() {
    return fval;
  }

  /**
   * @return the dval
   */
  public Date getDval() {
    return dval;
  }

  /**
   * @return the tval
   */
  public Time getTval() {
    return tval;
  }

  /**
   * @return the isDate
   */
  public boolean isDate() {
    return isDate;
  }

  /**
   * @return the isTime
   */
  public boolean isTime() {
    return isTime;
  }
}
