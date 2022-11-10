package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.SVariable svariable = new clp.run.res.SVariable();

  public clp.run.res.SVariable getSVariable() {
    return svariable;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TSTRING;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TSTRING0 = new VarType();
      TSTRING0.setVarType(refVarType);
        Boolean bTSTRING0 = TSTRING0.parse(parser, isOptional);

        if (bTSTRING0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TSTRING0.getRendering() + " " );
        svariable.setTSTRING(TSTRING0.getVarType());

      StringVariable stringVariable1 = new StringVariable();
        Boolean bstringVariable1 = stringVariable1.parse(parser, isOptional);

        if (bstringVariable1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( stringVariable1.getRendering() + " " );
        svariable.setStringVariable(stringVariable1.getStringVariable());

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
