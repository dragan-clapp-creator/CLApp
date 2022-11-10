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
public class UiInputField extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.UiInputField uiinputfield = new clp.run.res.ui.UiInputField();

  public clp.run.res.ui.UiInputField getUiInputField() {
    return uiinputfield;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("FIELD", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("FIELD ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    uiinputfield.setName(parser.sval);
    
    buffer.append( uiinputfield.getName() + " " );

      IsRequired isRequired2 = new IsRequired();
      isOk = isRequired2.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        uiinputfield.setIsRequired(isRequired2.getIsRequired());
        uiinputfield.setIsIsRequired(true);
        buffer.append( isRequired2.getRendering()+" " );
      }
      isOk = true;

      IsEnabled isEnabled3 = new IsEnabled();
      isOk = isEnabled3.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        uiinputfield.setIsEnabled(isEnabled3.getIsEnabled());
        uiinputfield.setIsIsEnabled(true);
        buffer.append( isEnabled3.getRendering()+" " );
      }
      isOk = true;

      IsPassword isPassword4 = new IsPassword();
      isOk = isPassword4.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        uiinputfield.setIsPassword(isPassword4.getIsPassword());
        uiinputfield.setIsIsPassword(true);
        buffer.append( isPassword4.getRendering()+" " );
      }
      isOk = true;

      isOk = hutBlock5(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean hutBlock5(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match('=', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "= " );
    uiinputfield.setChar((char)token);

      VarIdentifier varIdentifier1 = new VarIdentifier();
      isOk = varIdentifier1.parse(parser, true);
      if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
      uiinputfield.setVarIdentifier(varIdentifier1.getVarIdentifier());
      uiinputfield.setIsVarIdentifier(true);
      buffer.append( varIdentifier1.getRendering()+" " );


    return true;
  }


}
