package dungeonmodel;

/**
 * It represents interface for the model. It allows controller to access
 * methods to create two dimensional grid, create player, distribute
 * treasure, arrow and monster. It allows controller to navigate 
 * player, kill monster and collect arrow and treasure.
 */
public interface IDungeon extends IReadOnlyDungeon {

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
   * It helps user to move from one location to other for GUI
   * representation. 
   * 
   * @param row represents the row of new location.
   * @param col represents the column of new location.
   * 
   * @return true if move to other location is successful.
   */
  boolean move(int row, int col);
  
  /**
   * It is used to pick up treasure from a location in the 
   * cave of the dungeon.
   * 
   * @param pickUpItem represents the arrow to treasure to be picked up
   * @return true if player is able to pick up the treasure.
   */
  boolean pickUpItem(String pickUpItem);

  /**
   * It is used to attack the monster by throwing an 
   * arrow.
   * 
   * @param dir represents direction to throw the arrow
   * @param distance represents the length arrow should travel.
   * 
   * @return 2, if monster is half injured, 1 if monster is dead
   *     0 if arrow is wasted. -1 if arrow cannot be thrown
   */
  int attack(String dir, int distance);

  /**
   * It helps user to fight roaming monster in a hand to hand
   * battle. It return 1 if both hit and 0 if none are hit.
   * 
   * @return 1, 2, 3, 4 on basis of monster or player getting hit.
   */
  int fightMonster();

}
