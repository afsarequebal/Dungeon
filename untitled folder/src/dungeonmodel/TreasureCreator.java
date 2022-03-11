package dungeonmodel;

import java.util.List;

/**
 * Factory class to create more than one treasure
 * at a time. Treasure created can be diamond,
 * sapphire, rubies.
 *
 */
interface TreasureCreator {

  /**
   * Create a list of treasure of the a particular
   * types. It can be of any subtype.
   * 
   * @param numOfTreasure represents the number of treasures.
   * @return a list of treasures.
   */
  List<ITreasure> createTreasure(int numOfTreasure);
}
