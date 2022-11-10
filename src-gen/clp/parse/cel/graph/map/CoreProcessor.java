package clp.parse.cel.graph.map;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CoreProcessor extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.CoreProcessor coreprocessor = new clp.run.cel.graph.map.CoreProcessor();

  public clp.run.cel.graph.map.CoreProcessor getCoreProcessor() {
    return coreprocessor;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("CORE", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("CORE ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    coreprocessor.setGnode(parser.sval);
    
    buffer.append( coreprocessor.getGnode() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
