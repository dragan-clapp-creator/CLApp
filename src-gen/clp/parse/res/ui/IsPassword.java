package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IsPassword extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.IsPassword ispassword;

  public clp.run.res.ui.IsPassword getIsPassword() {
    return ispassword;
  }

  public void setIsPassword(clp.run.res.ui.IsPassword ref) {
    ispassword = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (ispassword == null) {
      clp.run.res.ui.IsPassword[] values = clp.run.res.ui.IsPassword.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.ui.IsPassword ispasswordResult = values[i];
        if (match(ispasswordResult.getVal(), parser.sval)) {
          ispassword = ispasswordResult;
          buffer.append( ispassword.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(ispassword.getVal(), parser.sval)) {
        buffer.append( ispassword.getVal() + " " );
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
