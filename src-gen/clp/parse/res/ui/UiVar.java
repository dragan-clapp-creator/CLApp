package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.VarType;
import clp.parse.res.weave.CstOrVar;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.UiVar uivar = new clp.run.res.ui.UiVar();

  public clp.run.res.ui.UiVar getUiVar() {
    return uivar;
  }

  private clp.run.res.VarType refVarType = clp.run.res.VarType.TUI;

  public clp.run.res.VarType getVarType() {
    return refVarType;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      VarType TUI0 = new VarType();
      TUI0.setVarType(refVarType);
        Boolean bTUI0 = TUI0.parse(parser, isOptional);

        if (bTUI0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( TUI0.getRendering() + " " );
        uivar.setTUI(TUI0.getVarType());

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    uivar.setName(parser.sval);
    
    buffer.append( uivar.getName() + " " );

      isOk = hutBlock2(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      UiBundle uiBundle4 = new UiBundle();
        Boolean buiBundle4 = uiBundle4.parse(parser, isOptional);

        if (buiBundle4 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( uiBundle4.getRendering() + " " );
        uivar.setUiBundle(uiBundle4.getUiBundle());

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
    isOk = match("title", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("title ");
    uivar.setIsTitle(true);

      CstOrVar title1 = new CstOrVar();
      isOk = title1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      uivar.setTitle(title1.getCstOrVar());
      uivar.setIsCstOrVar(true);
      buffer.append( title1.getRendering()+" " );


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
 
    UiBundle uiBundle = new UiBundle();
    isOk = uiBundle.parse(parser, true);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      uivar.addUiBundle(uiBundle.getUiBundle());
      buffer.append( uiBundle.getRendering()+" " );
    }


    return isOk;
  }


}
