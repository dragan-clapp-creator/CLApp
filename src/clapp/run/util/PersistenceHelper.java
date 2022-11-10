package clapp.run.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import clapp.run.http.WebRequest;
import clp.run.res.Resources;
import clp.run.res.SVar;
import clp.run.res.Variable;
import clp.run.res.WebVariable;


public class PersistenceHelper {

  public static void persist(Resources res, String result, String name) {
    Variable var = ResourceUtility.getInstance().getVariable(res, name);
    if (var != null) {
      if (var instanceof SVar) {
        // persist in a string variable
        ((SVar)var).setValue(result);
      }
      else if (var instanceof WebVariable) {
        WebVariable wr = (WebVariable)var;
        // send to a CLApp web server
        new WebRequest(res).send(wr, result, 0, null);
      }
    }
    else {
      try {
        // persist in a file (that's a real persistance)
        FileOutputStream fos = new FileOutputStream(checkPath(name));
        fos.write(result.getBytes());
        fos.close();
      }
      catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  private static String checkPath(String filename) {
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
