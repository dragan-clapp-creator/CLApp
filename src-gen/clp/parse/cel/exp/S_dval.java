package clp.parse.cel.exp;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class S_dval extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.S_dval s_dval = new clp.run.cel.exp.S_dval();

  public clp.run.cel.exp.S_dval getS_dval() {
    return s_dval;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("D", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("D ");
    

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(false);
    }
    isOk = true;
    s_dval.setDay((int) parser.nval);
    
    buffer.append( s_dval.getDay() + " " );

    token = parser.nextToken();
    isOk = match('/', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "/ " );

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(false);
    }
    isOk = true;
    s_dval.setMonth((int) parser.nval);
    
    buffer.append( s_dval.getMonth() + " " );

    token = parser.nextToken();
    isOk = match('/', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "/ " );

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER) {
      return parser.errorLog(false);
    }
    isOk = true;
    s_dval.setYear((int) parser.nval);
    
    buffer.append( s_dval.getYear() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
