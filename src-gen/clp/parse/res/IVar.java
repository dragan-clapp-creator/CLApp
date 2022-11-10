package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.IVar ivar = new clp.run.res.IVar();

  public clp.run.res.IVar getIVar() {
    return ivar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TINT;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TINT0 = new VarType();
      TINT0.setVarType(refVarType);
        Boolean bTINT0 = TINT0.parse(parser, isOptional);

        if (bTINT0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TINT0.getRendering() + " " );
        ivar.setTINT(TINT0.getVarType());

      IntVar intVar1 = new IntVar();
        Boolean bintVar1 = intVar1.parse(parser, isOptional);

        if (bintVar1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( intVar1.getRendering() + " " );
        ivar.setIntVar(intVar1.getIntVar());

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
