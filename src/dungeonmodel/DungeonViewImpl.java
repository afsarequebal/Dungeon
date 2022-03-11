package dungeonmodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

/**
 * Represents a view interacting with controller and the read only model.
 * It shows gui for a the game to screen. It also responds to user input for 
 * example click and key board input for move, pick up and throw. Once
 * action is completed, view is refreshed to reflect correct values. 
 *
 */
public class DungeonViewImpl extends JFrame implements IDungeonView {

  private static final long serialVersionUID = 6432977136432344154L;
  private JButton cancelButton;
  private JLabel display;
  private IReadOnlyDungeon rm;
  private JMenuItem newGameItem;
  private JMenuItem restartGameItem;
  private JMenuItem reuseGameItem;
  private JMenuItem quit;
  private OptionFrame optionFrame;
  private PlayerFrame playerFrame;
  private JButton playerDesc; 
  private DungeonPanel dungeonPanel;
  private boolean pickUpStarted;
  private boolean shootStarted;
  private boolean shootAndDirGiven;
  private String shootDir;

  /**
   * Constructs a view object to represent maze in the screen.
   * The maze represent player details, instruction to run the game.
   * 
   * @param caption the caption to shown to the user.
   * @param rm the read-only model used by the view to get
   *     relevant information from the user.
   */
  public DungeonViewImpl(String caption, IReadOnlyDungeon rm) {
    super(caption);
    if (null == rm) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    JPanel infoPanel;
    JScrollPane scrollPane;
    JButton quitButton;
    JFrame j = new JFrame();
    this.rm = rm;
    setSize(800, 800);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    GridLayout gridLayout1 = new GridLayout();
    this.setLayout(gridLayout1);
    gridLayout1.setColumns(2);
    gridLayout1.setRows(1);
    dungeonPanel = new DungeonPanel(rm);
    dungeonPanel.setPreferredSize(new Dimension(500, 500));
    scrollPane = new JScrollPane(dungeonPanel);
    scrollPane.setSize(800, 800);
    this.add(scrollPane, BorderLayout.WEST);

    //info panel
    infoPanel = new JPanel();
    GridBagLayout gridLayout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 0.0;
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy = 1;
    c.anchor = GridBagConstraints.PAGE_START;
    infoPanel.setLayout(gridLayout);
    infoPanel.setBackground(Color.CYAN);
    this.add(infoPanel);

    display = new JLabel(dungeonPanel.locationDesc());

    infoPanel.add(display, c);

    playerDesc = new JButton("My Details");
    playerDesc.setActionCommand("Ok Button");
    c.fill = GridBagConstraints.FIRST_LINE_START;
    c.gridwidth = 1;
    c.gridx = 1;
    c.gridy = 0;
    infoPanel.add(playerDesc, c);


    cancelButton = new JButton("Cancel Current action");
    c.fill = GridBagConstraints.FIRST_LINE_START;
    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
    c.gridx = 1;
    c.gridy = 2;
    c.gridwidth = 1;
    infoPanel.add(cancelButton, c);

    //quit button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weighty = 1.0;  
    c.anchor = GridBagConstraints.PAGE_END;
    c.gridx = 2;      
    c.gridwidth = 1;  
    c.gridy = 2;      
    infoPanel.add(quitButton, c);

    JMenuBar jmenu = new JMenuBar();
    setJMenuBar(jmenu);

    JMenu settingsMenu = new JMenu("New Game");
    jmenu.add(settingsMenu);
    newGameItem = new JMenuItem("New Game");
    settingsMenu.add(newGameItem);

    restartGameItem = new JMenuItem("Restart");
    settingsMenu.add(restartGameItem);

    reuseGameItem = new JMenuItem("Reuse");
    settingsMenu.add(reuseGameItem);

    quit = new JMenuItem("Quit");
    settingsMenu.add(quit);

    pack();
    setVisible(true);
    j.setVisible(false);
    cancelButton.setEnabled(false);
  }

