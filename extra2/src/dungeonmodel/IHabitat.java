package dungeonmodel;

import java.util.List;

/**
 * Habitant interface represent method to get available 
 * directions. It also returns available treasures.
 * 
 */
interface IHabitat {

  /**
   * This method is used to get
   * the available directions of the cave or a tunnels.
   * 
   * @return a list of available directions.
   */
  List<Integer> getAvblDir();

  /**
   * This method is used to get
   * the available treasure of the cave or tunnel.
   * 
   * @return a list of available treasures.
   */
  List<ITreasure> getAvblTreasure();

 
  /**
   * It is used to update the treasure of the caves.
   * 
   * @param represents updates list of treasure.
   * @return the name of the removed item.
   */
  String removeTreasureOrArmor(String removeItem);

  /**
   * It is used to get the monster in the location.
   * 
   * @return the monster in the current location.
   */
  IMonster getMonster();

  /**
   * It is used to get the weapon of the player.
   * 
   * @return a list of player weapons.
   */
  List<IWeapon> getWeapon();

  /**
   * It checks if habitat has already been visited.
   * 
   * @return true if visited.
   */
  boolean isVisited();

  /**
   * Assign true if habitat is visited.
   * 
   * @param visited true if visited.
   */
  void setVisited(boolean visited);

  IMonster getRMonster();

  boolean isPit();

  Thief theif();

  void removeRMonster();

}
