package clp.parse.cel.asm;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class BoolAssignment extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.asm.BoolAssignment boolassignment = new clp.run.cel.asm.BoolAssignment();

  public clp.run.cel.asm.BoolAssignment getBoolAssignment() {
    return boolassignment;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("bset", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("bset ");
    

      Modifier modifier1 = new Modifier();
      isOk = modifier1.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        boolassignment.setModifier(modifier1.getModifier());
        boolassignment.setIsModifier(true);
        buffer.append( modifier1.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    isOk = match("keeping", parser.sval);
    if (isOk == Boolean.TRUE) {
      buffer.append("keeping ");
      boolassignment.setIsKeeping(true);
      
    }
    else {
      parser.pushBack();
      isOk = true;
    }
    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    boolassignment.setVar(parser.sval);
    
    buffer.append( boolassignment.getVar() + " " );

      Ifclause ifclause4 = new Ifclause();
      isOk = ifclause4.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        boolassignment.setIfclause(ifclause4.getIfclause());
        boolassignment.setIsIfclause(true);
        buffer.append( ifclause4.getRendering()+" " );
      }
      isOk = true;



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
