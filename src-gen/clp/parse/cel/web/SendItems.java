package clp.parse.cel.web;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.msc.SendFile;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SendItems extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.web.SendItems senditems;

  public clp.run.cel.web.SendItems getSendItems() {
    return senditems;
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

    SendFile bSendFile = new SendFile();
    isOk = bSendFile.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      senditems = bSendFile.getSendFile();
      buffer.append( bSendFile.getRendering()+" " );
      return true;
    }

    SendStream bSendStream = new SendStream();
    isOk = bSendStream.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      senditems = bSendStream.getSendStream();
      buffer.append( bSendStream.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
