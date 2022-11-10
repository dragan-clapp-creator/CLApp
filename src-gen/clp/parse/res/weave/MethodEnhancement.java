package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MethodEnhancement extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.MethodEnhancement methodenhancement = new clp.run.res.weave.MethodEnhancement();

  public clp.run.res.weave.MethodEnhancement getMethodEnhancement() {
    return methodenhancement;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("onMethod", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("onMethod ");
    

      CstOrVar methodTarget1 = new CstOrVar();
        Boolean bmethodTarget1 = methodTarget1.parse(parser, isOptional);

        if (bmethodTarget1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( methodTarget1.getRendering() + " " );
        methodenhancement.setMethodTarget(methodTarget1.getCstOrVar());

      isOk = hutBlock2(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

      isOk = hutBlock3(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      isOk = starBlock5(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match('(', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "( " );
    methodenhancement.setChar((char)token);

      ArgListApprox argListApprox1 = new ArgListApprox();
      isOk = argListApprox1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      methodenhancement.setArgListApprox(argListApprox1.getArgListApprox());
      methodenhancement.setIsArgListApprox(true);
      buffer.append( argListApprox1.getRendering()+" " );

    token = parser.nextToken();
    isOk = match(')', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ") " );
    methodenhancement.setChar((char)token);


    return true;
  }

  private Boolean hutBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match("place", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("place ");
    methodenhancement.setIsPlace(true);

      Location loadLoc1 = new Location();
      isOk = loadLoc1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      methodenhancement.setLoadLoc(loadLoc1.getLocation());
      methodenhancement.setIsLocation(true);
      buffer.append( loadLoc1.getRendering()+" " );


    return true;
  }

  private Boolean starBlock5(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock5(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock5(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    MethodAddOn methodAddOn = new MethodAddOn();
    isOk = methodAddOn.parse(parser, true);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      methodenhancement.addMethodAddOn(methodAddOn.getMethodAddOn());
      buffer.append( methodAddOn.getRendering()+" " );
    }


    return isOk;
  }


}
