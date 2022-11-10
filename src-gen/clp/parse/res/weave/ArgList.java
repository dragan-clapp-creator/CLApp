package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArgList extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.ArgList arglist = new clp.run.res.weave.ArgList();

  public clp.run.res.weave.ArgList getArgList() {
    return arglist;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      ArgumentPair argumentPair0 = new ArgumentPair();
        Boolean bargumentPair0 = argumentPair0.parse(parser, isOptional);

        if (bargumentPair0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( argumentPair0.getRendering() + " " );
        arglist.setArgumentPair(argumentPair0.getArgumentPair());

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
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    ArgumentPair argumentPair = new ArgumentPair();
    isOk = argumentPair.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    arglist.addArgumentPair(argumentPair.getArgumentPair());
    buffer.append( argumentPair.getRendering()+" " );


    return isOk;
  }


}
