package clapp.run.vis;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import clapp.cmp.ClappMain;
import clapp.debug.WebTrace;
import clapp.graph.ClpGraphMaker;
import clapp.graph.GraphContext;
import clapp.graph.GraphExpressionEvaluator;
import clapp.graph.GraphManager;
import clapp.run.Supervisor;
import clapp.run.exe.ExecutingUnit;
import clapp.run.http.WebRequest;
import clapp.run.token.EventHandler;
import clapp.run.token.TokenHandler;
import clapp.run.ui.ClpVisualizer;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.ClpCutStatement;
import clapp.run.util.ClpJavaCallCatcher;
import clapp.run.util.ResourceUtility;
import clapp.struct.util.ActivityRenderer;
import clapp.struct.util.DataRenderer;
import clapp.struct.util.Introspector;
import clp.run.cel.Cell;
import clp.run.cel.asm.AssignStatement;
import clp.run.cel.asm.BoolAssignment;
import clp.run.cel.asm.Ifclause;
import clp.run.cel.asm.VarAssignment;
import clp.run.cel.dom.ClappCommand;
import clp.run.cel.dom.ClappStatement;
import clp.run.cel.dom.CommandVisitor;
import clp.run.cel.dom.CutStatement;
import clp.run.cel.dom.ExecStatement;
import clp.run.cel.dom.SysStatement;
import clp.run.cel.graph.GParser;
import clp.run.cel.graph.GraphRef;
import clp.run.cel.graph.eval.GEvaluator;
import clp.run.cel.graph.init.GInit;
import clp.run.cel.graph.map.GAssistent;
import clp.run.cel.graph.map.GMapper;
import clp.run.cel.graph.rnd.GRenderer;
import clp.run.cel.java.JavaStatement;
import clp.run.cel.prt.P_constant;
import clp.run.cel.prt.P_exp;
import clp.run.cel.prt.P_var;
import clp.run.cel.prt.PrintElt;
import clp.run.cel.prt.PrintEltVisitor;
import clp.run.cel.prt.PrintStatement;
import clp.run.cel.ref.ReflectStatement;
import clp.run.cel.web.Response;
import clp.run.cel.web.SendItems;
import clp.run.cel.web.WebStatement;
import clp.run.msc.MetaScenario;
import clp.run.msc.OutputTarget;
import clp.run.res.BVar;
import clp.run.res.Resources;
import clp.run.res.SVar;
import clp.run.res.Variable;
import clp.run.res.WebVariable;
import clp.run.res.graph.Graph;
import clp.run.res.ui.UiVar;
import clp.run.ui.VisualizeStatement;

public class ClpCommandVisitor implements CommandVisitor {

  private Cell cell;
  private Resources res;
  private Target targetType;
  private String targetName;
  private ExecutingUnit executingUnit;
  private boolean isInterruptable;

  /**
   * CONSTRUCTOR
   * 
   * @param eu executingUnit 
   * @param cell
   */
  public ClpCommandVisitor(Cell c, ExecutingUnit eu) {
    super();
    cell = c;
    executingUnit = eu;
    res = c.getBlock().getResources();
    isInterruptable = true;
  }

  /**
   * assignment statement used to assign clapp variables.
   */
  @Override
  public void visitAssignStatement(AssignStatement x) {
    ClpAssignStatementVisitor vis = new ClpAssignStatementVisitor();
    vis.accept(x);
    Variable var = vis.getVar();
    if (var == null) {
      ConsoleProvider.getInstance().eprint("Error: assignment statement");
    }
    else {
      isInterruptable = false;
    }
  }

  /**
   * clapp statement, used to inspect the internal structure (dynamic
   * and static one).
   */
  @Override
  public void visitClappStatement(ClappStatement x) {
    if (x.getClappCommand() == ClappCommand.WEBREFLECT) {
      String var = x.getWebName();
      Variable r = ResourceUtility.getInstance().getVariable(res, var);
      if (r instanceof WebVariable) {
        WebTrace wt = new WebTrace((WebVariable)r);
        Supervisor.getInstance().inspect(wt);
      }
    }
  }

