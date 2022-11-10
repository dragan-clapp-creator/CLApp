package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TermOperator extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.TermOperator termoperator;

  public clp.run.res.TermOperator getTermOperator() {
    return termoperator;
  }

  public void setTermOperator(clp.run.res.TermOperator ref) {
    termoperator = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (termoperator == null) {
      clp.run.res.TermOperator[] values = clp.run.res.TermOperator.values();
      for (int i=0; i<values.length; i++) {
        clp.run.res.TermOperator termoperatorResult = values[i];
        if (token == parser.TT_WORD && match(termoperatorResult.getVal(), parser.sval) ||
            match(termoperatorResult.getVal().charAt(0), token)) {
          termoperator = termoperatorResult;
          buffer.append( termoperator.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(termoperator.getVal().charAt(0), token)) {
        buffer.append( termoperator.getVal() + " " );
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
