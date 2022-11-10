package clp.parse.cel.web;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SendStream extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.web.SendStream sendstream = new clp.run.cel.web.SendStream();

  public clp.run.cel.web.SendStream getSendStream() {
    return sendstream;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      isOk = hutBlock0(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

      SendObject sendObject1 = new SendObject();
        Boolean bsendObject1 = sendObject1.parse(parser, isOptional);

        if (bsendObject1 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( sendObject1.getRendering() + " " );
        sendstream.setSendObject(sendObject1.getSendObject());

      isOk = starBlock2(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock0(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match("STREAM", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("STREAM ");
    sendstream.setIsSTREAM(true);


    return true;
  }

  private Boolean starBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock2(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    SendObject sendObject = new SendObject();
    isOk = sendObject.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    sendstream.addSendObject(sendObject.getSendObject());
    buffer.append( sendObject.getRendering()+" " );


    return isOk;
  }


}