  /**
   * exec statement, used to trigger external batch.
   */
  @Override
  public void visitExecStatement(ExecStatement x) {
    String name = x.getFilename();
    try {
      String envName = System.getenv("SHELL");
      if (envName != null && envName.contains("bash")) {
        String[] cmd = {"bash", name};
        Runtime.getRuntime().exec(cmd);
      }
      else {
        Runtime.getRuntime().exec(name);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * java statement either starts a java program in a new thread, or call a
   * method directly.
   */
  @Override
  public void visitJavaStatement(JavaStatement x) {
    x.getJavaStatementInterface().accept(new ClpJavaStatementInterfaceVisitor(res));
  }

  /**
   * print statement.
   */
  @Override
  public void visitPrintStatement(PrintStatement x) {
    final StringBuffer sb = new StringBuffer();
    PrintElt e = x.getPrintElt();
    if (e != null) {
      List<PrintElt> pl = new ArrayList<PrintElt>( x.getPrintElts() );
      pl.add(0, e);
      for (PrintElt elt : pl) {
        elt.accept(new PrintEltVisitor() {
          @Override
          public void visitP_var(P_var x) {
            ResourceUtility util = ResourceUtility.getInstance();
            Variable v = util.getVariable(res, x.getVar());
            if (v == null) {
              sb.append("####ERROR on getting variable "+x.getVar());
            }
            else {
              sb.append( util.getValue(v) );
            }
            sb.append(" ");
          }
          @Override
          public void visitP_exp(P_exp x) {
          }
          @Override
          public void visitP_constant(P_constant x) {
            sb.append( x.getConstant() );
          }
        });
      }
    }
    ConsoleProvider.getInstance().print(sb, x.getOutputTarget());
  }

  /**
   * system statement is used to
   * . clear all load time weavings registered
   * . process incoming web entry
   */
  @Override
  public void visitSysStatement(SysStatement x) {
    if (x.getSysCommand() != null) {
      switch(x.getSysCommand()) {
        case EXIT:
          Supervisor.getInstance().stopAll(null, null);
          break;
        case PROCESS_INBOUND:
          break;
      }
    }
    else if (x.getSysExp() != null) {
      switch (x.getSysExp()) {
        case ACTIVE:
          break;
        case INACTIVE:
          break;
      }
    }
    else if (x.getLoadMarks() != null) {
      TokenHandler.loadMarks(x.getLoadMarks().getName(), x.getLoadMarks().getMarks());
    }
  }

  /**
   * web statement used to send a set of key-value pairs on a specified port,
   * either directly from the input stream, or through a file;
   * alternatively, just by referring to the completely defined WebVariable.
   */
  @Override
  public void visitWebStatement(WebStatement x) {
    if (x.isSendItems()) {
      String name = x.getWebVarName();
      WebVariable var = null;
      Object v = null;
      Resources r = findResources(res, x.getRes());
      if (r == null) {
        r = res;
      }
      v = ResourceUtility.getInstance().getVariable(r, name);
      if (v instanceof WebVariable) {
        var = (WebVariable)v;
        String resp = x.isReceiving() ? getResponse(x.getResponse()) : null;
        if (x.isSendItems()) {
          SendItems si = x.getSendItems();
          ClpSendItemsVisitor vis = new ClpSendItemsVisitor(res, var.getEncryption());
          si.accept(vis);
          if (vis.isFile()) {
            new WebRequest(res) .sendFile(var, vis.getFileName());
          }
          else {
            new WebRequest(res).send(var, vis.getItems(), x.getTimeout(), resp);
          }
        }
        else {
          new WebRequest(res).send(var, x.getSendItems(), x.getTimeout(), resp);
        }
      }
    }
  }

  private String getResponse(Response response) {
    String var = response.getVariable();
    if (var != null) {
      return (String) ResourceUtility.getInstance().getValue(var);
    }
    return response.getRespString();
  }

  private Resources findResources(Resources res, String rname) {
    if (res.getName().equals(rname)) {
      return res;
    }
    MetaScenario msc = Supervisor.getInstance().getMetaScenario();
    if (msc != null ) {
      ArrayList<Resources> rlist = msc.getMetaScenarioBody().getResourcess();
      for (Resources r : rlist) {
        if (r.getName().equals(rname)) {
          return r;
        }
      }
    }
    return null;
  }

  @Override
  public void visitReflectStatement(ReflectStatement x) {
    ClappMain clapp = Supervisor.getInstance().getClapp();
    MetaScenario msc = clapp.getMetaScenario();
    OutputTarget target = x.getOutputTarget();
    if (target.isStringCONSOLE()) {
      targetType = Target.CONSOLE;
    }
    else if (target.getName() != null) {
      targetType = Target.CUSTOM;
      targetName = target.getName();
    }
    else {
      targetType = Target.FILE;
      targetName = target.getSendFile().getFileName();
    }
    StringBuffer sb;
    switch (x.getReflectObject()) {
      case ACTIVITY:
        sb = new ActivityRenderer().getRendering(msc);
        break;
      case DATA:
        sb = clapp.render(new DataRenderer().getRendering(msc).toString());
        break;
      case STRUCTURE:
        sb = clapp.render(new Introspector().getRendering(msc).toString());
        break;
      default:
        sb = new StringBuffer();
        break;
    }
    if (targetType == Target.CONSOLE || targetType == Target.CUSTOM) {
      ConsoleProvider.getInstance().print(sb, x.getOutputTarget());
    }
    else {
      // TODO add possibility to set content in a variable
      Variable v = ResourceUtility.getInstance().getVariable(res, targetName);
      if (v instanceof SVar) {
        ((SVar)v).setValue(sb.toString());
      }
    }
  }

  // INNER CLASS

  class ClpAssignStatementVisitor {
    private Variable var;

    public void accept(AssignStatement x) {
      if (x.getBoolAssignment() != null) {
        visitBoolAssignment(x.getBoolAssignment());
      }
      else {
        visitVarAssignment(x.getVarAssignment());
      }
    }

    public void visitVarAssignment(VarAssignment x) {
      var = ResourceUtility.getInstance().getVariable(res, x.getVar());
      ResourceUtility.getInstance().setValueFromExpression(res, var, x.getExpression());
    }

    public void visitBoolAssignment(BoolAssignment x) {
      Variable v = ResourceUtility.getInstance().getVariable(res, x.getVar());
      if (v instanceof BVar) {
        var = v;
        boolean b = true;
        if (x.isIfclause()) {
          Ifclause icl = x.getIfclause();
          Variable v2 = ResourceUtility.getInstance().getVariable(res, icl.getVar());
          if (v2 instanceof BVar) {
            EventHandler.getInstance().registerConditionalAssignment((BVar)v, (BVar)v2, x.getModifier(), x.isKeeping(), cell.getName());
            return;
          }
        }
        else if (!x.isKeeping()) {
          BVar v2 = new BVar();
          v2.setValue(true);
          EventHandler.getInstance().registerConditionalAssignment((BVar)v, v2, x.getModifier(), false, cell.getName());
        }
        if (x.isModifier()) {
          switch (x.getModifier()) {
            case NOT:
              b = false;
              break;
            case UP:
              EventHandler.getInstance().markVarEvent(x.getVar(), true);
              return;
            case DOWN:
              EventHandler.getInstance().markVarEvent(x.getVar(), false);
              return;
          }
        }
        ResourceUtility.getInstance().setValue(x.getVar(), var, b);
        EventHandler.getInstance().markVarEvent(x.getVar(), b);
      }
    }

    Variable getVar() {
      return var;
    }
  }

  @Override
  public void visitCutStatement(CutStatement x) {
    ResourceUtility util = ResourceUtility.getInstance();
    Variable v = util.getVariable(res, x.getNode());
    if (v instanceof SVar) {
      String val = (String) util.getValue(v);
      ClpCutStatement cs = new ClpCutStatement(res.getMetaScenario(), val);
      cs.cut();
    }
    else {
      ConsoleProvider.getInstance().eprint("ERROR: "+x.getNode()+" should be a string variable in the cut statement");
    }
  }

  public enum Target {
    CONSOLE, CUSTOM, VARIABLE, FILE;
  }

  public Target getTargetType() {
    return targetType;
  }

  public void setTargetType(Target t) {
    targetType = t;
  }

  public String getTargetName() {
    return targetName;
  }

  public void setTargetName(String n) {
    targetName = n;
  }

  public Resources getRes() {
    return res;
  }

  @Override
  public void visitGraphParseStatement(GParser x) {
    final GraphContext gc = createGraphRef(x.getGraphRef());

    if (x.getParseObject().getString() != null) {
      GraphManager.getInstance().parse(getRes(), x.getParseObject().getString(), gc);
    }
    else {
      Variable v = ResourceUtility.getInstance().getVariable(getRes(), x.getParseObject().getVariable());
      if (v instanceof SVar) {
        GraphManager.getInstance().parse(getRes(), ((SVar) v).getValue(), gc);
      }
    }
  }

  @Override
  public void visitGraphAssistStatement(GAssistent x) {
    ClpGraphMaker gm1 = getOrCreateGraphMaker(x.getGname1());
    ClpGraphMaker gm2 = getOrCreateGraphMaker(x.getGname2());
    gm2.setAssistantOf(gm1);
    gm1.setAssistedBy(gm2);
  }

  @Override
  public void visitGraphMapStatement(GMapper x) {
    String gname = x.getGname();
    ClpGraphMaker gm = getOrCreateGraphMaker(gname);
    if (gm != null) {
      ClpMappingVisitor mvis = new ClpMappingVisitor(getRes(), gm);
      x.getMapping().accept(mvis);
    }
  }

  @Override
  public void visitGraphRenderStatement(GRenderer x) {
    final GraphContext gc = createGraphRef(x.getGraphRef());

    StringBuffer sb = GraphManager.getInstance().render(gc, getRes());
    OutputTarget target = x.getOutputTarget();
    if (target.isStringCONSOLE()) {
      setTargetType(Target.CONSOLE);
    }
    else {
      setTargetType(Target.CUSTOM);
      setTargetName(target.getName());
    }
    if (targetType == Target.CONSOLE || targetType == Target.CUSTOM) {
      ConsoleProvider.getInstance().print(sb, x.getOutputTarget());
    }
    else {
      // TODO add possibility to set content in a variable
      Variable v = ResourceUtility.getInstance().getVariable(getRes(), getTargetName());
      if (v instanceof SVar) {
        if (sb == null) {
          ((SVar)v).setValue(null);
        }
        else {
          ((SVar)v).setValue(sb.toString());
        }
      }
    }
  }

  @Override
  public void visitGraphEvaluateStatement(GEvaluator x) {
    final GraphContext gc = createGraphRef(x.getGraphRef());

    if (x.isUsing()) {
      if (x.getProcessor().getJavaProcessor() != null) {
        ClpJavaCallCatcher cc = new ClpJavaCallCatcher(x.getProcessor().getJavaProcessor().getJavaCall());
        gc.setJavaContext(cc.getJavaContext());
      }
      else {
        gc.setCoreNode(x.getProcessor().getCoreProcessor().getGnode());
      }
    }

    GraphManager.getInstance().evaluate(gc, getRes());
  }

  @Override
  public void visitGraphReinitStatement(GInit x) {
    ClpGraphMaker gm = getOrCreateGraphMaker(x.getGname());
    gm.setParserInstance(null);
    gm.setRuntimeInstance(null);
    ClpGraphMaker gm2 = gm.setAssistantOf(null);
    if (gm2 != null) {
      gm2.setAssistedBy(null);
    }
    gm2 = gm.setAssistedBy(null);
    if (gm2 != null) {
      gm2.setAssistantOf(null);
    }
    GraphExpressionEvaluator.clearRenderer();
  }

  @Override
  public void visitVisualizeStatement(VisualizeStatement x) {
    Variable var = ResourceUtility.getInstance().getVariable(getRes(), x.getUiname());
    if (var instanceof UiVar) {
      ClpVisualizer visualizer = ClpVisualizer.getRegistered((UiVar) var);
      if (visualizer == null) {
        visualizer = new ClpVisualizer(getRes(), (UiVar) var, x.isKeeping());
        executingUnit.setVisualizer(visualizer);
      }
      visualizer.handleVisualization();
    }
  }

  //
  private GraphContext createGraphRef(GraphRef ref) {
    final GraphContext gc = new GraphContext();
    gc.setName( ref.getGname() );
    if (ref.getNode() != null) {
      gc.setNode( ref.getNode() );
      if (ref.getElement() != 0) {
        gc.setElement( ref.getElement() );
      }
    }
    return gc;
  }

  //
  private ClpGraphMaker getOrCreateGraphMaker(String gname) {
    ClpGraphMaker gm = GraphManager.getInstance().getGraphMaker(gname);
    if (gm == null) {
      Variable v = ResourceUtility.getInstance().getVariable(getRes(), gname);
      if (v instanceof Graph) {
        String str = ((Graph)v).getGraphSentences();
        try {
          gm = GraphManager.getInstance().addGraphMaker(gname, str);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return gm;
  }

  public boolean isInterruptable() {
    return isInterruptable;
  }
}
