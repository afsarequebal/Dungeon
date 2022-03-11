package dungeonmodel;

import java.util.List;

/**
 * It is the interface of the players. It exposes the current location
 * and available treasures. It also allows to update location and the 
 * treasure of the players.
 */
interface IHabitant {

  /**
   * This method is used to get the location of the player.
   * 
   * @return returns the curr location of the player
   */
  int getCurrLocation();

  /**
   * This method is used to get the tresure of the player.
   * 
   * @return the treasure of the player.
   */
  String getAvblTreasure();

  /**
   * It is used to update the current locatiion.
   * 
   * @param dir r represents the direction to move.
   * @param avblDir represents the available directions.
   * @param noOfRows represents the number of rows.
   * @param noOfColumns represents the number of columns.
   * 
   * @return true if update location is successful
   */
  boolean updateLocation(String dir, List<Integer> avblDir, int noOfRows, int noOfColumns);

  /**
   * It is is used to update the treasure or armor of the player.
   *  
   * @param pickupItem represents the pickup item.
   * @param name represents the name of item to be picked up.
   * 
   * @return true if item update is successful.
   */
  boolean updateTreasureOrArmor(String pickupItem, String name);

  /**
   * It is reduce the count of arrow.
   */
  void reduceArrowCount();

  /**
   * It returns the number of arrows in the bag.
   * 
   * @return the number of arrows in the bad.
   */
  int noOfArrowCount();

  /** 
   * It is used to get arrows available to the user.
   * 
   * @return string representation of the arrows available to player.
   */
  String getAvblArrow();

  void removeTreasure();

  void updateStrength();

  int getStrength();

}
