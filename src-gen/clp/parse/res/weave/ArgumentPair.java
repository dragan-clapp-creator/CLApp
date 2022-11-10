package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgumentPair extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.ArgumentPair argumentpair = new clp.run.res.weave.ArgumentPair();

  public clp.run.res.weave.ArgumentPair getArgumentPair() {
    return argumentpair;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      ArgumentSum argumentSum0 = new ArgumentSum();
        Boolean bargumentSum0 = argumentSum0.parse(parser, isOptional);

        if (bargumentSum0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( argumentSum0.getRendering() + " " );
        argumentpair.setArgumentSum(argumentSum0.getArgumentSum());

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
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ": " );
    argumentpair.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      argumentpair.setType(parser.sval);
      argumentpair.setIsType(true);
      buffer.append( argumentpair.getType() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }


}
