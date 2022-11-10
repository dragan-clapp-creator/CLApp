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
public class BInitSegment extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.BInitSegment binitsegment = new clp.run.res.BInitSegment();

  public clp.run.res.BInitSegment getBInitSegment() {
    return binitsegment;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match('[', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "[ " );

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    binitsegment.setInit1(parser.sval.equalsIgnoreCase("true"));
    
    buffer.append( binitsegment.getInit1() + " " );

    token = parser.nextToken();
    isOk = match("...", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("... ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    binitsegment.setInit2(parser.sval.equalsIgnoreCase("true"));
    
    buffer.append( binitsegment.getInit2() + " " );

    token = parser.nextToken();
    isOk = match(']', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "] " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