  /**
   * Accept the set of callbacks from the controller, and hook up as needed to
   * various things in this view.
   * 
   * @param f the set of feature callbacks as a Features object
   */
  @Override
  public void setFeatures(Features f) {
    cancelButton.addActionListener(l -> {
      pickUpStarted = false;
      shootStarted = false;
      shootAndDirGiven = false;
      cancelButton.setEnabled(false);
      refresh();
    });
    playerDesc.addActionListener(l -> {
      playerFrame = new PlayerFrame(rm);
      playerFrame.setVisible(true);
      playerFrame.setButtonListener();
    });
    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (rm.isGameOver()) {
          display.setText("Game is over. Please restart");
        }  else {
          int noOfRows = rm.getNoOfRows();
          int noOfColumns = rm.getNoOfColumns();
          int offSetX = 0;
          int offSetY = 0;
          if (noOfRows * 64 < dungeonPanel.getHeight()) {
            offSetY = (dungeonPanel.getHeight() - noOfRows * 64) / 2; 
          }
          if (noOfColumns * 64 < dungeonPanel.getWidth()) {
            offSetX = (dungeonPanel.getWidth() - noOfColumns * 64) / 2; 
          }
          int row = (e.getY() - offSetY) / 64;
          int col = (e.getX() - offSetX) / 64;
            f.move(row, col);
        }
      }
    };
    dungeonPanel.addMouseListener(ml);
    newGameItem.addActionListener(l -> {
      optionFrame = new OptionFrame();
      optionFrame.setVisible(true);
      optionFrame.checkBoxListener();
      optionFrame.setButtonListener(f);
    }); 

    restartGameItem.addActionListener(l -> f.restartGame());
    reuseGameItem.addActionListener(l -> f.reuseGame());
    quit.addActionListener(l -> f.exitProgram());


    addKeyBinding(dungeonPanel, KeyEvent.VK_UP, "Turn Up", (evt) -> {
      if (rm.isGameOver()) {
        display.setText("Game is over. Please restart");
      } else if (shootStarted) {
        shootAndDirGiven = true;
        shootDir = "N";
        shootStarted = false;
        display.setText("<html>Please provide the distane (1-5) to shoot<br>"
            + "Or Cancel by clicking on Cancel Current Action button<br></html>");
      } else if (!pickUpStarted && !shootAndDirGiven) {
        f.move("N");
      }
    });
    addKeyBinding(dungeonPanel, KeyEvent.VK_DOWN, "Turn Down", (evt) -> {
      if (rm.isGameOver()) {
        display.setText("Game is over. Please restart");
      } else if (shootStarted) {
        shootAndDirGiven = true;
        shootDir = "S";
        shootStarted = false;
        display.setText("<html>Please provide the distane (1-5) to shoot<br>"
            + "Or Cancel by clicking on Cancel Current Action button<br></html>");
      }  else if (!pickUpStarted && !shootAndDirGiven) {
        f.move("S");
      }
    });
    addKeyBinding(dungeonPanel, KeyEvent.VK_LEFT, "Turn Left", (evt) -> {
      if (rm.isGameOver()) {
        display.setText("Game is over. Please restart");
      } else if (shootStarted) {
        shootAndDirGiven = true;
        shootDir = "W";
        shootStarted = false;
        display.setText("<html>Please provide the distane (1-5) to shoot<br>"
            + "Or Cancel by clicking on Cancel Current Action button<br></html>");
      }  else if (!pickUpStarted && !shootAndDirGiven) {
        f.move("W");
      }
    });
    addKeyBinding(dungeonPanel, KeyEvent.VK_RIGHT, "Turn Right", (evt) -> {
      if (rm.isGameOver()) {
        display.setText("Game is over. Please restart");
      } else if (shootStarted) {
        shootAndDirGiven = true;
        shootDir = "E";
        shootStarted = false;
        display.setText("<html>Please provide the distane (1-5) to shoot<br>"
            + "Or Cancel by clicking on Cancel Current Action button<br></html>");
      }  else if (!pickUpStarted && !shootAndDirGiven) {
        f.move("E");
      }
    });


    addKeyBinding(dungeonPanel, KeyEvent.VK_P, "Pick up item", (evt) -> {
      if (!rm.isGameOver() && !shootStarted && !shootAndDirGiven) {
        String locationDetails = rm.getlocationDetails();
        String [] locArr = locationDetails.split(":");
        String [] treasures = locArr[1].split(",");
        String [] weapons = locArr[2].split(",");
        int countOfTreasure = 0;
        if (null != treasures) {
          for (int i = 0 ; i < treasures.length; i++) {
            if (!treasures[i].trim().equals("")) {
              countOfTreasure++;
            }
          }
        }
        int countOfArrow = 0;
        if (null != weapons) {
          for (int i = 0 ; i < weapons.length; i++) {
            if (!weapons[i].trim().equals("")) {
              countOfArrow++;
            }
          }
        }
        if (countOfTreasure != 0 || countOfArrow != 0) {
          cancelButton.setEnabled(true);
          pickUpStarted = true;
          display.setText("<html>Please pick type of treasure or arrow. "
              + "<br>For eg. A for arrow, D for diamond, <br>E for sapphire, R for ruby<br>"
              + "Or Cancel by clicking on Cancel Current Action button<br></hmtl>");
        } 
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_A, "Pick up arrow", (evt) -> {
      if (!rm.isGameOver() && pickUpStarted) {
        f.pickUp("arrow");
        cancelButton.setEnabled(false);
        pickUpStarted = false;
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_D, "Pick up diamond", (evt) -> {
      if (!rm.isGameOver() && pickUpStarted) {
        f.pickUp("diamond");
        cancelButton.setEnabled(false);
        pickUpStarted = false;
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_E, "Pick up sapphire", (evt) -> {
      if (!rm.isGameOver() && pickUpStarted) {
        f.pickUp("sapphire");
        cancelButton.setEnabled(false);
        pickUpStarted = false;
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_R, "Pick up ruby", (evt) -> {
      if (!rm.isGameOver() && pickUpStarted) {
        f.pickUp("ruby");
        cancelButton.setEnabled(false);
        pickUpStarted = false;
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_S, "Shoot the monster", (evt) -> {
      if (!rm.isGameOver() && !pickUpStarted && !shootAndDirGiven) {
        cancelButton.setEnabled(true);
        shootStarted = true;
        display.setText("<html>Please provide direction to shoot<br>"
            + "Or Cancel by clicking on Cancel Current Action button<br></html>");
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_1, "Shoot at distance 1", (evt) -> {
      if (!rm.isGameOver() && shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        f.attack(shootDir, 1);
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_2, "Shoot at distance of 2", (evt) -> {
      if (!rm.isGameOver() && shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        f.attack(shootDir, 2);
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_3, "Shoot at distance of 3", (evt) -> {
      if (!rm.isGameOver() && shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        f.attack(shootDir, 3);
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_4, "Shoot a distance of 4", (evt) -> {
      if (!rm.isGameOver() && shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        f.attack(shootDir, 4);
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_5, "Shoot at distance of 5", (evt) -> {
      if (!rm.isGameOver() && shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        f.attack(shootDir, 5);
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_F, "Fight hand to hand", (evt) -> {
      if (!rm.isGameOver() && rm.isEncounteredRMonster() && !shootAndDirGiven && !shootStarted
          && !pickUpStarted) {
        f.fightMonster();
      }
    });
  }

  /*
   * In order to make this frame respond to keyboard events, it must be within
   * strong focus. Since there could be multiple components on the screen that
   * listen to keyboard events, we must set one as the "currently focussed" one so
   * that all keyboard events are passed to that component. This component is said
   * to have "strong focus".
   * 
   * We do this by first making the component focusable and then requesting focus
   * to it. Requesting focus makes the component have focus AND removes focus from
   * whoever had it before.
   */
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void makeInVisible() {
    setVisible(false);
  }
  
  @Override
  public void makeOptionFrameVisible() {
    optionFrame.setVisible(true);
  }

  @Override
  public void refresh() {
    dungeonPanel.setDone(false);
    dungeonPanel.setVisible(true);
    display.setText(dungeonPanel.locationDesc());
    String s = display.getText().substring(0, display.getText().length() - 7);
    display.setText(s +  "                  " + "</html>");
    dungeonPanel.paintM();
  }

  @Override
  public void printResult(String result) {
    if (null == result) {
      result = "";
    }
    dungeonPanel.setDone(false);
    dungeonPanel.setVisible(true);
    display.setText(dungeonPanel.locationDesc());
    String s = display.getText().substring(0, display.getText().length() - 7);
    s = s  + result + "<br></hmtl>";
    display.setText(s);

  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this,error,"Error",JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setModel(IReadOnlyDungeon rm) {
    if (null != rm) {
      this.rm = rm;
      dungeonPanel.setModel(rm);
    } else {
      throw new IllegalArgumentException("Argument can't be null");
    }
  }

  private void addKeyBinding(JComponent comp, int keyCode, 
      String id, ActionListener actionListener) {
    InputMap im = dungeonPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    im.put(KeyStroke.getKeyStroke(keyCode, 0, false), id);

    ActionMap ap = dungeonPanel.getActionMap();
    ap.put(id, new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionListener.actionPerformed(e);
      }
    });
  }

  @Override
  public void showAttackResult(int s) {
    if (s == 1) {
      printResult("<html>You hear a great howl, seems monster is dead.<br></html>");
    } else if (s == 2) {
      printResult("You hear a slight howl\n");
    }  else if (s == 3) {
      printResult("<html>You are out of arrows. Explore to find more<br></html>");
    } else if (s == 0) {
      printResult("<html>You missed monster.<br><html>");
    } else {
      printResult("<html>You missed monster.<br><html>");
    }
  }

  @Override
  public void showFightResult(int s) {
    String finalS = "";
    if (s == 1) {
      finalS =  "<html>You hit the monster and monster hit you.<br>";
    } else if (s == 2) {
      finalS = "<html>You missed hitting the monster and monster hit you.<br>";
    } else if (s == 3) {
      finalS = "<html>You missed hitting the monster and monster missed hitting you.<br>";
    } else if (s == 4) {
      finalS = "<html>You hit the monster and monster missed hitting you.<br>";
    }

    if (rm.isEncounteredRMonster() && !rm.isGameOver()) {
      printResult(finalS);
    } else {
      refresh();
    }
  }
}

