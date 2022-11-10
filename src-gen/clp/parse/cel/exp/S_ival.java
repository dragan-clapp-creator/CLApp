package clp.parse.cel.exp;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.TermOperator;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class S_ival extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.S_ival s_ival = new clp.run.cel.exp.S_ival();

  public clp.run.cel.exp.S_ival getS_ival() {
    return s_ival;
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
        s_ival.setTermOperator(termOperator0.getTermOperator());
        s_ival.setIsTermOperator(true);
        buffer.append( termOperator0.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(true);
    }
    isOk = true;
    s_ival.setIval((int) parser.nval);
    
    buffer.append( s_ival.getIval() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
