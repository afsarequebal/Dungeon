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

public class DungeonViewImpl extends JFrame implements IDungeonView{

  /**
   * 
   */
  private static final long serialVersionUID = 6432977136432344154L;
  private JButton cancelButton,quitButton;
  private JPanel infoPanel;
  private DungeonPanel dungeonPanel;
  private JLabel display;
  private IReadOnlyDungeon rm;
  private JScrollPane scrollPane;
  private JFrame j = new JFrame();
  private JMenuItem newGameItem;
  private JMenuItem restartGameItem;
  private JMenuItem quit;
  private OptionFrame optionFrame;
  private PlayerFrame playerFrame;
  private JButton playerDesc; 

  private boolean pickUpStarted;
  private boolean shootStarted;
  private boolean shootAndDirGiven;
  private String shootDir;
  //private boolean noAc

  /**
   * Constructor.
   * 
   * @param caption    the caption to usu
   * @param controller the controller to use
   */
  public DungeonViewImpl(String caption, IReadOnlyDungeon rm) {
    super(caption);
    this.rm = rm;
    setSize(800, 800);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //JPanel container = new JPanel();

    // this.setLayout(new FlowLayout());
    GridLayout gridLayout1 = new GridLayout();
    this.setLayout(gridLayout1);
    // gridLayout1.preferredLayoutSize(this);
    gridLayout1.setColumns(2);
    gridLayout1.setRows(1);
    dungeonPanel = new DungeonPanel(rm);
    dungeonPanel.setPreferredSize(new Dimension(500, 500));
    // container.add(dungeonPanel);
    //  container.setPreferredSize(new Dimension(400, 400));
    scrollPane = new JScrollPane(dungeonPanel);
    scrollPane.setSize(800, 800);
    this.add(scrollPane, BorderLayout.WEST);

    //info panel
    infoPanel = new JPanel();
    GridBagLayout gridLayout = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    // c.fill = GridBagConstraints.FIRST_LINE_START;
    // c.ipady = 40;      //make this component tall
    c.weightx = 0.0;
    c.gridwidth = 3;
    c.gridx = 0;
    c.gridy = 1;
    c.anchor = GridBagConstraints.PAGE_START;
    // gridLayout.
    // gridLayout.setColumns(1);
    //gridLayout.setRows(3);
    infoPanel.setLayout(gridLayout);
    infoPanel.setBackground(Color.CYAN);
    this.add(infoPanel);

    display = new JLabel(dungeonPanel.locationDesc());

    infoPanel.add(display, c);


    //buttons

    playerDesc = new JButton("My Details");
    playerDesc.setActionCommand("Ok Button");
    c.fill = GridBagConstraints.FIRST_LINE_START;
    //c.ipady = 0;      //make this component tall
    //c.weightx = 0.0;
    c.gridwidth = 1;
    //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
    c.gridx = 1;
    c.gridy = 0;
    infoPanel.add(playerDesc, c);


    cancelButton = new JButton("Cancel Current action");
    c.fill = GridBagConstraints.FIRST_LINE_START;
    //c.ipady = 0;      //make this component tall
    //c.weightx = 0.0;
    //c.gridwidth = 1;
    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
    c.gridx = 1;
    c.gridy = 2;
    c.gridwidth = 1;
    infoPanel.add(cancelButton, c);

    //quit button
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    c.fill = GridBagConstraints.HORIZONTAL;
    //c.ipady = 0;       //reset to default
    c.weighty = 1.0;   //request any extra vertical space
    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
    c.gridx = 2;       //aligned with button 2
    c.gridwidth = 1;   //2 columns wide
    c.gridy = 2;       //third row
    infoPanel.add(quitButton, c);

    JMenuBar jmenu = new JMenuBar();
    setJMenuBar(jmenu);

    JMenu settingsMenu = new JMenu("New Game");
    jmenu.add(settingsMenu);
    newGameItem = new JMenuItem("New Game");
    settingsMenu.add(newGameItem);

    restartGameItem = new JMenuItem("Restart");
    settingsMenu.add(restartGameItem);

    quit = new JMenuItem("Quit");
    settingsMenu.add(quit);

    //Create and populate the panel.
    //this.add(jmenu);
    //p.setActionCommand("hj");
    //p.addActionListener(this);
    /*  display = new JLabel("To be displayed");
    this.add(display);

    // the text field
    input = new JTextField(10);
    this.add(input);

    // echo button
    echoButton = new JButton("Echo");
    echoButton.setActionCommand("Echo Button");
    this.add(echoButton);

    // toggle button
    toggleButton = new JButton("Toggle color");
    toggleButton.setActionCommand("Toggle color button");
    this.add(toggleButton);

    // exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);*/


    //this.setResizable(false);
    pack();
    setVisible(true);
    j.setVisible(false);
    cancelButton.setEnabled(false);
    // this.pack();
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
      // OptionFrame optionFrame = new OptionFrame();
      // optionFrame.setVisible(true);
      playerFrame = new PlayerFrame(rm);
      playerFrame.setVisible(true);
      playerFrame.setButtonListener();
      // optionFrame.setButtonListener();
      //optionFrame.pack();
    });
    MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // int row = (e.getY() - BoardPanel.OFFSET) / BoardPanel.CELL_DIM;
        // int col = (e.getX() - BoardPanel.OFFSET) / BoardPanel.CELL_DIM;
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
          if (rm.getPlayersLocation() == row * noOfColumns + col) {
            printError("Please select different location");
          } else {
            f.move(row, col);
          }
        }
      }
    };
    dungeonPanel.addMouseListener(ml);
    // setFocusable(true);

    //newGameItem.addActionListener(l -> f.processInput(input.getText())); //call model retaing values.
    newGameItem.addActionListener(l -> {
      // OptionFrame optionFrame = new OptionFrame();
      // optionFrame.setVisible(true);
      optionFrame = new OptionFrame();
      optionFrame.setVisible(true);
      optionFrame.checkBoxListener();
      optionFrame.setButtonListener(f);
      // optionFrame.setButtonListener();
      //optionFrame.pack();
    }); //call model retaing values.
    // exit program is tied to the exit button
    restartGameItem.addActionListener(l -> f.restartGame());
    // toggle color is tied to a toggle button. Originally this functionality
    // was
    // exposed only by a key press. Having a set of callbacks to call gives
    // the view full control over which UI elements to map to which features.
    quit.addActionListener(l -> f.exitProgram());


    addKeyBinding(dungeonPanel, KeyEvent.VK_UP, "Turn Up", (evt) -> {
      if (rm.isGameOver()) {
        display.setText("Game is over. Please restart");
      } else if(shootStarted) {
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
      } else if(shootStarted) {
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
      } else if(shootStarted) {
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
      } else if(shootStarted) {
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
      if(!shootStarted && !shootAndDirGiven) {
        String locationDetails = rm.getlocationDetails();
        String [] locArr = locationDetails.split(":");
        String [] directions = locArr[0].split(",");
        String [] treasures = locArr[1].split(",");
        String [] weapons = locArr[2].split(",");
        String [] monster = locArr[3].split(",");
        int countOfTreasure = 0;
        if (null != treasures) {
          String treasure = "";
          for (int i = 0 ; i < treasures.length; i++) {
            if (!treasures[i].trim().equals("")) {
              treasure += treasures[i].trim() + ", ";
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
              + "<br>For eg. A for arrow, D for diamond, <br>E for emerald, R for ruby<br>"
              + "Or Cancel by clicking on Cancel Current Action button<br></hmtl>");
        } else {
          printError("There is no item to pickup");
        }
        // if (rm.isGameOver()) {

        //}  else {
        // f.move("E");
        //}
      }});

    addKeyBinding(dungeonPanel, KeyEvent.VK_A, "Pick up arrow", (evt) -> {
      if (pickUpStarted && f.pickUp("arrow")) {
        cancelButton.setEnabled(false);
        pickUpStarted = false;
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_D, "Pick up diamond", (evt) -> {
      //enableCancelButton = false;
      // pickUpStarted = true;
      if (pickUpStarted && f.pickUp("diamond")) {
        cancelButton.setEnabled(false);
        pickUpStarted = false;
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_E, "Pick up emerald", (evt) -> {
      //pickUpStarted = true;
      if (pickUpStarted && f.pickUp("sapphire")) {
        cancelButton.setEnabled(false);
        pickUpStarted = false;
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_R, "Pick up ruby", (evt) -> {
      //pickUpStarted = true;
      if (pickUpStarted && f.pickUp("ruby")) {
        cancelButton.setEnabled(false);
        pickUpStarted = false;
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_S, "Shoot the monster", (evt) -> {
      if (!pickUpStarted && !shootAndDirGiven) {
        cancelButton.setEnabled(true);
        shootStarted = true;
        display.setText("<html>Please provide direction to shoot<br>"
            + "Or Cancel by clicking on Cancel Current Action button<br></html>");
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_1, "Shoot at distance 1", (evt) -> {
      if(shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        int s = f.attack(shootDir, 1);
        if (s == 1) {
          printResult("<html>You hear a great howl, seems monster is dead.<br></html>");
        } else if (s == 2) {
          printResult("You hear a slight howl\n");
        } 
        if (s == 3) {
          printResult("<html>You are out of arrows. Explore to find more<br></html>");
        }
        if (s == 0) {
          printResult("<html>You missed monster.<br><html>");
        }
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_2, "Shoot at distance of 2", (evt) -> {
      if(shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        int s = f.attack(shootDir, 2);
        if (s == 1) {
          printResult("<html>You hear a great howl, seems monster is dead<br></html>");
        } else if (s == 2) {
          printResult("<html>You hear a slight howl<br></html>");
        } 
        if (s == 3) {
          printResult("<html>You are out of arrows. Explore to find more<br></html>");
        }
        if (s == 0) {
          printResult("<html>You missed monster.<br></html>");
        }
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_3, "Shoot at distance of 3", (evt) -> {
      if(shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        int s = f.attack(shootDir, 3);
        if (s == 1) {
          printResult("<html>You hear a great howl, seems monster is dead<br></html>");
        } else if (s == 2) {
          printResult("<html>You hear a slight howl<br></html>");
        } 
        if (s == 3) {
          printResult("<html>You are out of arrows. Explore to find more<br></html>");
        }
        if (s == 0) {
          printResult("<html>You missed monster.<br></html>");
        }
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_4, "Shoot a distance of 4", (evt) -> {
      if(shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        int s = f.attack(shootDir, 4);
        if (s == 1) {
          printResult("<html>You hear a great howl, seems monster is dead<br></html>");
        } else if (s == 2) {
          printResult("<html>You hear a slight howl<br></html>");
        } 
        if (s == 3) {
          printResult("<html>You are out of arrows. Explore to find more<br></html>");
        }
        if (s == 0) {
          printResult("<html>You missed monster.<br></html>");
        }
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_5, "Shoot at distance of 5", (evt) -> {
      if(shootAndDirGiven) {
        cancelButton.setEnabled(false);
        shootAndDirGiven = false;
        int s = f.attack(shootDir, 5);
        if (s == 1) {
          printResult("<html>You hear a great howl, seems monster is dead<br></html>");
        } else if (s == 2) {
          printResult("<html>You hear a slight howl<br></html>");
        } 
        if (s == 3) {
          printResult("<html>You are out of arrows. Explore to find more<br></html>");
        }
        if (s == 0) {
          printResult("<html>You missed monster.<br></html>");
        }
      }
    });

    addKeyBinding(dungeonPanel, KeyEvent.VK_F, "Fight hand to hand", (evt) -> {
      int s = f.fightMonster();
      if (s == 1) {
        printResult("<html>You hit the monster and monster hit you.<br></html>");
      } else if (s == 2) {
        printResult("<html>You missed hitting the monster and monster hit you.<br></html>");
      } else if (s == 3) {
        printResult("<html>You missed hitting the monster and monster missed hitting you.<br></html>");
      } else if (s == 4) {
        printResult("<html>You hit the monster and monster missed hitting you.<br><html>");
      }
    });
    // shootAndDirGiven = true; then act on 1,2,3,4,5
    /*  addKeyBinding(dungeonPanel, KeyEvent.VK_S, "Shoot", (evt) -> {
      display.setText("<html>Please enter direction to shoot<br></hmtl>");
     });

    addKeyBinding(dungeonPanel, KeyEvent.VK_R, "Pick up ruby", (evt) -> {
      f.pickUp("ruby");
     });

    addKeyBinding(dungeonPanel, KeyEvent.VK_R, "Pick up ruby", (evt) -> {
      f.pickUp("ruby");
     });

    addKeyBinding(dungeonPanel, KeyEvent.VK_R, "Pick up ruby", (evt) -> {
      f.pickUp("ruby");
     });*/

    // process input is tied to the echo button
    /* echoButton.addActionListener(l -> f.processInput(input.getText()));
    // exit program is tied to the exit button
    exitButton.addActionListener(l -> f.exitProgram());
    // toggle color is tied to a toggle button. Originally this functionality
    // was
    // exposed only by a key press. Having a set of callbacks to call gives
    // the view full control over which UI elements to map to which features.
    toggleButton.addActionListener(l -> f.toggleColor());

    // toggle color, make upper case and restore case are tied to the keyboard.
    this.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 't') {
          f.toggleColor();
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 'c') {
          f.makeUppercase();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 'c') {
          f.restoreCase();
        }
      }
    });*/
  }

  /*  @Override
  public void addClickListener(GUIController listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void refresh() {
    // TODO Auto-generated method stub

  }

   */

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
  public void addClickListener(Features f) {

    /* MouseListener ml = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        // int row = (e.getY() - BoardPanel.OFFSET) / BoardPanel.CELL_DIM;
        // int col = (e.getX() - BoardPanel.OFFSET) / BoardPanel.CELL_DIM;
        if (rm.isGameOver()) {
          display.setText("Game is over. Please restart");
        }  else {
          int noOfRows = rm.getNoOfRows();
          int noOfColumns = rm.getNoOfColumns();
          int offSetX = 0;
          int offSetY = 0;
          if (noOfRows * 64 < 500) {
            offSetY = (500 - noOfRows * 64) / 2; 
          }
          if (noOfColumns * 64 < 500) {
            offSetX = (500 - noOfColumns * 64) / 2; 
          }
          int row = (e.getY() - offSetY) / 64;
          int col = (e.getX() - offSetX) / 64;
          if (rm.getPlayersLocation() == row * noOfColumns + col) {
            printError("Please select different location");
          } else {
            f.move(row, col);
          }
        }
      }
    };
    dungeonPanel.addMouseListener(ml);
    setFocusable(true);*/

  }

  @Override
  public void makeVisible() {
    setVisible(true);
    /*  dungeonPanel.setDone(false);
    dungeonPanel.xyz();
    dungeonPanel.setDone(false);
    dungeonPanel.xyz();
    dungeonPanel.setDone(false);
    dungeonPanel.xyz();*/
  }

  @Override
  public void makeInVisible() {
    setVisible(false);
  }

  //check wrapiingf for row
  @Override
  public void refresh() {
    // cancelButton.setEnabled(false);
    dungeonPanel.setDone(false);
    dungeonPanel.setVisible(true);
    //display.setText("h");
    //display.repaint();
    //  System.out.println(dungeonPanel.locationDesc());
    //display.setText(dungeonPanel.locationDesc());
    display.setText(dungeonPanel.locationDesc());
    //String s = "";
    // if (rm.isfallenIntoPit() || rm.isEncounteredRMonster() || rm.isGameOver()
    //     || rm.isEncounteredTheif()) {
    String s = display.getText().substring(0, display.getText().length() - 7);
    // }
    if (rm.isfallenIntoPit()) {
      s = s + "You have fallen into a pit.<br>";
    }
    if (rm.isEncounteredRMonster()) {
      s = s + "You have encountered a roaming monster. Click F to Fight<br>";
    }
    if (rm.isEncounteredTheif()) {
      s = s + "You have encountered a theif. He has stolen all your treasures.<br>";
    }
    if (rm.isGameOver()) {

      if (rm.getPlayersLocation() == rm.getEndPoint()) {
        s = s + "End location reached.<br>"; 
      }
      if (rm.getWinner()) {
        s = s + "Game is over! Player wins.<br>";
      } else {
        s = s + "Game is over! Player loses.<br>";
      }

    }
    display.setText(s +  "                  "+"</html>");
    //display.repaint();
    dungeonPanel.xyz();
    // this.repaint();
    //this.repaint();
    //this.setVisible(true);
    //  dungeonPanel = new DungeonPanel(rm);
    //dungeonPanel.setPreferredSize(new Dimension(500, 500));
    // container.add(dungeonPanel);
    //  container.setPreferredSize(new Dimension(400, 400));
    //  scrollPane = new JScrollPane(dungeonPanel);
    //  scrollPane.setSize(800, 800);
    //  this.add(scrollPane, BorderLayout.WEST);

    //scrollPane.setVisible(true);
    //this.setVisible(true);
    //scrollPane.revalidate();
    //this.refresh();
    //setVisible(true);
    //scrollPane.repaint();
    //scrollPane.repaint();

    //this.repaint();
    // setVisible(true);
    //  LayoutManager grid = dungeonPanel.getLayout();
    // grid.layoutContainer(scrollPane)grid.preferredLayoutSize(commandButton);

    //dungeonPanel.paintComp();
    // dungeonPanel.repaint();
    /* dungeonPanel = null;
    dungeonPanel = new DungeonPanel(rm);
    JScrollPane scrollPane = new JScrollPane(dungeonPanel);
    scrollPane.setSize(800, 800);
    this.add(scrollPane, BorderLayout.WEST);*/
    /* dungeonPanel.setDone(false);

   // this.revalidate();
    //dungeonPanel.repaint();
    //this.repaint();
   // dungeonPanel = null;
    dungeonPanel = new DungeonPanel(rm);
    dungeonPanel.setPreferredSize(new Dimension(500, 500));
   // container.add(dungeonPanel);
  //  container.setPreferredSize(new Dimension(400, 400));
    JScrollPane scrollPane = new JScrollPane(dungeonPanel);
    scrollPane.setSize(800, 800);
    this.add(scrollPane, BorderLayout.WEST);
    pack();
    display.setText("hh");
    setVisible(true);
    dungeonPanel.updateUI();*/
  }

  @Override
  public void printError(String errorMsg) {
    dungeonPanel.setDone(false);
    dungeonPanel.setVisible(true);
    String s = display.getText().substring(0, display.getText().length() - 7);
    s = s + "  " + "<br></hmtl>";
    display.setText(s);

  }

  @Override
  public void printResult(String result) {
    dungeonPanel.setDone(false);
    dungeonPanel.setVisible(true);
    display.setText(dungeonPanel.locationDesc());
    String s = display.getText().substring(0, display.getText().length() - 7);
    s = s  + result + "<br></hmtl>";
    display.setText(s);

  }

  /* @Override
  public void printPickError(String errorMsg) {
    dungeonPanel.setDone(false);
    dungeonPanel.setVisible(true);
    String s = display.getText().substring(0, display.getText().length() - 7);
    s = s  + errorMsg + "<br></hmtl>";
    display.setText(errorMsg);

  }*/

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this,error,"Error",JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setModel(IReadOnlyDungeon rm) {
    if(null != rm) {
      this.rm = rm;
    } else {
      throw new IllegalArgumentException("Argument can't be null");
    }
  }

  public void addKeyBinding(JComponent comp, int keyCode, String id, ActionListener actionListener) {
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
}

//infoScrollPane = new JScrollPane(infoPanel);
//infoScrollPane.setSize(800, 800);
//this.add(infoScrollPane);
