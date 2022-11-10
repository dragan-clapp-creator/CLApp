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
public class SimpleLVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleLVar simplelvar = new clp.run.res.SimpleLVar();

  public clp.run.res.SimpleLVar getSimpleLVar() {
    return simplelvar;
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
    simplelvar.setName(parser.sval);
    
    buffer.append( simplelvar.getName() + " " );

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
    simplelvar.setChar((char)token);

      Lsigned lsigned1 = new Lsigned();
      isOk = lsigned1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      simplelvar.setLsigned(lsigned1.getLsigned());
      simplelvar.setIsLsigned(true);
      buffer.append( lsigned1.getRendering()+" " );


    return true;
  }


}
