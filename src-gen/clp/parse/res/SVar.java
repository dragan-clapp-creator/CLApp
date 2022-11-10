package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SVar svar = new clp.run.res.SVar();

  public clp.run.res.SVar getSVar() {
    return svar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TSTRING;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TSTRING0 = new VarType();
      TSTRING0.setVarType(refVarType);
        Boolean bTSTRING0 = TSTRING0.parse(parser, isOptional);

        if (bTSTRING0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TSTRING0.getRendering() + " " );
        svar.setTSTRING(TSTRING0.getVarType());

      StringVar stringVar1 = new StringVar();
        Boolean bstringVar1 = stringVar1.parse(parser, isOptional);

        if (bstringVar1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( stringVar1.getRendering() + " " );
        svar.setStringVar(stringVar1.getStringVar());

    token = parser.nextToken();
    isOk = match(';', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "; " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
