import java.awt.event.*;

import javax.swing.*;

class EdgeWindowListener implements WindowListener {
  private EdgeConvertGUI parent;

  public EdgeWindowListener(EdgeConvertGUI parent) {
    this.parent = parent;
  }

  public void windowActivated(WindowEvent we) {
  }

  public void windowClosed(WindowEvent we) {
  }

  public void windowDeactivated(WindowEvent we) {
  }

  public void windowDeiconified(WindowEvent we) {
  }

  public void windowIconified(WindowEvent we) {
  }

  public void windowOpened(WindowEvent we) {
  }

  public void windowClosing(WindowEvent we) {
    if (!parent.isDataSaved()) {
      int answer = JOptionPane.showOptionDialog(null,
          "You currently have unsaved data. Would you like to save?",
          "Are you sure?",
          JOptionPane.YES_NO_CANCEL_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null, null, null);
      if (answer == JOptionPane.YES_OPTION) {
        if (parent.getSaveFile() == null) {
          parent.saveAs();
        }
        parent.writeSave();
      }
      if ((answer == JOptionPane.CANCEL_OPTION) || (answer == JOptionPane.CLOSED_OPTION)) {
        if (we.getSource() == EdgeConvertGUI.jfDT) {
          EdgeConvertGUI.jfDT.setVisible(true);
        }
        if (we.getSource() == EdgeConvertGUI.jfDR) {
          EdgeConvertGUI.jfDR.setVisible(true);
        }
        return;
      }
    }
    System.exit(0); // No was selected
  }
}