package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgumentSum extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.ArgumentSum argumentsum = new clp.run.res.weave.ArgumentSum();

  public clp.run.res.weave.ArgumentSum getArgumentSum() {
    return argumentsum;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      ArgumentName argumentName0 = new ArgumentName();
        Boolean bargumentName0 = argumentName0.parse(parser, isOptional);

        if (bargumentName0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( argumentName0.getRendering() + " " );
        argumentsum.setArgumentName(argumentName0.getArgumentName());

      isOk = starBlock1(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean starBlock1(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock1(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock1(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match('+', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "+ " );

    ArgumentName argumentName = new ArgumentName();
    isOk = argumentName.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    argumentsum.addArgumentName(argumentName.getArgumentName());
    buffer.append( argumentName.getRendering()+" " );


    return isOk;
  }


}
