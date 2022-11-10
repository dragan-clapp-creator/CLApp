package clapp.struct.util;

import clp.run.cel.exp.ArrayFactor;
import clp.run.cel.exp.FactorVisitor;
import clp.run.cel.exp.S_constant;
import clp.run.cel.exp.S_dval;
import clp.run.cel.exp.S_exp;
import clp.run.cel.exp.S_ival;
import clp.run.cel.exp.S_tval;
import clp.run.cel.exp.S_var;
import clp.run.cel.exp.SfactorVisitor;
import clp.run.cel.exp.SimpleFactor;
import clp.run.res.TermOperator;

public class RFactorVisitor implements FactorVisitor {

  String str = "";
  private ReflectUtility ru;

  public RFactorVisitor() {
    ru = ReflectUtility.getInstance();
  }

  @Override
  public void visitSimpleFactor(SimpleFactor x) {

    SfactorVisitor sfvis = new SfactorVisitor() {
      @Override
      public void visitS_var(S_var x) {
        str += x.getVar();
      }
      @Override
      public void visitS_ival(S_ival x) {
        TermOperator op = x.getTermOperator();
        if (op != null && op.getVal().equalsIgnoreCase("-")) {
          str += "-" + x.getIval();
        }
        else {
          str += x.getIval();
        }
      }
      @Override
      public void visitS_exp(S_exp x) {
        str += "(" + ru.getExpression( x.getExpression() ) + ")";
      }
      @Override
      public void visitS_constant(S_constant x) {
        str += "\"" + x.getConstant() + "\"";
      }
      @Override
      public void visitS_dval(S_dval x) {
        str += x.getDay() + "/" + x.getMonth() + "/" + x.getYear();
      }
      @Override
      public void visitS_tval(S_tval x) {
        str += x.getHour() + ":" + x.getMin() + ":" + x.getSec();
      }
    };
    x.getSfactor().accept(sfvis);
  }

  @Override
  public void visitArrayFactor(ArrayFactor x) {
  }

  String getString() {
    return str;
  }
}
