package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Setting extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Setting setting;

  public clp.run.res.Setting getSetting() {
    return setting;
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

    BVariable bBVariable = new BVariable();
    isOk = bBVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bBVariable.getBVariable();
      buffer.append( bBVariable.getRendering()+" " );
      return true;
    }

    FVariable bFVariable = new FVariable();
    isOk = bFVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bFVariable.getFVariable();
      buffer.append( bFVariable.getRendering()+" " );
      return true;
    }

    IVariable bIVariable = new IVariable();
    isOk = bIVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bIVariable.getIVariable();
      buffer.append( bIVariable.getRendering()+" " );
      return true;
    }

    LVariable bLVariable = new LVariable();
    isOk = bLVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bLVariable.getLVariable();
      buffer.append( bLVariable.getRendering()+" " );
      return true;
    }

    SVariable bSVariable = new SVariable();
    isOk = bSVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bSVariable.getSVariable();
      buffer.append( bSVariable.getRendering()+" " );
      return true;
    }

    DVariable bDVariable = new DVariable();
    isOk = bDVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bDVariable.getDVariable();
      buffer.append( bDVariable.getRendering()+" " );
      return true;
    }

    TVariable bTVariable = new TVariable();
    isOk = bTVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bTVariable.getTVariable();
      buffer.append( bTVariable.getRendering()+" " );
      return true;
    }

    HVariable bHVariable = new HVariable();
    isOk = bHVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bHVariable.getHVariable();
      buffer.append( bHVariable.getRendering()+" " );
      return true;
    }

    PVariable bPVariable = new PVariable();
    isOk = bPVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bPVariable.getPVariable();
      buffer.append( bPVariable.getRendering()+" " );
      return true;
    }

    WebVariable bWebVariable = new WebVariable();
    isOk = bWebVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bWebVariable.getWebVariable();
      buffer.append( bWebVariable.getRendering()+" " );
      return true;
    }

    EVariable bEVariable = new EVariable();
    isOk = bEVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      setting = bEVariable.getEVariable();
      buffer.append( bEVariable.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
