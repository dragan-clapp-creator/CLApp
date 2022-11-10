package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiButton extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.UiButton uibutton = new clp.run.res.ui.UiButton();

  public clp.run.res.ui.UiButton getUiButton() {
    return uibutton;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("BUTTON", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("BUTTON ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    uibutton.setName(parser.sval);
    
    buffer.append( uibutton.getName() + " " );

      IsRollback isRollback2 = new IsRollback();
      isOk = isRollback2.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        uibutton.setIsRollback(isRollback2.getIsRollback());
        uibutton.setIsIsRollback(true);
        buffer.append( isRollback2.getRendering()+" " );
      }
      isOk = true;

      isOk = hutBlock3(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

      isOk = hutBlock4(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match("caption", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("caption ");
    uibutton.setIsCaption(true);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      uibutton.setTitle(parser.sval);
      uibutton.setIsTitle(true);
      buffer.append( uibutton.getTitle() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }

  private Boolean hutBlock4(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match('>', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "> " );
    uibutton.setChar((char)token);

      VarIdentifier varIdentifier1 = new VarIdentifier();
      isOk = varIdentifier1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      uibutton.setVarIdentifier(varIdentifier1.getVarIdentifier());
      uibutton.setIsVarIdentifier(true);
      buffer.append( varIdentifier1.getRendering()+" " );


    return true;
  }


}
