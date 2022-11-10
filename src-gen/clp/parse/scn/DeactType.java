package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class DeactType extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.DeactType deacttype;

  public clp.run.scn.DeactType getDeactType() {
    return deacttype;
  }

  public void setDeactType(clp.run.scn.DeactType ref) {
    deacttype = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (deacttype == null) {
      clp.run.scn.DeactType[] values = clp.run.scn.DeactType.values();
      for (int i=0; i<values.length; i++) {
        clp.run.scn.DeactType deacttypeResult = values[i];
        if (match(deacttypeResult.getVal(), parser.sval)) {
          deacttype = deacttypeResult;
          buffer.append( deacttype.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(deacttype.getVal(), parser.sval)) {
        buffer.append( deacttype.getVal() + " " );
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
