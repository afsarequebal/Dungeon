package dungeonmodel;

/**
 * It represents interface for read-only interface for the model.
 * It provides method to get players treasure, location details,
 * start and end point.
 */
public interface IReadOnlyDungeon {

  /**
   * It is used to get all the players treasures.
   * 
   * @return a list of all treasures a player can have,
   */
  String getPlayersTreasures();

  /**
   * It is used to get the current location of the player.
   * 
   * @return the location of the player.
   */
  int getPlayersLocation();

  /**
   * It is used to get the start point in the dungeon.
   * 
   * @return the start point of the dungeon.
   */
  int getStartPoint();

  /**
   * It is used to get the end point in the dungeon.
   * 
   * @return the end point of the dungeon.
   */
  int getEndPoint();

  /**
   * It is used to get the players directions and 
   * treasures.
   * 
   * @return a string representation of directions 
   *     and treasures.
   */
  String getlocationDetails();

  /**
   * It is used to return a string representation of the habitat.
   * It contains all the available treasures. 
   * A sample representation of a cell is 
   * {0(D, L, U, ) & (RBS4, RBS3, RBS8, ) }, here
   * 0 represents cell location.
   * D,L,U represents directions and
   * RBS5, RBS3, RBS8 represents treasure.
   *  
   * @return a string representation of the habitat.
   */
  String dungeonDescription();

  /**
   * It is used to return a dungeon representation to
   * evaluate paths needed to go from start to end.
   * 
   * @return a two dimensional array, where each cell
   *     contains a list of possible directionsm,
   *     weapons, treasure and monster.
   */
  String[][] dungeonRepresentation();

  /**
   * It is used to check if game is over or not.
   * 
   * @return true if game is over.
   */
  boolean isGameOver();

  /**
   * It is used to get the winner of the game.
   *  
   * @return true if player is winner.
   */
  boolean getWinner();

  /**
   * It is used to get the string representation of the players arrows.
   * 
   * @return string representation of the treasure.
   */
  String getPlayersArrows();

  /**
   * It is used to get the number of columns in the dungeon.
   * 
   * @return the number of columns in the dungeon.
   */
  int getNoOfColumns();

  /**
   * It is used to get the number of rows in the dungeon.
   * 
   * @return the number of rows in the dungeon.
   */
  int getNoOfRows();

  /**
   * It is used to get the interconnectivity in the dungeon.
   * 
   * @return the interconnectivity in the dungeon.
   */
  int getInterconnectivity();

  /**
   * It is used to get true if the dungeon is wrapping.
   * 
   * @return true if dungeon is wrapping.
   */
  boolean isWrapping();

  /**
   * It is used to get number of otyughs in the dungeon.
   * 
   * @return the number of otyughs in the dungeon.
   */
  int numberOfOtyughs();

  /**
   * It is used to get the percentage of treasures in the 
   * dungeon.
   * 
   * @return the percentage of treasure in the dungeon.
   */
  int percentageOfTreasure();

  /**
   * It is the get start value of seed for random number generator.
   * 
   * @return the seed of the random number generator.
   */
  int getSeed();

  /**
   * It is used to check if player has fallen into the pit.
   * 
   * @return true if player has fallen into the pit.
   */
  boolean isfallenIntoPit();

  /**
   * It is used to check thief is encountered in the maze.
   * 
   * @return true if thief is encountered.
   */
  boolean isEncounteredTheif();

  /**
   * It is used to check if roaming monster is encountered 
   * in the maze.
   * 
   * @return true if roaming monster is encountered in the maze.
   */
  boolean isEncounteredRMonster();
}
