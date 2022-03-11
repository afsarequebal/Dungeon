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
   * @return 
   */
  String removeTreasureOrArmor(String removeItem);

  IMonster getMonster();

  List<IWeapon> getWeapon();
}
