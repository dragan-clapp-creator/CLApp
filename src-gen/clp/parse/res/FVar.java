package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class FVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.FVar fvar = new clp.run.res.FVar();

  public clp.run.res.FVar getFVar() {
    return fvar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TFLOAT;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TFLOAT0 = new VarType();
      TFLOAT0.setVarType(refVarType);
        Boolean bTFLOAT0 = TFLOAT0.parse(parser, isOptional);

        if (bTFLOAT0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TFLOAT0.getRendering() + " " );
        fvar.setTFLOAT(TFLOAT0.getVarType());

      FloatVar floatVar1 = new FloatVar();
        Boolean bfloatVar1 = floatVar1.parse(parser, isOptional);

        if (bfloatVar1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( floatVar1.getRendering() + " " );
        fvar.setFloatVar(floatVar1.getFloatVar());

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
