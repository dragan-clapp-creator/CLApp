package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgumentName extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.ArgumentName argumentname = new clp.run.res.weave.ArgumentName();

  public clp.run.res.weave.ArgumentName getArgumentName() {
    return argumentname;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      Argtype argtype0 = new Argtype();
      isOk = argtype0.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        argumentname.setArgtype(argtype0.getArgtype());
        argumentname.setIsArgtype(true);
        buffer.append( argtype0.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(true);
    }
    isOk = true;
    argumentname.setName(parser.sval);
    
    buffer.append( argumentname.getName() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
