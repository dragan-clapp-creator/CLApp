package clp.parse.cel.asm;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Modifier extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.asm.Modifier modifier;

  public clp.run.cel.asm.Modifier getModifier() {
    return modifier;
  }

  public void setModifier(clp.run.cel.asm.Modifier ref) {
    modifier = ref;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (modifier == null) {
      clp.run.cel.asm.Modifier[] values = clp.run.cel.asm.Modifier.values();
      for (int i=0; i<values.length; i++) {
        clp.run.cel.asm.Modifier modifierResult = values[i];
        if (token == parser.TT_WORD && match(modifierResult.getVal(), parser.sval) ||
            match(modifierResult.getVal().charAt(0), token)) {
          modifier = modifierResult;
          buffer.append( modifier.getVal() + " " );
          return true;
        }
      }
      parser.pushBack();
      return null;
    }
    else {
      if (match(modifier.getVal().charAt(0), token)) {
        buffer.append( modifier.getVal() + " " );
        return true;
      }
    }
    parser.pushBack();
    isOk = null;



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
