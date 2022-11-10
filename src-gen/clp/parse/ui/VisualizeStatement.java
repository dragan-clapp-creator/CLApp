package clp.parse.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class VisualizeStatement extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.ui.VisualizeStatement visualizestatement = new clp.run.ui.VisualizeStatement();

  public clp.run.ui.VisualizeStatement getVisualizeStatement() {
    return visualizestatement;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("show", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("show ");
    

    token = parser.nextToken();
    isOk = match("keeping", parser.sval);
    if (isOk == Boolean.TRUE) {
      buffer.append("keeping ");
      visualizestatement.setIsKeeping(true);
      
    }
    else {
      parser.pushBack();
      isOk = true;
    }
    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    visualizestatement.setUiname(parser.sval);
    
    buffer.append( visualizestatement.getUiname() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
