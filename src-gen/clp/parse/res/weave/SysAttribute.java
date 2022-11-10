package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class SysAttribute extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.SysAttribute sysattribute = new clp.run.res.weave.SysAttribute();

  public clp.run.res.weave.SysAttribute getSysAttribute() {
    return sysattribute;
  }



  @Override
  public Boolean parse(CLAppParser parser, boolean isOptional)
  throws IOException {

    int token = 0;
    Boolean isOk = true;

    token = parser.nextToken();
    isOk = match("SYS", parser.sval);
    if (isOk != Boolean.TRUE) { return parser.errorLog(true); }
    buffer.append("SYS ");
    

      SysRef attribute1 = new SysRef();
        Boolean battribute1 = attribute1.parse(parser, isOptional);

        if (battribute1 != Boolean.TRUE) { return parser.errorLog(false); }
        buffer.append( attribute1.getRendering() + " " );
        sysattribute.setAttribute(attribute1.getSysRef());



    return isOk; 
  }

  public StringBuffer getRendering() {
      return buffer;
  }


}
