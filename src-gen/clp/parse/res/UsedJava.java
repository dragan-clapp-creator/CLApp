package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class UsedJava extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.UsedJava usedjava;

  public clp.run.res.UsedJava getUsedJava() {
    return usedjava;
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

    Binpath bBinpath = new Binpath();
    isOk = bBinpath.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      usedjava = bBinpath.getBinpath();
      buffer.append( bBinpath.getRendering()+" " );
      return true;
    }

    Jarpath bJarpath = new Jarpath();
    isOk = bJarpath.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      usedjava = bJarpath.getJarpath();
      buffer.append( bJarpath.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
