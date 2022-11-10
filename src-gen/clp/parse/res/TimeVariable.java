package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TimeVariable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.TimeVariable timevariable;

  public clp.run.res.TimeVariable getTimeVariable() {
    return timevariable;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    isOk = plusBlock(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return parser.errorLog(false);
    }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean plusBlock(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

    ArrayTVariable bArrayTVariable = new ArrayTVariable();
    isOk = bArrayTVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      timevariable = bArrayTVariable.getArrayTVariable();
      buffer.append( bArrayTVariable.getRendering()+" " );
      return true;
    }

    SimpleTVariable bSimpleTVariable = new SimpleTVariable();
    isOk = bSimpleTVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      timevariable = bSimpleTVariable.getSimpleTVariable();
      buffer.append( bSimpleTVariable.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
