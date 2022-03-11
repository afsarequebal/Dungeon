package dungeonmodel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Factory class to create a list of treasure.
 * It create treasure of type of diamond.
 *
 */
class DiamondCreator implements TreasureCreator {

  @Override
  public List<ITreasure> createTreasure(int numOfTreasure) {
    List<ITreasure> listOfTreasure = new CopyOnWriteArrayList<>();
    String name = "DIAM";
    for (int i = 0 ; i < numOfTreasure; i++) {
      listOfTreasure.add(new Diamond(TreasureType.DIAMOND, name + i));
    }
    return listOfTreasure;
  }
}
