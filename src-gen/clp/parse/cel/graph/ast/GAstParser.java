package clp.parse.cel.graph.ast;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class GAstParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.graph.map.GAssistent gassistent = new clp.run.cel.graph.map.GAssistent();

  public clp.run.cel.graph.map.GAssistent getGAssistent() {
    return gassistent;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("assist", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("assist ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    gassistent.setGname1(parser.sval);
    
    buffer.append( gassistent.getGname1() + " " );

    token = parser.nextToken();
    isOk = match("with", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("with ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    gassistent.setGname2(parser.sval);
    
    buffer.append( gassistent.getGname2() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
