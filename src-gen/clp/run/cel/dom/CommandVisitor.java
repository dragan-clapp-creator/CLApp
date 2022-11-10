package clp.run.cel.dom;

import clp.run.cel.asm.AssignStatement;
import clp.run.cel.graph.GParser;
import clp.run.cel.graph.eval.GEvaluator;
import clp.run.cel.graph.init.GInit;
import clp.run.cel.graph.map.GAssistent;
import clp.run.cel.graph.map.GMapper;
import clp.run.cel.graph.rnd.GRenderer;
import clp.run.cel.java.JavaStatement;
import clp.run.cel.prt.PrintStatement;
import clp.run.cel.ref.ReflectStatement;
import clp.run.cel.web.WebStatement;
import clp.run.ui.VisualizeStatement;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public interface CommandVisitor {

   //=== Methods ================================================================

   public void visitPrintStatement(PrintStatement x);
   public void visitReflectStatement(ReflectStatement x);
   public void visitCutStatement(CutStatement x);
   public void visitExecStatement(ExecStatement x);
   public void visitJavaStatement(JavaStatement x);
   public void visitWebStatement(WebStatement x);
   public void visitClappStatement(ClappStatement x);
   public void visitSysStatement(SysStatement x);
   public void visitAssignStatement(AssignStatement x);
   public void visitGraphParseStatement(GParser x);
   public void visitGraphAssistStatement(GAssistent x);
   public void visitGraphMapStatement(GMapper x);
   public void visitGraphRenderStatement(GRenderer x);
   public void visitGraphEvaluateStatement(GEvaluator x);
   public void visitGraphReinitStatement(GInit x);
   public void visitVisualizeStatement(VisualizeStatement x);


}
