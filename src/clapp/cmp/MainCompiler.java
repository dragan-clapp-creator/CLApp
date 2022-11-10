package clapp.cmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import clapp.cmp.ClappMain;
import clapp.debug.Logger;


public class MainCompiler {

  public MainCompiler() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    MainCompiler m = new MainCompiler();
    m.compile(args);
    System.out.println(("...END OF COMPILATION"));
    System.exit(0);
  }

  private static int getForceOptionIndex(String[] args) {
    for (int i=0; i<args.length; i++) {
      if (args[i].startsWith("-f")) {
        return i;
      }
    }
    return -1;
  }

  private static void info() {
    System.out.println("Usage: clapp.MainCompiler <opt> <arg>");
    System.out
        .println("\t<opt> is the option part; \t<arg> is a file name. Details are listed below:");
    System.out.println("\t\topt:\t-p \targ (default ending \".prj\")");
    System.out
        .println("\t\t\t\t -> represents a project file containing other inline options");
    System.out.println("\t\t\t-x \targ (default ending \".clp\")");
    System.out
        .println("\t\t\t\t -> represents a clapp file containing any clapp definition");
    System.out.println("\t\t\t-f (force weaving)");
  }

  /**
   * compile entry point
   * @param args
   */
  public void compile(String[] args) {
    if (args == null || args.length == 0) {
      info();
    }
    else {
      int ind = getForceOptionIndex(args);
      if (ind >= 0) {
        args = getRestArgs(ind, args);
      }
      try {
        parse(args);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private String[] getRestArgs(int ind, String[] args) {
    args[ind] = "";
    String str = args[0];
    for (int i=1; i<args.length; i++) {
      str += " " + args[i];
    }
    args = str.split(" ");
    return args;
  }

  private void parse(String[] args) throws IOException {
    for (int i = 0; i < args.length; i += 2) {
      if (i<args.length-1) {
        process(args[i], args[i + 1]);
      }
    }
  }

  private void process(String op, String name) throws IOException {
    if (op.equals("-p")) {
      File f = new File(getName(name, ".prj"));
      if (f.exists()) {
        String[] args = getFileContent(f);
        int ind = getForceOptionIndex(args);
        if (ind >= 0) {
          args = getRestArgs(ind, args);
        }
        parse(args);
      }
    }
    else if (op.equalsIgnoreCase("-x")) {
      new ClappMain().parse(getName(name, ".clp"));
    }
    else if (op.equalsIgnoreCase("-l")) {
      Logger.openLogFileWithTimestamp(name);
    }
    else {
      info();
    }
  }

  private String[] getFileContent(File f) throws IOException {
    StringBuffer buf = new StringBuffer();
    BufferedReader br = new BufferedReader(new FileReader(f));
    boolean eof = false;
    while (!eof) {
      try {
        String line = br.readLine();
        if (line == null) {
          eof = true;
        } else {
          buf.append(line + " ");
        }
      } catch (IOException e) {
        eof = true;
      }
    }
    br.close();
    StringTokenizer st = new StringTokenizer(buf.toString());
    Vector<String> args = new Vector<String>();
    while (st.hasMoreTokens()) {
      args.add(st.nextToken());
    }
    String res[] = new String[args.size()];
    for (int i = 0; i < args.size(); i++) {
      res[i] = new String((String) args.get(i));
    }
    return (res);
  }

  private String getName(String name, String type) {
    if (!name.endsWith(type)) {
      name += type;
    }
    return name;
  }

  static public void print() {
    System.out.println(new Throwable().getClass().getClassLoader());
  }
}
