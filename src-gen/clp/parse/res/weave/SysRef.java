package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SysRef extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.SysRef sysref;

  public clp.run.res.weave.SysRef getSysRef() {
    return sysref;
  }

  public void setSysRef(clp.run.res.weave.SysRef ref) {
    sysref = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (sysref == null) {
      clp.run.res.weave.SysRef[] values = clp.run.res.weave.SysRef.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.weave.SysRef sysrefResult = values[i];
        if (match(sysrefResult.getVal(), parser.sval)) {
          sysref = sysrefResult;
          buffer.append( sysref.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(sysref.getVal(), parser.sval)) {
        buffer.append( sysref.getVal() + " " );
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
