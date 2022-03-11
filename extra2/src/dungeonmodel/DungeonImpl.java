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
 * Additionally it put arrow and monster in the maze. 
 * 
 */
public class DungeonImpl implements IDungeon {

  private IGridTwoD gridTwoD;
  private IHabitant habitant;
  private int startPoint = 0;
  private int endPoint = 0;
  private boolean isGameOver = false;
  private boolean isPlayWinner = false;
  private int noOfRows;
  private int noOfColumns;
  private boolean randomPlayerWins;
  private boolean isRandom;
  private int seed;
  private int interconnectivity;
  private boolean isWrapping;
  private int percentageOfTreasure;
  private int numberOfOtyughs;
  private boolean fallenIntoPit = false;
  private boolean encounteredTheif = false;
  private boolean encounteredRMonster = false;

  /**
   * Constructs a dungeon model. This object is the entry point 
   * of the application. It creates two dimensioal matrix of 
   * caves, tunnels and treasure, arrow and puts monster in the
   * end location.
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
   * @param numberOfOtyughs denotes the number of monster in the
   *     dungeon.
   * @param isRandom represents is the maze generated randomly
   * @param randomPlayerWins represents if player wins if 
   *     monster is half injured.
   */
  public DungeonImpl(int noOfRows, int noOfColumns, int interConnectivity,
      boolean isWrapping, int percentageOfTreasure, int numberOfOtyughs, boolean isRandom,
      boolean randomPlayerWins, int seed) {

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
    this.interconnectivity = interConnectivity;
    this.isWrapping = isWrapping;
    this.randomPlayerWins = randomPlayerWins;
    this.isRandom = isRandom;
    this.percentageOfTreasure = percentageOfTreasure;
    this.numberOfOtyughs = numberOfOtyughs;
    this.seed = seed;
    this.gridTwoD = new GridTwoD(noOfRows, noOfColumns, interConnectivity,
        isWrapping, percentageOfTreasure, numberOfOtyughs, isRandom, seed);

    this.startPoint = this.gridTwoD.getStartPoint();
    this.endPoint = this.gridTwoD.getEndPoint();


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

  @Override
  public int attack(String dir, int distance) {
    if (null == dir || "".equals(dir)) {
      throw new IllegalArgumentException("Direction cannot be null.");
    }
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
    int currLocation = habitant.getCurrLocation();
    if (currLocation == endPoint) {
      throw new IllegalStateException("Game is over");
    }
    if (null == dir || "".equals(dir)) {
      throw new IllegalArgumentException("Path followed is invalid. Please try again!");
    }
    int currR = currLocation / noOfColumns;
    int currC = currLocation % noOfColumns;

    List<Integer> avblDir = this.gridTwoD.getAvblDir(currR, currC);

    boolean moveDone =  habitant.updateLocation(dir, avblDir, noOfRows, noOfColumns);

    int newLocation = habitant.getCurrLocation();
    int nCurrR = newLocation / noOfColumns;
    int nCurrC = newLocation % noOfColumns;
    if (moveDone) {
      gridTwoD.updatedVisited(nCurrR, nCurrC, true);
      if (habitant.getCurrLocation() == endPoint) {
        updatePlayerStateIfGameOver(nCurrR, nCurrC);
      } else if (null != this.gridTwoD.getMonster(nCurrR, nCurrC)) {
        updatePlayerStateIfNotGameOver(nCurrR, nCurrC);
      }
      updateEncounteredPitOrTheif(nCurrR, nCurrC);
    }

    return moveDone;
  }

  private void updateEncounteredPitOrTheif(int nCurrR, int nCurrC) {
    if (this.gridTwoD.isAPit(nCurrR, nCurrC)) {
      fallenIntoPit = true;
      isGameOver = true;
      isPlayWinner = false;
    }
    if (this.gridTwoD.getRMonster(nCurrR, nCurrC) != null) {
      encounteredRMonster = true;
    } else {
      encounteredRMonster = false;
    }
    if (this.gridTwoD.getThief(nCurrR, nCurrC) != null) {
      encounteredTheif = true;
      habitant.removeTreasure();
    } else {
      encounteredTheif = false;
    }
  }

  @Override
  public boolean move(int row, int col) {
    String dir = "";
    int currLocation = habitant.getCurrLocation();
    int currR = currLocation / noOfColumns;
    int currC = currLocation % noOfColumns;
    if (row == currR) {
      if (col == currC - 1 || (currC == 0 && col == noOfColumns - 1)) {
        dir = "W";
      } else if (col == currC + 1 || (currC == noOfColumns - 1 && col == 0))  {
        dir = "E";
      }
    } else if (col == currC) {
      if (row == currR - 1 || (currR == 0 && row == noOfRows - 1)) {
        dir = "N";
      } else  if (row == currR + 1 || (currR == noOfRows - 1 && row == 0)) {
        dir = "S";
      }
    } 
    return move(dir);
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
          int r = random.nextRandom(1, 2, seed);
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
        int r = random.nextRandom(1, 2, seed);
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
  }

  @Override
  public String getPlayersArrows() {
    return this.habitant.getAvblArrow();
  }

  @Override
  public boolean pickUpItem(String pickUpItem) {
    if (null == pickUpItem || "".equals(pickUpItem)) {
      throw new IllegalArgumentException("Pick up item is invalid");
    }
    int currLocation = habitant.getCurrLocation();
    int currR = currLocation / noOfColumns;
    int currC = currLocation % noOfColumns;
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

  @Override
  public String getlocationDetails() {
    int cloc = this.getPlayersLocation();
    int rowC = cloc / noOfColumns;
    int colC = cloc % noOfColumns;
    return this.gridTwoD.getlocationDetails(cloc, rowC, colC);
  }

  @Override
  public String dungeonDescription() {
    String desc = this.gridTwoD.dungeonDescription();
    return desc;
  }

  @Override
  public String[][] dungeonRepresentation() {
    return this.gridTwoD.dungeonRepresentation();
  }

  @Override
  public boolean isGameOver() {
    return isGameOver;
  }

  @Override
  public boolean getWinner() {
    return isPlayWinner;
  }

  @Override 
  public int getNoOfColumns() {
    return noOfColumns;

  }

  @Override
  public int getNoOfRows() {
    return noOfRows;
  }

  @Override
  public int getInterconnectivity() {
    return this.interconnectivity;
  }

  @Override
  public boolean isWrapping() {
    return isWrapping;
  }

  @Override
  public int numberOfOtyughs() {
    return numberOfOtyughs;
  }

  @Override
  public int percentageOfTreasure() {
    return percentageOfTreasure;
  }

  @Override
  public int getSeed() {
    return this.seed;
  }

  @Override
  public boolean isfallenIntoPit() {
    return fallenIntoPit;
  }

  @Override
  public boolean isEncounteredTheif() {
    return encounteredTheif;
  }

  @Override
  public boolean isEncounteredRMonster() {
    return encounteredRMonster;
  }

  @Override
  public int fightMonster() {
    int ret = 0;
    int newLocation = habitant.getCurrLocation();
    int nCurrR = newLocation / noOfColumns;
    int nCurrC = newLocation % noOfColumns;
    GenerateRandom random = new GenerateRandom();
    int r1 = random.nextRandom(1, 2, seed);
    if (r1 == 1) {
      habitant.updateStrength(); 
    } 
    int r2 = random.nextRandom(1, 2, seed);
    if (r2 == 1) {
      gridTwoD.getRMonster(nCurrR, nCurrC).updateStrength();
    } 
    
    if (r1 == 1 && r2 == 1) {
      ret = 1;
    } else if (r1 ==1 && r2 == 0) {
      ret = 2;
    } else if (r1 == 0 && r2 == 0) {
      ret = 3;
    } else if (r1 == 0 && r2 == 1) {
      ret = 4;
    }
    
    if (gridTwoD.getRMonster(nCurrR, nCurrC).getStrength() == 0) {
      gridTwoD.removeRMonster(nCurrR, nCurrC);
    }
    
    if (habitant.getStrength() == 0) {
      isGameOver = true;
      isPlayWinner = false;
    }
    
   return ret;
  }

}