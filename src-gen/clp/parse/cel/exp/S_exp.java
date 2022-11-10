package clp.parse.cel.exp;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class S_exp extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.S_exp s_exp = new clp.run.cel.exp.S_exp();

  public clp.run.cel.exp.S_exp getS_exp() {
    return s_exp;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match('(', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "( " );

      ExpParser expression1 = new ExpParser();
        Boolean bexpression1 = expression1.parse(parser, isOptional);

        if (bexpression1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( expression1.getRendering() + " " );
        s_exp.setExpression(expression1.getExpression());

    token = parser.nextToken();
    isOk = match(')', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ") " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
