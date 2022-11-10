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
public class SimpleDVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleDVariable simpledvariable = new clp.run.res.SimpleDVariable();

  public clp.run.res.SimpleDVariable getSimpleDVariable() {
    return simpledvariable;
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
    simpledvariable.setName(parser.sval);
    
    buffer.append( simpledvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      DInit dInit2 = new DInit();
        Boolean bdInit2 = dInit2.parse(parser, isOptional);

        if (bdInit2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( dInit2.getRendering() + " " );
        simpledvariable.setDInit(dInit2.getDInit());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
