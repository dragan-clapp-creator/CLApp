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
public class LoadMarks extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.LoadMarks loadmarks = new clp.run.cel.dom.LoadMarks();

  public clp.run.cel.dom.LoadMarks getLoadMarks() {
    return loadmarks;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("loadMarks", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("loadMarks ");
    

    token = parser.nextToken();
    isOk = match('(', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "( " );

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    loadmarks.setName(parser.sval);
    
    buffer.append( loadmarks.getName() + " " );

    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ", " );

    token = parser.nextToken();
    if (token != '\"') {
      return parser.errorLog(false);
    }
    isOk = true;
    loadmarks.setMarks(parser.sval);
    
    buffer.append( loadmarks.getMarks() + " " );

    token = parser.nextToken();
    isOk = match(')', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ") " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
