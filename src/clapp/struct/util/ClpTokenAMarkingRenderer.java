package clapp.struct.util;

import java.util.ArrayList;

import clp.run.cel.log.InitialATokens;
import clp.run.cel.log.LogicalTerm;
import clp.run.cel.log.LogicalTerms;
import clp.run.cel.log.TokenAMarkingVisitor;

public class ClpTokenAMarkingRenderer implements TokenAMarkingVisitor {

  private ArrayList<LogicalTerm> terms;

  public ClpTokenAMarkingRenderer() {
    terms = new ArrayList<>();
  }

  @Override
  public void visitInitialATokens(InitialATokens x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitLogicalTerms(LogicalTerms x) {
    if (x.getLogicalTerm() != null) {
      terms.add(x.getLogicalTerm());
    }
    terms.addAll( x.getLogicalTerms() );
  }

  public String render(ReflectUtility ru) {
    StringBuilder sb = new StringBuilder();
    if (terms != null) {
      for (LogicalTerm lterm : terms) {
        sb.append(ru.getTerm(lterm));
      }
    }
    return sb.toString();
  }

}
