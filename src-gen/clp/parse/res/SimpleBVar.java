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
public class SimpleBVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleBVar simplebvar = new clp.run.res.SimpleBVar();

  public clp.run.res.SimpleBVar getSimpleBVar() {
    return simplebvar;
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
    simplebvar.setName(parser.sval);
    
    buffer.append( simplebvar.getName() + " " );

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

     token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "= " );
    simplebvar.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      simplebvar.setInitial(parser.sval.equalsIgnoreCase("true"));
      simplebvar.setIsInitial(true);
      buffer.append( simplebvar.getInitial() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }


}
