package dungeonmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * It represent a player in the dungeon. It is created after
 * dungeon is created. A player can move to its adjacent location.
 * It captures its current location and a list of treasures.
 * 
 */
class Habitant implements IHabitant {

  private int currLocation;

  private List<ITreasure> avblTreasureList;

  private List<IWeapon> weaponList;

  /**
   * A construtor to create habitant object.
   * A habitant object is created with an empty list of 
   * treasure and a start location.
   * 
   * @param location represents the start location of the 
   *     player.
   */
  public Habitant(int location, List<IWeapon> weaponList) {
    if (weaponList == null || weaponList.size() != 3) {
      throw new IllegalArgumentException("Weapon list size is incorrect.");
    }
    this.avblTreasureList = new ArrayList<>();
    this.currLocation = location;
    this.weaponList = weaponList;
  }

  @Override
  public String getAvblTreasure() {
    /* List<ITreasure> retAvbTreasurelList = new ArrayList<>();
    for (int i = 0 ; i < avblTreasureList.size(); i++) {
      ITreasure treasure = avblTreasureList.get(i);
      ITreasure copiedTreasure;
      if (TreasureType.SAPPHIRE.equals(treasure.getTreasureType())) {
        copiedTreasure = new Sapphires(TreasureType.SAPPHIRE, treasure.getName());
      } else if (TreasureType.DIAMOND.equals(treasure.getTreasureType())) {
        copiedTreasure = new Diamond(TreasureType.DIAMOND, treasure.getName());
      } else  {
        copiedTreasure = new Rubies(TreasureType.RUBIES, treasure.getName());
      }
      retAvbTreasurelList.add(copiedTreasure);
    }*/

    String playerTreasure = "";
    if (null != avblTreasureList && avblTreasureList.size() > 0) {
      for (int k = 0 ; k < avblTreasureList.size(); k++) {
        playerTreasure = playerTreasure + avblTreasureList.get(k).getName() + ", ";
      }
    }
    if (!playerTreasure.equals("")) {
      playerTreasure = playerTreasure.substring(0, playerTreasure.length() - 2);
    }
    return playerTreasure;
  }

  @Override
  public String getAvblArrow() {
    String playerArrow = "";
    if (null != weaponList && weaponList.size() > 0) {
      for (int k = 0 ; k < weaponList.size(); k++) {
        playerArrow = playerArrow + weaponList.get(k).getName() + ", ";
      }
    }
    if (!playerArrow.equals("")) {
      playerArrow = playerArrow.substring(0, playerArrow.length() - 2);
    }
    return playerArrow;
  }

  @Override
  public int getCurrLocation() {
    return currLocation;
  }

  @Override
  public boolean updateLocation(String dir, List<Integer> avblDir, int noOfRows, int noOfColumns) {

    int currR = currLocation / noOfColumns;
    int currC = currLocation % noOfColumns;
    int nextR = noOfRows;
    int nextC = noOfColumns;
    if (dir.equalsIgnoreCase("N")) {
      nextR = currR - 1;
      nextC = currC;
      if (nextR == -1) {
        nextR = noOfRows - 1;
      }
    } else if (dir.equalsIgnoreCase("S")) {
      nextR = currR + 1;
      nextC = currC;
      if (nextR == noOfRows) {
        nextR = 0;
      }
    } else if (dir.equalsIgnoreCase("W")) {
      nextR = currR;
      nextC = currC - 1;
      if (nextC == -1) {
        nextC = noOfColumns - 1;
      }
    } else if (dir.equalsIgnoreCase("E")){
      nextR = currR ;
      nextC = currC + 1;
      if (nextC == noOfColumns) {
        nextC = 0;
      }
    }
    int location = noOfColumns * nextR + nextC;
    if (avblDir.contains(location)) {
      this.currLocation = location;
      return true; 
    } else {
      throw new IllegalArgumentException("Path followed is invalid");
    }
  }

  /*@Override
  public void updateTreasure(List<ITreasure> treasureList) {
    avblTreasureList.addAll(treasureList);
  }*/

  @Override
  public boolean updateTreasureOrArmor(String pickUpItem, String name) {
    boolean ret = false;
    if (null != pickUpItem && !"".equals(pickUpItem)) {
      if (name.equalsIgnoreCase("arrow")) {
        updateArmor(new Arrow(name));
        ret = true;
      } else if (name.equalsIgnoreCase("ruby")) {
        ret = updateTreasure(new Diamond(TreasureType.RUBY, name));
      } else if(name.equalsIgnoreCase("diamond")) {
        ret = updateTreasure(new Diamond(TreasureType.DIAMOND, name));
      } else if(name.equalsIgnoreCase("sapphire")) {
        ret = updateTreasure(new Diamond(TreasureType.SAPPHIRE, name));
      }
    }
    return ret;
  }

  @Override
  public void reduceArrowCount() {
    if (null != this.weaponList && weaponList.size() > 0) {
      weaponList.remove(0);
    }
  }

  private boolean updateArmor(IWeapon weapon) {
    boolean ret = false;
    if (this.weaponList == null) {
      this.weaponList = new ArrayList<>();
    }
    if (weapon != null) {
      weaponList.add(weapon);
      ret = true;
    }
    return ret;
  }

  @Override
  public int noOfArrowCount() {
    if (this.weaponList != null) {
      return weaponList.size();
    } else {
      return 0;
    }
  }

  private boolean updateTreasure(ITreasure treasure) {
    boolean ret = false;
    if (this.avblTreasureList == null) {
      this.avblTreasureList = new ArrayList<>();
    }
    if (treasure != null) {
      avblTreasureList.add(treasure);
      ret = true;
    }
    return ret;
  }

  @Override
  public int hashCode() {
    return Objects.hash(avblTreasureList, currLocation);
  }

  @Override
  public boolean equals(Object obj) {
    boolean ret = false;
    if (this == obj) {
      return true;
    }
    if (obj instanceof Habitant) {
      Habitant other = (Habitant) obj;
      ret = Objects.equals(avblTreasureList, other.avblTreasureList)
          && currLocation == other.currLocation;
    }
    return ret;
  }

  @Override
  public String toString() {
    return "Habitant [currLocation=" + currLocation + ", avblTreasureList=" + avblTreasureList
        + "]";
  }

}
