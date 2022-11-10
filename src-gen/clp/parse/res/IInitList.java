package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class IInitList extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.IInitList iinitlist = new clp.run.res.IInitList();

  public clp.run.res.IInitList getIInitList() {
    return iinitlist;
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

      Isigned isigned1 = new Isigned();
        Boolean bisigned1 = isigned1.parse(parser, isOptional);

        if (bisigned1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( isigned1.getRendering() + " " );
        iinitlist.setIsigned(isigned1.getIsigned());

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

    Isigned isigned = new Isigned();
    isOk = isigned.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    iinitlist.addIsigned(isigned.getIsigned());
    buffer.append( isigned.getRendering()+" " );


    return isOk;
  }


}
