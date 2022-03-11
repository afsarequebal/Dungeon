package dungeonmodel;

/**
 * It is used to expose treasure functionality.
 * Treasure can be of three types.
 * Rubies, diamond and sapphires.
 *
 */
interface ITreasure {

  /**
   * This method is used to get the name of the treasure. 
   * 
   * @return return the name of the sapphire.
   */
  String getName();

  /**
   * This method is used to get the type to the treasure.
   * 
   * @return the type to the treasure.
   */
  TreasureType getTreasureType();
}
