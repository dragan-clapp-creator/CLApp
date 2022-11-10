package clapp.run.ui;

import java.util.ArrayList;
import java.util.Hashtable;

import clapp.run.util.ResourceUtility;

public class UIChecker {

  public boolean assertNotEmpty(Object o) {
    return o != null && !o.toString().isEmpty();
  }

  public boolean assertNotNull(String s) {
    return s != null && !s.isEmpty();
  }

  public boolean assertCellEqualty(String s1, String s2) {
    return s1 != null && s1.endsWith(s2);
  }

  public boolean assertSelectedLine(int line) {
    return line > -1;
  }

  public void assign(ArrayList list, Integer line, String[] varNames, String[] values) {
    if (list != null && line < list.size() &&
        values != null && varNames != null && values.length == varNames.length) {
      Object obj = list.get(line);
      if (obj instanceof Hashtable) {
        Hashtable<?, ?> hash = (Hashtable<?, ?>) obj;
        for (int i=0; i<values.length; i++) {
          String key = values[i];
          Object val = hash.get(key);
          ResourceUtility.getInstance().setValue(varNames[i], val);
        }
      }
    }
  }
}
