package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class DInitList extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.DInitList dinitlist = new clp.run.res.DInitList();

  public clp.run.res.DInitList getDInitList() {
    return dinitlist;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match('{', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( "{ " );

      DInit dInit1 = new DInit();
        Boolean bdInit1 = dInit1.parse(parser, isOptional);

        if (bdInit1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( dInit1.getRendering() + " " );
        dinitlist.setDInit(dInit1.getDInit());

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
 
    token = parser.nextToken();
    isOk = match(',', token);
    if (isOk != Boolean.TRUE) {  return parser.errorLog(true); }
    buffer.append( ", " );

    DInit dInit = new DInit();
    isOk = dInit.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    dinitlist.addDInit(dInit.getDInit());
    buffer.append( dInit.getRendering()+" " );


    return isOk;
  }


}
