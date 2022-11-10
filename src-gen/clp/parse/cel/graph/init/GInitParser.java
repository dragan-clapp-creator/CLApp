package clp.parse.cel.graph.init;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GInitParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.init.GInit ginit = new clp.run.cel.graph.init.GInit();

  public clp.run.cel.graph.init.GInit getGInit() {
    return ginit;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("reinitialize", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("reinitialize ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    ginit.setGname(parser.sval);
    
    buffer.append( ginit.getGname() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
