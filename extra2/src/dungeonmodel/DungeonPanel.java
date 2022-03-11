package dungeonmodel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class DungeonPanel extends JPanel implements IDungeonPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 136925456806114533L;

  private final IReadOnlyDungeon rm;
  private StringBuilder out;
  private boolean done;

  public DungeonPanel(IReadOnlyDungeon rm) {
    if (null == rm) {
      throw new IllegalArgumentException("Argument can't be null.");
    }
    this.rm = rm;
    done = false;
    out = new StringBuilder("<html>");
    
  }

  @Override
  protected void paintComponent(Graphics g) {
    if(!done) {
      // super.paintComponent(g);
      //this.setBackground(Color.WHITE);
      Graphics2D g2 = (Graphics2D) g;
      // g2.drawLine(50, 0, 50, 150);
      GridBagLayout gLayout = new GridBagLayout();

      //  gLayout.setRows(rm.getNoOfRows());
      if (this.getComponentCount() > 0) {
        this.removeAll();
      }
      //  gLayout.setColumns(rm.getNoOfColumns());
      //   gLayout.setHgap(0);
      //  gLayout.setVgap(0);
      this.setLayout(gLayout);
      try {
        BufferedImage bImage1;
        bImage1 = ImageIO.read(new File("res/img/N.png"));
        this.setPreferredSize(new Dimension(bImage1.getWidth() * rm.getNoOfColumns(), bImage1.getHeight() * rm.getNoOfRows()));
        done = true;

        String[][] dungeonRepresention =  rm.dungeonRepresentation();
        GridBagConstraints c = new GridBagConstraints();

        for (int x = 0; x < dungeonRepresention.length; x++) {
          for (int y = 0; y < dungeonRepresention[0].length; y++) {
            BufferedImage bImage = null;
            String fPath = "";
            String locationDetails = dungeonRepresention[x][y];
            String [] locArr = locationDetails.split(":");
            String [] directions = locArr[0].split(",");
            String [] treasures = locArr[1].split(",");
            String [] weapons = locArr[2].split(",");
            String [] monster = locArr[3].split(",");
            String visited = locArr[4];
            // System.out.println(dungeonRepresention[x][y]);
            String isAdjPit = locArr[5];
            String isrMonster = locArr[6];
            String isThief = locArr[7];
            String isAPit = locArr[8];
            String isMonster = locArr[9];
            int countOfDirs = 0;
            int countOfTreasure = 0;
            int countOfArrow = 0;

            //
            if (null != directions) {
              String dirs = "";
              Set<String> dirSet = new HashSet<>();
              for (int i = 0 ; i < directions.length; i++) {
                if (!directions[i].trim().equals("")) {
                  dirs += directions[i] + ", ";
                  dirSet.add(directions[i].trim());
                  countOfDirs += 1;
                }
              }
              if (null != dirs) {
                dirs = dirs.trim();
                dirs = dirs.substring(0, dirs.length() - 1);
              }
              /*  if (countOfDirs == 2) {
            out.append("You are in a tunnel\n");
          } else {
            out.append("You are in a cave\n");
          }*/
              //out.append("Doors lead to the " + dirs + "\n");

              //fPath = "res/img/blank.png";
              // if (visited.trim().equals("Y")) {
              fPath = "res/img/"
                  + (dirSet.contains("N") ? "N" : "") 
                  + (dirSet.contains("S") ? "S" : "") 
                  + (dirSet.contains("E") ? "E" : "") 
                  + (dirSet.contains("W") ? "W" : "") + ".png";
              // }
              bImage = ImageIO.read(new File(fPath));
              /* bImage = overlay(bImage, fPath, 0);
            JLabel dirImgLabel = new JLabel(new ImageIcon(ImageIO.read(new File("res/img"
                + (dirSet.contains("N") ? "N" : "") 
                + (dirSet.contains("S") ? "S" : "") 
                + (dirSet.contains("E") ? "E" : "") 
                + (dirSet.contains("W") ? "W" : "") + ".png"))));*/
              /* JLabel dirImgLabel = new JLabel(new ImageIcon(bImage));
           dirImgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
          //  JLabel dirImgLabel = new JLabel("Helo");
           c.gridx = y;
           c.gridy = x;
           c.weightx = 0.0;
            this.add(dirImgLabel, c);*/

            }
            if (rm.getPlayersLocation() / rm.getNoOfColumns() == x  
                && rm.getPlayersLocation() % rm.getNoOfColumns() == y ) {
              bImage = overlay(bImage, "res/img/player.png", 20);
            }

            if (null != treasures) {
              String treasure = "";
              int sz = 0;
              for (int i = 0 ; i < treasures.length; i++) {
                if (!treasures[i].trim().equals("")) {
                  treasure += treasures[i].trim() + ", ";
                  bImage = overlay(bImage, "res/img/" + treasures[i].trim() + ".png", sz);
                  countOfTreasure++; 
                  sz = sz + 2;
                }
              }
              if (null != treasure) {
                treasure = treasure.trim();
                if (!treasure.equalsIgnoreCase("")) {
                  treasure = treasure.substring(0, treasure.length() - 1);
                }
              }
              if (!treasure.equals("")) {
                out.append("You find " + treasure + " here\n");
              }
            }

            if (null != weapons) {
              for (int i = 0 ; i < weapons.length; i++) {
                if (!weapons[i].trim().equals("")) {
                  countOfArrow++;
                }
              }
              if (countOfArrow > 0) {
                bImage = overlay(bImage, "res/img/arrow-white.png", 20);
                out.append("You find " + countOfArrow + " arrow here\n");
              }
            }

            Map<String, String> monsMap = new HashMap<>();
            if (null != monster) {
              for (int z = 0 ; z < monster.length; z++) {
                String [] monsterKeyVal = monster[z].split(";");
                if (null != monsterKeyVal && !monsterKeyVal[0].trim().equals("")) {
                  monsMap.put(monsterKeyVal[0].trim(), monsterKeyVal[1].trim());
                }
              }
            }
            if (monsMap.containsKey("1") 
                || monsMap.containsKey("2") && monsMap.get("2").compareTo("1") > 0) {
              out.append("You smell monster in nearby cell<br>");
              bImage = overlay(bImage, "res/img/" + "stench02.png", 10);
            } else if ( monsMap.containsKey("2")) {
              out.append("You found faint smell of monster<br>");
              bImage = overlay(bImage, "res/img/" + "stench01.png", 10);
            }

            if (isAdjPit.trim().equals("Y")) {
              out.append("You spotted a pit in nearby cell<br>");
              bImage = overlay(bImage, "res/img/pit.png", 10); 
            }

            if (isrMonster.trim().equals("Y")) {
              out.append("You spotted a pit in nearby cell<br>");
              bImage = overlay(bImage, "res/img/rmonster.png", 10); 
            }

            if (isThief.trim().equals("Y")) {
              out.append("You spotted a pit in nearby cell<br>");
              bImage = overlay(bImage, "res/img/theif.png", 10); 
            }

            if (isAPit.trim().equals("Y")) {
              out.append("You spotted a pit in nearby cell<br>");
              bImage = overlay(bImage, "res/img/hole.png", 10); 
            }

            if (isMonster.trim().equals("Y")) {
              out.append("You spotted a pit in nearby cell<br>");
              bImage = overlay(bImage, "res/img/otyugh.png", 10); 
            }

            if (visited.trim().equals("Y")) {
              JLabel dirImgLabel = new JLabel(new ImageIcon(bImage));
             //dirImgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
              //  JLabel dirImgLabel = new JLabel("Helo");
              c.gridx = y;
              c.gridy = x;
              c.weightx = 0.0;
              this.add(dirImgLabel, c);
            } else {
              JLabel dirImgLab = new JLabel(new ImageIcon(ImageIO.read(new File("res/img/blank.png"))));
             // dirImgLab.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
              //  JLabel dirImgLabel = new JLabel("Helo");
              c.gridx = y;
              c.gridy = x;
              c.weightx = 0.0;
              this.add(dirImgLab, c);
            }
          }
        }

        /*
         * for (int x = 1; x < dungeonRepresention.length; x++) { //for (int y = 1; y <
         * dungeonRepresention[0].length; y++) { g2.drawLine( bImage1.getWidth() * x ,
         * 0, bImage1.getWidth() * x, bImage1.getWidth()* dungeonRepresention.length);
         * g2.drawLine(0, bImage1.getWidth() * x, bImage1.getWidth() *
         * dungeonRepresention.length, bImage1.getWidth()*
         * dungeonRepresention[0].length); //} }
         */
      } catch (IOException e) {
        System.out.println(e.getMessage() + "he");
      }

      // System.out.println(rm.dungeonDescription());
    }
    // System.out.println(this.getWidth());
    //System.out.println(this.getHeight());
     System.out.println("hell");

  }

  @Override
  public String locationDesc() {
    StringBuilder out = new StringBuilder("<html>");
    out.append("Instructions<br>");
    String playerArrow = rm.getPlayersArrows();
    String playerTreasure = rm.getPlayersTreasures();
   /* if (!"".equals(playerArrow)) {
      out.append("You have " + playerArrow + "<br>");
    }
    if (!"".equals(playerTreasure)) {
      out.append("You have " + playerTreasure + "<br>");
    }*/
    int currLocation = rm.getPlayersLocation();
    String[][] dungeonRepresention =  rm.dungeonRepresentation();
    int noOfColumns = rm.getNoOfColumns();
    int row = currLocation / noOfColumns;
    int col = currLocation % noOfColumns;
    String locationDetails = dungeonRepresention[row][col];
    System.out.println(currLocation);
    // System.out.println(locationDetails);
    String [] locArr = locationDetails.split(":");
    String [] directions = locArr[0].split(",");
    String [] treasures = locArr[1].split(",");
    String [] weapons = locArr[2].split(",");
    String [] monster = locArr[3].split(",");
    String visited = locArr[4];
    String isAdjPit = locArr[5];
    int countOfDirs = 0;
    int countOfTreasure = 0;
    int countOfArrow = 0;
    Map<String, String> monsMap = new HashMap<>();
    if (null != monster) {
      for (int z = 0 ; z < monster.length; z++) {
        String [] monsterKeyVal = monster[z].split(";");
        if (null != monsterKeyVal && !monsterKeyVal[0].trim().equals("")) {
          monsMap.put(monsterKeyVal[0].trim(), monsterKeyVal[1].trim());
        }
      }
    }
  /*  if (monsMap.containsKey("1") 
        || monsMap.containsKey("2") && monsMap.get("2").compareTo("1") > 0) {
      out.append("You smell monster in nearby cell<br>");
    } else if ( monsMap.containsKey("2")) {
      out.append("You found faint smell of monster<br>");
    }*/
    if (null != directions) {
      String dirs = "";
      for (int i = 0 ; i < directions.length; i++) {
        if (!directions[i].trim().equals("")) {
          dirs += directions[i] + ", ";
          countOfDirs += 1;
        }
      }
      if (null != dirs) {
        dirs = dirs.trim();
        dirs = dirs.substring(0, dirs.length() - 1);
      }
    /*  if (countOfDirs == 2) {
        out.append("You are in a tunnel<br>");
      } else {
        out.append("You are in a cave<br>");
      }
      out.append("Doors lead to the " + dirs + "<br>");*/
    }

    if (null != treasures) {
      String treasure = "";
      for (int i = 0 ; i < treasures.length; i++) {
        if (!treasures[i].trim().equals("")) {
          treasure += treasures[i].trim() + ", ";
          countOfTreasure++;
        }
      }
      if (null != treasure) {
        treasure = treasure.trim();
        if (!treasure.equalsIgnoreCase("")) {
          treasure = treasure.substring(0, treasure.length() - 1);
        }
      }
   /*  if (!treasure.equals("")) {
        out.append("You find " + treasure + " here<br>");
      }*/
    }

    if (null != weapons) {
      for (int i = 0 ; i < weapons.length; i++) {
        if (!weapons[i].trim().equals("")) {
          countOfArrow++;
        }
      }
     /* if (countOfArrow > 0) {
        out.append("You find " + countOfArrow + " arrow here<br>");
      }*/
    }
   /* if (isAdjPit.trim().equals("Y")) {
      out.append("You spotted a pit in nearby cell<br>");
    }*/
    // update need here
    out.append("To move use arrow (UP, DOWN, LEFT, RIGHT) key/mouse click. <br>"
        + " To pick up treasure click P, followed by R for ruby,<br> D for diamond, E for emerald,"
        + " A for arrow.<br>"
        + " To shoot monster click S followed by arrow key<br> for direction and number(1-5) for distance.<br>");
    out.append("</html>");
    return out.toString();  
  }

  private BufferedImage overlay(BufferedImage starting, String fpath, int offset) throws IOException {
    BufferedImage overlay = ImageIO.read(new File(fpath));
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }

  @Override
  public void setDone(boolean done) {
    this.done = done;
  }

  public void xyz() {
    this.paintComponent(getGraphics());
    //try {
    // this.repaint();
    // this.repaint();
    //this.paintComponent(getGraphics());
    /* BufferedImage bImage;
   String fPath = "res/img/N.png";

    bImage = ImageIO.read(new File(fPath));*/

    /* bImage = overlay(bImage, fPath, 0);
   JLabel dirImgLabel = new JLabel(new ImageIcon(ImageIO.read(new File("res/img"
       + (dirSet.contains("N") ? "N" : "") 
       + (dirSet.contains("S") ? "S" : "") 
       + (dirSet.contains("E") ? "E" : "") 
       + (dirSet.contains("W") ? "W" : "") + ".png"))));*/
    /* JLabel dirImgLabel = new JLabel(new ImageIcon(bImage));
 //  JLabel dirImgLabel = new JLabel("Helo");
  //GridBagLayout layout = (GridBagLayout)getLayout();
  GridBagConstraints c = new GridBagConstraints();
  c.gridx = 0;
  c.gridy = 0;
  this.removeAll();
  this.add(dirImgLabel, c, 0);
  this.validate();
  this.setVisible(true);
  super.paintComponent(getGraphics());
 // Component toRemove = getComponent(0);
//  GridBagLayout layout = (GridBagLayout)getLayout();
 // GridBagConstraints gbc = layout.getConstraints(toRemove);
//  remove(toRemove);
//  add(dirImgLabel, gbc, 0);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    setVisible(true);
//    getContentPane().revalidate();
    repaint();*/
  }
  //}

}
