package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SysCommand extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.SysCommand syscommand;

  public clp.run.cel.dom.SysCommand getSysCommand() {
    return syscommand;
  }

  public void setSysCommand(clp.run.cel.dom.SysCommand ref) {
    syscommand = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (syscommand == null) {
      clp.run.cel.dom.SysCommand[] values = clp.run.cel.dom.SysCommand.values();
      for (int i=0; i<values.length; i++) {
        clp.run.cel.dom.SysCommand syscommandResult = values[i];
        if (match(syscommandResult.getVal(), parser.sval)) {
          syscommand = syscommandResult;
          buffer.append( syscommand.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(syscommand.getVal(), parser.sval)) {
        buffer.append( syscommand.getVal() + " " );
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
