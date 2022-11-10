package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SysExp extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.SysExp sysexp;

  public clp.run.cel.dom.SysExp getSysExp() {
    return sysexp;
  }

  public void setSysExp(clp.run.cel.dom.SysExp ref) {
    sysexp = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (sysexp == null) {
      clp.run.cel.dom.SysExp[] values = clp.run.cel.dom.SysExp.values();
      for (int i=0; i<values.length; i++) {
        clp.run.cel.dom.SysExp sysexpResult = values[i];
        if (match(sysexpResult.getVal(), parser.sval)) {
          sysexp = sysexpResult;
          buffer.append( sysexp.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(sysexp.getVal(), parser.sval)) {
        buffer.append( sysexp.getVal() + " " );
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
