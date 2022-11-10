package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class TInitList extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.TInitList tinitlist = new clp.run.res.TInitList();

  public clp.run.res.TInitList getTInitList() {
    return tinitlist;
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

      TInit tInit1 = new TInit();
        Boolean btInit1 = tInit1.parse(parser, isOptional);

        if (btInit1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( tInit1.getRendering() + " " );
        tinitlist.setTInit(tInit1.getTInit());

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

    TInit tInit = new TInit();
    isOk = tInit.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    tinitlist.addTInit(tInit.getTInit());
    buffer.append( tInit.getRendering()+" " );


    return isOk;
  }


}
