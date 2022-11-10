package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class AtLocation extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.AtLocation atlocation = new clp.run.res.weave.AtLocation();

  public clp.run.res.weave.AtLocation getAtLocation() {
    return atlocation;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("at", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("at ");
    

      Place place1 = new Place();
        Boolean bplace1 = place1.parse(parser, isOptional);

        if (bplace1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( place1.getRendering() + " " );
        atlocation.setPlace(place1.getPlace());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
