package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.TVar tvar = new clp.run.res.TVar();

  public clp.run.res.TVar getTVar() {
    return tvar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TTIME;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TTIME0 = new VarType();
      TTIME0.setVarType(refVarType);
        Boolean bTTIME0 = TTIME0.parse(parser, isOptional);

        if (bTTIME0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TTIME0.getRendering() + " " );
        tvar.setTTIME(TTIME0.getVarType());

      TimeVar timeVar1 = new TimeVar();
        Boolean btimeVar1 = timeVar1.parse(parser, isOptional);

        if (btimeVar1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( timeVar1.getRendering() + " " );
        tvar.setTimeVar(timeVar1.getTimeVar());

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
