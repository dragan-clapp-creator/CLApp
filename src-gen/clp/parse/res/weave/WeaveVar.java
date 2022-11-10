package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.VarType;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class WeaveVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.WeaveVar weavevar = new clp.run.res.weave.WeaveVar();

  public clp.run.res.weave.WeaveVar getWeaveVar() {
    return weavevar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TWEAVER;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TWEAVER0 = new VarType();
      TWEAVER0.setVarType(refVarType);
        Boolean bTWEAVER0 = TWEAVER0.parse(parser, isOptional);

        if (bTWEAVER0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TWEAVER0.getRendering() + " " );
        weavevar.setTWEAVER(TWEAVER0.getVarType());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    weavevar.setName(parser.sval);
    
    buffer.append( weavevar.getName() + " " );

    token = parser.nextToken();
    isOk = match("onClass", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("onClass ");
    

      CstOrVar pack3 = new CstOrVar();
        Boolean bpack3 = pack3.parse(parser, isOptional);

        if (bpack3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( pack3.getRendering() + " " );
        weavevar.setPack(pack3.getCstOrVar());

    token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ": " );

      CstOrVar clazz5 = new CstOrVar();
        Boolean bclazz5 = clazz5.parse(parser, isOptional);

        if (bclazz5 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( clazz5.getRendering() + " " );
        weavevar.setClazz(clazz5.getCstOrVar());

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      isOk = starBlock7(parser, isOptional);
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

  private Boolean starBlock7(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock7(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock7(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    MethodEnhancement methodEnhancement = new MethodEnhancement();
    isOk = methodEnhancement.parse(parser, true);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      weavevar.addMethodEnhancement(methodEnhancement.getMethodEnhancement());
      buffer.append( methodEnhancement.getRendering()+" " );
    }


    return isOk;
  }


}
