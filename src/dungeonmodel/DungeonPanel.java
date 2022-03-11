package dungeonmodel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * It represents maze of the dungeon in the GUI.
 * It contains treasure, arrow, player, monster, menu,
 * details, quit button. On click of the button various 
 * method of the controller is called and result is 
 * reflected in the screen. 
 *
 */
class DungeonPanel extends JPanel implements IDungeonPanel {

  private static final long serialVersionUID = 136925456806114533L;

  private IReadOnlyDungeon rm;
  private boolean done;

  /**
   * Constructs a panel object for the dungeon.
   * 
   * @param rm represent read-only object to get values
   *     from the model.
   */
  public DungeonPanel(IReadOnlyDungeon rm) {
    if (null == rm) {
      throw new IllegalArgumentException("Argument can't be null.");
    }
    this.rm = rm;
    done = false;
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (!done) {
      Graphics2D g2 = (Graphics2D) g;
      GridBagLayout gLayout = new GridBagLayout();

      if (this.getComponentCount() > 0) {
        this.removeAll();
      }
      this.setLayout(gLayout);
      try {
        BufferedImage bImage1;
        InputStream stream = getClass().getResourceAsStream("/img/blank.png");
        bImage1 = ImageIO.read(stream);
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
            String isAdjPit = locArr[5];
            String isrMonster = locArr[6];
            String isThief = locArr[7];
            String isAPit = locArr[8];
            String isMonster = locArr[9];
            int countOfArrow = 0;
            if (visited.trim().equals("Y")) {
              if (null != directions) {
                String dirs = "";
                Set<String> dirSet = new HashSet<>();
                for (int i = 0 ; i < directions.length; i++) {
                  if (!directions[i].trim().equals("")) {
                    dirs += directions[i] + ", ";
                    dirSet.add(directions[i].trim());
                  }
                }
                if (null != dirs) {
                  dirs = dirs.trim();
                  dirs = dirs.substring(0, dirs.length() - 1);
                }
                fPath = "/img/"
                    + (dirSet.contains("N") ? "N" : "") 
                    + (dirSet.contains("S") ? "S" : "") 
                    + (dirSet.contains("E") ? "E" : "") 
                    + (dirSet.contains("W") ? "W" : "") + ".png";
                stream = getClass().getResourceAsStream(fPath);
                bImage = ImageIO.read(stream);
              }
              if (rm.getPlayersLocation() / rm.getNoOfColumns() == x  
                  && rm.getPlayersLocation() % rm.getNoOfColumns() == y ) {
                bImage = overlay(bImage, "/img/player.png", 20);
              }

              if (null != treasures) {
                String treasure = "";
                int sz = 0;
                for (int i = 0 ; i < treasures.length; i++) {
                  if (!treasures[i].trim().equals("")) {
                    treasure += treasures[i].trim() + ", ";
                    bImage = overlay(bImage, "/img/" + treasures[i].trim() + ".png", sz);
                    sz = sz + 2;
                  }
                }
                if (null != treasure) {
                  treasure = treasure.trim();
                  if (!treasure.equalsIgnoreCase("")) {
                    treasure = treasure.substring(0, treasure.length() - 1);
                  }
                }
              }

              if (null != weapons) {
                for (int i = 0 ; i < weapons.length; i++) {
                  if (!weapons[i].trim().equals("")) {
                    countOfArrow++;
                  }
                }
                if (countOfArrow > 0) {
                  bImage = overlay(bImage, "/img/arrow-white.png", 20);
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
                bImage = overlay(bImage, "/img/" + "stench02.png", 10);
              } else if ( monsMap.containsKey("2")) {
                bImage = overlay(bImage, "/img/" + "stench01.png", 10);
              }

              if (isAdjPit.trim().equals("Y")) {
                bImage = overlay(bImage, "/img/pit.png", 10); 
              }

              if (isrMonster.trim().equals("Y")) {
                bImage = overlay(bImage, "/img/rmonster.png", 10); 
              }

              if (isThief.trim().equals("Y")) {
                bImage = overlay(bImage, "/img/theif.png", 10); 
              }

              if (isAPit.trim().equals("Y")) {
                bImage = overlay(bImage, "/img/hole.png", 10); 
              }

              if (isMonster.trim().equals("Y")) {
                bImage = overlay(bImage, "/img/otyugh.png", 10); 
              }


              JLabel dirImgLabel = new JLabel(new ImageIcon(bImage));
              c.gridx = y;
              c.gridy = x;
              c.weightx = 0.0;
              this.add(dirImgLabel, c);
            } else {
              JLabel dirImgLab = new JLabel(new ImageIcon(bImage1));
              c.gridx = y;
              c.gridy = x;
              c.weightx = 0.0;
              this.add(dirImgLab, c);
            }
          }
        }

      } catch (Exception e) {
        System.out.println(e);
      }

    }
  }

  @Override
  public String locationDesc() {
    StringBuilder out = new StringBuilder("<html>");
    if (!rm.isGameOver()) {
      out.append("<b>Instructions</b><br>");
      out.append("<b>To move use arrow (UP, DOWN, LEFT, RIGHT) "
          + "key/mouse click. <br>"
          + " To pick up treasure click P, followed by R for "
          + "ruby,<br> D for diamond, E for sapphire,"
          + " A for arrow.<br>"
          + " To shoot monster click S followed by arrow "
          + "key<br> for direction and number(1-5) for distance.</b><br>");
      out.append("<br>");
      out.append("<br>");
      out.append("<b>Location details</b><br>");
      int currLocation = rm.getPlayersLocation();
      String[][] dungeonRepresention =  rm.dungeonRepresentation();
      int noOfColumns = rm.getNoOfColumns();
      int row = currLocation / noOfColumns;
      int col = currLocation % noOfColumns;
      String locationDetails = dungeonRepresention[row][col];
      String [] locArr = locationDetails.split(":");
      String [] directions = locArr[0].split(",");
      String [] treasures = locArr[1].split(",");
      String [] weapons = locArr[2].split(",");
      String [] monster = locArr[3].split(",");
      String isAdjPit = locArr[5];
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
      if (monsMap.containsKey("1") 
          || monsMap.containsKey("2") && monsMap.get("2").compareTo("1") > 0) {
        out.append("You smell monster in nearby cell<br>");
      } else if ( monsMap.containsKey("2")) {
        out.append("You found faint smell of monster<br>");
      }
      if (null != directions) {
        String dirs = "";
        for (int i = 0 ; i < directions.length; i++) {
          if (!directions[i].trim().equals("")) {
            dirs += directions[i] + ", ";
          }
        }
        if (null != dirs) {
          dirs = dirs.trim();
          dirs = dirs.substring(0, dirs.length() - 1);
        }
      }

      if (null != treasures) {
        String treasure = "";
        for (int i = 0 ; i < treasures.length; i++) {
          if (!treasures[i].trim().equals("")) {
            treasure += treasures[i].trim() + ", ";
          }
        }
        if (null != treasure) {
          treasure = treasure.trim();
          if (!treasure.equalsIgnoreCase("")) {
            treasure = treasure.substring(0, treasure.length() - 1);
          }
        }
        if (!treasure.equals("")) {
          out.append("You find " + treasure + " here<br>");
        }
      }

      if (null != weapons) {
        for (int i = 0 ; i < weapons.length; i++) {
          if (!weapons[i].trim().equals("")) {
            countOfArrow++;
          }
        }
        if (countOfArrow > 0) {
          out.append("You find " + countOfArrow + " arrow here<br>");
        }
      }
      if (isAdjPit.trim().equals("Y")) {
        out.append("You spotted a pit in nearby cell<br>");
      }

      if (rm.isEncounteredRMonster()) {
        out.append("You have encountered a roaming monster. Click F to Fight<br>");
      }
      if (rm.isEncounteredTheif()) {
        out.append("You have encountered a theif. He has stolen all your treasures.<br>");
      }
    }
    if (rm.isfallenIntoPit()) {
      out.append( "You have fallen into a pit.<br>");
    }
    if (rm.isGameOver()) {

      if (rm.getPlayersLocation() == rm.getEndPoint()) {
        out.append("End location reached.<br>"); 
      }
      if (rm.getWinner()) {
        out.append("Game is over! Player wins.<br>");
      } else {
        out.append("Game is over! Player loses.<br>");
      }

    }

    out.append("</html>");
    return out.toString();  
  }

  private BufferedImage overlay(BufferedImage starting, 
      String fpath, int offset) throws IOException {
    InputStream stream = getClass().getResourceAsStream(fpath);
    BufferedImage overlay = ImageIO.read(stream);
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

  @Override
  public void paintM() {
    this.paintComponent(getGraphics());
  }

  public void setModel(IReadOnlyDungeon rm2) {
    this.rm = rm2;
  }

}
