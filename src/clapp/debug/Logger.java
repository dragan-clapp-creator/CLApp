package clapp.debug;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.Locale;

public class Logger {

  private boolean isTimestamp;
  private String  fileName;
  private RandomAccessFile file;

  /**
   * date formatter
   */
  private static java.text.SimpleDateFormat dateFrmt;
  private static java.text.SimpleDateFormat timeFrmt;
  private static Logger logger = new Logger();
  static
  {
    dateFrmt = new java.text.SimpleDateFormat( "yyyy-MMM-d", Locale.GERMANY);
    timeFrmt = new java.text.SimpleDateFormat( "HH:mm:ss", Locale.GERMANY);
  }

  // constructor
  private Logger(String filename, boolean stamp) throws IOException {
    isTimestamp = stamp;
    fileName = filename;
    file = getFile();
  }

  public Logger() {
    // just dummy logger
  }

  private RandomAccessFile getFile() throws IOException {
    File f = new File(fileName, "rw");
    RandomAccessFile rf = new RandomAccessFile(fileName, "rw");
    if (!f.exists()) {
      rf.writeBytes("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
      rf.writeBytes("<l>");
      rf.writeBytes("<h n=\"" + fileName + ".trc\" v=\"1.5\" d=\"" + dateFrmt.format(new Date()) +
                     "\" t=\"" + timeFrmt.format(new Date()) + "\">");
      rf.writeBytes("<f n=\"CLApp Logger\" v=\"" + fileName + "\" />");
      rf.writeBytes("<f n=\"Timestamp\" v=\"" + isTimestamp + "\" />");
      rf.writeBytes("</h>");
      rf.writeBytes("<rs>");
    }
    rf.seek(rf.length());
    return rf;
  }

  // public methods---
  public void log(String string, String severity) {
    if (file == null) {
      return;
    }
    try {
      if (isTimestamp) {
	file.writeBytes("<r id=\"" + System.currentTimeMillis() +
	                "\" s=\"" + severity +
	                "\" d=\"" + dateFrmt.format(new Date()) +
	                "\" t=\"" + timeFrmt.format(new Date()) + "\"" +
	                " m=\"" + string + "\" />");
      }
      else {
        file.writeBytes("<r m=\"" + string + "\" />");
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void closeLogger() throws IOException {
    file.writeBytes("</rs>");
    file.writeBytes("</l>");
    file.close();
  }

  // static providers---
  public static Logger getInstance() {
    return logger;
  }

  public static Logger openLogFile(String name) throws IOException {
    return logger = new Logger(name, false);
  }

  public static Logger openLogFileWithTimestamp(String name) throws IOException {
    return logger = new Logger(name, true);
  }
}
