package clp.run.res;

import clp.run.res.graph.Graph;
import clp.run.res.ui.UiVar;
import clp.run.res.weave.WeaveVar;


/**
 * Code generated using AKDL
 * @author Dragan Matic
 *
 */
public interface VariableVisitor {

   //=== Methods ================================================================

   public void visitBVar(BVar x);
   public void visitFVar(FVar x);
   public void visitIVar(IVar x);
   public void visitLVar(LVar x);
   public void visitSVar(SVar x);
   public void visitDVar(DVar x);
   public void visitTVar(TVar x);
   public void visitHVar(HVar x);
   public void visitPVar(PVar x);
   public void visitWebVariable(WebVariable x);
   public void visitGraph(Graph x);
   public void visitWeaveVar(WeaveVar x);
   public void visitUiVar(UiVar x);


}
