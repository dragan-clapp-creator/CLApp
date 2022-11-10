package clp.parse.res.ui;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class CstOrVarIdentifier extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.ui.CstOrVarIdentifier cstorvaridentifier;

  public clp.run.res.ui.CstOrVarIdentifier getCstOrVarIdentifier() {
    return cstorvaridentifier;
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

    Literal bLiteral = new Literal();
    isOk = bLiteral.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      cstorvaridentifier = bLiteral.getLiteral();
      buffer.append( bLiteral.getRendering()+" " );
      return true;
    }

    VarIdentifier bVarIdentifier = new VarIdentifier();
    isOk = bVarIdentifier.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      cstorvaridentifier = bVarIdentifier.getVarIdentifier();
      buffer.append( bVarIdentifier.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
