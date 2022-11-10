package clp.parse.cel.exp;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Factor extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.Factor factor;

  public clp.run.cel.exp.Factor getFactor() {
    return factor;
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

    SimpleFactor bSimpleFactor = new SimpleFactor();
    isOk = bSimpleFactor.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      factor = bSimpleFactor.getSimpleFactor();
      buffer.append( bSimpleFactor.getRendering()+" " );
      return true;
    }

    ArrayFactor bArrayFactor = new ArrayFactor();
    isOk = bArrayFactor.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      factor = bArrayFactor.getArrayFactor();
      buffer.append( bArrayFactor.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
