package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Decryption extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.Decryption decryption = new clp.run.msc.Decryption();

  public clp.run.msc.Decryption getDecryption() {
    return decryption;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("decryptFile", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("decryptFile ");
    

      ClassReference clazz1 = new ClassReference();
        Boolean bclazz1 = clazz1.parse(parser, isOptional);

        if (bclazz1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( clazz1.getRendering() + " " );
        decryption.setClazz(clazz1.getClassReference());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
