package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class VarType extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.VarType vartype;

  public clp.run.res.VarType getVarType() {
    return vartype;
  }

  public void setVarType(clp.run.res.VarType ref) {
    vartype = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (vartype == null) {
      clp.run.res.VarType[] values = clp.run.res.VarType.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.VarType vartypeResult = values[i];
        if (match(vartypeResult.getVal(), parser.sval)) {
          vartype = vartypeResult;
          buffer.append( vartype.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(vartype.getVal(), parser.sval)) {
        buffer.append( vartype.getVal() + " " );
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
