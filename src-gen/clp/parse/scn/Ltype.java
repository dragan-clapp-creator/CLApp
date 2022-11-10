package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Ltype extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.Ltype ltype;

  public clp.run.scn.Ltype getLtype() {
    return ltype;
  }

  public void setLtype(clp.run.scn.Ltype ref) {
    ltype = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (ltype == null) {
      clp.run.scn.Ltype[] values = clp.run.scn.Ltype.values();
      for (int i=0; i<values.length; i++) {
        clp.run.scn.Ltype ltypeResult = values[i];
        if (token == parser.TT_WORD && match(ltypeResult.getVal(), parser.sval) ||
            match(ltypeResult.getVal().charAt(0), token)) {
          ltype = ltypeResult;
          buffer.append( ltype.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(ltype.getVal().charAt(0), token)) {
        buffer.append( ltype.getVal() + " " );
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
