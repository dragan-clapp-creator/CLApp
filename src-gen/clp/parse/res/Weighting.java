package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Weighting extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Weighting weighting = new clp.run.res.Weighting();

  public clp.run.res.Weighting getWeighting() {
    return weighting;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (token == StreamTokenizer.TT_NUMBER && parser.nval >= 0 && parser.nval < 10) {
      weighting.setMark((char) (48+parser.nval));
    }
    else if (parser.sval.length() == 1) {
      weighting.setMark((char) parser.sval.charAt(0));
    }
    else {
      return parser.errorLog(true);
    }
    
    buffer.append( weighting.getMark() + " " );

    token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( ": " );

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(false);
    }
    isOk = true;
    weighting.setWeight((int) parser.nval);
    
    buffer.append( weighting.getWeight() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
