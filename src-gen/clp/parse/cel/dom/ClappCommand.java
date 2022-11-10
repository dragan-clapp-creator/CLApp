package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ClappCommand extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.ClappCommand clappcommand;

  public clp.run.cel.dom.ClappCommand getClappCommand() {
    return clappcommand;
  }

  public void setClappCommand(clp.run.cel.dom.ClappCommand ref) {
    clappcommand = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (clappcommand == null) {
      clp.run.cel.dom.ClappCommand[] values = clp.run.cel.dom.ClappCommand.values();
      for (int i=0; i<values.length; i++) {
        clp.run.cel.dom.ClappCommand clappcommandResult = values[i];
        if (match(clappcommandResult.getVal(), parser.sval)) {
          clappcommand = clappcommandResult;
          buffer.append( clappcommand.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(clappcommand.getVal(), parser.sval)) {
        buffer.append( clappcommand.getVal() + " " );
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
