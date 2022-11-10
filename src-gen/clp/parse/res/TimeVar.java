package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TimeVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.TimeVar timevar;

  public clp.run.res.TimeVar getTimeVar() {
    return timevar;
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

    ArrayTVar bArrayTVar = new ArrayTVar();
    isOk = bArrayTVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      timevar = bArrayTVar.getArrayTVar();
      buffer.append( bArrayTVar.getRendering()+" " );
      return true;
    }

    SimpleTVar bSimpleTVar = new SimpleTVar();
    isOk = bSimpleTVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      timevar = bSimpleTVar.getSimpleTVar();
      buffer.append( bSimpleTVar.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
