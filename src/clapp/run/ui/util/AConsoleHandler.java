package clapp.run.ui.util;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import clp.run.msc.MscOutput;
import clp.run.msc.Out;
import clp.run.msc.Output;
import clp.run.msc.OutputTarget;

public abstract class AConsoleHandler {

  abstract public void initialize(MscOutput mscOutput);
  abstract public void initialize(MscOutput mscOutput, JTabbedPane tp);
  abstract public void refresh();

  private boolean isInitialized;

  private Hashtable<String, Info> console;

  private static class Info {
    private JTextPane pane;
    private boolean isLog;
  }
  /**
   * constructor
   */
  public AConsoleHandler() {
    console = new Hashtable<>();
  }

  /**
   * add new tab (console) to given tabbed pane
   * 
   * @param tabbedPane
   * @param name
   * @return
   */
  public JTextPane addTab(JTabbedPane tabbedPane, String name) {
    CommonScrollPanel sp = new CommonScrollPanel(tabbedPane.getBounds());
    JTextPane ed = new JTextPane();
    JPanel jp = new JPanel();
    jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
    sp.getViewport().add(jp);
    jp.add(ed);
    removeOldTab(tabbedPane, name);
    tabbedPane.add(name, sp);
    Info info = new Info();
    info.pane = ed;
    console.put(name, info);
    return ed;
  }

  //
  private void removeOldTab(JTabbedPane tabbedPane, String name) {
    for (int i=0; i<tabbedPane.getTabCount(); i++) {
      String title = tabbedPane.getTitleAt(i);
      if (name.equals(title)) {
        tabbedPane.remove(i);
        break;
      }
    }
  }
  /**
   * set character and back-end colors if given, as well as output accessibility
   * 
   * @param ed
   * @param out
   * @param key 
   */
  public void setColors(JTextPane ed, Output out, String key) {
    Info info = console.get(key);
    info.isLog = (out.getOut() == Out.LOG);
    ed.setEnabled(out.getOut() != Out.OFF);
    ed.setBackground(getColor(out.getBackground()));
    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, getColor(out.getColor()));

    aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
    aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

    ed.setCharacterAttributes(aset, false);
  }

  //
  private Color getColor(String color) {
    String s = color.replace("#", "0x");
    return new Color(Integer.decode(s));
  }

  /**
   * print given string to given output
   * 
   * @param sb 
   * @param outputTarget
   */
  synchronized public void print(StringBuffer sb, OutputTarget outputTarget) {
    if (!isInitialized) {
      System.out.println(sb);
      return;
    }
    if (outputTarget == null || outputTarget.isStringCONSOLE()) {
      Info info = console.get("CONSOLE");
      if (info != null) {
        JTextPane c = info.pane;
        if (c == null) {
          System.err.println("ERROR: standard console not found");
        }
        else {
          write(sb.toString(), c, info.isLog);
        }
      }
    }
    else if (outputTarget.getName() != null) {
      Info info = console.get(outputTarget.getName());
      JTextPane c = info == null ? null : info.pane;
      if (c == null) {
        System.err.println("ERROR: console " + outputTarget.getName() + " not declared");
      }
      else {
        write(sb.toString(), c, info.isLog);
      }
    }
  }

  /**
   * print given string to standard output as error
   * 
   * @param string
   */
  public void eprint(String string) {
    if (!isInitialized || console.get("CONSOLE") == null) {
      System.err.println(string);
      return;
    }
    JTextPane c = console.get("CONSOLE").pane;
    if (c == null) {
      System.err.println("ERROR: standard console not found");
    }
    else {
      write(string, c, false);
    }
  }

  /**
   * print given string to standard output
   * 
   * @param string
   */
  public void print(String string) {
    if (!isInitialized || console.isEmpty()) {
      System.out.println(string);
      return;
    }
    Info info = console.get("CONSOLE");
    JTextPane c = info == null ? null : info.pane;
    if (c == null) {
      System.err.println("ERROR: standard console not found");
    }
    else {
      write(string, c, info.isLog);
    }
  }

  //
  private void write(String text, JTextPane c, boolean isLog) {
    if (c.isEnabled()) {
      if (isLog) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        text = "[" + timestamp + "]" + text;
      }
      c.setText(c.getText()+"\n"+text);
      SwingUtilities.invokeLater( new Runnable() { 
        public void run() { 
          c.requestFocus();
          refresh();
        } 
      } );
    }
  }

  /**
   * @param isInitialized the isInitialized to set
   */
  public void setInitialized(boolean isInitialized) {
    this.isInitialized = isInitialized;
  }

  /**
   * @return the isInitialized
   */
  public boolean isInitialized() {
    return isInitialized;
  }
}
