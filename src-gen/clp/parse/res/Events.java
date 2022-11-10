package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Events extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Events events = new clp.run.res.Events();

  public clp.run.res.Events getEvents() {
    return events;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("events", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("events ");
    

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      Event event2 = new Event();
        Boolean bevent2 = event2.parse(parser, isOptional);

        if (bevent2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( event2.getRendering() + " " );
        events.setEvent(event2.getEvent());

      isOk = starBlock3(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean starBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock3(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    Event event = new Event();
    isOk = event.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    events.addEvent(event.getEvent());
    buffer.append( event.getRendering()+" " );


    return isOk;
  }


}
