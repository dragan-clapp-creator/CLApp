package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Output extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.Output output = new clp.run.msc.Output();

  public clp.run.msc.Output getOutput() {
    return output;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      OutputTarget outputTarget0 = new OutputTarget();
        Boolean boutputTarget0 = outputTarget0.parse(parser, isOptional);

        if (boutputTarget0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( outputTarget0.getRendering() + " " );
        output.setOutputTarget(outputTarget0.getOutputTarget());

      Out out1 = new Out();
      isOk = out1.parse(parser, true);
      if (isOk == Boolean.FALSE) {
        return false;
      }
      if (isOk == Boolean.TRUE) {
        output.setOut(out1.getOut());
        output.setIsOut(true);
        buffer.append( out1.getRendering()+" " );
      }
      isOk = true;

    token = parser.nextToken();
    if (token != '\"' || !parser.sval.matches("#[0-9|a-f|A-F]{6}")) {
      return parser.errorLog(false);
    }
    isOk = true;
    output.setColor(parser.sval);
    buffer.append( output.getColor() + " " );

    token = parser.nextToken();
    isOk = match('/', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "/ " );

    token = parser.nextToken();
    if (token != '\"' || !parser.sval.matches("#[0-9|a-f|A-F]{6}")) {
      return parser.errorLog(false);
    }
    isOk = true;
    output.setBackground(parser.sval);
    buffer.append( output.getBackground() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
