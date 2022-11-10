package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MscOutput extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.MscOutput mscoutput = new clp.run.msc.MscOutput();

  public clp.run.msc.MscOutput getMscOutput() {
    return mscoutput;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("output", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("output ");
    

    token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ": " );

      Output output2 = new Output();
        Boolean boutput2 = output2.parse(parser, isOptional);

        if (boutput2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( output2.getRendering() + " " );
        mscoutput.setOutput(output2.getOutput());

      isOk = starBlock3(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }

    token = parser.nextToken();
    isOk = match(';', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "; " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean starBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock3(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    Output output = new Output();
    isOk = output.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    mscoutput.addOutput(output.getOutput());
    buffer.append( output.getRendering()+" " );


    return isOk;
  }


}
