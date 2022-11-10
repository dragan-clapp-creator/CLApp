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
public class S_constant extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.exp.S_constant s_constant = new clp.run.cel.exp.S_constant();

  public clp.run.cel.exp.S_constant getS_constant() {
    return s_constant;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    if (token != '\"') {
      return parser.errorLog(true);
    }
    isOk = true;
    s_constant.setConstant(parser.sval);
    
    buffer.append( s_constant.getConstant() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
