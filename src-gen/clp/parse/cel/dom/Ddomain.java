package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Ddomain extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.Ddomain ddomain = new clp.run.cel.dom.Ddomain();

  public clp.run.cel.dom.Ddomain getDdomain() {
    return ddomain;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("DD", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("DD ");
    

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      DeactivationDomain dd2 = new DeactivationDomain();
        Boolean bdd2 = dd2.parse(parser, isOptional);

        if (bdd2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( dd2.getRendering() + " " );
        ddomain.setDd(dd2.getDeactivationDomain());

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
