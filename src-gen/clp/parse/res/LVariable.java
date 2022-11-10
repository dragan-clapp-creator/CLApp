package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.LVariable lvariable = new clp.run.res.LVariable();

  public clp.run.res.LVariable getLVariable() {
    return lvariable;
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
        lvariable.setTLONG(TLONG0.getVarType());

      LongVariable longVariable1 = new LongVariable();
        Boolean blongVariable1 = longVariable1.parse(parser, isOptional);

        if (blongVariable1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( longVariable1.getRendering() + " " );
        lvariable.setLongVariable(longVariable1.getLongVariable());

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
