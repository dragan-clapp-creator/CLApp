package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class LInitList extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.LInitList linitlist = new clp.run.res.LInitList();

  public clp.run.res.LInitList getLInitList() {
    return linitlist;
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

      Lsigned lsigned1 = new Lsigned();
        Boolean blsigned1 = lsigned1.parse(parser, isOptional);

        if (blsigned1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( lsigned1.getRendering() + " " );
        linitlist.setLsigned(lsigned1.getLsigned());

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

    Lsigned lsigned = new Lsigned();
    isOk = lsigned.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    linitlist.addLsigned(lsigned.getLsigned());
    buffer.append( lsigned.getRendering()+" " );


    return isOk;
  }


}
