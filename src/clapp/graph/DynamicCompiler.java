package clapp.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import clapp.run.ui.util.ConsoleProvider;

public class DynamicCompiler {

  private static DiagnosticCollector<JavaFileObject> diagnostics;

  public static void main(String[] args) {
    DynamicCompiler cmp = new DynamicCompiler();
    try {
      cmp.compileDynamically(args[0], args[1], true);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 'dirName' is the file system tree node containing all java files that have
   * to be compiled 
   * @param dirName
   * @param isVerbose
   * @return 
   * @throws IOException 
   */
  public boolean compileDynamically(String dirName, String dest, boolean isVerbose)
  throws IOException {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    if (compiler == null) {
      ConsoleProvider.getInstance().eprint("Compiler not found");
      return false;
    }

    List<File> fileNames = getFiles(dirName);
    if (fileNames.isEmpty()) {
      ConsoleProvider.getInstance().eprint("nothing to compile");
      return true;
    }

    List<String> options = new ArrayList<String>();
    options.addAll(Arrays.asList("-d", dest));
    new File(dest).mkdirs();

    CompilationTask task = makeCompileTask(fileNames, options, compiler);

    boolean isSuccess = true;
    if (!task.call()) {
      ConsoleProvider.getInstance().eprint("Compilation failed");
      ConsoleProvider.getInstance().eprint("");
      isSuccess = false;
    }
    else if (diagnostics.getDiagnostics().isEmpty()) {
      ConsoleProvider.getInstance().print("compilation ended successfully");
      return true;
    }
    List<Diagnostic<? extends JavaFileObject>> diags;
    if (isVerbose) {
      for (File file : fileNames) {
        diags = getDiagnotics(file.getAbsolutePath());
        if (!diags.isEmpty()) {
          isSuccess = false;
          for (Diagnostic<? extends JavaFileObject> d : diags) {
            ConsoleProvider.getInstance().print(d.toString());
            ConsoleProvider.getInstance().print("");
          }
        }
      }
    }
    else {
      diags = diagnostics.getDiagnostics();
      if (!diags.isEmpty()) {
        isSuccess = false;
        for (Diagnostic<? extends JavaFileObject> d : diags) {
          ConsoleProvider.getInstance().print(d.toString());
          ConsoleProvider.getInstance().print("");
        }
      }
    }
    if (isSuccess) {
      ConsoleProvider.getInstance().print("compilation ended successfully");
    }
    return isSuccess;
  }

  private List<Diagnostic<? extends JavaFileObject>> getDiagnotics(String name) {
    List<Diagnostic<? extends JavaFileObject>> diags =
      new ArrayList<Diagnostic<? extends JavaFileObject>>();

    for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) {
      if (d.getSource().toString().equals(name)) {
        diags.add(d);
      }
    }
    return diags;
  }

  /**
   * create a compilation task using the supplied standard file manager,
   * diagnostic collector, and applied to the Java file objects
   * @param fileNames
   * @param options
   * @param compiler
   * @return task
   * @throws IOException 
   */
  private CompilationTask makeCompileTask(List<File> fileNames,
                                         List<String> options,
                                         JavaCompiler compiler)
  throws IOException {
    diagnostics = new DiagnosticCollector<JavaFileObject>();

    StandardJavaFileManager fileMan =
      compiler.getStandardFileManager(diagnostics, null, null);

    Iterable<? extends JavaFileObject> fileObjs =
      fileMan.getJavaFileObjectsFromFiles(fileNames);

    return compiler.getTask(null, fileMan, diagnostics, options, null, fileObjs);
  }

  /**
   * gather all java files in the subtree named by 'dirName'
   * @param dirName
   * @return
   * @throws IOException 
   */
  private List<File> getFiles(String dirName) throws IOException {
    ArrayList<File> files = new ArrayList<File>();
    File f = new File(dirName);
    if (f == null || !f.exists()) {
      ConsoleProvider.getInstance().eprint("file "+dirName+" not found");
      throw new IOException();
    }
    File[] lf = f.listFiles();
    for (int i=0; i<lf.length; i++) {
      if (lf[i].isFile()) {
        if (lf[i].getName().endsWith(".java")) {
          files.add(lf[i]);
        }
      }
      else {
        files.addAll(getFiles(lf[i].getAbsolutePath()));
      }
    }
    return files;
  }
}