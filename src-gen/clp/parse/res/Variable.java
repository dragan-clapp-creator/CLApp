package clp.parse.res;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.res.graph.Graph;
import clp.parse.res.ui.UiVar;
import clp.parse.res.weave.WeaveVar;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Variable extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.res.Variable variable;

  public clp.run.res.Variable getVariable() {
    return variable;
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

    BVar bBVar = new BVar();
    isOk = bBVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bBVar.getBVar();
      buffer.append( bBVar.getRendering()+" " );
      return true;
    }

    FVar bFVar = new FVar();
    isOk = bFVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bFVar.getFVar();
      buffer.append( bFVar.getRendering()+" " );
      return true;
    }

    IVar bIVar = new IVar();
    isOk = bIVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bIVar.getIVar();
      buffer.append( bIVar.getRendering()+" " );
      return true;
    }

    LVar bLVar = new LVar();
    isOk = bLVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bLVar.getLVar();
      buffer.append( bLVar.getRendering()+" " );
      return true;
    }

    SVar bSVar = new SVar();
    isOk = bSVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bSVar.getSVar();
      buffer.append( bSVar.getRendering()+" " );
      return true;
    }

    DVar bDVar = new DVar();
    isOk = bDVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bDVar.getDVar();
      buffer.append( bDVar.getRendering()+" " );
      return true;
    }

    TVar bTVar = new TVar();
    isOk = bTVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bTVar.getTVar();
      buffer.append( bTVar.getRendering()+" " );
      return true;
    }

    HVar bHVar = new HVar();
    isOk = bHVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bHVar.getHVar();
      buffer.append( bHVar.getRendering()+" " );
      return true;
    }

    PVar bPVar = new PVar();
    isOk = bPVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bPVar.getPVar();
      buffer.append( bPVar.getRendering()+" " );
      return true;
    }

    WebVariable bWebVariable = new WebVariable();
    isOk = bWebVariable.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bWebVariable.getWebVariable();
      buffer.append( bWebVariable.getRendering()+" " );
      return true;
    }

    Graph bGraph = new Graph();
    isOk = bGraph.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bGraph.getGraph();
      buffer.append( bGraph.getRendering()+" " );
      return true;
    }

    WeaveVar bWeaveVar = new WeaveVar();
    isOk = bWeaveVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bWeaveVar.getWeaveVar();
      buffer.append( bWeaveVar.getRendering()+" " );
      return true;
    }

    UiVar bUiVar = new UiVar();
    isOk = bUiVar.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      variable = bUiVar.getUiVar();
      buffer.append( bUiVar.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
