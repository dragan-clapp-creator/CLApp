package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class ExecutionDomain extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.ExecutionDomain executiondomain = new clp.run.cel.dom.ExecutionDomain();

  public clp.run.cel.dom.ExecutionDomain getExecutionDomain() {
    return executiondomain;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      isOk = starBlock0(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean starBlock0(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock0(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock0(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    CommandLine commandLine = new CommandLine();
    isOk = commandLine.parse(parser, true);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      executiondomain.addCommandLine(commandLine.getCommandLine());
      buffer.append( commandLine.getRendering()+" " );
    }


    return isOk;
  }


}
