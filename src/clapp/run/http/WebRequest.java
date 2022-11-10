package clapp.run.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import clapp.debug.Logger;
import clapp.debug.Severity;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.ResourceUtility;
import clapp.run.util.Watchdog;
import clapp.run.vis.ClpSendItemsVisitor;
import clp.run.cel.web.SendItems;
import clp.run.res.Address;
import clp.run.res.Resources;
import clp.run.res.WebVariable;


public class WebRequest {

  static private final int LIMIT = 5000;
  static private StringBuffer buffer = new StringBuffer();
  static private Watchdog watchdog = new Watchdog();

  private Resources res;
  private WebVariable webVar;
  private DefaultEncrypter encrypter;

  public WebRequest(Resources r) {
    res = r;
    encrypter = new DefaultEncrypter();
  }

  public void send(WebVariable wbv, String req, int timeout, String resp) {
    if (req.isBlank()) {
      ConsoleProvider.getInstance().print("[SEND] nothing to send");
      return;
    }
    try {
      webVar = wbv;
      fillBuffer(req, timeout);
      if (isReadyToSend(timeout)) {
        req = getBufferedReqs();
        String str = req.length() >= 20 ? req.substring(0, 20) : req;
        ConsoleProvider.getInstance().print("[SEND] "+str+"...");
        int code = request(getSocket(wbv), req, resp);
        interpretCode(code, req);
      }
    }
    catch (UnknownHostException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

   public void send(WebVariable wb, SendItems items, int timeout, String resp) {
    try {
      ClpSendItemsVisitor siv = new ClpSendItemsVisitor(res, wb.getEncryption());
      items.accept(siv);
      int code;
      if (siv.isFile()) {
        code = requestFromFile(getSocket(wb), siv.getFileName());
        interpretCode(code, "from file "+siv.getFileName());
      }
      else {
        webVar = wb;
        if (fillBuffer(siv.getItems(), timeout)) {
          if (isReadyToSend(timeout)) {
            String req = getBufferedReqs();
            code = request(getSocket(wb), req, resp);
            interpretCode(code, req);
          }
        }
      }
    }
    catch (UnknownHostException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private boolean fillBuffer(String req, int limit) {
    if (req == null || req.length() == 0) {
      return false;
    }
    if (limit <= 0) {
      buffer.append(replaceHolders(req));
      return true;
    }
    if (watchdog.isStoped()) {
      watchdog.setLimit(limit);
      watchdog.register(this);
      watchdog.start();
    }
    else {
      watchdog.touch();
    }
    buffer.append(req);
    return true;
  }

  private String replaceHolders(String req) {
    String[] sp = req.split("#");
    String updatedString = sp[0];
    for (int i=1; i<sp.length; i++) {
      updatedString += getReplacedString(sp[i-1], sp[i]);
    }
    return updatedString;
  }

  private String getReplacedString(String string1, String string2) {
    String[] sp = string2.split(" ");
    String name = sp[0];
    sp = string1.split(" ");
    for (int i=sp.length-2; i>0; i--) {
      if (name.equals(sp[i])) {
        String type = sp[i-1];
        String value = findValue(type, name);
        if ("STRING".equals(type)) {
          return string2.replace(name, "\"" + value + "\"");
        }
        return string2.replace(name, value);
      }
    }
    return null;
  }

  private String findValue(String stype, String name) {
    if ("EVENT".equals(stype)) {
      return "true";
    }
    return "" + ResourceUtility.getInstance().getValue(name);
  }

  private boolean isReadyToSend(int timeout) {
    return  timeout <= 0 || buffer.length() > LIMIT || watchdog.isStoped();
  }

  private String getBufferedReqs() {
    StringBuffer sb = buffer;
    buffer = new StringBuffer();
    return sb.toString();
  }

  public void sendStop() {
    try {
      String req = getBufferedReqs();
      int code = request(getSocket(webVar), req+"/END_MESSAGE/DONE", null);
      interpretCode(code, req);
    }
    catch (UnknownHostException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void sendFile(WebVariable wbv, String fileName) {
    try {
      Socket socket = getSocket(wbv);
      int code = requestFromFile(socket, fileName);
      interpretCode(code, "from file");
    }
    catch (UnknownHostException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  //============================================================================

  private Socket getSocket(WebVariable wbv) throws UnknownHostException, IOException {
    Address adr = wbv.getAddress();
    if (adr == null) {
      return new Socket("localhost", wbv.getPort().getNum());
    }
    return new Socket(wbv.getAddress().toString(), wbv.getPort().getNum());
  }

  private int request(Socket socket, String req, String resp )
  throws IOException, InterruptedException {
    String ostr;
    if (resp != null) {
      ostr = "RCLAPP " + req + "§§" + resp;
    }
    else {
      ostr = "CLAPP " + req + "\n";
    }
    OutputStream os = socket.getOutputStream();
    os.write(encrypter.encrypt(ostr.getBytes()));
    os.write(0);
    os.flush();
    return response(socket);
  }

  private int requestFromFile(Socket socket, String file)
  throws IOException, InterruptedException {
    File f = new File(file);
    if (f.exists() && f.isFile()) {
      FileInputStream is = new FileInputStream(f);
      byte[] b = new byte[(int) f.length()+6];
      is.read(b, 6, (int) f.length());
      b[0] = 'C';
      b[1] = 'L';
      b[2] = 'A';
      b[3] = 'P';
      b[4] = 'P';
      b[5] = ' ';
      OutputStream os = socket.getOutputStream();
      os.write(encrypter.encrypt(b));
      os.write(0);
      os.flush();
      is.close();
      return response(socket);
    }
    return -1;
  }

  private int response(Socket socket) throws IOException, InterruptedException {
    int code = -1;
    InputStream is = returnAfterWait(socket);
    if (is == null) {
      return -1;
    }
    BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
    String istr = in.readLine();
    if (istr == null) {
      ConsoleProvider.getInstance().eprint("no input stream...");
    }
    else {
      StringTokenizer st = new StringTokenizer(istr);
      if (st != null && st.countTokens() > 2) {
        String s = st.nextToken();
        s = st.nextToken();
        code = Integer.parseInt(s);
      }
      ConsoleProvider.getInstance().print("client received response:");
      while (istr != null) {
        ConsoleProvider.getInstance().print("\t" + istr);
        extractData(istr);
        istr = in.readLine();
      }
    }
    in.close();
    return code;
  }

  //
  private void extractData(String istr) {
    String[] sp = istr.split(":");
    if (sp.length == 2) {
      String key = sp[0];
      String val = sp[1].trim();
      Object value = convert(val.substring(1, val.length()-1));
      ResourceUtility.getInstance().setValue(key, value);
    }
  }

  //
  private Object convert(String val) {
    if ("true".equalsIgnoreCase(val) || "false".equalsIgnoreCase(val)) {
      return Boolean.parseBoolean(val);
    }
    try {
      return Integer.parseInt(val);
    }
    catch(NumberFormatException e) {}
    try {
      return Float.parseFloat(val);
    }
    catch(NumberFormatException e) {}

    return val;
  }

  private InputStream returnAfterWait(Socket socket) throws IOException, InterruptedException {
    InputStream is = socket.getInputStream();
    while (is == null) {
      Thread.sleep(200);
      is = socket.getInputStream();
    }
    return is;
  }

  private void interpretCode(int code, String req) {
    if (code > 400) {
      Logger.getInstance().log("error "+code+" for request "+req.replaceAll("\"", "'"), Severity.ERROR);
    }
    else if (code < 0) {
      Logger.getInstance().log("no input stream for request "+req.replaceAll("\"", "'"), Severity.ERROR);
    }
    else if (code != 200) {
      Logger.getInstance().log("code =" + code + " for request "+req.replaceAll("\"", "'"), Severity.ERROR);
    }
    else {
      ConsoleProvider.getInstance().print("DONE");
    }
  }
}
