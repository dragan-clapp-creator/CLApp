package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Attribute extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.Attribute attribute;

  public clp.run.res.weave.Attribute getAttribute() {
    return attribute;
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

    LocalAttribute bLocalAttribute = new LocalAttribute();
    isOk = bLocalAttribute.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      attribute = bLocalAttribute.getLocalAttribute();
      buffer.append( bLocalAttribute.getRendering()+" " );
      return true;
    }

    GlobalAttribute bGlobalAttribute = new GlobalAttribute();
    isOk = bGlobalAttribute.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      attribute = bGlobalAttribute.getGlobalAttribute();
      buffer.append( bGlobalAttribute.getRendering()+" " );
      return true;
    }

    SysAttribute bSysAttribute = new SysAttribute();
    isOk = bSysAttribute.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      attribute = bSysAttribute.getSysAttribute();
      buffer.append( bSysAttribute.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
