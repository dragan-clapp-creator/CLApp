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
public class SimpleTVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleTVariable simpletvariable = new clp.run.res.SimpleTVariable();

  public clp.run.res.SimpleTVariable getSimpleTVariable() {
    return simpletvariable;
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
    simpletvariable.setName(parser.sval);
    
    buffer.append( simpletvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      TInit tInit2 = new TInit();
        Boolean btInit2 = tInit2.parse(parser, isOptional);

        if (btInit2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( tInit2.getRendering() + " " );
        simpletvariable.setTInit(tInit2.getTInit());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
