package dungeonmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Dungeon model is the first class exposed to driver or user.
 * It functionalities includes creating a two dimensional 
 * habitant, habitant. Providing treasure to cells and players.
 * It also allows a player to move from current location to the 
 * adjacent location. It also allows driver to get the treasure
 * in the current location.
 * In this representation, each location is represented as number 
 * ranging from zero to noOfRows*noOfColumns -1 sequentially.
 */
public class DungeonImpl implements IDungeon {

  private GridTwoD gridTwoD;
  private IHabitant habitant;
  private int startPoint = 0;
  private int endPoint = 0;
  private boolean isGameOver = false;
  private boolean isPlayWinner = false;
  private int noOfRows;
  private int noOfColumns;
  private boolean randomPlayerWins;
  private boolean isRandom;

  /**
   * Constructs a dungeon model. This object is the entry point 
   * of the application. It creates two dimensioal matrix of 
   * caves, tunnels and treasure.
   * 
   * @param noOfRows represents the number of rows of the two 
   *     dimensional matrix.
   * @param noOfColumns represent the number of columns of the 
   *     two dimeensional matrix.
   * @param interConnectivity represents the edges in addition
   *     to the current minimum spanning tree.
   * @param isWrapping represent whether the dungeon a wrapping 
   *     or not.
   * @param percentageOfTreasure represents the percentage of 
   *     caves containing treasures.
   */
  public DungeonImpl(int noOfRows, int noOfColumns, int interConnectivity,
      boolean isWrapping, int percentageOfTreasure, int numberOfOtyughs, boolean isRandom,
      boolean randomPlayerWins) {

    if (noOfRows <= 0) {
      throw new IllegalArgumentException("Invalid input given for "
          + "noOfRows.");
    }

    if (noOfColumns <= 0) {
      throw new IllegalArgumentException("Invalid input given for "
          + "noOfColumns.");
    }

    if (interConnectivity < 0) {
      throw new IllegalArgumentException("Invalid input given for "
          + "interConnectivity.");
    }

    if (percentageOfTreasure < 0) {
      throw new IllegalArgumentException("Invalid input given for "
          + "percentage Of Treasure.");
    }

    if (numberOfOtyughs < 1) {
      throw new IllegalArgumentException("Invalid input given for "
          + "number of otyughs.");
    }

    this.noOfColumns = noOfColumns;
    this.noOfRows = noOfRows;
    this.randomPlayerWins = randomPlayerWins;
    this.isRandom = isRandom;
    /* List<Integer> numbers = Stream.iterate(0, n -> n + 1)
        .limit(noOfRows * noOfColumns)
        .collect(Collectors.toList());

    if (isRandom) {
      Collections.shuffle(numbers);
    }*/

    this.gridTwoD = new GridTwoD(noOfRows, noOfColumns, interConnectivity,
        isWrapping, percentageOfTreasure, numberOfOtyughs, isRandom);

    this.startPoint = this.gridTwoD.getStartPoint();
    this.endPoint = this.gridTwoD.getEndPoint();


    //distribute weapon and monsters
    //List<IMonster> monsterList = getAllMonsters();
    List<IWeapon> weaponList = getInitialArmory();

    this.habitant = new Habitant(startPoint, weaponList);
  }

  private List<IWeapon> getInitialArmory() {
    List<IWeapon> weaponList = new ArrayList<>();
    for (int i = 0 ; i < 3; i++) {
      weaponList.add(new Arrow("Arrow"));
    }
    return weaponList;
  }

  //  private List<IMonster> getAllMonsters() {
  //    List<IWeapon> weaponList = new ArrayList<>();
  //    for (int i = 0 ; i < 3; i++) {
  //      weaponList.add(new Arrow("Arrow"));
  //    }
  //    return weaponList;
  //  }

  @Override
  public int attack(String dir, int distance) {
    int cloc = this.getPlayersLocation();
    if (habitant.noOfArrowCount() == 0) {
      return 3;
    }
    int ret =  this.gridTwoD.attack(cloc, dir, distance);
    this.habitant.reduceArrowCount();
    return ret;
  }

