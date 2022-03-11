package dungeonmodel;

import java.util.List;

/**
 * Driver class to test the behavior of dungeon project.
 * This class will create dungeon model.
 * It will test functionalities of the dungeon including
 * creating dungeon. Start the game by putting player in the start position.
 * Provide treasure to the caves. It provides capability to change the location
 * of player.
 *
 */
public interface IDungeon {

  /**
   * It helps a player to move from curr location to adjacent 
   * location.
   * 
   * @param dir represent the direction player move.
   * @return true if move by player is successful else 
   *     return false.
   */
  boolean move(String dir);

  /**
   * It is used to pick up treasure from a location in the 
   * cave of the dungeon.
   * 
   * @param represents the arrow to treasure to be picked up
   * @return true if player is able to pick up the treasure.
   */
  boolean pickUpItem(String pickUpItem);

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
   * It is used to get the players directtions and 
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
   *     contains a list of possible directions.
   */
  List<Integer>[][] dungeonRepresentation();

  int attack(String dir, int distance);

  boolean isGameOver();

  boolean getWinner();

  String getPlayersArrows();
}
