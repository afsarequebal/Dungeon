package dungeonmodel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * This frame represents the region where the configuration is asked.
 * It shows asks for no of rows, columns, interconnectivity,
 * no of monsters, is wrapping.
 */
class OptionFrame extends JFrame implements IOptionFrame {

  private static final long serialVersionUID = -1215083976143155041L;
  private JButton done;
  private JCheckBox cb;
  private int isWrapping;
  private int noOfRows;
  private int noOfColumns;
  private int interConnectivity;
  private int percentageOfTreasure;
  private int numberOfOtyughs;
  private JTextField rowTextField;
  private JTextField colTextField;
  private JTextField icConnTextField;
  private JTextField treasurePercentTextField;
  private JTextField otyughTextField;

  /**
   * Constructs an object of option frame to accept
   * user input for dungeon initial configurations.
   */
  public OptionFrame() {
    super();
    isWrapping = 0;
    JLabel rowLabel;

    JLabel colLabel;

    JLabel iConnLabel;

    JLabel wrapLabel;

    JLabel treasurePercentLabel;

    JLabel otyughLabel;

    noOfRows = 0;
    noOfColumns = 0;
    interConnectivity = 0;
    percentageOfTreasure = 0;
    numberOfOtyughs = 0;

    setLayout(new GridBagLayout());

    setSize(400, 400);
    setResizable(false);
    setLocation(300, 300);
    GridBagConstraints c = new GridBagConstraints();

    rowLabel = new JLabel("Rows");
    rowTextField = new JTextField(16);
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(rowLabel, c);
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(rowTextField, c);

    colLabel = new JLabel("Columns");
    colTextField = new JTextField(16);
    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(colLabel, c);
    c.gridx = 1;
    c.gridy = 1;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(colTextField, c);

    iConnLabel = new JLabel("Interconnectivity");
    icConnTextField = new JTextField(16);
    c.gridx = 0;
    c.gridy = 2;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(iConnLabel, c);
    c.gridx = 1;
    c.gridy = 2;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(icConnTextField, c);

    wrapLabel = new JLabel("Wrapping");
    //wrapTextField = new JTextField(16);
    cb = new JCheckBox();
    c.gridx = 0;
    c.gridy = 3;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(wrapLabel, c);
    c.gridx = 1;
    c.gridy = 3;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 5;
    add(cb, c);


    treasurePercentLabel = new JLabel("Treasure Percent");
    treasurePercentTextField = new JTextField(16);
    c.gridx = 0;
    c.gridy = 4;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(treasurePercentLabel, c);
    c.gridx = 1;
    c.gridy = 4;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(treasurePercentTextField, c);

    otyughLabel = new JLabel("Otyughs");
    otyughTextField = new JTextField(16);
    c.gridx = 0;
    c.gridy = 5;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(otyughLabel, c);
    c.gridx = 1;
    c.gridy = 5;
    c.weightx = 1;
    c.weighty = 1;
    c.gridwidth = 1;
    add(otyughTextField, c);

    done = new JButton("Done");
    c.gridx = 0;
    c.gridy = 6;
    c.weightx = 2;
    c.weighty = 1;
    c.gridwidth = 2;
    add(done,c);
    setBackground(Color.black);
  }

  @Override
  public void setButtonListener(Features f) {
    done.addActionListener(l -> {

      String row = rowTextField.getText();
      String col = colTextField.getText();
      String iconnectivity = icConnTextField.getText();
      cb.addItemListener((ItemEvent e) -> {
        if (e.getStateChange() == 0) {
          setUnWrapping();
        } else {
          setWrapping();
        }
      });
      String treasurePercent = treasurePercentTextField.getText();
      String otyugh = otyughTextField.getText();

      try {
        if (null != row) {
          noOfRows = Integer.valueOf(row);
        }
        if (null != col) {
          noOfColumns = Integer.valueOf(col);
        }

        if (null != iconnectivity) {
          interConnectivity = Integer.valueOf(iconnectivity);
        }

        if (null != treasurePercent) {
          percentageOfTreasure = Integer.valueOf(treasurePercent);
        }

        if (null != otyugh) {
          numberOfOtyughs = Integer.valueOf(otyugh);
        }
        boolean success = f.newGame(noOfRows, noOfColumns, interConnectivity,
            isWrapping == 0 ? false : true, percentageOfTreasure, numberOfOtyughs);
        if (success) {
          this.setVisible(false);
        }
      } catch (Exception e) {
        showErrorMessage("Please enter correct values"
            + " of configurations.");
      }
    });
  }

  private void setUnWrapping() {
    isWrapping = 0;
  }

  private void setWrapping() {
    isWrapping = 1;
  }

  @Override
  public void checkBoxListener() {
    cb.addItemListener((ItemEvent e) -> {
      if (e.getStateChange() == 1) {
        setWrapping();
      } else {
        setUnWrapping();
      }
    });
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this,error,"Error",JOptionPane.ERROR_MESSAGE);
  }
}
