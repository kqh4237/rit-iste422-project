import java.awt.event.*;
import java.io.File;

import javax.swing.*;

class EdgeMenuListener implements ActionListener {
  private EdgeConvertGUI parent;

  public EdgeMenuListener(EdgeConvertGUI parent) {
    this.parent = parent;
  }

  public void actionPerformed(ActionEvent ae) {
    int returnVal;
    if ((ae.getSource() == parent.getJmiDTOpenEdge()) || (ae.getSource() == parent.getJmiDTOpenEdge())) {
      if (!parent.isDataSaved()) {
        int answer = JOptionPane.showConfirmDialog(null, "You currently have unsaved data. Continue?",
            "Are you sure?", JOptionPane.YES_NO_OPTION);
        if (answer != JOptionPane.YES_OPTION) {
          return;
        }
      }
      parent.getJfcEdge().addChoosableFileFilter(parent.getEffEdge());
      returnVal = parent.getJfcEdge().showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        parent.setParseFile(parent.getJfcEdge().getSelectedFile());
        parent.setEcfp(new EdgeConvertFileParser(parent.getParseFile()));
        parent.setTables(parent.getEcfp().getEdgeTables());
        for (int i = 0; i < parent.getTables().length; i++) {
          parent.getTables()[i].makeArrays();
        }
        parent.setFields(parent.getEcfp().getEdgeFields());
        parent.setEcfp(null);
        parent.populateLists();
        parent.setSaveFile(null);
        parent.getJmiDTSave().setEnabled(false);
        parent.getJmiDRSave().setEnabled(false);
        parent.getJmiDTSaveAs().setEnabled(true);
        parent.getJmiDRSaveAs().setEnabled(true);
        parent.getJbDTDefineRelations().setEnabled(true);

        parent.getJbDTCreateDDL().setEnabled(true);
        parent.getJbDRCreateDDL().setEnabled(true);

        parent.truncatedFilename = parent.getParseFile().getName()
            .substring(parent.getParseFile().getName().lastIndexOf(File.separator) + 1);
        parent.getJfDT().setTitle(EdgeConvertGUI.DEFINE_TABLES + " - " + parent.truncatedFilename);
        parent.getJfDR().setTitle(EdgeConvertGUI.DEFINE_RELATIONS + " - " + parent.truncatedFilename);
      } else {
        return;
      }
      parent.setDataSaved(true);
    }

    if ((ae.getSource() == parent.getJmiDTOpenSave()) || (ae.getSource() == parent.getJmiDROpenSave())) {
      if (!parent.isDataSaved()) {
        int answer = JOptionPane.showConfirmDialog(null, "You currently have unsaved data. Continue?",
            "Are you sure?", JOptionPane.YES_NO_OPTION);
        if (answer != JOptionPane.YES_OPTION) {
          return;
        }
      }
      parent.getJfcEdge().addChoosableFileFilter(parent.getEffSave());
      returnVal = parent.getJfcEdge().showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        parent.setSaveFile(parent.getJfcEdge().getSelectedFile());
        parent.setEcfp(new EdgeConvertFileParser(parent.getSaveFile()));
        parent.setTables(parent.getEcfp().getEdgeTables());
        parent.setFields(parent.getEcfp().getEdgeFields());
        parent.setEcfp(null);
        parent.populateLists();
        parent.setSaveFile(null);
        parent.getJmiDTSave().setEnabled(false);
        parent.getJmiDRSave().setEnabled(false);
        parent.getJmiDTSaveAs().setEnabled(true);
        parent.getJmiDRSaveAs().setEnabled(true);
        parent.getJbDTDefineRelations().setEnabled(true);

        parent.getJbDTCreateDDL().setEnabled(true);
        parent.getJbDRCreateDDL().setEnabled(true);

        parent.truncatedFilename = parent.getSaveFile().getName()
            .substring(parent.getSaveFile().getName().lastIndexOf(File.separator) + 1);
        parent.getJfDT().setTitle(EdgeConvertGUI.DEFINE_TABLES + " - " + parent.truncatedFilename);
        parent.getJfDR().setTitle(EdgeConvertGUI.DEFINE_RELATIONS + " - " + parent.truncatedFilename);
      } else {
        return;
      }
      parent.setDataSaved(true);
    }

    if ((ae.getSource() == parent.getJmiDTSaveAs()) || (ae.getSource() == parent.getJmiDRSaveAs()) ||
        (ae.getSource() == parent.getJmiDTSave()) || (ae.getSource() == parent.getJmiDRSave())) {
      if ((ae.getSource() == parent.getJmiDTSaveAs()) || (ae.getSource() == parent.getJmiDRSaveAs())) {
        parent.saveAs();
      } else {
        parent.writeSave();
      }
    }

    if ((ae.getSource() == parent.getJmiDTExit()) || (ae.getSource() == parent.getJmiDRExit())) {
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
        }
        if ((answer == JOptionPane.CANCEL_OPTION) || (answer == JOptionPane.CLOSED_OPTION)) {
          return;
        }
      }
      System.exit(0); // No was selected
    }

    if ((ae.getSource() == parent.getJmiDTOptionsOutputLocation())
        || (ae.getSource() == parent.getJmiDROptionsOutputLocation())) {
      parent.setOutputDir();
    }

    if ((ae.getSource() == parent.getJmiDTOptionsShowProducts())
        || (ae.getSource() == parent.getJmiDROptionsShowProducts())) {
      JOptionPane.showMessageDialog(null,
          "The available products to create DDL statements are:\n" + parent.displayProductNames());
    }

    if ((ae.getSource() == parent.getJmiDTHelpAbout()) || (ae.getSource() == parent.getJmiDRHelpAbout())) {
      JOptionPane.showMessageDialog(null, "EdgeConvert ERD To DDL Conversion Tool\n" +
          "by Stephen A. Capperell\n" +
          " 2007-2008");
    }
  } // EdgeMenuListener.actionPerformed()
} // EdgeMenuListener