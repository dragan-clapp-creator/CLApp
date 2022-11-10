package clp.parse.msc;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MscTasksParser extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.msc.MscTasks msctasks = new clp.run.msc.MscTasks();

  public clp.run.msc.MscTasks getMscTasks() {
    return msctasks;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("tasks", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("tasks ");
    

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      isOk = starBlock2(parser, isOptional);
      if (isOk == Boolean.FALSE) {
        return false;
      }

    token = parser.nextToken();
    isOk = match('}', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "} " );



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean starBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock2(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock2(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    MscTaskName mscTaskName = new MscTaskName();
    isOk = mscTaskName.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    msctasks.addMscTaskName(mscTaskName.getMscTaskName());
    buffer.append( mscTaskName.getRendering()+" " );

    token = parser.nextToken();
    isOk = match(';', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "; " );


    return isOk;
  }


}
