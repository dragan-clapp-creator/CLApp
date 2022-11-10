package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IInitSegment extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.IInitSegment iinitsegment = new clp.run.res.IInitSegment();

  public clp.run.res.IInitSegment getIInitSegment() {
    return iinitsegment;
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

      Isigned init11 = new Isigned();
        Boolean binit11 = init11.parse(parser, isOptional);

        if (binit11 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( init11.getRendering() + " " );
        iinitsegment.setInit1(init11.getIsigned());

    token = parser.nextToken();
    isOk = match("...", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("... ");
    

      Isigned init23 = new Isigned();
        Boolean binit23 = init23.parse(parser, isOptional);

        if (binit23 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( init23.getRendering() + " " );
        iinitsegment.setInit2(init23.getIsigned());

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
