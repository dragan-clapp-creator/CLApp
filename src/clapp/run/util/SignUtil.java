package clapp.run.util;

import clp.run.res.Fsigned;
import clp.run.res.Isigned;
import clp.run.res.Lsigned;
import clp.run.res.TermOperator;

public class SignUtil {

  public static Integer getIInitial(Isigned is) {
  	if (is == null) {
  		return null;
  	}
    int init = is.getInitial();
  	if (is.getTermOperator() == TermOperator.MINUS) {
  		init = -init;
  	}
    return init;
  }

  public static Double getFInitial(Fsigned fs) {
  	if (fs == null) {
  		return null;
  	}
    double init = fs.getInitial();
  	if (fs.getTermOperator() == TermOperator.MINUS) {
  		init = -init;
  	}
    return init;
  }

	public static Long getLInitial(Lsigned ls) {
  	if (ls == null) {
  		return null;
  	}
    long init = ls.getInitial();
  	if (ls.getTermOperator() == TermOperator.MINUS) {
  		init = -init;
  	}
    return init;
  }
}
