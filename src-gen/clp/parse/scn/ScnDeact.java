package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnDeact extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.ScnDeact scndeact = new clp.run.scn.ScnDeact();

  public clp.run.scn.ScnDeact getScnDeact() {
    return scndeact;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("deactivation", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("deactivation ");
    

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      DeactType deactType2 = new DeactType();
        Boolean bdeactType2 = deactType2.parse(parser, isOptional);

        if (bdeactType2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( deactType2.getRendering() + " " );
        scndeact.setDeactType(deactType2.getDeactType());

    token = parser.nextToken();
    isOk = match(';', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "; " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
