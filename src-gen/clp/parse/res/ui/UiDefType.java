package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UiDefType extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.UiDefType uideftype;

  public clp.run.res.ui.UiDefType getUiDefType() {
    return uideftype;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    isOk = plusBlock(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return parser.errorLog(false);
    }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean plusBlock(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

    UiLabel bUiLabel = new UiLabel();
    isOk = bUiLabel.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      uideftype = bUiLabel.getUiLabel();
      buffer.append( bUiLabel.getRendering()+" " );
      return true;
    }

    UiInputField bUiInputField = new UiInputField();
    isOk = bUiInputField.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      uideftype = bUiInputField.getUiInputField();
      buffer.append( bUiInputField.getRendering()+" " );
      return true;
    }

    UiTextArea bUiTextArea = new UiTextArea();
    isOk = bUiTextArea.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      uideftype = bUiTextArea.getUiTextArea();
      buffer.append( bUiTextArea.getRendering()+" " );
      return true;
    }

    UiTable bUiTable = new UiTable();
    isOk = bUiTable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      uideftype = bUiTable.getUiTable();
      buffer.append( bUiTable.getRendering()+" " );
      return true;
    }

    UiButton bUiButton = new UiButton();
    isOk = bUiButton.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      uideftype = bUiButton.getUiButton();
      buffer.append( bUiButton.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
