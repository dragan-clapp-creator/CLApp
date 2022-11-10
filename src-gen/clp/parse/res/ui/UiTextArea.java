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
public class UiTextArea extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.UiTextArea uitextarea = new clp.run.res.ui.UiTextArea();

  public clp.run.res.ui.UiTextArea getUiTextArea() {
    return uitextarea;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("TEXT", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("TEXT ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    uitextarea.setName(parser.sval);
    
    buffer.append( uitextarea.getName() + " " );

    token = parser.nextToken();
    isOk = match('<', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "< " );

      CstOrVarIdentifier value3 = new CstOrVarIdentifier();
        Boolean bvalue3 = value3.parse(parser, isOptional);

        if (bvalue3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( value3.getRendering() + " " );
        uitextarea.setValue(value3.getCstOrVarIdentifier());

      isOk = starBlock4(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean starBlock4(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock4(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock4(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    VarIdentifier varIdentifier = new VarIdentifier();
    isOk = varIdentifier.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    uitextarea.addVarIdentifier(varIdentifier.getVarIdentifier());
    buffer.append( varIdentifier.getRendering()+" " );


    return isOk;
  }


}
