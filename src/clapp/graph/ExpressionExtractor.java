package clapp.graph;

import java.lang.reflect.Field;
import java.util.ArrayList;

import clapp.run.util.JavaInvoker;


public class ExpressionExtractor extends Extractor {

  private Object owner;
  private double exp;

  public ExpressionExtractor(Object rtobj, JavaInvoker ji, Object dc) {
    super(ji, dc);
    owner = rtobj;
  }

  public double getExpression() {
    return exp;
  }

  @Override
  public void handleEvaluation(Object rtobj, ArrayList<Object> buf, JavaInvoker ji, Object dc) {
    exp = new GraphExpressionEvaluator(rtobj, buf, ji, dc).expression();
    String name = findName( rtobj.getClass().getSimpleName() );
    addResult(ji, dc, name, exp);
  }

  private String findName(String simpleName) {
    if (owner != null) {
      Field[] fields = owner.getClass().getDeclaredFields();
      for (Field field : fields) {
        if (field.getName().equalsIgnoreCase(simpleName)) {
          return field.getName();
        }
      }
    }
    return simpleName;
  }

  private void addResult(JavaInvoker ji, Object dc, String name, Object val) {
    Class<?>[] argTypes = new Class[2];
    argTypes[0] = String.class;
    argTypes[1] = Object.class;
    Object[] args = new Object[2];
    args[0] = name;
    args[1] = val;
    ji.callMethod(dc, "put", argTypes, args);
  }
}
