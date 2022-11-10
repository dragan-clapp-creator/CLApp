package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Adomain extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.Adomain adomain = new clp.run.cel.dom.Adomain();

  public clp.run.cel.dom.Adomain getAdomain() {
    return adomain;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("AD", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("AD ");
    

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      ActivationDomain ad2 = new ActivationDomain();
        Boolean bad2 = ad2.parse(parser, isOptional);

        if (bad2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( ad2.getRendering() + " " );
        adomain.setAd(ad2.getActivationDomain());

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
