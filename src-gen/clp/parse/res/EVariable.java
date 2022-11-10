package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class EVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.EVariable evariable = new clp.run.res.EVariable();

  public clp.run.res.EVariable getEVariable() {
    return evariable;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TEVENT;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TEVENT0 = new VarType();
      TEVENT0.setVarType(refVarType);
        Boolean bTEVENT0 = TEVENT0.parse(parser, isOptional);

        if (bTEVENT0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TEVENT0.getRendering() + " " );
        evariable.setTEVENT(TEVENT0.getVarType());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    evariable.setName(parser.sval);
    
    buffer.append( evariable.getName() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    evariable.setInitial(parser.sval.equalsIgnoreCase("true"));
    
    buffer.append( evariable.getInitial() + " " );

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
