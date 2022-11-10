package clapp.run.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.StringTokenizer;

import clapp.run.ui.util.ConsoleProvider;

public class ClappSender {

  private Socket socket;
  private IKrypter encrypter;
  private Properties header = new Properties();

  static public void main(String[] args) {
    int port = Integer.parseInt(args[0]);
    String arg = args[1];
    if (arg.equals("R")) {
      try {
        new ClappSender().sendReceiving(port, args[2]);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    else {
      File file = new File(arg);
      try {
        new ClappSender().sendFile(port, file);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * default constructor
   */
  public ClappSender() {
    this(null);
  }

  /**
   * constructor with given encrypting file
   * 
   * @param krypter
   */
  public ClappSender(IKrypter krypter) {
    if (krypter == null) {
      encrypter = new DefaultEncrypter();
      System.out.println("use of DefaultEncrypter");
    }
    else {
      encrypter = krypter;
      System.out.println("use of "+krypter.getClass().getSimpleName());
    }
  }

  /**
   * send file to port
   * 
   * @param port
   * @param file
   * @throws IOException
   */
  public void sendFile(int port, File file) throws IOException {
    sendFile(null, port, file);
  }

  public void sendReceiving(int port, String string) throws IOException {
    if (port == 0) {
      ConsoleProvider.getInstance().eprint("Port should not be 0");
    }
    else if (string == null) {
      ConsoleProvider.getInstance().eprint("String should not be null");
    }
    else if (connect(null, port)) {
      String str = "RCLAP " + string;
      int code = requestFromStream(str.getBytes());
      interpretCode(code);
      System.out.println(header);
    }
    else {
      ConsoleProvider.getInstance().eprint("could not connect to port " + port);
    }
  }

  /**
   * send file to port on remote host
   * 
   * @param host
   * @param port
   * @param file
   * @throws IOException
   */
  public void sendFile(String host, int port, File file) throws IOException {
    if (port == 0) {
      ConsoleProvider.getInstance().eprint("Port should not be 0");
    }
    else if (file == null) {
      ConsoleProvider.getInstance().eprint("File should not be null");
    }
    else if (connect(host, port)) {
      int code = requestFromFile(file);
      interpretCode(code);
    }
    else {
      ConsoleProvider.getInstance().eprint("could not connect to port " + port);
    }
  }

  /**
   * send source text to port
   * 
   * @param port
   * @param source
   * @return 
   * @throws IOException
   */
  public int sendSource(int port, String source) throws IOException {
    return sendSource(null, port, "CLAPP "+source);
  }

  /**
   * send source text to port on remote host
   * 
   * @param host
   * @param port
   * @param source
   * @return 
   * @throws IOException
   */
  public int sendSource(String host, int port, String source) throws IOException {
    if (port == 0) {
      ConsoleProvider.getInstance().eprint("Port should not be 0");
    }
    else if (source == null) {
      ConsoleProvider.getInstance().eprint("Source should not be null");
    }
    else if (connect(host, port)) {
      int code = requestFromStream(source.getBytes());
      interpretCode(code);
      return code;
    }
    else {
      ConsoleProvider.getInstance().eprint("could not connect to port " + port);
    }
    return -1;
  }

  /**
   * send empty statement ('.') to notify server should stop
   * 
   * @param port
   * @return code from receiver
   * @throws IOException 
   */
  public int sendFinish(int port) {
    return sendFinish(null, port);
  }

  /**
   * send empty statement ('.') to notify remote host server should stop
   * 
   * @param host
   * @param port
   * @return code from receiver
   * @throws IOException 
   */
  public int sendFinish(String host, int port) {
    ConsoleProvider.getInstance().print("======>FINISH");
    try {
      return sendSource(host, port, "FINISH\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return -1;
  }

  //
  private boolean connect(String host, int port) throws IOException {
    ConsoleProvider.getInstance().print("client connection...");
    if (socket != null) {
      socket.close();
    }
    if (host == null) {
      socket = new Socket("127.0.0.1", port);
    }
    else {
      socket = new Socket(host, port);
    }
    return true;
  }

  //
  private int requestFromFile(File f) throws IOException {
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
      int ret = requestFromStream(b);
      is.close();
      return ret;
    }
    return -1;
  }

  //
  private int requestFromStream(byte[] b) throws IOException {
    OutputStream os = socket.getOutputStream();
    os.write(encrypter.encrypt(b));
    os.write(0);
    os.flush();
    return response();
  }

  //
  private int response() throws IOException {
    int code = -1;
    InputStream is = socket.getInputStream();
    BufferedReader in = new BufferedReader( new InputStreamReader( is ) );
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
        String[] sp = istr.split(":");
        if (sp.length == 2 && !sp[0].equals("Date")) {
          header.put(sp[0], sp[1].trim());
        }
        else {
          ConsoleProvider.getInstance().print("\t" + istr);
        }
        istr = in.readLine();
      }
    }
    in.close();
    return code;
  }

  //
  private void interpretCode(int code) {
    if (code > 400) {
      ConsoleProvider.getInstance().eprint("error "+code);
    }
    else if (code < 0) {
      ConsoleProvider.getInstance().eprint("error : local file doesn't exist ("+code+")");
    }
    else {
      ConsoleProvider.getInstance().print("DONE");
    }
  }
}
