package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ExecStatement extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.ExecStatement execstatement = new clp.run.cel.dom.ExecStatement();

  public clp.run.cel.dom.ExecStatement getExecStatement() {
    return execstatement;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("exec", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("exec ");
    

    token = parser.nextToken();
    if (token != '\"') {
      return parser.errorLog(false);
    }
    isOk = true;
    execstatement.setFilename(parser.sval);
    
    buffer.append( execstatement.getFilename() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
