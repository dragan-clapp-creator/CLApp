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
public class PVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.PVar pvar = new clp.run.res.PVar();

  public clp.run.res.PVar getPVar() {
    return pvar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TREF;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TREF0 = new VarType();
      TREF0.setVarType(refVarType);
        Boolean bTREF0 = TREF0.parse(parser, isOptional);

        if (bTREF0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TREF0.getRendering() + " " );
        pvar.setTREF(TREF0.getVarType());

      Array array1 = new Array();
      isOk = array1.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        pvar.setArray(array1.getArray());
        pvar.setIsArray(true);
        buffer.append( array1.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    pvar.setName(parser.sval);
    
    buffer.append( pvar.getName() + " " );

      isOk = hutBlock3(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    isOk = match(';', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "; " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "= " );
    pvar.setChar((char)token);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      pvar.setInitial(parser.sval);
      pvar.setIsInitial(true);
      buffer.append( pvar.getInitial() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }


}
