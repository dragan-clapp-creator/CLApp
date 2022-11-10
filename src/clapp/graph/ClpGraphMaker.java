package clapp.graph;

import java.io.Reader;
import java.util.HashMap;

import clp.run.res.Variable;

public class ClpGraphMaker extends akdl.graph.GraphMaker {

  private HashMap<String, Variable> imap;
  private HashMap<String, Variable> omap;
  private String name;  // this graph name
  private Object parserInstance;
  private Object runtimeInstance;
  private ClpGraphMaker assisted;
  private ClpGraphMaker assistent;

  public ClpGraphMaker(Reader r, String n) {
    super(r);
    name = n;
    imap = new HashMap<String, Variable>();
    omap = new HashMap<String, Variable>();
  }

  public void mapInput(String node, Variable v) {
    imap.put(node, v);
  }

  public void mapOutput(String node, Variable v) {
    omap.put(node, v);
  }

  public Variable getInputVariable(String node) {
    return imap.get(node);
  }

  public Variable getOutputVariable(String node) {
    return omap.get(node);
  }

  public String getName() {
    return name;
  }

  public Object getParserInstance() {
    return parserInstance;
  }

  public void setParserInstance(Object instance) {
    parserInstance = instance;
  }

  public Object getRuntimeInstance() {
    return runtimeInstance;
  }

  public void setRuntimeInstance(Object rt) {
    runtimeInstance = rt;
  }

  public ClpGraphMaker getAssisted() {
    return assisted;
  }

  public ClpGraphMaker setAssistantOf(ClpGraphMaker gm) {
    ClpGraphMaker temp = assisted;
    assisted = gm;
    return temp;
  }

  public ClpGraphMaker getAssistent() {
    return assistent;
  }

  public ClpGraphMaker setAssistedBy(ClpGraphMaker gm) {
    ClpGraphMaker temp = assistent;
    assistent = gm;
    return temp;
  }

  public HashMap<String, Variable> getIMap() {
    return imap;
  }

  public HashMap<String, Variable> getOMap() {
    return omap;
  }
}
