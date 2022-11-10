package clp.parse.cel.graph;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ParseObject extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.ParseObject parseobject = new clp.run.cel.graph.ParseObject();

  public clp.run.cel.graph.ParseObject getParseObject() {
    return parseobject;
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
      parseobject.setString(parser.sval);
      buffer.append( parseobject.getString() + " " );
      return isOk != Boolean.FALSE; 
    }
    else {
      parser.pushBack();
    }
    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD) {
      parseobject.setVariable(parser.sval);
      buffer.append( parseobject.getVariable() + " " );
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
