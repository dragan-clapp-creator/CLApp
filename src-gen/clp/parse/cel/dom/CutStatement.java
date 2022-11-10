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
public class CutStatement extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.CutStatement cutstatement = new clp.run.cel.dom.CutStatement();

  public clp.run.cel.dom.CutStatement getCutStatement() {
    return cutstatement;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("cut", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("cut ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    cutstatement.setNode(parser.sval);
    
    buffer.append( cutstatement.getNode() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
