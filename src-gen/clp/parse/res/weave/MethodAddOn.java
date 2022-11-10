package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class MethodAddOn extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.MethodAddOn methodaddon;

  public clp.run.res.weave.MethodAddOn getMethodAddOn() {
    return methodaddon;
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

    Export bExport = new Export();
    isOk = bExport.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      methodaddon = bExport.getExport();
      buffer.append( bExport.getRendering()+" " );
      return true;
    }

    Notification bNotification = new Notification();
    isOk = bNotification.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      methodaddon = bNotification.getNotification();
      buffer.append( bNotification.getRendering()+" " );
      return true;
    }

    CopyFromStream bCopyFromStream = new CopyFromStream();
    isOk = bCopyFromStream.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      methodaddon = bCopyFromStream.getCopyFromStream();
      buffer.append( bCopyFromStream.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
