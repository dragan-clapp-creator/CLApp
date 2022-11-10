package clp.parse.cel.exp;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.TermOperator;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ExpParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.Expression expression = new clp.run.cel.exp.Expression();

  public clp.run.cel.exp.Expression getExpression() {
    return expression;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      TermParser term0 = new TermParser();
        Boolean bterm0 = term0.parse(parser, isOptional);

        if (bterm0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( term0.getRendering() + " " );
        expression.setTerm(term0.getTerm());

      isOk = hutBlock1(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock1(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

       TermOperator op0 = new TermOperator();
      isOk = op0.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
      expression.setOp(op0.getTermOperator());
      expression.setIsTermOperator(true);
      buffer.append( op0.getRendering()+" " );

      ExpParser expression1 = new ExpParser();
      isOk = expression1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      expression.setExpression(expression1.getExpression());
      expression.setIsExpression(true);
      buffer.append( expression1.getRendering()+" " );


    return true;
  }


}
