package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class KindOf extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.KindOf kindof;

  public clp.run.res.weave.KindOf getKindOf() {
    return kindof;
  }

  public void setKindOf(clp.run.res.weave.KindOf ref) {
    kindof = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (kindof == null) {
      clp.run.res.weave.KindOf[] values = clp.run.res.weave.KindOf.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.weave.KindOf kindofResult = values[i];
        if (match(kindofResult.getVal(), parser.sval)) {
          kindof = kindofResult;
          buffer.append( kindof.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(kindof.getVal(), parser.sval)) {
        buffer.append( kindof.getVal() + " " );
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
