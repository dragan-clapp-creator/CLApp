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
public class Fsigned extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Fsigned fsigned = new clp.run.res.Fsigned();

  public clp.run.res.Fsigned getFsigned() {
    return fsigned;
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
        fsigned.setTermOperator(termOperator0.getTermOperator());
        fsigned.setIsTermOperator(true);
        buffer.append( termOperator0.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(true);
    }
    isOk = true;
    fsigned.setInitial((double) parser.nval);
    
    buffer.append( fsigned.getInitial() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
