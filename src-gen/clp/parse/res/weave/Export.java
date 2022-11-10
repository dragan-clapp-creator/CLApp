package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Export extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.Export export = new clp.run.res.weave.Export();

  public clp.run.res.weave.Export getExport() {
    return export;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("export", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("export ");
    

      Attribute attribute1 = new Attribute();
        Boolean battribute1 = attribute1.parse(parser, isOptional);

        if (battribute1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( attribute1.getRendering() + " " );
        export.setAttribute(attribute1.getAttribute());

    token = parser.nextToken();
    isOk = match("to", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("to ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    export.setClappVariable(parser.sval);
    
    buffer.append( export.getClappVariable() + " " );

    token = parser.nextToken();
    isOk = match(';', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "; " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
