package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IsRollback extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.IsRollback isrollback;

  public clp.run.res.ui.IsRollback getIsRollback() {
    return isrollback;
  }

  public void setIsRollback(clp.run.res.ui.IsRollback ref) {
    isrollback = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (isrollback == null) {
      clp.run.res.ui.IsRollback[] values = clp.run.res.ui.IsRollback.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.ui.IsRollback isrollbackResult = values[i];
        if (match(isrollbackResult.getVal(), parser.sval)) {
          isrollback = isrollbackResult;
          buffer.append( isrollback.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(isrollback.getVal(), parser.sval)) {
        buffer.append( isrollback.getVal() + " " );
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
