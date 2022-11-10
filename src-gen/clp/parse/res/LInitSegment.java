package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LInitSegment extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.LInitSegment linitsegment = new clp.run.res.LInitSegment();

  public clp.run.res.LInitSegment getLInitSegment() {
    return linitsegment;
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

      Lsigned init11 = new Lsigned();
        Boolean binit11 = init11.parse(parser, isOptional);

        if (binit11 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( init11.getRendering() + " " );
        linitsegment.setInit1(init11.getLsigned());

    token = parser.nextToken();
    isOk = match("...", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("... ");
    

      Lsigned init23 = new Lsigned();
        Boolean binit23 = init23.parse(parser, isOptional);

        if (binit23 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( init23.getRendering() + " " );
        linitsegment.setInit2(init23.getLsigned());

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
