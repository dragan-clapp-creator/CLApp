package clp.parse.scn;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ScnTask extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.scn.ScnTask scntask = new clp.run.scn.ScnTask();

  public clp.run.scn.ScnTask getScnTask() {
    return scntask;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      ScnTaskName scnTaskName0 = new ScnTaskName();
        Boolean bscnTaskName0 = scnTaskName0.parse(parser, isOptional);

        if (bscnTaskName0 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( scnTaskName0.getRendering() + " " );
        scntask.setScnTaskName(scnTaskName0.getScnTaskName());

    token = parser.nextToken();
    isOk = match("operatingOn", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    buffer.append("operatingOn ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(false);
    }
    isOk = true;
    scntask.setOperOn(parser.sval);
    
    buffer.append( scntask.getOperOn() + " " );

      isOk = hutBlock3(parser, true) != Boolean.FALSE;
      if (!isOk) {
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

  private Boolean hutBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match("passingTo", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("passingTo ");
    scntask.setIsPassingTo(true);

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD || token == '\"') {
      scntask.setPassTo(parser.sval);
      scntask.setIsPassTo(true);
      buffer.append( scntask.getPassTo() + " " );
    }
    else {
      parser.pushBack();
    }

    return true;
  }


}
