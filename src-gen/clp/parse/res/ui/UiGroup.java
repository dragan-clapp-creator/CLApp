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
public class UiGroup extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.UiGroup uigroup = new clp.run.res.ui.UiGroup();

  public clp.run.res.ui.UiGroup getUiGroup() {
    return uigroup;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("GROUP", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("GROUP ");
    

    token = parser.nextToken();
    if (token != '\"') {
      return parser.errorLog(false);
    }
    isOk = true;
    uigroup.setTitle(parser.sval);
    
    buffer.append( uigroup.getTitle() + " " );

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      UiBundle uiBundle3 = new UiBundle();
        Boolean buiBundle3 = uiBundle3.parse(parser, isOptional);

        if (buiBundle3 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( uiBundle3.getRendering() + " " );
        uigroup.setUiBundle(uiBundle3.getUiBundle());

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
 
    UiBundle uiBundle = new UiBundle();
    isOk = uiBundle.parse(parser, true);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      uigroup.addUiBundle(uiBundle.getUiBundle());
      buffer.append( uiBundle.getRendering()+" " );
    }


    return isOk;
  }


}
