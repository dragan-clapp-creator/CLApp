package clapp.run.http;

import java.util.ArrayList;

import clapp.run.util.ResourceUtility;

public class ResponseHandler {

  static final private long defaultTimeout = 1000L;

  private Response response;
  private ArrayList<String> list;
  private ArrayList<Boolean> vals;
  private ClappServer clappServer;
  private RespWatchdog watchdog;

  /**
   * 
   * @param r response
   * @param s string
   * @param c clappServer 
   */
  public ResponseHandler(Response r, String s, ClappServer c) {
    this.response = r;
    this.clappServer = c;
    this.list = new ArrayList<>();
    this.vals = new ArrayList<>();
    String[] sp = s.split(",");
    for (String str : sp) {
      if (str.startsWith("#TIMEOUT")) {
        try {
          long timeout = Long.parseLong(str.substring(8));
          watchdog = new RespWatchdog(this, timeout);
        }
        catch (NumberFormatException e) {
        }
      }
      else if (str.startsWith("!")) {
        list.add(str.substring(1));
        vals.add(false);
      }
      else {
        list.add(str);
        vals.add(true);
      }
    }
    if (watchdog == null) {
      watchdog = new RespWatchdog(this, defaultTimeout);
    }
  }

  public void notifyDone(String key, Object val) {
    int i = list.indexOf(key);
    if (i >= 0) {
      if (vals.get(i) == Boolean.FALSE) {
        if (val == Boolean.FALSE) {
          list.remove(i);
          vals.remove(i);
          response.header.put(key, "\""+val+"\"");
        }
      }
      else {
        list.remove(i);
        vals.remove(i);
        response.header.put(key, "\""+val+"\"");
      }
    }
    if (list.isEmpty()) {
      clappServer.sendResponse( response.status, response.mimeType, response.header, response.data );
      System.out.println(response.header);
      System.out.println("response sent from notification");
    }
  }

  public void expired() {
    watchdog.cancel();
    if (!list.isEmpty()) {
      for (int i=0; i<list.size(); i++) {
        String key = list.get(i);
        Object val = ResourceUtility.getInstance().getValue(key);
        if (vals.get(i) == Boolean.FALSE) {
          if (val == Boolean.FALSE) {
            response.header.put(key, "\""+val+"\"");
          }
        }
        else if (val != null) {
          response.header.put(key, "\""+val+"\"");
        }
      }
    }
    clappServer.sendResponse( response.status, response.mimeType, response.header, response.data );
    System.out.println("response sent from timeout");
  }
}
