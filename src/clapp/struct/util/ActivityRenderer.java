package clapp.struct.util;

import java.util.ArrayList;

import clapp.run.Supervisor;
import clapp.run.util.AInterpreter;
import clapp.run.util.CellChainLink;
import clapp.run.util.CellQueueHandler;
import clp.run.act.Actor;
import clp.run.msc.MetaScenario;
import clp.run.scn.Scenario;

public class ActivityRenderer {

  /**
   * gets all existing tasks ({@link ADXThread} list) and extracts their queue info
   * @param msc
   * @return
   */
  public StringBuffer getRendering(MetaScenario msc) {
    ArrayList<String> keys = new ArrayList<String>();
    StringBuffer sb = new StringBuffer();
    for (AInterpreter task : Supervisor.getInstance().getConsolidatedTasks()) {
      String aname = task.getActor().getName();
      String prefix = getChainedNames(task);
      if (!keys.contains(aname)) {
        keys.add(aname);
        sb.append(renderFromQueue(task.getOperatingQueue(), prefix));
        sb.append(renderFromQueue(task.getPassingQueue(), prefix));
      }
    }
    return sb;
  }

  private String renderFromQueue(CellQueueHandler queue, String prefix) {
    String str = "";
    if (queue != null) {
      String qname = queue.getName();
      CellChainLink ccl = queue.getFirstCell();
      while (ccl != null) {
        str += prefix + qname + " -> ";
        str += ccl.getName() + "[" + ccl.getCell().getActivity() + "]\n";
        ccl = ccl.getNext();
      }
    }
    return str;
  }

  private String getChainedNames(AInterpreter thread) {
    Actor act = thread.getActor();
    Scenario scn = act.getScenario();
    MetaScenario msc = scn.getMetaScenario();
    return msc.getName() + ":" + scn.getName() + ":" + act.getName() + ":";
  }

}
