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
public class HVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.HVar hvar = new clp.run.res.HVar();

  public clp.run.res.HVar getHVar() {
    return hvar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.THASH;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType THASH0 = new VarType();
      THASH0.setVarType(refVarType);
        Boolean bTHASH0 = THASH0.parse(parser, isOptional);

        if (bTHASH0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( THASH0.getRendering() + " " );
        hvar.setTHASH(THASH0.getVarType());

      Array array1 = new Array();
      isOk = array1.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        hvar.setArray(array1.getArray());
        hvar.setIsArray(true);
        buffer.append( array1.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    hvar.setName(parser.sval);
    
    buffer.append( hvar.getName() + " " );

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
    hvar.setChar((char)token);

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );
    hvar.setChar((char)token);

      InitHash initHash2 = new InitHash();
      isOk = initHash2.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      hvar.setInitHash(initHash2.getInitHash());
      hvar.setIsInitHash(true);
      buffer.append( initHash2.getRendering()+" " );

      isOk = starBlock3(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );
    hvar.setChar((char)token);


    return true;
  }

  private Boolean starBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock3(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );
    hvar.setChar((char)token);

    InitHash initHash = new InitHash();
    isOk = initHash.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    hvar.addInitHash(initHash.getInitHash());
    buffer.append( initHash.getRendering()+" " );


    return isOk;
  }


}
