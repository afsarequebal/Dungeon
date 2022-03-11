package dungeonmodel;

import java.util.Objects;

/**
 * This class represents a type of treasure.
 * Both a player and a habitat can have this treasure.
 *
 */
class Rubies implements ITreasure {

  private TreasureType treasureType;
  private String name;

  /**
   * Constructs a rubies object.
   * 
   * @param treasureType represents the type of treasure.
   * @param name represents the name of the treasure.
   */
  public Rubies(TreasureType treasureType, String name) {
    if (null == name || "".equals(name) || null == treasureType) {
      throw new IllegalArgumentException("Input provided is not valid");
    }
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
    if (obj instanceof Rubies) {
      Rubies other = (Rubies) obj;
      ret = Objects.equals(name, other.name) && treasureType == other.treasureType;
    }
    return ret;
  }
}
