package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class BVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.BVar bvar = new clp.run.res.BVar();

  public clp.run.res.BVar getBVar() {
    return bvar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TBOOL;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TBOOL0 = new VarType();
      TBOOL0.setVarType(refVarType);
        Boolean bTBOOL0 = TBOOL0.parse(parser, isOptional);

        if (bTBOOL0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TBOOL0.getRendering() + " " );
        bvar.setTBOOL(TBOOL0.getVarType());

      BoolVar boolVar1 = new BoolVar();
        Boolean bboolVar1 = boolVar1.parse(parser, isOptional);

        if (bboolVar1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( boolVar1.getRendering() + " " );
        bvar.setBoolVar(boolVar1.getBoolVar());

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
