package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.LVar lvar = new clp.run.res.LVar();

  public clp.run.res.LVar getLVar() {
    return lvar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TLONG;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TLONG0 = new VarType();
      TLONG0.setVarType(refVarType);
        Boolean bTLONG0 = TLONG0.parse(parser, isOptional);

        if (bTLONG0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TLONG0.getRendering() + " " );
        lvar.setTLONG(TLONG0.getVarType());

      LongVar longVar1 = new LongVar();
        Boolean blongVar1 = longVar1.parse(parser, isOptional);

        if (blongVar1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( longVar1.getRendering() + " " );
        lvar.setLongVar(longVar1.getLongVar());

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
