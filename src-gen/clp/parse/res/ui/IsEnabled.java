package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IsEnabled extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.IsEnabled isenabled;

  public clp.run.res.ui.IsEnabled getIsEnabled() {
    return isenabled;
  }

  public void setIsEnabled(clp.run.res.ui.IsEnabled ref) {
    isenabled = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (isenabled == null) {
      clp.run.res.ui.IsEnabled[] values = clp.run.res.ui.IsEnabled.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.ui.IsEnabled isenabledResult = values[i];
        if (match(isenabledResult.getVal(), parser.sval)) {
          isenabled = isenabledResult;
          buffer.append( isenabled.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(isenabled.getVal(), parser.sval)) {
        buffer.append( isenabled.getVal() + " " );
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
