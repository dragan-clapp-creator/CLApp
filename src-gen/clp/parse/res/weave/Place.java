package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Place extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.Place place;

  public clp.run.res.weave.Place getPlace() {
    return place;
  }

  public void setPlace(clp.run.res.weave.Place ref) {
    place = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (place == null) {
      clp.run.res.weave.Place[] values = clp.run.res.weave.Place.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.weave.Place placeResult = values[i];
        if (match(placeResult.getVal(), parser.sval)) {
          place = placeResult;
          buffer.append( place.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(place.getVal(), parser.sval)) {
        buffer.append( place.getVal() + " " );
        return true;
      }
    }
    parser.pushBack();
    isOk = null;



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
