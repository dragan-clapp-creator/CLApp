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
public class KeyRefInterface extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.KeyRefInterface keyrefinterface = new clp.run.res.KeyRefInterface();

  public clp.run.res.KeyRefInterface getKeyRefInterface() {
    return keyrefinterface;
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
      keyrefinterface.setLkey(parser.sval);
      buffer.append( keyrefinterface.getLkey() + " " );
      return isOk != Boolean.FALSE; 
    }
    else {
      parser.pushBack();
    }
    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD) {
      keyrefinterface.setKeyName(parser.sval);
      buffer.append( keyrefinterface.getKeyName() + " " );
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
