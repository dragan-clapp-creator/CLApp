package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgListCount extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.ArgListCount arglistcount = new clp.run.res.weave.ArgListCount();

  public clp.run.res.weave.ArgListCount getArgListCount() {
    return arglistcount;
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
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(false);
    }
    isOk = true;
    arglistcount.setCount((int) parser.nval);
    
    buffer.append( arglistcount.getCount() + " " );

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
