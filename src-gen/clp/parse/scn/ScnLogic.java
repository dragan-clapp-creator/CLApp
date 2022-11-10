package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnLogic extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.ScnLogic scnlogic = new clp.run.scn.ScnLogic();

  public clp.run.scn.ScnLogic getScnLogic() {
    return scnlogic;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("logic", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("logic ");
    

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      ScnLogBody scnLogBody2 = new ScnLogBody();
        Boolean bscnLogBody2 = scnLogBody2.parse(parser, isOptional);

        if (bscnLogBody2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( scnLogBody2.getRendering() + " " );
        scnlogic.setScnLogBody(scnLogBody2.getScnLogBody());

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
