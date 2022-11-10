package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Position extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.Position position;

  public clp.run.res.weave.Position getPosition() {
    return position;
  }

  public void setPosition(clp.run.res.weave.Position ref) {
    position = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (position == null) {
      clp.run.res.weave.Position[] values = clp.run.res.weave.Position.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.weave.Position positionResult = values[i];
        if (match(positionResult.getVal(), parser.sval)) {
          position = positionResult;
          buffer.append( position.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(position.getVal(), parser.sval)) {
        buffer.append( position.getVal() + " " );
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
