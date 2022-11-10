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
public class DInit extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.DInit dinit = new clp.run.res.DInit();

  public clp.run.res.DInit getDInit() {
    return dinit;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (token != '\"') {
      return parser.errorLog(true);
    }
    isOk = true;
    dinit.setInitial(parser.sval);
    
    buffer.append( dinit.getInitial() + " " );

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
    isOk = match("match", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("match ");
    dinit.setIsMatch(true);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      dinit.setPattern(parser.sval);
      dinit.setIsPattern(true);
      buffer.append( dinit.getPattern() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }


}
