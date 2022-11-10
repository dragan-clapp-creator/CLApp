package clp.parse.cel.exp;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SimpleFactor extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.SimpleFactor simplefactor = new clp.run.cel.exp.SimpleFactor();

  public clp.run.cel.exp.SimpleFactor getSimpleFactor() {
    return simplefactor;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      Sfactor sfactor0 = new Sfactor();
        Boolean bsfactor0 = sfactor0.parse(parser, isOptional);

        if (bsfactor0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( sfactor0.getRendering() + " " );
        simplefactor.setSfactor(sfactor0.getSfactor());

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

     token = parser.nextToken();
    isOk = match('^', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "^ " );
    simplefactor.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_NUMBER || token == '\"') {
      simplefactor.setExp((int) parser.nval);
      simplefactor.setIsExp(true);
      buffer.append( simplefactor.getExp() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }


}
