package clapp.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.JavaInvoker;


public class GraphExpressionEvaluator {

  static private HashMap<String, ArrayList<Object>> renderer = new HashMap<String, ArrayList<Object>>();
  private int index;
  private ArrayList<Object> buffer;
  private JavaInvoker ji;
  private Object dc;
  private Object owner;

  enum GraphTermOperator {
    PLUS('+'), MINUS('-'), NONE('.');
    private char op;

    private GraphTermOperator(char c) {
      op = c;
    }
    char getOp() {
      return op;
    }
  }

  enum GraphFactOperator {
    MUL('*'), DIV('/'), NONE('.');
    private char op;

    private GraphFactOperator(char c) {
      op = c;
    }
    char getOp() {
      return op;
    }
  }

  enum ExpoOperator {
    EXP('^'), NONE('.');
    private char op;

    private ExpoOperator(char c) {
      op = c;
    }
    char getOp() {
      return op;
    }
  }

  public GraphExpressionEvaluator(Object rtobj, ArrayList<Object> b, JavaInvoker ji, Object dc) {
    owner = rtobj;
    buffer = b;
    for (int i=0; i<buffer.size(); i++) {
      Object o = buffer.get(i);
      if (o instanceof String) {
        if (((String)o).length() == 1) {
          buffer.set(i, new Character(((String)o).charAt(0)));
        }
      }
    }
    renderer.put(owner.getClass().getSimpleName(), buffer);
    this.ji = ji;
    this.dc = dc;
  }

  static public void clearRenderer() {
    renderer.clear();
  }

  static public boolean isRTRendering() {
    return !renderer.isEmpty();
  }

  static public StringBuffer getRendering(String iTypeName) {
    ArrayList<Object> buffer = renderer.get(iTypeName);
    if (buffer != null && !buffer.isEmpty()) {
      StringBuffer sb = new StringBuffer();
      Extractor extractor = new ExpressionExtractor(null, null, null);
      for (Object obj : buffer) {
        if (obj instanceof Character || obj instanceof String) {
          sb.append(" "+obj+" ");
        }
        else {
          Object val = extractor.extractValue(obj);
          if (val instanceof Integer && ((Integer)val) < 0) {
            sb.append( "("+val+")" );
          }
          else if (val instanceof Long && ((Long)val) < 0) {
            sb.append( "("+val+")" );
          }
          else if (val instanceof Float && ((Float)val) < 0) {
            sb.append( "("+val+")" );
          }
          else if (val instanceof Double && ((Double)val) < 0) {
            sb.append( "("+val+")" );
          }
          else {
            sb.append( val );
          }
        }
      }
      return sb;
    }
    return null;
  }

  /**
   * entry point: will evaluate the expresion by parsing the buffer content
   * 
   */
  public double expression() {
    double exp = getTerm();
    if (index < buffer.size()-1) {
      switch (getTermOperator()) {
        case MINUS:
          exp -= expression();
          break;
        case PLUS:
          exp += expression();
          break;
        case NONE:
          return exp;
      }
    }
    return exp;
  }

  private GraphTermOperator getTermOperator() {
    Object c = buffer.get(index);
    if (c instanceof Character) {
      if (((Character)c).charValue() == '+') {
        index++;
        return GraphTermOperator.PLUS;
      }
      if (((Character)c).charValue() == '-') {
        index++;
        return GraphTermOperator.MINUS;
      }
    }
    return GraphTermOperator.NONE;
  }

  private double getTerm() {
    double exp = getFactor();
    if (index < buffer.size()-1) {
      switch (getFactOperator()) {
        case DIV:
          double div = getTerm();
          if (div == 0) {
            ConsoleProvider.getInstance().eprint("try to divide by 0");
          }
          else {
            exp /= div;
          }
          break;
        case MUL:
          exp *= getTerm();
          break;
        case NONE:
          return exp;
      }
    }
    return exp;
  }

