package clapp.struct.util;

import java.util.ArrayList;

import clapp.run.ui.util.ConsoleProvider;
import clapp.struct.util.vis.CommandVisitorRenderer;
import clp.run.act.Actor;
import clp.run.cel.Cell;
import clp.run.cel.Heap;
import clp.run.cel.dom.Command;
import clp.run.cel.dom.CommandLine;
import clp.run.cel.log.LogicalExpression;
import clp.run.cel.log.TransposingLine;
import clp.run.msc.MetaScenario;
import clp.run.msc.MetaScenarioBody;
import clp.run.msc.MscOutput;
import clp.run.msc.MscTaskName;
import clp.run.msc.Output;
import clp.run.msc.OutputTarget;
import clp.run.res.Resources;
import clp.run.res.Variable;
import clp.run.scn.Scenario;
import clp.run.scn.ScenarioBody;
import clp.run.scn.ScnLogBody;
import clp.run.scn.ScnPropBody;
import clp.run.scn.ScnQueue;
import clp.run.scn.ScnTask;

public class Introspector {

  private ReflectUtility ru;

  public Introspector() {
    ru = ReflectUtility.getInstance();
  }

  /**
   * returns the rendered internal structure 
   * @param msc
   * @return
   */
  public StringBuffer getRendering(MetaScenario msc) {
    final StringBuffer sb = new StringBuffer();
    if (msc == null) {
      ConsoleProvider.getInstance().eprint("ERROR: no meta scenario defined");
    }
    else {
      sb.append("metaScenario "); sb.append(msc.getName()); sb.append(" {\n");
      MetaScenarioBody mscb = msc.getMetaScenarioBody();
      sb.append("  properties {\n");
      sb.append("    tasks {\n");
      ArrayList<MscTaskName> procs = mscb.getMscTasks().getMscTaskNames();
      for (MscTaskName proc : procs) {
        sb.append("      "); sb.append(proc.getVal());
        sb.append(";\n");
      }
      sb.append("    }\n");
      if (mscb.isPort()) {
        sb.append("  port ");
        sb.append( mscb.getPort().getNum() );
        sb.append(";\n");
      }
      if (mscb.isMscOutput()) {
        sb.append("  output : ");
        sb.append( getMscOutput(mscb.getMscOutput()) );
        sb.append(";\n");
      }
      sb.append("  }\n");
      if (mscb.hasResourcess()) {
        ArrayList<Resources> lres = mscb.getResourcess();
        for (Resources res : lres) {
          sb.append("  resources ");
          sb.append(res.getName());
          sb.append("  {\n");
          ArrayList<Variable> lvar = res.getVariables();
          for (Variable var : lvar) {
            VariableVisitorRenderer vis = new VariableVisitorRenderer();
            var.accept(vis);
            sb.append(vis.getType());
            sb.append(" ");
            sb.append(vis.getName());
            Object init = vis.getInitial();
            if (init != null) {
              sb.append(" = ");
              sb.append(init);
              sb.append(";\n");
            }
            else if ("WEB".equals(vis.getType())) {
              sb.append(" {\n port ");
              sb.append(vis.getPort());
              sb.append(" }");
            }
            else {
              sb.append(";\n");
            }
          }
          sb.append("  }\n");
        }
      }
      if (mscb.hasScenarios()) {
        sb.append(getScenarios(mscb.getScenarios()));
      }
      sb.append("}\n");
    }
    return sb;
  }

  // get output rendering
  private StringBuffer getMscOutput(MscOutput mscOutput) {
    StringBuffer sb = new StringBuffer();
    ArrayList<Output> outputs = new ArrayList<>();
    outputs.add(mscOutput.getOutput());
    outputs.addAll(mscOutput.getOutputs());
    for (int i=0; i<outputs.size()-1; i++) {
      fillOutput(sb, outputs.get(i));
      sb.append(",\n");
    }
    fillOutput(sb, outputs.get(outputs.size()-1));
    sb.append(";\n");
    return sb;
  }

  //
  private void fillOutput(StringBuffer sb, Output out) {
    OutputTarget target = out.getOutputTarget();
    sb.append(target.isStringCONSOLE() ? target.getStringCONSOLE() : target.getName());
    if (out.isOut()) {
      sb.append(" "+out.getOut().getVal());
    }
    sb.append(" \"" +out.getColor() + "\"/\"" + out.getBackground() + "\"");
  }

  // get scenarios rendering
  private StringBuffer getScenarios(ArrayList<Scenario> scns) {
    StringBuffer sb = new StringBuffer();
    for (Scenario scn : scns) {
      sb.append("  scenario "); sb.append(scn.getName()); sb.append(" {\n");
      ScenarioBody scnb = scn.getScenarioBody();
      sb.append("    properties {\n");
      ScnPropBody scnp = scnb.getScnPropBody();
      sb.append( getLogic(scnp.getScnLogic().getScnLogBody()) );
      sb.append( getTasks(scnp.getScnTasks().getScnTasks()) );
      sb.append( getQueues(scnp.getScnQueues().getScnQueues()) );
      sb.append("    }\n");
      if (scnb.hasActors()) {
        ArrayList<Actor> acts = scnb.getActors();
        sb.append(getActors(acts));
      }
      sb.append("  }\n");
    }
    return sb;
  }

