package clp.parse.cel.asm;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Ifclause extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.asm.Ifclause ifclause = new clp.run.cel.asm.Ifclause();

  public clp.run.cel.asm.Ifclause getIfclause() {
    return ifclause;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("if", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("if ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    ifclause.setVar(parser.sval);
    
    buffer.append( ifclause.getVar() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
