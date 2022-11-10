package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class DVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.DVar dvar = new clp.run.res.DVar();

  public clp.run.res.DVar getDVar() {
    return dvar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TDATE;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TDATE0 = new VarType();
      TDATE0.setVarType(refVarType);
        Boolean bTDATE0 = TDATE0.parse(parser, isOptional);

        if (bTDATE0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TDATE0.getRendering() + " " );
        dvar.setTDATE(TDATE0.getVarType());

      DateVar dateVar1 = new DateVar();
        Boolean bdateVar1 = dateVar1.parse(parser, isOptional);

        if (bdateVar1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( dateVar1.getRendering() + " " );
        dvar.setDateVar(dateVar1.getDateVar());

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
