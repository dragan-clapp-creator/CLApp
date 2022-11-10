package clapp.run.ui.util;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import clp.run.msc.MscOutput;
import clp.run.msc.Output;
import clp.run.msc.OutputTarget;

public class DefaultConsoleHandler extends AConsoleHandler {

  private JFrame consoleFrame;

  private JTabbedPane tabbedPane;

  /**
   * constructor
   */
  public DefaultConsoleHandler() {
    super();
  }

  /**
   * initialize console according to given info
   * 
   * @param mscOutput
   */
  public void initialize(MscOutput mscOutput) {
    if (!isInitialized() && mscOutput != null) {
      consoleFrame = new JFrame("Console");
      consoleFrame.setSize(1200, 800);
      consoleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      tabbedPane = new JTabbedPane();
      consoleFrame.add(tabbedPane);

      ArrayList<Output> outList = new ArrayList<>();
      outList.add(mscOutput.getOutput());
      outList.addAll(mscOutput.getOutputs());
      for (Output out : outList) {
        OutputTarget target = out.getOutputTarget();
        String key = "UNKNOWN";
        if (target.isStringCONSOLE()) {
          key = target.getStringCONSOLE();
        }
        else if (target.getName() != null) {
          key = target.getName();
        }
        JTextPane standard = addTab(tabbedPane, key);
        setColors(standard, out, key);
      }

      GraphicsConfiguration conf = consoleFrame.getGraphicsConfiguration();
      Rectangle screenRect = conf.getBounds();
      Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(conf);
      Dimension size = consoleFrame.getSize();
      int centerWidth = screenRect.width < size.width ?
          screenRect.x : screenRect.x + screenRect.width / 2 - size.width / 2;
      int centerHeight = screenRect.height < size.height ?
          screenRect.y : screenRect.y + screenRect.height / 2 - size.height / 2;
      centerHeight = centerHeight < screenInsets.top ?
          screenInsets.top : centerHeight;
      consoleFrame.setLocation(centerWidth, centerHeight);

      consoleFrame.setVisible(true);
      setInitialized(true);
    }
  }

  @Override
  public void refresh() {
    consoleFrame.getRootPane().updateUI();
  }

  @Override
  public void initialize(MscOutput mscOutput, JTabbedPane tp) {
  }
}
