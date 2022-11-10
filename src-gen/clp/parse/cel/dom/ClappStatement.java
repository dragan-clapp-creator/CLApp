package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ClappStatement extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.ClappStatement clappstatement = new clp.run.cel.dom.ClappStatement();

  public clp.run.cel.dom.ClappStatement getClappStatement() {
    return clappstatement;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      ClappCommand clappCommand0 = new ClappCommand();
        Boolean bclappCommand0 = clappCommand0.parse(parser, isOptional);

        if (bclappCommand0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( clappCommand0.getRendering() + " " );
        clappstatement.setClappCommand(clappCommand0.getClappCommand());

    token = parser.nextToken();
    isOk = match("using", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("using ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    clappstatement.setWebName(parser.sval);
    
    buffer.append( clappstatement.getWebName() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
