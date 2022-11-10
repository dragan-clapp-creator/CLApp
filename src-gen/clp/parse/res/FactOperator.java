package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class FactOperator extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.FactOperator factoperator;

  public clp.run.res.FactOperator getFactOperator() {
    return factoperator;
  }

  public void setFactOperator(clp.run.res.FactOperator ref) {
    factoperator = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (factoperator == null) {
      clp.run.res.FactOperator[] values = clp.run.res.FactOperator.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.FactOperator factoperatorResult = values[i];
        if (token == parser.TT_WORD && match(factoperatorResult.getVal(), parser.sval) ||
            match(factoperatorResult.getVal().charAt(0), token)) {
          factoperator = factoperatorResult;
          buffer.append( factoperator.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(factoperator.getVal().charAt(0), token)) {
        buffer.append( factoperator.getVal() + " " );
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
