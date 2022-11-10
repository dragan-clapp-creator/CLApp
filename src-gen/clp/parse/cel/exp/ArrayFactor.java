package clp.parse.cel.exp;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ArrayFactor extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.ArrayFactor arrayfactor = new clp.run.cel.exp.ArrayFactor();

  public clp.run.cel.exp.ArrayFactor getArrayFactor() {
    return arrayfactor;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match('[', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "[ " );

      SimpleFactor simpleFactor1 = new SimpleFactor();
        Boolean bsimpleFactor1 = simpleFactor1.parse(parser, isOptional);

        if (bsimpleFactor1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( simpleFactor1.getRendering() + " " );
        arrayfactor.setSimpleFactor(simpleFactor1.getSimpleFactor());

      isOk = starBlock2(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }

    token = parser.nextToken();
    isOk = match(']', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "] " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean starBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock2(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    SimpleFactor simpleFactor = new SimpleFactor();
    isOk = simpleFactor.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    arrayfactor.addSimpleFactor(simpleFactor.getSimpleFactor());
    buffer.append( simpleFactor.getRendering()+" " );


    return isOk;
  }


}
