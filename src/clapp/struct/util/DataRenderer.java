package clapp.struct.util;

import java.util.ArrayList;

import clapp.run.util.ResourceUtility;
import clp.run.msc.MetaScenario;
import clp.run.res.Binpath;
import clp.run.res.CellEvent;
import clp.run.res.CellMarkSet;
import clp.run.res.CellMarks;
import clp.run.res.Event;
import clp.run.res.EventVisitor;
import clp.run.res.Jarpath;
import clp.run.res.Resources;
import clp.run.res.UsedJava;
import clp.run.res.UsedJavaVisitor;
import clp.run.res.VarEvent;
import clp.run.res.Variable;

public class DataRenderer {

  public StringBuffer getRendering(MetaScenario msc) {
    StringBuffer sb = new StringBuffer();
    ResourceUtility ru = ResourceUtility.getInstance();
    ArrayList<Resources> rlist = msc.getMetaScenarioBody().getResourcess();
    for (Resources res : rlist) {
      sb.append(res.getName());
      sb.append("{");
      ArrayList<Variable> vlist = res.getVariables();
      for (Variable var : vlist) {
        sb.append(ru.getName(var));
        sb.append(" = ");
        sb.append(ru.getValue(var));
        sb.append(";");
      }
      if (res.isEvents()) {
        sb.append("events {");
        Event ev = res.getEvents().getEvent();
        sb.append(getEventDeclaration(ev));
        for (Event e : res.getEvents().getEvents()) {
          sb.append(getEventDeclaration(e));
        }
        sb.append("}");
      }
      if (res.isMarks()) {
        sb.append("marks {");
        CellMarkSet m = res.getMarks().getCellMarkSet();
        sb.append(getMarksDeclaration(m.getCellMarks()));
        for (CellMarks cm : m.getCellMarkss()) {
          sb.append(getMarksDeclaration(cm));
        }
        sb.append("}");
      }
      if (res.isUsedLib()) {
        sb.append("usedJavaList {");
        UsedJava lib = res.getUsedLib().getUsedJava();
        if (lib != null) {
          sb.append(getLibDeclaration(lib));
        }
        for (UsedJava uj : res.getUsedLib().getUsedJavas()) {
          sb.append(", "+getLibDeclaration(uj));
        }
        sb.append("}");
      }
      sb.append("}");
    }
    return sb;
  }

  //
  private StringBuffer getLibDeclaration(UsedJava usedJava) {
    StringBuffer sb = new StringBuffer();
    usedJava.accept(new UsedJavaVisitor() {
      
      @Override
      public void visitJarpath(Jarpath x) {
        sb.append("jar "+x.getJar());
      }
      
      @Override
      public void visitBinpath(Binpath x) {
        sb.append("bin "+x.getDir());
      }
    });
    return sb;
  }

  //
  private StringBuffer getMarksDeclaration(CellMarks cellMarks) {
    StringBuffer sb = new StringBuffer();
    // TODO
    return sb;
  }

  //
  private StringBuffer getEventDeclaration(Event ev) {
    StringBuffer sb = new StringBuffer();
    ev.accept(new EventVisitor() {
      
      @Override
      public void visitVarEvent(VarEvent x) {
        sb.append("VAR " + x.getName()+";");
      }
      
      @Override
      public void visitCellEvent(CellEvent x) {
        sb.append("CELL " + x.getCellName());
        if (x.isDelay()) {
          sb.append(" " + x.getTime() + " " + x.getDelay() + " " + x.getUnit().getVal() + " " + x.isCycle());
        }
        sb.append(";");
      }
    });
    return sb;
  }
}
