package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Argtype extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.Argtype argtype;

  public clp.run.res.weave.Argtype getArgtype() {
    return argtype;
  }

  public void setArgtype(clp.run.res.weave.Argtype ref) {
    argtype = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (argtype == null) {
      clp.run.res.weave.Argtype[] values = clp.run.res.weave.Argtype.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.weave.Argtype argtypeResult = values[i];
        if (match(argtypeResult.getVal(), parser.sval)) {
          argtype = argtypeResult;
          buffer.append( argtype.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(argtype.getVal(), parser.sval)) {
        buffer.append( argtype.getVal() + " " );
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
