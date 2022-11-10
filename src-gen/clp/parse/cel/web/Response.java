package clp.parse.cel.web;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Response extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.web.Response response = new clp.run.cel.web.Response();

  public clp.run.cel.web.Response getResponse() {
    return response;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    isOk = plusBlock(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return parser.errorLog(false);
    }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean plusBlock(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (token == '\"') {
      response.setRespString(parser.sval);
      buffer.append( response.getRespString() + " " );
      return isOk != Boolean.FALSE; 
    }
    else {
      parser.pushBack();
    }
    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD) {
      response.setVariable(parser.sval);
      buffer.append( response.getVariable() + " " );
      return true; 
    }
    else {
      parser.pushBack();
    }

    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
