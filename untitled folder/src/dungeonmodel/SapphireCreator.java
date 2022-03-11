package dungeonmodel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Factory class to create a list of treasure.
 * It create treasure of type of sapphire.
 *
 */
class SapphireCreator implements TreasureCreator {

  @Override
  public List<ITreasure> createTreasure(int numOfTreasure) {
    List<ITreasure> listOfTreasure = new CopyOnWriteArrayList<>();
    String name = "SAP";
    for (int i = 0 ; i < numOfTreasure; i++) {
      listOfTreasure.add(new Sapphires(TreasureType.SAPPHIRE, name + i));
    }
    return listOfTreasure;
  }

}
