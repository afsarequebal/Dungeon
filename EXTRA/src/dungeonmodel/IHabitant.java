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

  boolean updateLocation(String dir, List<Integer> avblDir, int noOfRows, int noOfColumns);

  /**
   * 
   * @param pickupItem
   * @param name
   * @return
   */
  boolean updateTreasureOrArmor(String pickupItem, String name);

  void reduceArrowCount();

  int noOfArrowCount();

  String getAvblArrow();

}
