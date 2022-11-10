package clp.parse.cel.asm;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.cel.exp.ExpParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class VarAssignment extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.asm.VarAssignment varassignment = new clp.run.cel.asm.VarAssignment();

  public clp.run.cel.asm.VarAssignment getVarAssignment() {
    return varassignment;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("set", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("set ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    varassignment.setVar(parser.sval);
    
    buffer.append( varassignment.getVar() + " " );

    token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "= " );

      ExpParser expression3 = new ExpParser();
        Boolean bexpression3 = expression3.parse(parser, isOptional);

        if (bexpression3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( expression3.getRendering() + " " );
        varassignment.setExpression(expression3.getExpression());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
