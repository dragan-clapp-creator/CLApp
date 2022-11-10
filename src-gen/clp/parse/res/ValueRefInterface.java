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
public class ValueRefInterface extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ValueRefInterface valuerefinterface = new clp.run.res.ValueRefInterface();

  public clp.run.res.ValueRefInterface getValueRefInterface() {
    return valuerefinterface;
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
      valuerefinterface.setLvalue(parser.sval);
      buffer.append( valuerefinterface.getLvalue() + " " );
      return isOk != Boolean.FALSE; 
    }
    else {
      parser.pushBack();
    }
    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD) {
      valuerefinterface.setValueName(parser.sval);
      buffer.append( valuerefinterface.getValueName() + " " );
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
