package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SendFile extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.SendFile sendfile = new clp.run.msc.SendFile();

  public clp.run.msc.SendFile getSendFile() {
    return sendfile;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("FILE", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("FILE ");
    

    token = parser.nextToken();
    if (token != '\"') {
      return parser.errorLog(false);
    }
    isOk = true;
    sendfile.setFileName(parser.sval);
    
    buffer.append( sendfile.getFileName() + " " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
