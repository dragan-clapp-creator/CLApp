package clapp.run.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.JTableHeader;

import clapp.run.ui.elt.BundleInfo;
import clapp.run.ui.elt.LineInfo;
import clapp.run.ui.elt.TableInfo;
import clapp.run.ui.elt.TextAreaInfo;
import clapp.run.ui.elt.api.UIInfo;
import clp.run.res.Resources;

public class Window implements Serializable {

  private static final long serialVersionUID = -2881258369527061285L;

  private static final int BASE_WIDTH = 150;
  private static final int BASE_HEIGHT = 45;

  private JDialog frame;
  private JEditorPane infoArea;
  private String message;

  public Window(String title) {
    frame = new JDialog((JFrame)null, title, false);
    frame.setAlwaysOnTop(true);
    message = "";
  }

  //
  public void initialize(BundleInfo uiroot, Resources res) {
    frame.setLayout(new BorderLayout());
    frame.setPreferredSize(new Dimension(getWidth(uiroot.getChildren()), getHeight(uiroot.getChildren())));
    frame.setBackground(Color.gray);
    JPanel globalPanel = new JPanel();
    globalPanel.setLayout(new BoxLayout(globalPanel, BoxLayout.PAGE_AXIS));
    globalPanel.setBackground(Color.gray);
    frame.add(globalPanel);
    fillUIElements(uiroot, globalPanel, res);
    frame.pack();
  }

  //
  private void fillUIElements(BundleInfo uiroot, JPanel globalPanel, Resources res) {
    if (uiroot == null || uiroot.getChildren().isEmpty()) {
      return;
    }
    for (UIInfo info : uiroot.getChildren()) {
      if (info instanceof BundleInfo) {
        BundleInfo group = (BundleInfo)info;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(EtchedBorder.RAISED),
                group.getTitle()) );
        addElements(panel, group.getChildren(), res);
        globalPanel.add(panel);
      }
    }
    infoArea = new JEditorPane();
    infoArea.setContentType("text/html");
    infoArea.setEditable(false);
    globalPanel.add(infoArea);
  }

  //
  private void addElements(JPanel panel, ArrayList<UIInfo> children, Resources res) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridy = 0;
    for (UIInfo info : children) {
      if (info instanceof LineInfo) {
        c.gridy++;
        addLineToPane(((LineInfo)info).getChildren(), panel, c, res); 
      }
    }
  }

  //
  private void addLineToPane(ArrayList<UIInfo> children, JPanel panel, GridBagConstraints c, Resources res) {
    c.gridx = 0;
    for (UIInfo info : children) {
      Component comp = info.getUIElement();
      if (comp != null) {
        info.setupVariable(res);
        c.gridx++;
        panel.add(comp, c);
        if (comp instanceof JTableHeader) {
          c.gridy++;
          comp = ((TableInfo)info).getTableComponent();
          panel.add(comp, c);
        }
      }
    }
  }

  //
  private int getHeight(ArrayList<UIInfo> children) {
    if (children == null || children.isEmpty()) {
      return BASE_HEIGHT;
    }
    int nblines = children.size();
    for (UIInfo info : children) {
      if (info instanceof BundleInfo) {
        BundleInfo group = (BundleInfo)info;
        int nb = getNbLines(group.getChildren());
        if (nb > nblines) {
          nblines = nb;
        }
      }
    }
    return BASE_HEIGHT * (nblines+2);
  }

  //
  private int getNbLines(ArrayList<UIInfo> children) {
    int nblines = children.size();
    int nb = 0;
    for (UIInfo info : children) {
      if (info instanceof BundleInfo) {
        BundleInfo group = (BundleInfo)info;
        nb = getNbLines(group.getChildren());
      }
      else if (info instanceof LineInfo) {
        for (UIInfo elt : ((LineInfo)info).getChildren()) {
          if (elt instanceof BundleInfo) {
            nb = getNbLines(((BundleInfo)elt).getChildren());
          }
          else if (elt instanceof TableInfo) {
            ((TableInfo)elt).getUIElement();
            nb += ((TableInfo)elt).getNbRows() + 1;
          }
        }
      }
      if (nb > nblines) {
        nblines = nb;
      }
    }
    return nblines;
  }

  //
  private int getWidth(ArrayList<UIInfo> children) {
    if (children == null || children.isEmpty()) {
      return BASE_WIDTH;
    }
    int nbmaxElements = 1;
    for (UIInfo info : children) {
      if (info instanceof BundleInfo) {
        BundleInfo group = (BundleInfo)info;
        int nb = getNbMaxElements(group.getChildren());
        if (nb > nbmaxElements) {
          nbmaxElements = nb;
        }
      }
    }
    return BASE_WIDTH * nbmaxElements;
  }

  //
  private int getNbMaxElements(ArrayList<UIInfo> children) {
    int nbmaxElements = 1;
    int nb = 0;
    for (UIInfo info : children) {
      if (info instanceof BundleInfo) {
        BundleInfo group = (BundleInfo)info;
        nb = getNbMaxElements(group.getChildren());
      }
      else if (info instanceof LineInfo) {
        nb = 0;
        for (UIInfo elt : ((LineInfo)info).getChildren()) {
          if (elt instanceof TextAreaInfo) {
            nb += 2;
          }
          else if (elt instanceof TableInfo) {
            ((TableInfo)elt).getUIElement();
            nb += ((TableInfo)elt).getNbColumns() * 2 / 3;
          }
          else {
            nb++;
          }
        }
      }
      if (nb > nbmaxElements) {
        nbmaxElements = nb;
      }
    }
    return nbmaxElements;
  }

  //
  public void showFrame(String title) {
    if (frame != null) {
      frame.pack();

      Rectangle screenRect = frame.getGraphicsConfiguration().getBounds();
      Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(
          frame.getGraphicsConfiguration());

      int centerWidth = screenRect.width < frame.getSize().width ? screenRect.x
          : screenRect.x + screenRect.width / 2 - frame.getSize().width / 2;
      int centerHeight = screenRect.height < frame.getSize().height ? screenRect.y
          : screenRect.y + screenRect.height / 2 - frame.getSize().height / 2;

      centerHeight = centerHeight < screenInsets.top ? screenInsets.top
          : centerHeight;

      frame.setLocation(centerWidth, centerHeight);
    }
    frame.setVisible(true);
  }

  public void releaseFrame() {
    frame.setVisible(false);
  }

  public void disposeFrame() {
    frame.dispose();
  }

  public void setMessage(String msg) {
    message += msg;
    infoArea.setText(message);
    frame.validate();
  }
}