  @Override
  public boolean move(String dir) {
    //  IHabitat[][] twoDimHabitat = this.gridTwoD.gettwoDimHabitat();

    int currLocation = habitant.getCurrLocation();
    if (currLocation == endPoint) {
      throw new IllegalStateException("Game is over");
    }
    int currR = currLocation / noOfColumns;
    int currC = currLocation % noOfColumns;

    List<Integer> avblDir = this.gridTwoD.getAvblDir(currR, currC);

    boolean moveDone =  habitant.updateLocation(dir, avblDir, noOfRows, noOfColumns);

    int newLocation = habitant.getCurrLocation();
    int nCurrR = newLocation / noOfColumns;
    int nCurrC = newLocation % noOfColumns;
    if (moveDone) {
      if (habitant.getCurrLocation() == endPoint) {
        updatePlayerStateIfGameOver(nCurrR, nCurrC);
      } else if (null != this.gridTwoD.getMonster(nCurrR, nCurrC)) {
        updatePlayerStateIfNotGameOver(nCurrR, nCurrC);
      }
    }

    return moveDone;
  }

  private void updatePlayerStateIfGameOver(int nCurrR, int nCurrC) {
    isGameOver = true;
    if (null != this.gridTwoD.getMonster(nCurrR, nCurrC)) {
      int monsterStrength = this.gridTwoD.getMonster(nCurrR, nCurrC).getStrength();
      if (monsterStrength == 2) {
        isPlayWinner = false;
      } else if (monsterStrength == 1) {
        if (isRandom) {
          GenerateRandom random = new GenerateRandom();
          int r = random.nextRandom(1, 2);
          if (r == 1) {
            isPlayWinner = true;            
          } else {
            isPlayWinner = false;  
          } 
        } else if (randomPlayerWins) {
          isPlayWinner = true;  
        } else {
          isPlayWinner = false;  
        }
      } else if (monsterStrength == 0) {
        isPlayWinner = true;
      }
    }
  }

  private void updatePlayerStateIfNotGameOver(int nCurrR, int nCurrC) {
    int monsterStrength = this.gridTwoD.getMonster(nCurrR, nCurrC).getStrength();
    if (monsterStrength == 2) {
      isPlayWinner = false;
      isGameOver = true;
    } else if (monsterStrength == 1) {
      if (isRandom) {
        GenerateRandom random = new GenerateRandom();
        int r = random.nextRandom(1, 2);
        if (r == 2) {
          isGameOver = true;
          isPlayWinner = false;  
        } 
      } else if (!randomPlayerWins) {
        isGameOver = true;
        isPlayWinner = false;  
      }
    } 
  }

  @Override
  public String getPlayersTreasures() {
    return this.habitant.getAvblTreasure();
    /*String playerTreasure = "";
    for (int k = 0 ; k < treasureList.size(); k++) {
      playerTreasure = playerTreasure + treasureList.get(k).getName() + ", ";
    }
    return playerTreasure;*/
  }
  
  @Override
  public String getPlayersArrows() {
    return this.habitant.getAvblArrow();
    /*String playerTreasure = "";
    for (int k = 0 ; k < treasureList.size(); k++) {
      playerTreasure = playerTreasure + treasureList.get(k).getName() + ", ";
    }
    return playerTreasure;*/
  }

  @Override
  public boolean pickUpItem(String pickUpItem) {
    // IHabitat[][] twoDimHabitat = this.gridTwoD.gettwoDimHabitat();
    int currLocation = habitant.getCurrLocation();
    int currR = currLocation / noOfColumns;
    int currC = currLocation % noOfColumns;
    //List<ITreasure> treasureAtLocation = twoDimHabitat[currR][currC].getAvblTreasure();
    //twoDimHabitat[currR][currC].updateTreasure(new ArrayList<ITreasure>());
    //return habitant.updateTreasure(treasureAtLocation);
    String name = gridTwoD.removeTreasureOrArmor(pickUpItem, currR, currC);
    return habitant.updateTreasureOrArmor(pickUpItem, name);
  }

  @Override
  public int getStartPoint() {
    return startPoint;
  }

  @Override
  public int getEndPoint() {
    return endPoint;
  }

  @Override
  public int getPlayersLocation() {
    return habitant.getCurrLocation();
  }

