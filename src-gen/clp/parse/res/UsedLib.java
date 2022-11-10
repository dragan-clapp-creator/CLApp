package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UsedLib extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.UsedLib usedlib = new clp.run.res.UsedLib();

  public clp.run.res.UsedLib getUsedLib() {
    return usedlib;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("usedJavaList", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("usedJavaList ");
    

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(false); }
    buffer.append( "{ " );

      UsedJava usedJava2 = new UsedJava();
        Boolean busedJava2 = usedJava2.parse(parser, isOptional);

        if (busedJava2 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( usedJava2.getRendering() + " " );
        usedlib.setUsedJava(usedJava2.getUsedJava());

      isOk = starBlock3(parser, isOptional);
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

  private Boolean starBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    Boolean found = true;
    while (found == Boolean.TRUE) {
      found = groupBlock3(parser, isOptional);
    }
    return found != Boolean.FALSE;
  }

  private Boolean groupBlock3(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    UsedJava usedJava = new UsedJava();
    isOk = usedJava.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    usedlib.addUsedJava(usedJava.getUsedJava());
    buffer.append( usedJava.getRendering()+" " );


    return isOk;
  }


}
