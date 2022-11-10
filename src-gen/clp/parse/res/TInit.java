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
public class TInit extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.TInit tinit = new clp.run.res.TInit();

  public clp.run.res.TInit getTInit() {
    return tinit;
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
    tinit.setInitial(parser.sval);
    
    buffer.append( tinit.getInitial() + " " );

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
    tinit.setIsMatch(true);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      tinit.setPattern(parser.sval);
      tinit.setIsPattern(true);
      buffer.append( tinit.getPattern() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }


}
