package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class InitHash extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.InitHash inithash = new clp.run.res.InitHash();

  public clp.run.res.InitHash getInitHash() {
    return inithash;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      KeyRefInterface keyRefInterface0 = new KeyRefInterface();
        Boolean bkeyRefInterface0 = keyRefInterface0.parse(parser, isOptional);

        if (bkeyRefInterface0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( keyRefInterface0.getRendering() + " " );
        inithash.setKeyRefInterface(keyRefInterface0.getKeyRefInterface());

    token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ": " );

      ValueRefInterface valueRefInterface2 = new ValueRefInterface();
        Boolean bvalueRefInterface2 = valueRefInterface2.parse(parser, isOptional);

        if (bvalueRefInterface2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( valueRefInterface2.getRendering() + " " );
        inithash.setValueRefInterface(valueRefInterface2.getValueRefInterface());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
