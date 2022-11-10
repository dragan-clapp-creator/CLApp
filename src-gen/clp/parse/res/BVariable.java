package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class BVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.BVariable bvariable = new clp.run.res.BVariable();

  public clp.run.res.BVariable getBVariable() {
    return bvariable;
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
        bvariable.setTBOOL(TBOOL0.getVarType());

      BoolVariable boolVariable1 = new BoolVariable();
        Boolean bboolVariable1 = boolVariable1.parse(parser, isOptional);

        if (bboolVariable1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( boolVariable1.getRendering() + " " );
        bvariable.setBoolVariable(boolVariable1.getBoolVariable());

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
