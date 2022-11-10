package clp.parse.res.weave;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import java.io.StreamTokenizer;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CstOrVar extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.weave.CstOrVar cstorvar = new clp.run.res.weave.CstOrVar();

  public clp.run.res.weave.CstOrVar getCstOrVar() {
    return cstorvar;
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

    token = parser.nextToken();
    if (token == '\"') {
      cstorvar.setCst(parser.sval);
      buffer.append( cstorvar.getCst() + " " );
      return isOk != Boolean.FALSE; 
    }
    else {
      parser.pushBack();
    }
    token = parser.nextToken();
    if (token == StreamTokenizer.TT_WORD) {
      cstorvar.setId(parser.sval);
      buffer.append( cstorvar.getId() + " " );
      return true; 
    }
    else {
      parser.pushBack();
    }

    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