  private GraphFactOperator getFactOperator() {
    Object c = buffer.get(index);
    if (c instanceof Character) {
      if (((Character)c).charValue() == '*') {
        index++;
        return GraphFactOperator.MUL;
      }
      if (((Character)c).charValue() == '/') {
        index++;
        return GraphFactOperator.DIV;
      }
    }
    return GraphFactOperator.NONE;
  }

  private double getFactor() {
    double exp = 0;
    Object obj = buffer.get(index++);
    if (obj instanceof Character) {
      char c = ((Character)obj).charValue();
      if (c == '+') {
        return getFactor();
      }
      if (c == '-') {
        return - getFactor();
      }
    }
    else {
      exp = newExtraction(obj);
      switch (getExpoOperator()) {
        case EXP:
          Object num = buffer.get(index++);
          if (num instanceof Integer) {
            for (int i=0; i<(Integer)num-1; i++) {
              exp *= exp;
            }
          }
          break;
      }
    }
    return exp;
  }

  private double newExtraction(Object obj) {
    double exp = 0;
    ExpressionExtractor extractor = new ExpressionExtractor(owner, ji, dc);
    boolean b = extractor.extract( obj );
    if (b) {
      exp = extractor.getExpression();
    }
    else {
      Object val = extractor.extractValue( obj );
      if (val instanceof Number) {
        exp = ((Number)val).doubleValue();
      }
      else if (val instanceof String) {
        exp = extractValueUsingPattern((String)val);
      }
    }
    return exp;
  }

  /**
   * the pattern syntax is:
   *    "<M>-<R>-<A>:<class name>#<method>"
   * whereas
   *  <M> is one of the following modifiers
   *    S static
   *    V virtual (non static)
   *  <R> is the return type (may be V for void)
   *  <A> is the arguments set (coma separated)
   * Both <R> & <A> are combinations out of the following basic types:
   *    B byte signed byte
   *    C char character
   *    D double double precision IEEE float
   *    F float single precision IEEE float
   *    I int integer
   *    J long long integer
   *    L; ... an object of the given class (ex. "Ljava/lang/System;")
   *    S short signed short
   *    Z boolean true or false
   *    [ ... array (ex. "[Ljava/lang/String;")
   * @param pattern
   */
  private double extractValueUsingPattern(String pattern) {
    if (index < buffer.size()-1) {
      Object c = buffer.get(index++);
      if (c instanceof Character) {
        if (((Character)c).charValue() == '(') {
          StringTokenizer st = new StringTokenizer(pattern, "-:#");
          String modif = st.nextToken();
          String ret = st.nextToken();
          String arg = st.nextToken();
          String cl = st.nextToken();
          String meth = st.nextToken();
          int nb = arg.length();
          Double[] args = new Double[nb];
          for (int i=0; i<nb; i++) {
            Object obj = buffer.get(index++);
            args[i] = newExtraction(obj);
            if (nb > 1) {
              c = buffer.get(index++);
              if (!(c instanceof Character) ||
                  ((Character)c).charValue() != ',') {
                ConsoleProvider.getInstance().eprint("wrong syntax at "+index+" on "+buffer);
              }
            }
          }
          c = buffer.get(index++);
          if (!(c instanceof Character) ||
              ((Character)c).charValue() != ')') {
            ConsoleProvider.getInstance().eprint("wrong syntax at "+index+" on "+buffer);
          }
          else {
            PatternInterpreter pati = new PatternInterpreter.Build()
              .setClassName(cl)
              .setMethodName(meth)
              .setModifier(modif)
              .setTypes(arg)
              .setReturnType(ret)
              .setArguments(args)
              .getInstance();
            return pati.calculate();
          }
        }
      }
    }
    return 0;
    
  }

  private ExpoOperator getExpoOperator() {
    if (index < buffer.size()-1) {
      Object c = buffer.get(index);
      if (c instanceof Character) {
        if (((Character)c).charValue() == '^') {
          index++;
          return ExpoOperator.EXP;
        }
      }
    }
    return ExpoOperator.NONE;
  }
}
