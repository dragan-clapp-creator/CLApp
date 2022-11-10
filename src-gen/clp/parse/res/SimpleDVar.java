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
public class SimpleDVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleDVar simpledvar = new clp.run.res.SimpleDVar();

  public clp.run.res.SimpleDVar getSimpleDVar() {
    return simpledvar;
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
    simpledvar.setName(parser.sval);
    
    buffer.append( simpledvar.getName() + " " );

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
    simpledvar.setChar((char)token);

      DInit dInit1 = new DInit();
      isOk = dInit1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      simpledvar.setDInit(dInit1.getDInit());
      simpledvar.setIsDInit(true);
      buffer.append( dInit1.getRendering()+" " );


    return true;
  }


}
