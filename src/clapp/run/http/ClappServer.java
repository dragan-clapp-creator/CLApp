package clapp.run.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import clapp.cmp.ClappMain;
import clapp.run.Supervisor;
import clapp.run.token.MarkHandler;
import clapp.run.ui.util.ConsoleProvider;
import clapp.run.util.ResourceUtility;

public class ClappServer extends Thread {

  enum State {
    PENDING, PROCESS, DEPLOY, FINISH;
  }

  private static ClappServer instance;

  // current server parameters
  private static int port;
  private static ServerSocket socket;
  private static IKrypter encryption;

  private static State state;


  private enum Type {
    CLAPP, RCLAPP, REJECTED;
  }
  private Type type;

  private StringBuffer buffer;  // received data

  private ClappMain clapp;

  /**
   * constructor
   */
  public ClappServer() {
    currentThread().setName("ClappServer");
  }

  static public boolean start(int p, IKrypter k) {
    try {
      port = p;
      encryption = k;
      socket = new ServerSocket( port );
      instance = new ClappServer();
      instance.start();
      return true;
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static boolean isSocketClosed() {
    return socket == null || socket.isClosed();
  }

  public void run() {
    boolean done = false;
    state = State.PROCESS;
    while (!done) {
      waitFor(123);
      switch (state) {
        case PROCESS:
          state = State.PENDING;
          try {
            ConsoleProvider.getInstance().print("server PROCESS listening on port " + port + " ...");
            new HTTPSession( socket.accept() );
            ConsoleProvider.getInstance().print("... server has PROCESSED");
          }
          catch ( IOException ioe ) {
            // something happened during the processing
            // a message was sent
            // now, we want to return to a normal state
          }
          break;
        case DEPLOY:
          if (clapp != null) {
            Supervisor.getInstance().deployAndRestart(clapp.getResult());
          }
          state = State.PROCESS;
          break;

        default:
          break;
      }
      done = (state == State.FINISH);
    }
    try {
      socket.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    ConsoleProvider.getInstance().print("server EXIT");
  }

  //============================================================================

  private void waitFor(int i) {
    try {
      Thread.sleep(123);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
      state = State.FINISH;
    }
  }

  //
  private void processCommand(String str) throws InterruptedException {
    if (str != null) {
      String[] sp = str.split("§§");
      
      String ret = null;
      if (str.startsWith("FINISH")) {
        state = State.FINISH;
      }
      else {
        ret = parseClapp(sp[0]);
      }
      if (ret == null) {
        Response r = new Response();
        handleReturn(r, sp);
        if (state != State.FINISH) {
          state = State.DEPLOY;
        }
      }
      else {
        Response r = new Response(ClappServer.HTTP_BADREQUEST);
        r.addHeader("ERROR", ret);
        sendResponse( r.status, r.mimeType, r.header, r.data );
        state = State.PROCESS;
      }
    }
  }

  //
  private void handleReturn(Response r, String[] sp) {
    if (type == Type.RCLAPP) {
      if (sp.length != 2) {
        r = new Response(ClappServer.HTTP_BADREQUEST);
        r.addHeader("ERROR", sp[0]);
        sendResponse( r.status, r.mimeType, r.header, r.data );
        state = State.PROCESS;
      }
      else {
        ResponseHandler rh = new ResponseHandler(r, sp[1], this);
        ResourceUtility.getInstance().register(rh);
        MarkHandler.getInstance().register(rh);
      }
    }
    else {
      sendResponse( r.status, r.mimeType, r.header, r.data );
    }
  }

  //
  private String parseClapp(String str) {
    System.out.printf("command = %s\n", str);
    InputStream is = new ByteArrayInputStream(str.getBytes(), 0, str.length());
    clapp = new ClappMain();
    String ret = clapp.silentParse(is);
    if (clapp.getResult().isMetaScenario()) {
      ret = "undeployable source";
      clapp = null;
    }
    return ret;
  }

  /**
   * Sends given response to the socket.
   */
  public void sendResponse( String status, String mime,
                             Properties header, InputStream data )
  {
    try {
      if ( status == null )
        throw new Error( "sendResponse(): Status can't be null." );

      OutputStream out = mySocket.getOutputStream();
      PrintWriter pw = new PrintWriter( out );
      pw.print("HTTP/1.0 " + status + " \n");

      if ( mime != null )
        pw.print("Content-Type: " + mime + "\n");

      if ( header == null || header.getProperty( "Date" ) == null )
        pw.print( "Date: " + gmtFrmt.format( new Date()) + "\n");

      if ( header != null ) {
        Enumeration<?> e = header.keys();
        while ( e.hasMoreElements()) {
          String key = (String)e.nextElement();
          String value = header.getProperty( key );
          pw.print( key + ": " + value + "\n");
        }
      }

      pw.print("\n");
      pw.flush();

      if ( data != null ) {
        byte[] buff = new byte[2048];
        while (true) {
          int read = data.read( buff, 0, 2048 );
          if (read <= 0)
            break;
          out.write( buff, 0, read );
        }
      }
      out.flush();
      out.close();
      if ( data != null )
        data.close();
    }
    catch( IOException ioe )
    {
      try { mySocket.close(); } catch( Throwable t ) {}
    }
    ResourceUtility.getInstance().register(null);
  }

  //====== INNER CLASS: HTTPSession ============================================

  /**
   * Some HTTP response status codes
   */
  public static final String
    HTTP_OK = "200 OK",
    HTTP_REDIRECT = "301 Moved Permanently",
    HTTP_FORBIDDEN = "403 Forbidden",
    HTTP_NOTFOUND = "404 Not Found",
    HTTP_BADREQUEST = "400 Bad Request",
    HTTP_INTERNALERROR = "500 Internal Server Error",
    HTTP_NOTIMPLEMENTED = "501 Not Implemented";

  /**
   * Common mime types for dynamic content
   */
  public static final String
    MIME_PLAINTEXT = "text/plain",
    MIME_HTML = "text/html",
    MIME_DEFAULT_BINARY = "application/octet-stream";

  /**
   * GMT date formatter
   */
  private static java.text.SimpleDateFormat gmtFrmt;
  static
  {
    gmtFrmt = new java.text.SimpleDateFormat( "E, d MMM yyyy HH:mm:ss 'GMT'",
                                              Locale.GERMANY );
    gmtFrmt.setTimeZone(TimeZone.getTimeZone("GMT"));
  }

  private Socket mySocket;

  /**
   * Handles one session, i.e. parses the HTTP request
   * and returns the response.
   */
  private class HTTPSession implements Runnable {

    public HTTPSession( Socket s ) {
      mySocket = s;
      Thread t = new Thread( this );
      t.setDaemon( true );
      t.start();
    }

    public void run() {
      try {
        interpretARequest();
      }
      catch ( IOException | InterruptedException ie ) {
        // ignore and exit the thread.
      }
    }

    private void interpretARequest() throws IOException, InterruptedException {
      InputStream is = mySocket.getInputStream();
      if ( is == null) return;
      InputStreamReader in = new InputStreamReader( is );

      String ret = serve( in );  // this will fill a string buffer
      if (type == Type.REJECTED) {
        Response r = new Response(ClappServer.HTTP_BADREQUEST);
        r.addHeader("ERROR", "Request not accepted");
        sendResponse( r.status, r.mimeType, r.header, r.data );
        state = State.PROCESS;
      }

      processCommand(ret);

      if (type != Type.RCLAPP) {
        in.close();
      }
    }

    private String serve(InputStreamReader in) throws IOException {
      buffer = new StringBuffer();
      fillBuffer(in);
      System.out.printf("buffer = %s\n", buffer);
      String str = encryption.decrypt(buffer);
      if (str.startsWith("CLAPP")) {
        type = Type.CLAPP;
      }
      else if (str.startsWith("RCLAP")) {
        type = Type.RCLAPP;
      }
      else {
        type = Type.REJECTED;
      }
      return type == Type.REJECTED ? null : str.substring(6);
    }

    //
    private void fillBuffer(InputStreamReader in) throws IOException {
      int b = in.read();
      while (b > 0) {
        buffer.append((char) b);
        b = in.read();
      }
    }
  }
}
