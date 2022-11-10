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
public class UiLabel extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.UiLabel uilabel = new clp.run.res.ui.UiLabel();

  public clp.run.res.ui.UiLabel getUiLabel() {
    return uilabel;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("LABEL", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("LABEL ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    uilabel.setName(parser.sval);
    
    buffer.append( uilabel.getName() + " " );

    token = parser.nextToken();
    isOk = match('<', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "< " );

      CstOrVarIdentifier value3 = new CstOrVarIdentifier();
        Boolean bvalue3 = value3.parse(parser, isOptional);

        if (bvalue3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( value3.getRendering() + " " );
        uilabel.setValue(value3.getCstOrVarIdentifier());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
