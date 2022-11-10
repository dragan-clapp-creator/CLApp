package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SortOrder extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.SortOrder sortorder;

  public clp.run.scn.SortOrder getSortOrder() {
    return sortorder;
  }

  public void setSortOrder(clp.run.scn.SortOrder ref) {
    sortorder = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (sortorder == null) {
      clp.run.scn.SortOrder[] values = clp.run.scn.SortOrder.values();
      for (int i=0; i<values.length; i++) {
        clp.run.scn.SortOrder sortorderResult = values[i];
        if (match(sortorderResult.getVal(), parser.sval)) {
          sortorder = sortorderResult;
          buffer.append( sortorder.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(sortorder.getVal(), parser.sval)) {
        buffer.append( sortorder.getVal() + " " );
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
