package clapp.run.http;

public class RespWatchdog {

  private ResponseHandler responseHandler;
  private long expirationTime;
  private WdThread wd;

  public RespWatchdog(ResponseHandler rh, long delay) {
    responseHandler = rh;
    expirationTime = System.currentTimeMillis() + delay;
    wd = new WdThread(Thread.currentThread().getThreadGroup(), "CLAppWatchDog");
    wd.start();
  }

  public void cancel() {
    wd = null;
  }

  private void expire() {
    responseHandler.expired();
  }

  private class WdThread extends Thread {

    public WdThread(ThreadGroup threadGroup, String string) {
      super(threadGroup, string);
      setDaemon(true);
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
            expire();
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
