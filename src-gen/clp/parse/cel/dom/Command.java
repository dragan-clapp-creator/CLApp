package clp.parse.cel.dom;

import java.io.IOException;

import clp.parse.AParser;
import clp.parse.CLAppParser;
import clp.parse.cel.asm.AsmStatParser;
import clp.parse.cel.graph.GParseParser;
import clp.parse.cel.graph.ast.GAstParser;
import clp.parse.cel.graph.eval.GEvalParser;
import clp.parse.cel.graph.init.GInitParser;
import clp.parse.cel.graph.map.GMapParser;
import clp.parse.cel.graph.rnd.GRenderParser;
import clp.parse.cel.java.JavaParser;
import clp.parse.cel.prt.PrtParser;
import clp.parse.cel.ref.RefStatParser;
import clp.parse.cel.web.WebStatParser;
import clp.parse.ui.VisualizeStatement;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public class Command extends AParser {

  StringBuffer buffer = new StringBuffer();

  // RT declarations
  private clp.run.cel.dom.Command command;

  public clp.run.cel.dom.Command getCommand() {
    return command;
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

    PrtParser bPrtParser = new PrtParser();
    isOk = bPrtParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bPrtParser.getPrintStatement();
      buffer.append( bPrtParser.getRendering()+" " );
      return true;
    }

    RefStatParser bRefStatParser = new RefStatParser();
    isOk = bRefStatParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bRefStatParser.getReflectStatement();
      buffer.append( bRefStatParser.getRendering()+" " );
      return true;
    }

    CutStatement bCutStatement = new CutStatement();
    isOk = bCutStatement.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bCutStatement.getCutStatement();
      buffer.append( bCutStatement.getRendering()+" " );
      return true;
    }

    ExecStatement bExecStatement = new ExecStatement();
    isOk = bExecStatement.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bExecStatement.getExecStatement();
      buffer.append( bExecStatement.getRendering()+" " );
      return true;
    }

    JavaParser bJavaParser = new JavaParser();
    isOk = bJavaParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bJavaParser.getJavaStatement();
      buffer.append( bJavaParser.getRendering()+" " );
      return true;
    }

    WebStatParser bWebStatParser = new WebStatParser();
    isOk = bWebStatParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bWebStatParser.getWebStatement();
      buffer.append( bWebStatParser.getRendering()+" " );
      return true;
    }

    ClappStatement bClappStatement = new ClappStatement();
    isOk = bClappStatement.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bClappStatement.getClappStatement();
      buffer.append( bClappStatement.getRendering()+" " );
      return true;
    }

    SysStatement bSysStatement = new SysStatement();
    isOk = bSysStatement.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bSysStatement.getSysStatement();
      buffer.append( bSysStatement.getRendering()+" " );
      return true;
    }

    AsmStatParser bAsmStatParser = new AsmStatParser();
    isOk = bAsmStatParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bAsmStatParser.getAssignStatement();
      buffer.append( bAsmStatParser.getRendering()+" " );
      return true;
    }

    GParseParser bGParseParser = new GParseParser();
    isOk = bGParseParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bGParseParser.getGParser();
      buffer.append( bGParseParser.getRendering()+" " );
      return true;
    }

    GAstParser bGAstParser = new GAstParser();
    isOk = bGAstParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bGAstParser.getGAssistent();
      buffer.append( bGAstParser.getRendering()+" " );
      return true;
    }

    GMapParser bGMapParser = new GMapParser();
    isOk = bGMapParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bGMapParser.getGMapper();
      buffer.append( bGMapParser.getRendering()+" " );
      return true;
    }

    GRenderParser bGRenderParser = new GRenderParser();
    isOk = bGRenderParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bGRenderParser.getGRenderer();
      buffer.append( bGRenderParser.getRendering()+" " );
      return true;
    }

    GEvalParser bGEvalParser = new GEvalParser();
    isOk = bGEvalParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bGEvalParser.getGEvaluator();
      buffer.append( bGEvalParser.getRendering()+" " );
      return true;
    }

    GInitParser bGInitParser = new GInitParser();
    isOk = bGInitParser.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bGInitParser.getGInit();
      buffer.append( bGInitParser.getRendering()+" " );
      return true;
    }

    VisualizeStatement bVisualizeStatement = new VisualizeStatement();
    isOk = bVisualizeStatement.parse(parser, isOptional);
    if (isOk == Boolean.FALSE) {
      return false;
    }
    if (isOk == Boolean.TRUE) {
      command = bVisualizeStatement.getVisualizeStatement();
      buffer.append( bVisualizeStatement.getRendering()+" " );
      return true;
    }


    if (isOk == null) {
      return isOptional ? null : false;
    }
    return isOk ? true : isOptional ? null : false;
  }


}
