package clapp.cmp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import clapp.run.ui.util.ConsoleProvider;
import clp.parse.CLAppParser;
import clp.run.CLApp;
import clp.run.act.Actor;
import clp.run.msc.MetaScenario;
import clp.run.res.Resources;
import clp.run.scn.Scenario;


public class ClappMain {

  private MetaScenario msc;
  private CLApp result;

  public MetaScenario getMetaScenario() {
    return msc;
  }

  public void setMetaScenario(MetaScenario ms) {
    msc = ms;
  }

  /**
   * parse the named file. it could enclose any clapp structure like
   * meta-scenario, weaver, scenario, resource block, actor or cell block. it
   * can also be any combination of those.
   * there is one restriction: only one meta-scenario will be taken in
   * account (the last you give).
   * 
   * @param file
   * @param force 
   * @throws IOException
   */
  public void parse(String file) throws IOException {
    FileInputStream fis = new FileInputStream(file);
    parse(fis);
    fis.close();
  }

  /**
   * parse content of entry input stream. it could enclose any clapp structure like
   * meta-scenario, weaver, scenario, resource block, actor or cell block. it
   * can also be any combination of those.
   * there is one restriction: only one meta-scenario will be taken in
   * account (the last you give).
   * 
   * @param file
   * @throws IOException
   */
  public void parse(InputStream is) {
    CLAppParser parser = new CLAppParser(new BufferedReader(new InputStreamReader(is)));
    if (parser.parse() != Boolean.TRUE) {
      ConsoleProvider.getInstance().eprint("compilation ended with "+parser.getError());
      System.exit(-1);
    }
    MetaScenario lmsc = parser.getCLApp().getMetaScenario();
    if (lmsc != null) {
      msc = lmsc;
    }
  }

  /**
   * @param is
   * @return
   */
  public String silentParse(InputStream is) {
    CLAppParser parser = new CLAppParser(new BufferedReader(new InputStreamReader(is)));
    if (parser.parse() != Boolean.TRUE ) {
      String str = "compilation ended with "+parser.getError();
      ConsoleProvider.getInstance().eprint(str);
      return str;
    }
    result = parser.getCLApp();
    MetaScenario lmsc = result.getMetaScenario();
    if (lmsc != null) {
      msc = lmsc;
    }
    return null;
  }

  /**
   * smart rendering with indentation
   */
  public StringBuffer render(String rendering) {
    StringBuffer sb = new StringBuffer();
    int indent = 0;
    String line = "";
    StringTokenizer st = new StringTokenizer(rendering, "{};", true);
    boolean isWithinEvaluation = false;
    int countBraces = 0;
    while (st.hasMoreTokens()) {
      String token = st.nextToken().trim();
      if (isWithinEvaluation) {
        if ("{".equals(token)) {
          sb.append(" { ");
          countBraces++;
        }
        else  if ("}".equals(token)) {
          countBraces--;
          if (countBraces == 0) {
            sb.append("\n");
            indent--;
            sb.append(getIndent(indent));
            sb.append("}\n");
            isWithinEvaluation = false;
          }
          else {
            sb.append(" } ");
          }
        }
        else  if (";".equals(token)) {
          sb.append(" ; ");
        }
        else {
          sb.append(token);
        }
      }
      else if (token.length() == 1 && "{};".contains(token)) {
        // it's a delimiter
        switch(token.charAt(0)) {
          case '{':
            sb.append(getIndent(indent));
            sb.append(line);
            sb.append(" {\n");
            indent++;
            if (line.equals("AD") || line.equals("DD") || line.equals("XD")) {
              isWithinEvaluation = true;
              countBraces = 1;
              sb.append(getIndent(indent));
            }
            line = "";
            break;
          case '}':
            if (line != null && line.length() > 0) {
              sb.append(getIndent(indent));
              sb.append(line);
              sb.append("\n");
              line = null;
            }
            indent--;
            sb.append(getIndent(indent));
            sb.append("}\n");
            break;
          case ';':
            sb.append(getIndent(indent));
            sb.append(line);
            sb.append(";\n");
            line = null;
            break;
        }
      }
      else {
        line = token;
      }
    }
    return sb;
  }

  private String getIndent(int indent) {
    if (indent < 1) {
      return "";
    }
    String str = "";
    for (int i=0; i<indent; i++) {
      str += "  ";
    }
    return str;
  }

  /**
   * returns scenario instance attached to the given meta-scenario
   * according to the given scenario name.
   */
  public Scenario findScenario(MetaScenario msc, String sname) {
    ArrayList<Scenario> scenarios = msc.getMetaScenarioBody().getScenarios();
    for (Scenario scn : scenarios) {
      if (sname.equals(scn.getName())) {
        return scn;
      }
    }
    return null;
  }

  /**
   * returns actor instance attached to a scenario within the given meta-scenario
   * according to the given actor name.
   */
  public Actor findActor(MetaScenario msc, String aname) {
    if (aname == null) {
      return null;
    }
    ArrayList<Scenario> scenarios = msc.getMetaScenarioBody().getScenarios();
    for (Scenario scn : scenarios) {
      Actor act = findActor(scn, aname);
      if (act != null) {
        return act;
      }
    }
    return null;
  }

  /*
   * returns actor instance attached to the given scenario
   * according to the given actor name.
   */
  private Actor findActor(Scenario scn, String aname) {
    ArrayList<Actor> actors = scn.getScenarioBody().getActors();
    for (Actor act : actors) {
      if (aname.equals(act.getName())) {
        return act;
      }
    }
    return null;
  }

  /**
   * returns resources block instance attached to the given meta-scenario
   * according to the given resources block name.
   */
  public Resources findResources(MetaScenario msc, String rname) {
    if (rname == null) {
      return null;
    }
    ArrayList<Resources> resources = msc.getMetaScenarioBody().getResourcess();
    for (Resources res : resources) {
      if (rname.equals(res.getName())) {
        return res;
      }
    }
    return null;
  }

  /**
   * @return the parsing result
   */
  public CLApp getResult() {
    return result;
  }
}