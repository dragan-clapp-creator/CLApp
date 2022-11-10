package clp.parse.cel.asm;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class AsmStatParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.asm.AssignStatement assignstatement = new clp.run.cel.asm.AssignStatement();

  public clp.run.cel.asm.AssignStatement getAssignStatement() {
    return assignstatement;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    isOk = plusBlock(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return parser.errorLog(false);
    }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean plusBlock(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

    VarAssignment bVarAssignment = new VarAssignment();
    isOk = bVarAssignment.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      assignstatement.setVarAssignment(bVarAssignment.getVarAssignment());
      buffer.append( bVarAssignment.getRendering()+" " );
      return true;
    }

    BoolAssignment bBoolAssignment = new BoolAssignment();
    isOk = bBoolAssignment.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      assignstatement.setBoolAssignment(bBoolAssignment.getBoolAssignment());
      buffer.append( bBoolAssignment.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
