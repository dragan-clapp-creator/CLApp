package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Xdomain extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.Xdomain xdomain = new clp.run.cel.dom.Xdomain();

  public clp.run.cel.dom.Xdomain getXdomain() {
    return xdomain;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("XD", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("XD ");
    

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      ExecutionDomain xd2 = new ExecutionDomain();
        Boolean bxd2 = xd2.parse(parser, isOptional);

        if (bxd2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( xd2.getRendering() + " " );
        xdomain.setXd(xd2.getExecutionDomain());

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
