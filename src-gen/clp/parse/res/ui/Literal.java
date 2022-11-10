package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Literal extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.Literal literal = new clp.run.res.ui.Literal();

  public clp.run.res.ui.Literal getLiteral() {
    return literal;
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
    literal.setValue(parser.sval);
    
    buffer.append( literal.getValue() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
