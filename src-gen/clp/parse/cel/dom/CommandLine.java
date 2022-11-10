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
public class CommandLine extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.CommandLine commandline = new clp.run.cel.dom.CommandLine();

  public clp.run.cel.dom.CommandLine getCommandLine() {
    return commandline;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

      isOk = hutBlock0(parser, true) != Boolean.FALSE;
      if (!isOk) {
        return false;
      }

      Command command1 = new Command();
        Boolean bcommand1 = command1.parse(parser, isOptional);

        if (bcommand1 != Boolean.TRUE) { return parser.errorLog(true); }
        buffer.append( command1.getRendering() + " " );
        commandline.setCommand(command1.getCommand());

      isOk = hutBlock2(parser, true) != Boolean.FALSE;
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

  private Boolean hutBlock0(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER && token != '\"') { return parser.errorLog(true); }
    commandline.setLevel((int) parser.nval);
    commandline.setIsLevel(true);
    buffer.append( commandline.getLevel() + " " );

    token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ": " );
    commandline.setChar((char)token);


    return true;
  }

  private Boolean hutBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

     token = parser.nextToken();
    isOk = match(':', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ": " );
    commandline.setChar((char)token);

    token = parser.nextToken();
    if (token != StreamTokenizer.TT_NUMBER && token != '\"') { return parser.errorLog(true); }
    commandline.setNext((int) parser.nval);
    commandline.setIsNext(true);
    buffer.append( commandline.getNext() + " " );


    return true;
  }


}
