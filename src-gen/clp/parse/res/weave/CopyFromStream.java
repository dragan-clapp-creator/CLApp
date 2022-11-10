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
public class CopyFromStream extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.CopyFromStream copyfromstream = new clp.run.res.weave.CopyFromStream();

  public clp.run.res.weave.CopyFromStream getCopyFromStream() {
    return copyfromstream;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("copyFromStream", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("copyFromStream ");
    

      Attribute attribute1 = new Attribute();
        Boolean battribute1 = attribute1.parse(parser, isOptional);

        if (battribute1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( attribute1.getRendering() + " " );
        copyfromstream.setAttribute(attribute1.getAttribute());

    token = parser.nextToken();
    isOk = match("to", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("to ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    copyfromstream.setClappVariable(parser.sval);
    
    buffer.append( copyfromstream.getClappVariable() + " " );

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
