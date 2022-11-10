package clapp.weave.res.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

import org.apache.bcel.classfile.JavaClass;

public class ClpWeavePersistence {

  private String pathFromLib;
  private String pathToLib;
  private String pathToClasses;
  private String toClass;
  private JavaClass java_class;

  public ClpWeavePersistence(String pfl, String ptl, String ptc, String tc, JavaClass jc) {
    pathFromLib = pfl;
    pathToLib = ptl;
    if (ptl != null && !ptl.endsWith("/")) {
      pathToLib += "/";
    }
    pathToClasses = ptc;
    if (ptc != null && !ptc.endsWith("/")) {
      pathToClasses += "/";
    }
    toClass = tc;
    java_class = jc;
  }

  public void persist() throws FileNotFoundException, IOException {
    if (pathFromLib != null && pathToLib != null) {
      persistClassInLib();
    }
    else {
      persistClassInDir();
    }
  }

  private void persistClassInDir() throws IOException {
    String file_name = pathToClasses + toClass.replace('.', '/') + ".class";
    java_class.dump(file_name);
  }

  private void persistClassInLib()
        throws FileNotFoundException, IOException {
    JarInputStream  jis = new JarInputStream(new FileInputStream(pathFromLib));
    JarOutputStream jos = new JarOutputStream(new FileOutputStream(checkPath(pathToLib)),
                                              jis.getManifest());
    boolean isdone = false;
    while (!isdone) {
      JarEntry ije = jis.getNextJarEntry();
      if (ije == null) {
        isdone = true;
      }
      else {
        if (ije.getName().equalsIgnoreCase(toClass)) {
          JarEntry nje = new JarEntry(toClass);
          jos.putNextEntry(nje);
          jos.write(java_class.getBytes());
        }
        else {
          jos.putNextEntry(ije);
          while (jis.available() != 0) {
            jos.write(jis.read());
          }
        }
      }
    }
    jos.close();
    jis.close();
  }

  private String checkPath(String filename) {
    int l = filename.lastIndexOf(File.separator);
    if (l < 0) {
      l = filename.lastIndexOf("/"); //$NON-NLS-1$
      if (l < 0) {
        throw new RuntimeException("incorrect path name"); //$NON-NLS-1$
      }
    }
    String dirname = filename.substring(0, l);
    File f = new File(dirname);
    if (!f.exists()) {
      f.mkdirs();
    }
    return filename;
  }
}
