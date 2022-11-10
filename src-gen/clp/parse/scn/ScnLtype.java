package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnLtype extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.ScnLtype scnltype = new clp.run.scn.ScnLtype();

  public clp.run.scn.ScnLtype getScnLtype() {
    return scnltype;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("type", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("type ");
    

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      Ltype ltype2 = new Ltype();
        Boolean bltype2 = ltype2.parse(parser, isOptional);

        if (bltype2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( ltype2.getRendering() + " " );
        scnltype.setLtype(ltype2.getLtype());

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
