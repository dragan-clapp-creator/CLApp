package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class FVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.FVariable fvariable = new clp.run.res.FVariable();

  public clp.run.res.FVariable getFVariable() {
    return fvariable;
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
        fvariable.setTFLOAT(TFLOAT0.getVarType());

      FloatVariable floatVariable1 = new FloatVariable();
        Boolean bfloatVariable1 = floatVariable1.parse(parser, isOptional);

        if (bfloatVariable1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( floatVariable1.getRendering() + " " );
        fvariable.setFloatVariable(floatVariable1.getFloatVariable());

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
