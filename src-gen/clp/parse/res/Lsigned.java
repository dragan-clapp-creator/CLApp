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
public class Lsigned extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Lsigned lsigned = new clp.run.res.Lsigned();

  public clp.run.res.Lsigned getLsigned() {
    return lsigned;
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
        lsigned.setTermOperator(termOperator0.getTermOperator());
        lsigned.setIsTermOperator(true);
        buffer.append( termOperator0.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(true);
    }
    isOk = true;
    lsigned.setInitial((long) parser.nval);
    
    buffer.append( lsigned.getInitial() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
