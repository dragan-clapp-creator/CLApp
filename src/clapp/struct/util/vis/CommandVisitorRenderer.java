package clapp.struct.util.vis;

import java.util.ArrayList;
import java.util.List;

import clapp.run.util.ResourceUtility;
import clapp.struct.util.ReflectUtility;
import clp.run.cel.asm.AssignStatement;
import clp.run.cel.dom.ClappStatement;
import clp.run.cel.dom.CommandVisitor;
import clp.run.cel.dom.CutStatement;
import clp.run.cel.dom.ExecStatement;
import clp.run.cel.dom.SysCommand;
import clp.run.cel.dom.SysStatement;
import clp.run.cel.exp.Expression;
import clp.run.cel.graph.GParser;
import clp.run.cel.graph.eval.GEvaluator;
import clp.run.cel.graph.init.GInit;
import clp.run.cel.graph.map.GAssistent;
import clp.run.cel.graph.map.GMapper;
import clp.run.cel.graph.rnd.GRenderer;
import clp.run.cel.java.JavaCallNew;
import clp.run.cel.java.JavaCallStart;
import clp.run.cel.java.JavaCallee;
import clp.run.cel.java.JavaExportPart;
import clp.run.cel.java.JavaStatement;
import clp.run.cel.java.JavaStatementInterfaceVisitor;
import clp.run.cel.prt.P_constant;
import clp.run.cel.prt.P_exp;
import clp.run.cel.prt.P_var;
import clp.run.cel.prt.PrintElt;
import clp.run.cel.prt.PrintEltVisitor;
import clp.run.cel.prt.PrintStatement;
import clp.run.cel.ref.ReflectStatement;
import clp.run.cel.web.Response;
import clp.run.cel.web.SendItems;
import clp.run.cel.web.SendItemsVisitor;
import clp.run.cel.web.SendObject;
import clp.run.cel.web.SendStream;
import clp.run.cel.web.WebStatement;
import clp.run.msc.OutputTarget;
import clp.run.msc.SendFile;
import clp.run.ui.VisualizeStatement;

public class CommandVisitorRenderer implements CommandVisitor {

  final StringBuffer sb = new StringBuffer();
  private ReflectUtility ru;

  public CommandVisitorRenderer() {
    ru = ReflectUtility.getInstance();
  }

  public StringBuffer getLine() {
    return sb;
  }

  @Override
  public void visitWebStatement(WebStatement x) {
    sb.append("send ");
    if (x.isSendItems()) {
      SendItems si = x.getSendItems();
      si.accept(new SendItemsVisitor() {
        @Override
        public void visitSendStream(SendStream x) {
          SendObject o = x.getSendObject();
          if (o != null) {
            ArrayList<SendObject> os = x.getSendObjects();
            if (!os.contains(o)) {
              os.add(0, o);
            }
            for (int i=0; i<os.size(); i++) {
              SendObject obj = os.get(i);
              sb.append(obj.getVar());
              if (i < os.size()-1) {
                sb.append(", ");
              }
            }
          }
        }
        @Override
        public void visitSendFile(SendFile x) {
          sb.append(x.getFileName());
        }
      });
    }
    sb.append(" using ");
    if (x.isRes()) {
      // WORKAROUND
      if (!x.getRes().equals(x.getWebVarName())) {
        sb.append(x.getRes());
        sb.append(":");
      }
    }
    sb.append(x.getWebVarName());
    if (x.isTimeout()) {
      sb.append(" (");
      sb.append(x.getTimeout());
      sb.append(")");
    }
    if (x.isReceiving()) {
      sb.append(" receiving ");
      sb.append(getResponse(x.getResponse()));
    }
  }

  private String getResponse(Response response) {
    String var = response.getVariable();
    if (var != null) {
      return (String) ResourceUtility.getInstance().getValue(var);
    }
    return response.getRespString();
  }

  @Override
  public void visitSysStatement(SysStatement x) {
    SysCommand cmd = x.getSysCommand();
    sb.append(cmd.getVal());
  }

