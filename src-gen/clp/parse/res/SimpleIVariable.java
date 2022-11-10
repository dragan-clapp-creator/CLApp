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
public class SimpleIVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleIVariable simpleivariable = new clp.run.res.SimpleIVariable();

  public clp.run.res.SimpleIVariable getSimpleIVariable() {
    return simpleivariable;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(true);
    }
    isOk = true;
    simpleivariable.setName(parser.sval);
    
    buffer.append( simpleivariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      Isigned isigned2 = new Isigned();
        Boolean bisigned2 = isigned2.parse(parser, isOptional);

        if (bisigned2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( isigned2.getRendering() + " " );
        simpleivariable.setIsigned(isigned2.getIsigned());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
