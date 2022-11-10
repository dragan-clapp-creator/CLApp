package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.msc.ClassReference;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Encryption extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Encryption encryption = new clp.run.res.Encryption();

  public clp.run.res.Encryption getEncryption() {
    return encryption;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("encryptFile", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("encryptFile ");
    

      ClassReference clazz1 = new ClassReference();
        Boolean bclazz1 = clazz1.parse(parser, isOptional);

        if (bclazz1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( clazz1.getRendering() + " " );
        encryption.setClazz(clazz1.getClassReference());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
