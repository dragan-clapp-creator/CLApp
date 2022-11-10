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
public class SimpleTVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleTVar simpletvar = new clp.run.res.SimpleTVar();

  public clp.run.res.SimpleTVar getSimpleTVar() {
    return simpletvar;
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
    simpletvar.setName(parser.sval);
    
    buffer.append( simpletvar.getName() + " " );

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
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "= " );
    simpletvar.setChar((char)token);

      TInit tInit1 = new TInit();
      isOk = tInit1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      simpletvar.setTInit(tInit1.getTInit());
      simpletvar.setIsTInit(true);
      buffer.append( tInit1.getRendering()+" " );


    return true;
  }


}