  @Override
  public void visitPrintStatement(PrintStatement x) {
    sb.append("print ");
    PrintElt e = x.getPrintElt();
    if (e != null) {
      List<PrintElt> pl = new ArrayList<PrintElt>( x.getPrintElts() );
      pl.add(0, e);
      for (int i=0; i<pl.size(); i++) {
        PrintElt elt = pl.get(i);
        elt.accept(new PrintEltVisitor() {
          @Override
          public void visitP_var(P_var x) {
            sb.append( x.getVar() );
          }
          @Override
          public void visitP_exp(P_exp x) {
          }
          @Override
          public void visitP_constant(P_constant x) {
            String str = x.getConstant();
            str = str.replace("\n", "\\n");
            str = str.replace("\t", "\\t");
            sb.append("\""+str+"\"");
          }
        });
        if (i < pl.size()-1) {
          sb.append(", ");
        }
        else {
          sb.append(" ");
        }
      }
      if (x.isOutputTarget()) {
        OutputTarget target = x.getOutputTarget();
        if (target.isStringCONSOLE()) {
          sb.append("to " + target.getStringCONSOLE());
        }
        else if (target.getName() != null) {
          sb.append("to " + target.getName());
        }
        else {
          sb.append("to " + target.getSendFile().getFileName());
        }
      }
    }
  }

  @Override
  public void visitJavaStatement(JavaStatement x) {
    sb.append("java ");
    x.getJavaStatementInterface().accept(new JavaStatementInterfaceVisitor() {
      
      @Override
      public void visitJavaCallStart(JavaCallStart x) {
        if (x.isStart()) {
          sb.append("start ");
        }
        String name = x.getJavaCall().getClassName().getClname();
        if (name != null) {
          sb.append(name+" ");
        }
        else {
          sb.append("\""+x.getJavaCall().getClassName().getClassString()+"\" ");
        }
        JavaCallee callee = x.getJavaCall().getJavaCallee();
        if (callee.isPass()) {
          sb.append(" pass ");
          JavaExportPart xp = callee.getJavaExportPart();
          if (xp.getExpression() != null) {
            sb.append(ru.getExpression(xp.getExpression()));
          }
          for (Expression e : xp.getExpressions()) {
            sb.append(", " + ru.getExpression(e));
          }
        }
        if (callee.isGet()) {
          sb.append(" get "+callee.getVariable());
        }
      }
      
      @Override
      public void visitJavaCallNew(JavaCallNew x) {
        sb.append("new \""+x.getClassName()+"\"");
        if (x.isPass()) {
          sb.append(" pass ");
          JavaExportPart xp = x.getJavaExportPart();
          if (xp.getExpression() != null) {
            sb.append(ru.getExpression(xp.getExpression()));
          }
          for (Expression e : xp.getExpressions()) {
            sb.append(", " + ru.getExpression(e));
          }
        }
        if (x.isGet()) {
          sb.append(" get "+x.getVariable());
        }
      }
    });
  }

  @Override
  public void visitExecStatement(ExecStatement x) {
    sb.append("exec \"");
    sb.append(x.getFilename());
    sb.append("\"");
  }

  @Override
  public void visitClappStatement(ClappStatement x) {
  }

  @Override
  public void visitAssignStatement(AssignStatement x) {
    if (x.getBoolAssignment() != null) {
      if (x.getBoolAssignment().isModifier()) {
        sb.append(x.getBoolAssignment().getModifier().getVal());
      }
      sb.append(x.getBoolAssignment().getVar());
      if (x.getBoolAssignment().isIfclause()) {
        sb.append(" if ");
        sb.append(x.getBoolAssignment().getIfclause().getVar());
      }
    }
    else {
      sb.append( x.getVarAssignment().getVar() );
      sb.append( " = " );
      sb.append( ru.getExpression( x.getVarAssignment().getExpression() ) );
    }
  }

  @Override
  public void visitReflectStatement(ReflectStatement x) {
    sb.append("reflect ");
    sb.append(x.getReflectObject().getVal());
    sb.append(" to ");
    OutputTarget target = x.getOutputTarget();
    if (target.isStringCONSOLE()) {
      sb.append("CONSOLE");
    }
    else if (target.getName() != null) {
      sb.append(target.getName());
    }
    else {
      sb.append(target.getSendFile().getFileName());
    }
  }

  @Override
  public void visitCutStatement(CutStatement x) {
    sb.append("cut ");
    sb.append(x.getNode());
  }

  @Override
  public void visitGraphParseStatement(GParser x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitGraphAssistStatement(GAssistent x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitGraphMapStatement(GMapper x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitGraphRenderStatement(GRenderer x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitGraphEvaluateStatement(GEvaluator x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitGraphReinitStatement(GInit x) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void visitVisualizeStatement(VisualizeStatement x) {
    // TODO Auto-generated method stub
    
  }
}
