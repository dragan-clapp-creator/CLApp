package clapp.run.util;

import clapp.run.http.WebRequest;

public class Watchdog {

  private long expirationTime;
  private long TIMEOUT;
  private WdThread wdthread;
  private boolean isStarted;
  private WebRequest request;

  public Watchdog() {
    super();
  }


  public void setLimit(int i) {
    TIMEOUT = i*1000;
  }

  public void start() {
    if (TIMEOUT > 0) {
      expirationTime = System.currentTimeMillis() + TIMEOUT;
      if (wdthread != null && wdthread.isAlive()) {
        wdthread.cancel();  // kill it softly, since another one will work instead
      }
      startNewOne();
    }
  }

  public void register(WebRequest request) {
    this.request = request;
  }


  public void touch() {
    expirationTime = System.currentTimeMillis() + TIMEOUT;
  }

  private void startNewOne() {
    wdthread = new WdThread(Thread.currentThread().getThreadGroup(), "CLAppWatchDog");
    wdthread.start();
    isStarted = true;
  }


  public boolean isStoped() {
    return !isStarted;
  }

  private void expire() {
    isStarted = false;
    request.sendStop();
  }

  /**
   * internal class that handles timeout in a separate Thread
   * @author Dragan Matic
   *
   */
  private class WdThread extends Thread {

    private boolean isCancelled;

    public WdThread(ThreadGroup threadGroup, String string) {
      super(threadGroup, string);
    }

    public void cancel() {
      isCancelled = true;
    }

    public void run() {
      try {
        boolean done = false;
        while (!done) {
          long ct = System.currentTimeMillis();
          long delta = expirationTime - ct;
          if (delta > 0) {
            Thread.sleep(delta);
          }
          else {
            if (!isCancelled) {
              expire();
            }
            done = true;
          }
        }
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
