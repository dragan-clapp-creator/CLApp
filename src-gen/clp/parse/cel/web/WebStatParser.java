package clp.parse.cel.web;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class WebStatParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.web.WebStatement webstatement = new clp.run.cel.web.WebStatement();

  public clp.run.cel.web.WebStatement getWebStatement() {
    return webstatement;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("send", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("send ");
    

      SendItems sendItems1 = new SendItems();
      isOk = sendItems1.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        webstatement.setSendItems(sendItems1.getSendItems());
        webstatement.setIsSendItems(true);
        buffer.append( sendItems1.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    isOk = match("using", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("using ");
    

      isOk = hutBlock3(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    webstatement.setWebVarName(parser.sval);
    
    buffer.append( webstatement.getWebVarName() + " " );

      isOk = hutBlock5(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

      isOk = hutBlock6(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match('>', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "> " );
    webstatement.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      webstatement.setRes(parser.sval);
      webstatement.setIsRes(true);
      buffer.append( webstatement.getRes() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }

  private Boolean hutBlock5(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match('(', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "( " );
    webstatement.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_NUMBER || token == '\"') {
      webstatement.setTimeout((int) parser.nval);
      webstatement.setIsTimeout(true);
      buffer.append( webstatement.getTimeout() + " " );
    }
    else {
      parser.pushBack();
    }
    token = parser.nextToken();
    isOk = match(')', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ") " );
    webstatement.setChar((char)token);


    return true;
  }

  private Boolean hutBlock6(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match("receiving", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("receiving ");
    webstatement.setIsReceiving(true);

      Response response1 = new Response();
      isOk = response1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      webstatement.setResponse(response1.getResponse());
      webstatement.setIsResponse(true);
      buffer.append( response1.getRendering()+" " );


    return true;
  }


}
