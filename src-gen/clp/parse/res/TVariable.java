package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.TVariable tvariable = new clp.run.res.TVariable();

  public clp.run.res.TVariable getTVariable() {
    return tvariable;
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
        tvariable.setTTIME(TTIME0.getVarType());

      TimeVariable timeVariable1 = new TimeVariable();
        Boolean btimeVariable1 = timeVariable1.parse(parser, isOptional);

        if (btimeVariable1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( timeVariable1.getRendering() + " " );
        tvariable.setTimeVariable(timeVariable1.getTimeVariable());

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
