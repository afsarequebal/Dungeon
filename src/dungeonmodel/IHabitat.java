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

  /**
   * It is used to get the roaming monster at the current maze location.
   * 
   * @return roaming monster at the given location in the maze.
   */
  IMonster getRMonster();

  /**
   * It is used to check if a pit is present in the adjacent location.
   * 
   * @return true if pit is present in the adjacent location.
   */
  boolean isPit();
  
  /**
   * It is used to get thief at a given location in the maze.
   * 
   * @return the thief at a given location in the maze.
   */
  Thief theif();

  /**
   * It is used to remove monster from the given location in the maze.
   * 
   */
  void removeRMonster();

  /**
   * It is used to remove monster from the given location in the maze.
   * 
   */
  void removeOtyugh();

}
