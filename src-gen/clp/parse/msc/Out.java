package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Out extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.Out out;

  public clp.run.msc.Out getOut() {
    return out;
  }

  public void setOut(clp.run.msc.Out ref) {
    out = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (out == null) {
      clp.run.msc.Out[] values = clp.run.msc.Out.values();
      for (int i=0; i<values.length; i++) {
        clp.run.msc.Out outResult = values[i];
        if (match(outResult.getVal(), parser.sval)) {
          out = outResult;
          buffer.append( out.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(out.getVal(), parser.sval)) {
        buffer.append( out.getVal() + " " );
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
