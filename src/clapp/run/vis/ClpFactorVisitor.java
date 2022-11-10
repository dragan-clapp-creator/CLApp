package clapp.run.vis;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import clapp.run.util.ResourceUtility;
import clp.run.cel.exp.ArrayFactor;
import clp.run.cel.exp.FactorVisitor;
import clp.run.cel.exp.SimpleFactor;
import clp.run.res.Resources;
import clp.run.res.Variable;

public class ClpFactorVisitor implements FactorVisitor {

  public static final int PLUS  =  1;
  public static final int MINUS = -1;

  private ClpSimpleFactorVisitor vis;
  private boolean isArray;
  @SuppressWarnings("rawtypes")
  private ArrayList avalues;
  private Resources res;
  private Variable var;
  private Date date;
  private Time time;
  private int ival;


  public ClpFactorVisitor(Resources res) {
    super();
    this.res = res;
  }

  @SuppressWarnings("unchecked")
  public void visitArrayFactor(ArrayFactor af) {
    isArray = true;
    avalues = new ArrayList<>();
    for (SimpleFactor f : af.getSimpleFactors()) {
      ClpSimpleFactorVisitor v = new ClpSimpleFactorVisitor();
      f.getSfactor().accept(v);
      if (v.isConst()) {
        avalues.add(v.getSconst());
      }
      else if (v.isInt()) {
        avalues.add(new Integer(v.getIval()));
      }
    }
  }

  @Override
  public void visitSimpleFactor(SimpleFactor f) {
    vis = new ClpSimpleFactorVisitor();
    f.getSfactor().accept(vis);
    if (vis.isVariableName()) {
      String name = vis.getSvar();
      var = ResourceUtility.getInstance().getVariable(res, name);
    }
    else if (vis.isDate()) {
      date = vis.getDval();
    }
    else if (vis.isTime()) {
      time = vis.getTval();
    }
    else if (vis.isInt()) {
      ival = vis.getIval();
    }
  }

  public boolean isArray() {
    return isArray;
  }

  public String[] getStringValues() {
    String[] s = new String[avalues.size()];
    for (int i=0; i<s.length; i++) {
      s[i] = (String) avalues.get(i);
    }
    return s;
  }

  public int[] getIntValues() {
    int[] s = new int[avalues.size()];
    for (int i=0; i<s.length; i++) {
      s[i] = (Integer) avalues.get(i);
    }
    return s;
  }

  public double[] getFloatValues() {
    double[] s = new double[avalues.size()];
    for (int i=0; i<s.length; i++) {
      s[i] = (Double) avalues.get(i);
    }
    return s;
  }

  public boolean isInt() {
    return vis.isInt();
  }

  public boolean isDate() {
    return vis.isDate();
  }

  public boolean isTime() {
    return vis.isTime();
  }

  public int getIntVal() {
    return vis.getIval();
  }

  public boolean isFloat() {
    return vis.isFloat();
  }

  public Variable getVar() {
    return var;
  }

  public String getVarVal() {
    return vis.getSvar();
  }

  public boolean isConst() {
    return vis.isConst();
  }

  public boolean isVar() {
    return vis.isVariableName();
  }

  public String getConstVal() {
    return vis.getSconst();
  }

  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * @return the time
   */
  public Time getTime() {
    return time;
  }

  /**
   * @return the ival
   */
  public int getIval() {
    return ival;
  }

  /**
   * @return the avalues
   */
  public ArrayList getAvalues() {
    return avalues;
  }
}
