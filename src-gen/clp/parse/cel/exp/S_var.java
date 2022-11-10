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
public class S_var extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.S_var s_var = new clp.run.cel.exp.S_var();

  public clp.run.cel.exp.S_var getS_var() {
    return s_var;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_WORD) {
      return parser.errorLog(true);
    }
    isOk = true;
    s_var.setVar(parser.sval);
    
    buffer.append( s_var.getVar() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
