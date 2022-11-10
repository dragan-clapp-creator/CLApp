package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SelType extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.SelType seltype;

  public clp.run.res.ui.SelType getSelType() {
    return seltype;
  }

  public void setSelType(clp.run.res.ui.SelType ref) {
    seltype = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (seltype == null) {
      clp.run.res.ui.SelType[] values = clp.run.res.ui.SelType.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.ui.SelType seltypeResult = values[i];
        if (match(seltypeResult.getVal(), parser.sval)) {
          seltype = seltypeResult;
          buffer.append( seltype.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(seltype.getVal(), parser.sval)) {
        buffer.append( seltype.getVal() + " " );
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