  /**
   * It returns the direction from start to end location.
   * 
   * @param start represents start location.
   * @param end represents end location.
   * @param noOfRows represents number of rows.
   * @param noOfColumns represents number of columns.
   * @return
   */
  /* private String getDirection(int start, int end, int noOfRows,
      int noOfColumns) {
    int startRow = start / noOfColumns; 
    int startColumn = start % noOfColumns; 
    int endRow = end / noOfColumns;
    int endColumn = end % noOfColumns;

    if (startRow == endRow) {
      if (endColumn == startColumn + 1 || (startColumn == (noOfColumns - 1) 
          && endColumn == 0)) {
        return "R";
      } else {
        return "L";
      }
    } else {
      if (endRow == startRow + 1 || (startRow == (noOfRows - 1) 
          && endRow == 0)) {
        return "D";
      } else {
        return "U";
      }
    }
  }*/

  @Override
  public String getlocationDetails() {
    // IHabitat[][] twoDimHabitat = this.gridTwoD.gettwoDimHabitat();
    int cloc = this.getPlayersLocation();
    int rowC = cloc / noOfColumns;
    int colC = cloc % noOfColumns;
    return this.gridTwoD.getlocationDetails(cloc, rowC, colC);
    /* String directions = "";
    List<Integer> possibleDir =  twoDimHabitat[rowC][colC].getAvblDir();
    for (int k = 0 ; k < possibleDir.size(); k++) {
      directions = directions + getDirection(
          cloc, possibleDir.get(k), twoDimHabitat.length, twoDimHabitat[0].length) + ", ";
    }

    String treasures = "";
    List<ITreasure> treasureList = twoDimHabitat[rowC][colC].getAvblTreasure();
    for (int k = 0 ; k < treasureList.size(); k++) {
      treasures = treasures + treasureList.get(k).getName() + ", ";
    }

    return directions + " : " + treasures;*/
  }

  @Override
  public String dungeonDescription() {
    String desc = this.gridTwoD.dungeonDescription();
    /*String desc = "";
    for (int i = 0 ; i < twoDimHabitat.length; i++) {

      for (int j = 0 ; j < twoDimHabitat[0].length; j++) {
        int curr = twoDimHabitat[0].length * i + j;
        String treasures = "";
        // List<ITreasure> treasureList = twoDimHabitat[i][j].getAvblTreasure();
        List<ITreasure> treasureList = this.gridTwoD.getAvblTreasure(i, j);
        for (int k = 0 ; k < treasureList.size(); k++) {
          treasures = treasures + treasureList.get(k).getTreasureType() + ", ";
        }

        //List<Integer> avblDir = twoDimHabitat[i][j].getAvblDir();
        List<Integer> avblDir = this.gridTwoD.getAvblDir(i, j);
        String direction = "";
        for (int k = 0; k < avblDir.size(); k++) {
          direction = direction + getDirection(curr, 
              avblDir.get(k), twoDimHabitat.length, twoDimHabitat[0].length) + ", ";
        }

        List<IWeapon> avblWeapons = this.gridTwoD.getWeapon(i, j);
        String weapon = "";
        if (null != avblWeapons) {
          for (int k = 0; k < avblWeapons.size(); k++) {
            weapon = weapon + avblWeapons.get(k).getName() + ", ";
          }
        }

        IMonster monster = this.gridTwoD.getMonster(i, j);
        String monsterDesc = "";
        if (null != monster) {
          monsterDesc = monsterDesc + monster.getName() + ", ";
        }

        desc = desc + "{" + curr + "(" + direction + ")" + " & " + "(" + treasures + ") "
            + " & " + "(" + weapon + ")" + " & " + "(" + monsterDesc + ")" + "} ";
      }
      desc += "\n";
    }*/
    return desc;
  }

  @Override
  public List<Integer>[][] dungeonRepresentation() {
    return this.gridTwoD.dungeonRepresentation();
    /* IHabitat[][] twoDimHabitat = this.gridTwoD.gettwoDimHabitat();
    List<Integer>[][] retDungeon = new ArrayList[twoDimHabitat.length][twoDimHabitat[0].length];
    for (int i = 0 ; i < twoDimHabitat.length; i++) {
      for (int j = 0 ; j < twoDimHabitat[0].length; j++) {
        retDungeon[i][j] = twoDimHabitat[i][j].getAvblDir();
      }
    }
    return retDungeon;*/
  }

  @Override
  public boolean isGameOver() {
    return isGameOver;
  }

  @Override
  public boolean getWinner() {
    return isPlayWinner;
  }
}