package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Notification extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.Notification notification = new clp.run.res.weave.Notification();

  public clp.run.res.weave.Notification getNotification() {
    return notification;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("notify", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("notify ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    notification.setClappVariable(parser.sval);
    
    buffer.append( notification.getClappVariable() + " " );

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
