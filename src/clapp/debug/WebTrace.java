package clapp.debug;

import clapp.run.http.WebRequest;
import clp.run.res.WebVariable;


public class WebTrace extends ATrace {

  private WebVariable wbv;
  private WebRequest req;

  public WebTrace(WebVariable var) {
    wbv = var;
    req = new WebRequest(null);
  }

  public void send(String str) {
    req.send(wbv, str, 0, null);
  }
}
