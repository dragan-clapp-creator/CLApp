/*
 * Created on Jan 13, 2012
 *
 */
package clapp.run.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;

/**
 * @author Dragan Matic
 *
 */
public class ClpClassHandler {

  final static public String MYCLASSES = "Temp/PatchedClasses/";

  private static ClpClassHandler instance = new ClpClassHandler();

  private ClpLoader loader;
  private ArrayList<String> paths;

  // CONSTRUCTOR
  private ClpClassHandler() {
    paths = new ArrayList<String>();
  }

  public static ClpClassHandler getInstance() {
    return instance;
  }

  public void registerBin(String dir) {
    if (!dir.endsWith("/")) {
      dir += "/";
    }
    if (!paths.contains(dir)) {
      paths.add(dir);
    }
  }

  public void registerJar(String file) {
    if (!paths.contains(file)) {
      paths.add(file);
    }
  }

  public ClpLoader getLoader() {
    return loader;
  }

  public void removePatches() {
    remove(new File(MYCLASSES));
  }

  private void remove(File path) {
    File files[] = path.listFiles();
    if (files != null) {
      for (int i = 0; i < files.length; i++) {
      	if (files[i].isDirectory()) {
      	  remove(files[i]);
      	}
      	files[i].delete();
      }
    }
  }

  @SuppressWarnings("deprecation")
  public void initializeLoader() {
    loader = null;
    if (!paths.isEmpty()) {
      try {
        URL[] urls = new URL[paths.size()+1];
        urls[0] = new File(MYCLASSES).toURL();
        for (int i=0; i<paths.size(); i++) {
          urls[i+1] = new File((String) paths.get(i)).toURL();
        }
        loader = new ClpLoader(urls);
        Thread.currentThread().setContextClassLoader(loader);
      }
      catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
  }

  // INNER CLASS
  static private class ClpLoader extends URLClassLoader {

    public ClpLoader(URL[] urls) {
      super(urls);
    }


    public ClpLoader(URL[] urls, ClassLoader parent) {
      super(urls, parent);
    }

    public ClpLoader(URL[] urls, ClassLoader parent,
        URLStreamHandlerFactory factory) {
      super(urls, parent, factory);
    }

    public ClpLoader(ClpLoader loader, URL[] urls) {
      super(urls);
    }

    // override the original
    public synchronized Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException {

      if (resolve) {
        return null;
      }
      Class<?> drvClass = null;
      try {
//        if (hasClassWeaver(name)) {
//          byte[] b = getClassData(name);
//          drvClass = defineClass(name, b, 0, b.length);
//        }
//        else {
          return super.loadClass(name, resolve);
//        }
      }
      catch (NoClassDefFoundError e) {
        // catches abrstact & interface modules
      }
      return drvClass;
    }
  }

  public Class<?> loadClass(String clname) throws ClassNotFoundException {
    return loader.loadClass(clname);
  }

  public ArrayList<String> getPaths() {
    return paths;
  }
}
