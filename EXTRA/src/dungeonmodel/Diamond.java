package dungeonmodel;

import java.util.Objects;

/**
 * This class represents a type of treasure. It is of type diamond.
 * Both a player and a habitat can have this treasure.
 *
 */
class Diamond implements ITreasure {

  private TreasureType treasureType;
  private String name;

  /**
   * Constructs a diamond object.
   * 
   * @param name represents the name of the treasure
   */
  public Diamond(TreasureType treasureType, String name) {
    this.name = name;
    this.treasureType = treasureType;
  }

  @Override
  public String toString() {
    return String.format("Treasure name -> %s, type -> %s", name, treasureType);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public TreasureType getTreasureType() {
    return this.treasureType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, treasureType);
  }

  @Override
  public boolean equals(Object obj) {
    boolean ret = false;
    if (this == obj) {
      return true;
    }
    if (obj instanceof Diamond) {
      Diamond other = (Diamond) obj;
      ret = Objects.equals(name, other.name) && treasureType == other.treasureType;
    }
    return ret;
  }
}
