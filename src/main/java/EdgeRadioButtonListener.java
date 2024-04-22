import java.awt.event.*;
import javax.swing.*;

public class EdgeRadioButtonListener implements ActionListener {
  private EdgeConvertGUI parent;

  public EdgeRadioButtonListener(EdgeConvertGUI parent) {
    this.parent = parent;
  }

  public void actionPerformed(ActionEvent ae) {
    JRadioButton[] jrbDataType = parent.getJrbDataType();
    JTextField jtfDTVarchar = parent.getJtfDTVarchar();
    JButton jbDTVarchar = parent.getJbDTVarchar();
    JTextField jtfDTDefaultValue = parent.getJtfDTDefaultValue();
    EdgeField currentDTField = parent.getCurrentDTField();

    for (int i = 0; i < jrbDataType.length; i++) {
      if (jrbDataType[i].isSelected()) {
        currentDTField.setDataType(i);
        break;
      }
    }
    if (jrbDataType[0].isSelected()) {
      jtfDTVarchar.setText(Integer.toString(EdgeField.VARCHAR_DEFAULT_LENGTH));
      jbDTVarchar.setEnabled(true);
    } else {
      jtfDTVarchar.setText("");
      jbDTVarchar.setEnabled(false);
    }
    jtfDTDefaultValue.setText("");
    currentDTField.setDefaultValue("");
    parent.setDataSaved(false);
  }
}
