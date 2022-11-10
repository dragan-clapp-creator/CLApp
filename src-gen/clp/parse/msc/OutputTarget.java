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
public class OutputTarget extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.OutputTarget outputtarget = new clp.run.msc.OutputTarget();

  public clp.run.msc.OutputTarget getOutputTarget() {
    return outputtarget;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    isOk = plusBlock(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return parser.errorLog(false);
    }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean plusBlock(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("CONSOLE", parser.sval);
    if (isOk == Boolean.TRUE) {
      outputtarget.setIsStringCONSOLE(true);
      outputtarget.setStringCONSOLE(parser.sval);
      buffer.append("CONSOLE ");
      return true;
    }
    else {
      parser.pushBack();
      isOk = true;
    }
    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD) {
      outputtarget.setName(parser.sval);
      buffer.append( outputtarget.getName() + " " );
      return true; 
    }
    else {
      parser.pushBack();
    }
    SendFile bSendFile = new SendFile();
    isOk = bSendFile.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      outputtarget.setSendFile(bSendFile.getSendFile());
      buffer.append( bSendFile.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
