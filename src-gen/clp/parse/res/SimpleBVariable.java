package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleBVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleBVariable simplebvariable = new clp.run.res.SimpleBVariable();

  public clp.run.res.SimpleBVariable getSimpleBVariable() {
    return simplebvariable;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(true);
    }
    isOk = true;
    simplebvariable.setName(parser.sval);
    
    buffer.append( simplebvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    simplebvariable.setInitial(parser.sval.equalsIgnoreCase("true"));
    
    buffer.append( simplebvariable.getInitial() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
