package dungeonmodel;

import java.util.List;

/**
 * It represents two dimensional grid. It offers method to get monster,
 * get weapon, get treasure, get location details and dungeon description.
 * 
 */
public interface IGridTwoD {

  /**
   * It is used to get the start point.
   * 
   * @return the start point.
   */
  int getStartPoint();

  /**
   * It is used to get the end point.
   * 
   * @return the end point.
   */
  int getEndPoint();

  /**
   * It is used to get all avaible treasure at the given location.
   * 
   * @param currR represents current row.
   * @param currC represents current column.
   * 
   * @return a list of available treasures.
   */
  List<ITreasure> getAvblTreasure(int currR, int currC);

  /**
   * It is used to get the weapon at the location.
   * 
   * @param currR represents the current row
   * @param currC represents the current column
   * @return weapon at the current location.
   */
  List<IWeapon> getWeapon(int currR, int currC);

  /**
   * It is used to get monster at the given location.
   * @param currR represents the current row.
   * @param currC represents the current column.
   * 
   * @return the monster at the given location.
   */
  IMonster getMonster(int currR, int currC);

  /**
   * It is used to remove the treasure from the given location.
   * 
   * @param pickUpItem represent name of pickup items
   * @param currR represent current row
   * @param currC represent current column
   * 
   * @return name of the removed treasure or armor.
   */
  String removeTreasureOrArmor(String pickUpItem, int currR, int currC);

  /**
   * It is used to get available directions.
   * 
   * @param currR represents the current row
   * @param currC represents the current column
   * 
   * @return return the available directions.
   */
  List<Integer> getAvblDir(int currR, int currC);

  /**
   * It is used to get description of the dungeon.
   * 
   * @return the description of the dungeon.
   */
  String dungeonDescription();

  /**
   * It is used to get the details of the location.
   * 
   * @param curr represents the current row
   * @param rowC represents the current column
   * @param colC reprsents teh current location.
   * 
   * @return the string representation of the location.
   */
  String getlocationDetails(int curr, int rowC, int colC);

  /**
   * It is used to attack the monster by the arrow.
   * 
   * @param cloc represents the current location.
   * @param dir represents the direction to throw arrow.
   * @param distance the length arrow will travel.
   * 
   * @return return 2 if monster is half injured, 1 if monster
   *     is dead, 0 if no monster found.
   */
  int attack(int cloc, String dir, int distance);

  /**
   * It is used to get two dimensional representation of the 
   * dungeon.
   * 
   * @return the two dimension representation of the dungeon.
   */
  String[][] dungeonRepresentation();

  /**
   * It assigns visited to the habitat is move is successful.
   * 
   * @param visited true if visited else false.
   * @param nCurrR new visited row.
   * @param nCurrC new visited column.
   */
  void updatedVisited(int nCurrR, int nCurrC, boolean visited);

  public boolean isAdjacentPit(int currR, int currC, int noOfRows, 
      int noOfColumns);

  IMonster getRMonster(int currR, int currC);

  Thief getThief(int currR, int currC);

  boolean isAPit(int currR, int currC);

  void removeRMonster(int nCurrR, int nCurrC);
}
