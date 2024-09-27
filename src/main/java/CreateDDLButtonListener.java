import java.awt.event.*;
import javax.swing.*;

class CreateDDLButtonListener implements ActionListener {
    private EdgeConvertGUI parent;

    public CreateDDLButtonListener(EdgeConvertGUI parent) {
        this.parent = parent;
    }


    public void actionPerformed(ActionEvent ae) {
        while (parent.getOutputDir() == null) {
            JOptionPane.showMessageDialog(null,
                    "You have not selected a path that contains valid output definition files yet.\nPlease select a path now.");
            parent.setOutputDir();
        }
        parent.getOutputClasses(); // in case outputDir was set before a file was loaded and EdgeTable/EdgeField
                            // objects created
        parent.sqlString = parent.getSQLStatements();
        if (parent.sqlString.equals(EdgeConvertGUI.CANCELLED)) {
            return;
        }
        parent.writeSQL(parent.sqlString);
    }
}