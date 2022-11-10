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
public class SimpleLVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SimpleLVariable simplelvariable = new clp.run.res.SimpleLVariable();

  public clp.run.res.SimpleLVariable getSimpleLVariable() {
    return simplelvariable;
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
    simplelvariable.setName(parser.sval);
    
    buffer.append( simplelvariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      Lsigned lsigned2 = new Lsigned();
        Boolean blsigned2 = lsigned2.parse(parser, isOptional);

        if (blsigned2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( lsigned2.getRendering() + " " );
        simplelvariable.setLsigned(lsigned2.getLsigned());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
