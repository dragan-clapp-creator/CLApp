package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Port extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.Port port = new clp.run.msc.Port();

  public clp.run.msc.Port getPort() {
    return port;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("port", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("port ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(false);
    }
    isOk = true;
    port.setNum((int) parser.nval);
    
    buffer.append( port.getNum() + " " );

      Decryption decryption2 = new Decryption();
      isOk = decryption2.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        port.setDecryption(decryption2.getDecryption());
        port.setIsDecryption(true);
        buffer.append( decryption2.getRendering()+" " );
      }
      isOk = true;

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
