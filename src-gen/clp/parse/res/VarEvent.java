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
public class VarEvent extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.VarEvent varevent = new clp.run.res.VarEvent();

  public clp.run.res.VarEvent getVarEvent() {
    return varevent;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("VAR", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("VAR ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    varevent.setName(parser.sval);
    
    buffer.append( varevent.getName() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
