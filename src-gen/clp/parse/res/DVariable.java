package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class DVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.DVariable dvariable = new clp.run.res.DVariable();

  public clp.run.res.DVariable getDVariable() {
    return dvariable;
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
        dvariable.setTDATE(TDATE0.getVarType());

      DateVariable dateVariable1 = new DateVariable();
        Boolean bdateVariable1 = dateVariable1.parse(parser, isOptional);

        if (bdateVariable1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( dateVariable1.getRendering() + " " );
        dvariable.setDateVariable(dateVariable1.getDateVariable());

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
