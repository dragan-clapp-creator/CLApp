package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class FInitList extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.FInitList finitlist = new clp.run.res.FInitList();

  public clp.run.res.FInitList getFInitList() {
    return finitlist;
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

      Fsigned fsigned1 = new Fsigned();
        Boolean bfsigned1 = fsigned1.parse(parser, isOptional);

        if (bfsigned1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( fsigned1.getRendering() + " " );
        finitlist.setFsigned(fsigned1.getFsigned());

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

    Fsigned fsigned = new Fsigned();
    isOk = fsigned.parse(parser, true);
    if (isOk != Boolean.TRUE) { return parser.errorLog(false); }
    finitlist.addFsigned(fsigned.getFsigned());
    buffer.append( fsigned.getRendering()+" " );


    return isOk;
  }


}
