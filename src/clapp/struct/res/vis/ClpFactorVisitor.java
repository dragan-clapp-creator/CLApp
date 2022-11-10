package clapp.struct.res.vis;

import java.util.ArrayList;

import clp.run.cel.exp.ArrayFactor;
import clp.run.cel.exp.FactorVisitor;
import clp.run.cel.exp.SimpleFactor;

public class ClpFactorVisitor implements FactorVisitor {

  @Override
  public void visitArrayFactor(ArrayFactor x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitSimpleFactor(SimpleFactor x) {
    // TODO Auto-generated method stub
    
  }

  public boolean isArray() {
    // TODO Auto-generated method stub
    return false;
  }

  public void setValues(ArrayList avalues) {
    // TODO Auto-generated method stub
    
  }

  public void setValue(String svalue) {
    // TODO Auto-generated method stub
    
  }

  public boolean isStrConst() {
    // TODO Auto-generated method stub
    return false;
  }

  public Object[] getValues() {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean isNumConst() {
    // TODO Auto-generated method stub
    return false;
  }

  public Object getValue() {
    // TODO Auto-generated method stub
    return null;
  }

  public String getIvalue() {
    // TODO Auto-generated method stub
    return null;
  }

}
