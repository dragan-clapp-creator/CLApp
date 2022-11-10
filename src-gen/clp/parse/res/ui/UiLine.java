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
public class UiLine extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.UiLine uiline = new clp.run.res.ui.UiLine();

  public clp.run.res.ui.UiLine getUiLine() {
    return uiline;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("LINE", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("LINE ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(false);
    }
    isOk = true;
    uiline.setNumber((int) parser.nval);
    
    buffer.append( uiline.getNumber() + " " );

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      UiDefType uiDefType3 = new UiDefType();
        Boolean buiDefType3 = uiDefType3.parse(parser, isOptional);

        if (buiDefType3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( uiDefType3.getRendering() + " " );
        uiline.setUiDefType(uiDefType3.getUiDefType());

      isOk = starBlock4(parser, isOptional);
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

    UiDefType uiDefType = new UiDefType();
    isOk = uiDefType.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    uiline.addUiDefType(uiDefType.getUiDefType());
    buffer.append( uiDefType.getRendering()+" " );


    return isOk;
  }


}
