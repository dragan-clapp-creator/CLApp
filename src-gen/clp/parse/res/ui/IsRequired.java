package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IsRequired extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.IsRequired isrequired;

  public clp.run.res.ui.IsRequired getIsRequired() {
    return isrequired;
  }

  public void setIsRequired(clp.run.res.ui.IsRequired ref) {
    isrequired = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (isrequired == null) {
      clp.run.res.ui.IsRequired[] values = clp.run.res.ui.IsRequired.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.ui.IsRequired isrequiredResult = values[i];
        if (match(isrequiredResult.getVal(), parser.sval)) {
          isrequired = isrequiredResult;
          buffer.append( isrequired.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(isrequired.getVal(), parser.sval)) {
        buffer.append( isrequired.getVal() + " " );
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
