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
public class GlobalAttribute extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.GlobalAttribute globalattribute = new clp.run.res.weave.GlobalAttribute();

  public clp.run.res.weave.GlobalAttribute getGlobalAttribute() {
    return globalattribute;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("GLOB", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("GLOB ");
    

      isOk = hutBlock1(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ": " );

    token = parser.nextToken();
    if (token != '\"') {
      return parser.errorLog(false);
    }
    isOk = true;
    globalattribute.setAttribute(parser.sval);
    
    buffer.append( globalattribute.getAttribute() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock1(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD && token != '\"') { return parser.errorLog(true); }
    globalattribute.setType(parser.sval);
    globalattribute.setIsType(true);
    buffer.append( globalattribute.getType() + " " );


    return true;
  }


}
