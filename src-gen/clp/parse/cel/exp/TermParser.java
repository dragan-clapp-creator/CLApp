package clp.parse.cel.exp;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.FactOperator;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TermParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.Term term = new clp.run.cel.exp.Term();

  public clp.run.cel.exp.Term getTerm() {
    return term;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      Factor factor0 = new Factor();
        Boolean bfactor0 = factor0.parse(parser, isOptional);

        if (bfactor0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( factor0.getRendering() + " " );
        term.setFactor(factor0.getFactor());

      isOk = hutBlock1(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock1(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

       FactOperator op0 = new FactOperator();
      isOk = op0.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
      term.setOp(op0.getFactOperator());
      term.setIsFactOperator(true);
      buffer.append( op0.getRendering()+" " );

      TermParser term1 = new TermParser();
      isOk = term1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      term.setTerm(term1.getTerm());
      term.setIsTerm(true);
      buffer.append( term1.getRendering()+" " );


    return true;
  }


}
