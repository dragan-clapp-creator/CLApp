package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MscTaskName extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.MscTaskName msctaskname;

  public clp.run.msc.MscTaskName getMscTaskName() {
    return msctaskname;
  }

  public void setMscTaskName(clp.run.msc.MscTaskName ref) {
    msctaskname = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (msctaskname == null) {
      clp.run.msc.MscTaskName[] values = clp.run.msc.MscTaskName.values();
      for (int i=0; i<values.length; i++) {
        clp.run.msc.MscTaskName msctasknameResult = values[i];
        if (match(msctasknameResult.getVal(), parser.sval)) {
          msctaskname = msctasknameResult;
          buffer.append( msctaskname.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(msctaskname.getVal(), parser.sval)) {
        buffer.append( msctaskname.getVal() + " " );
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
