package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Isigned extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Isigned isigned = new clp.run.res.Isigned();

  public clp.run.res.Isigned getIsigned() {
    return isigned;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      TermOperator termOperator0 = new TermOperator();
      isOk = termOperator0.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        isigned.setTermOperator(termOperator0.getTermOperator());
        isigned.setIsTermOperator(true);
        buffer.append( termOperator0.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(true);
    }
    isOk = true;
    isigned.setInitial((int) parser.nval);
    
    buffer.append( isigned.getInitial() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