  private StringBuffer getLogic(ScnLogBody log) {
    StringBuffer sb = new StringBuffer();
    sb.append("    logic {\n");
    sb.append("      deactivation = "); sb.append( log.getScnDeact().getDeactType().getVal() ); sb.append(";\n");
    sb.append("      level = "); sb.append( log.getScnLevel().getLevel() ); sb.append(";\n");
    if (log.isScnLtype()) {
      sb.append("      type = "); sb.append( log.getScnLtype().getLtype().getVal() ); sb.append(";\n");
    }
    sb.append("    }\n");
    return sb;
  }

  private StringBuffer getTasks(ArrayList<ScnTask> tsks) {
    StringBuffer sb = new StringBuffer();
    sb.append("    tasks {\n");
    for (ScnTask t : tsks) {
      sb.append("      "); sb.append( t.getScnTaskName().getVal() );
      sb.append(" operatingOn "); sb.append( t.getOperOn() );
      if (t.isPassTo()) {
        sb.append(" passingTo "); sb.append( t.getPassTo() );
      }
      sb.append(";\n");
    }
    sb.append("    }\n");
    return sb;
  }

  private StringBuffer getQueues(ArrayList<ScnQueue> qs) {
    StringBuffer sb = new StringBuffer();
    sb.append("    queues {\n");
    for (ScnQueue q : qs) {
      sb.append("      "); sb.append( q.getName() );
      sb.append(" income = "); sb.append( q.getSortOrder().getVal() );
      sb.append(";\n");
    }
    sb.append("    }\n");
    return sb;
  }

  private StringBuffer getActors(ArrayList<Actor> acts) {
    StringBuffer sb = new StringBuffer();
    for (Actor act : acts) {
      sb.append("    actor "); sb.append(act.getName()); sb.append(" {\n");
      ArrayList<Heap> heaps = act.getHeaps();
      for (Heap heap : heaps) {
        sb.append("      heap ");
        sb.append(heap.getName());
        if (heap.isRes()) {
          sb.append(" usedResources ");
          sb.append(heap.getRes());
        }
        sb.append(" loadOn "); sb.append(heap.getLoad());
        if (heap.isActivity()) {
          sb.append(" ["); sb.append(heap.getActivity()); sb.append("] ");
        }
        sb.append(" {\n");
        sb.append( getCells(heap.getCells()) );
        sb.append("      }\n");
      }
      sb.append("    }\n");
    }
    return sb;
  }

  private StringBuffer getCells(ArrayList<Cell> cells) {
    final StringBuffer sb = new StringBuffer();
    for (Cell cell : cells) {
      sb.append("        cell "); sb.append(cell.getName()); sb.append(" {\n");
      if (cell.isAdomain()) {
        sb.append("          AD {\n");
        sb.append(getTransposingLines(cell.getAdomain().getAd().getTransposingLines()));
        sb.append("          }\n");
      }
      if (cell.isDdomain()) {
        sb.append("          DD {\n");
        sb.append(getExpressions(cell.getDdomain().getDd().getLogicalExpressions()));
        sb.append("          }\n");
      }
      if (cell.isXdomain()) {
        sb.append("          XD {\n");
        sb.append(getCommands(cell.getXdomain().getXd().getCommandLines()));
        sb.append("          }\n");
      }
      sb.append("        }\n");
    }
    return sb;
  }

  private StringBuffer getCommands(ArrayList<CommandLine> cmds) {
    final StringBuffer sb = new StringBuffer();
    for (CommandLine cmd : cmds) {
      sb.append("            ");
      if (cmd.isLevel()) {
        sb.append(cmd.getLevel());
        sb.append(": ");
      }
      sb.append(getCommand(cmd.getCommand()));
      sb.append(";\n");
    }
    return sb;
  }

  private StringBuffer getCommand(Command cmd) {
    final StringBuffer sb = new StringBuffer();
    CommandVisitorRenderer vis = new CommandVisitorRenderer();
    cmd.accept(vis);
    sb.append(vis.getLine());
    return sb;
  }

  private StringBuffer getExpressions(ArrayList<LogicalExpression> exps) {
    final StringBuffer sb = new StringBuffer();
    for (LogicalExpression exp : exps) {
      if (exp.isLevel()) {
        sb.append(exp.getLevel());
        sb.append(": ");
      }
      sb.append(ru.getLogicalExpression(exp) + ";\n");
    }
    return sb;
  }
  private StringBuffer getTransposingLines(ArrayList<TransposingLine> tlines) {
    final StringBuffer sb = new StringBuffer();
    for (TransposingLine tline : tlines) {
      if (tline.isLevel()) {
        sb.append(tline.getLevel());
        sb.append(": ");
      }
      ClpTokenAMarkingRenderer vis = new ClpTokenAMarkingRenderer();
      tline.getTokenAMarking().accept(vis);
      sb.append(vis.render(ru));
      sb.append(";\n");
    }
    return sb;
  }
}
