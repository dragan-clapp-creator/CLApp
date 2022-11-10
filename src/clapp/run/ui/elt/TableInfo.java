package clapp.run.ui.elt;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import clapp.run.ui.Window;
import clapp.run.ui.elt.api.UIRefInfo;
import clapp.run.util.ResourceUtility;
import clp.run.res.Resources;
import clp.run.res.ui.SelType;

public class TableInfo extends UIRefInfo {

  private static final long serialVersionUID = 1882105362998470581L;

  private JTable table;
  private JTableHeader header;
  private TableModel model;
  private Vector<Vector<Object>> modeltable;
  private String[] headernames;
  private SelType selType;
  private int nbrows;
  private int nbcols;
  private int selectedLine = -1;


  public TableInfo() {
    super();
  }

  @Override
  public Component getUIElement() {
    if (table == null) {
      if (getReference() != null) {
        Object obj = ResourceUtility.getInstance().getValue(getReference());
        if (obj instanceof ArrayList) {
          ArrayList<?> rows = (ArrayList<?>) obj;
          if (rows != null && !rows.isEmpty()) {
            nbrows = rows.size();
            Object row = rows.get(0);
            if (row instanceof Hashtable) {
              Hashtable<?, ?> hash = (Hashtable<?, ?>)row;
              nbcols = hash.keySet().size();
              headernames = new String[nbcols];
              int i = 0;
              for (Enumeration<?> en=hash.keys(); en.hasMoreElements(); i++) {
                String n = (String) en.nextElement();
                headernames[i] = new String(n);
              }
              modeltable = new Vector<Vector<Object>>();
              for (int r=0; r<nbrows; r++) {
                Vector<Object> modelrow = new Vector<Object>();
                modeltable.add(modelrow);
                hash = (Hashtable<?, ?>)rows.get(r);
                for (int c=0; c<nbcols; c++) {
                  modelrow.add(hash.get(headernames[c]));
                }
              }
            }
          }
        }
        model = new TableModel();
        table = new JTable(model);
        header = table.getTableHeader();
        header.setBackground(Color.gray);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        if (selType != null) {
          table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          switch(selType) {
            case CELL:
              table.setCellSelectionEnabled(true);
              break;
            case COLUMN:
              table.setColumnSelectionAllowed(true);
              break;
            case LINE:
              table.setRowSelectionAllowed(true);
              break;
          }
          ListSelectionModel selectionModel = table.getSelectionModel();

          selectionModel.addListSelectionListener(new ListSelectionListener() {
              public void valueChanged(ListSelectionEvent e) {
                  handleSelectionEvent(e);
              }
          });
        }
      }
    }
    return header;
  }

  private void handleSelectionEvent(ListSelectionEvent e) {
    if (e.getValueIsAdjusting()) {
      return;
    }
    String strSource= e.getSource().toString();
    int start = strSource.indexOf("{")+1,
        stop  = strSource.length()-1;
    selectedLine = Integer.parseInt(strSource.substring(start, stop));
  }


  @Override
  public boolean checkAndUpdate(Window win) {
    Object obj = null;
    if (selType == null) {
//      ArrayList<Class> vtypes = getMethodInfo().getVtypes();
//      if (vtypes == null || vtypes.size() == 0) {
//        Object[] objects = new Object[1];
//        objects[0] = modeltable;
//        obj = invokeMethod(objects);
//      }
//      else {
//        Object[] objects = new Object[vtypes.size()];
//        for (int i=0; i<vtypes.size(); i++) {
//          objects[i] = getMethodInfo().getValue(i);
//        }
//        obj = invokeMethod(objects);
//      }
    }
    else {
      switch (selType) {
        case CELL:
          break;
        case COLUMN:
          break;
        case LINE:
//          getMethodInfo().setValue(0, selectedLine);
//          Object[] objects = new Object[2];
//          objects[0] = selectedLine;
//          objects[1] = modeltable;
//          obj = invokeMethod(objects);
          break;
      }
    }
    return true;
  }

  @Override
  public void rollBack() {
    // TODO
  }

  public void setSelType(SelType st) {
    selType = st;
  }

  public Component getTableComponent() {
    return table;
  }

  public int getNbRows() {
    return nbrows;
  }

  public int getNbColumns() {
    return nbcols;
  }

  @Override
  public void setupVariable(Resources res) {
    // TODO Auto-generated method stub
    
  }

  //***************************************************************

  class TableModel extends AbstractTableModel {

    private static final long serialVersionUID = 4911839238020168855L;

    @Override
    public int getRowCount() {
      if (nbrows < 2) {
        return nbrows;
      }
      return nbrows - 1;
    }

    @Override
    public int getColumnCount() {
      return nbcols;
    }

    @Override
    public String getColumnName(int col) {
      return headernames[col];
    }

    @Override
    public Class getColumnClass(int c) {
      Vector<Object> line = modeltable.get(0);
      return line.get(c).getClass();
    }

    @Override
    public Object getValueAt(int row, int col) {
      return modeltable.get(row).get(col);
    }
  }
}
