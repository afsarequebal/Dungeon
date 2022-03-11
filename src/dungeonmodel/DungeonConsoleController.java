package dungeonmodel;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Controller class is used by user for interaction. It asks user for input
 * using scanner. It outputs game state in each move of the player.
 * When game ends, playGame ends.
 * 
 */
public class DungeonConsoleController implements DungeonController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   * 
   * @param in  the source to read from
   * @param out the target to print to
   */
  public DungeonConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(IDungeon m) {
    if (m == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    while (!m.isGameOver()) {
      try {
        String playerArrow = m.getPlayersArrows();
        String playerTreasure = m.getPlayersTreasures();
        if (!"".equals(playerArrow)) {
          out.append("You have " + playerArrow + "\n");
        }
        if (!"".equals(playerTreasure)) {
          out.append("You have " + playerTreasure + "\n");
        }
        String locationDetails = m.getlocationDetails();
        String [] locArr = locationDetails.split(":");
        String [] directions = locArr[0].split(",");
        String [] treasures = locArr[1].split(",");
        String [] weapons = locArr[2].split(",");
        String [] monster = locArr[3].split(",");
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
        if (monsMap.containsKey("1") 
            || monsMap.containsKey("2") && monsMap.get("2").compareTo("1") > 0) {
          out.append("You smell monster in nearby cell\n");
        } else if ( monsMap.containsKey("2")) {
          out.append("You found faint smell of monster\n");
        }
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
          if (countOfDirs == 2) {
            out.append("You are in a tunnel\n");
          } else {
            out.append("You are in a cave\n");
          }
          out.append("Doors lead to the " + dirs + "\n");
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
            out.append("You find " + countOfArrow + " arrow here\n");
          }
        }

        while (true) {
          boolean pickUpDone = false;
          boolean moveDone = false;
          boolean arrowShot = false;
          out.append("Move, Pickup, or Shoot (M-P-S)? \n");
          String row = "";
          row = scan.next();
          if (row.equals("q")) {
            return;
          }
          if (row.equalsIgnoreCase("M") || row.equalsIgnoreCase("P") 
              || row.equalsIgnoreCase("S")) {
            if (row.equalsIgnoreCase("M")) {
              out.append("Where to ?\n");
              while (true) {
                row = scan.next();
                try { 
                  m.move(row);
                  out.append("\n");
                  moveDone = true;
                  break;
                } catch (IllegalArgumentException e) {
                  out.append(e.getMessage() + ". Try again\n");
                }
              }
            } else if (row.equalsIgnoreCase("P")) {
              if (countOfTreasure != 0 || countOfArrow != 0) {
                out.append("What ?\n");
                while (true) {
                  row = scan.next();
                  try { 
                    m.pickUpItem(row.toLowerCase());
                    pickUpDone = true;
                    out.append("\n");
                    break;
                  } catch (IllegalArgumentException e) {
                    out.append(e.getMessage() + ". Try again\n");
                  }
                }
              } else {
                out.append("Pickup item is not allowed \n");
              }
            } else if (row.equalsIgnoreCase("S")) {
              while (true) {
                int distancerow = -1;
                while (true) {
                  out.append("No. of caves (1-5)?\n");
                  try {
                    distancerow = Integer.parseInt(scan.next());
                    if (distancerow > 5 || distancerow < 1) {
                      out.append("No. of caves should be (1-5). Try again\n");
                    } else {
                      break;
                    }
                  } catch (IllegalArgumentException e) {
                    out.append(e.getMessage() + ". Try again\n");
                  } 

                }
                out.append("Where to?\n");
                int s = 0;
                while (true) {
                  row = scan.next();
                  try {
                    s = m.attack(row, distancerow);
                    break;
                  } catch (IllegalArgumentException e) {
                    out.append(e.getMessage() + ". Try again\n");
                  }
                }
                if (s == 1) {
                  out.append("You hear a great howl, seems monster is dead.\n");
                } else if (s == 2) {
                  out.append("You hear a slight howl\n");
                } 
                arrowShot = true;
                if (s == 3) {
                  out.append("You are out of arrows. Explore to find more\n");
                }
                break;
              }

            } 
            if (moveDone || pickUpDone || arrowShot) {
              out.append("\n"); 
              break;
            }
          } else {
            out.append("Not a valid action. Try again\n");
          }
        }
        if (m.isGameOver()) {
          if (m.getPlayersLocation() == m.getEndPoint()) {
            out.append("End location reached\n"); 
          }
          if (m.getWinner()) {
            out.append("Game is over! Player wins.");
          } else {
            out.append("Game is over! Player loses.");
          }
        }
      }
      catch (Exception e) {
        throw new IllegalStateException("Append failed", e);
      }
    } 
  }

}
