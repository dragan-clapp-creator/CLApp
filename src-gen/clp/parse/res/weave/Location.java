package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Location extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.Location location;

  public clp.run.res.weave.Location getLocation() {
    return location;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    isOk = plusBlock(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return parser.errorLog(false);
    }



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }

  private Boolean plusBlock(CLAppParser parser, boolean isOptional) throws IOException {
    int token = 0;
    Boolean isOk = true;

    AtLocation bAtLocation = new AtLocation();
    isOk = bAtLocation.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      location = bAtLocation.getAtLocation();
      buffer.append( bAtLocation.getRendering()+" " );
      return true;
    }

    LineNumber bLineNumber = new LineNumber();
    isOk = bLineNumber.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      location = bLineNumber.getLineNumber();
      buffer.append( bLineNumber.getRendering()+" " );
      return true;
    }

    AboutLocation bAboutLocation = new AboutLocation();
    isOk = bAboutLocation.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      location = bAboutLocation.getAboutLocation();
      buffer.append( bAboutLocation.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
